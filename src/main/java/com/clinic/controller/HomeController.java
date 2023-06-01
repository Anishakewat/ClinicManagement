package com.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clinic.entity.Doctor;
import com.clinic.entity.Patient;
import com.clinic.entity.Receptionist;
import com.clinic.entity.repository.DoctorRepository;
import com.clinic.entity.repository.PatientRepository;
import com.clinic.service.DoctorService;
import com.clinic.service.PatientService;
import com.clinic.service.ReceptionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private DoctorService docService;
	
	@Autowired
	private PatientService patService;
	@Autowired
	private PatientRepository patientRepo;
	@Autowired
	private DoctorRepository doctorRepo;
	@Autowired
	ReceptionService receptionService;
	
    @GetMapping("/")
	public String index() {
		return "index";
	}
    @GetMapping("/doctorLogin")
    public String doctorLogin() {
    	return "doctorLogin";
    }
    @GetMapping("/receptionLogin")
    public String receptionLogin() {
    	return "receptionLogin";
    }
    @GetMapping("/patientLogin")
    public String patientLogin() {
    	return "patientLogin";
    }
    @GetMapping("/patientRegister")
    public String patientRegister() {
    	return "patientRegister";
    }
    @GetMapping("/receptionRegister")
    public String receptionRegister() {
    	return "receptionRegister";
    }
    @GetMapping("/doctorRegister")
    public String doctorRegister() {
    	return "doctorRegister";
    }
    @GetMapping("/about")
    public String about() {
    	return "about";
    }
    
    
    @PostMapping("/createDoctor")
    public String createdoctor(@ModelAttribute Doctor doctor, HttpSession session) {
    	//System.out.println(doctor);
    	
    	boolean d=docService.checkDocEmail(doctor.getEmail());
    	if(d) {
    		session.setAttribute("msg1", "Email Id Already Exists");
    	}else {
    		Doctor doctor1 = docService.createDoctor(doctor);
        	if(doctor1!=null) {
        		session.setAttribute("msg1", "Register Sucessfully");
        	}else {
        		session.setAttribute("msg1", "Something wrong on server");
        	}
        	
    	}
    	return "redirect:/doctorRegister";
    }
    
    @PostMapping("/createReception")
    public String createreception(@ModelAttribute Receptionist reception, HttpSession session) {
    	//System.out.println(doctor);
    	
    	boolean r=receptionService.checkResEmailid(reception.getEmailid());
    	if(r) {
    		session.setAttribute("msg1", "Email Id Already Exists");
    	}else {
    		Receptionist reception1 = receptionService.createReception(reception);
        	if(reception1!=null) {
        		session.setAttribute("msg1", "Register Sucessfully");
        	}else {
        		session.setAttribute("msg1", "Something wrong on server");
        	}
        	
    	}
    	return "redirect:/receptionRegister";
    }
    
    
    
    
    @PostMapping("/createPatient")
    public String createpatient(@ModelAttribute Patient patient ,HttpSession session) {
    	boolean p=patService.checkPatEmail(patient.getEmail());
    	if(p) {
    		session.setAttribute("message", "Email Id Already Exists");
    	}else {
    		Patient patient1 = patService.createPatient(patient);
        	if(patient1!=null) {
        		session.setAttribute("message", "Register Sucessfully");
        	}else {
        		session.setAttribute("message", "Something wrong on server");
        	}
    	}
    	return "redirect:/patientRegister";
    }

    @GetMapping("/patientlogout")
    public String patientlogout(HttpServletRequest request) {
      // Retrieve session object
      HttpSession session = request.getSession(false);
      
      if (session != null) {
        // Clear session data
        session.invalidate();
      }
      
      // Redirect to login page
      return "redirect:/";
    }
    @GetMapping("/doctorlogout")
    public String doctorlogout(HttpServletRequest request) {
      // Retrieve session object
      HttpSession session = request.getSession(false);
      
      if (session != null) {
        // Clear session data
        session.invalidate();
      }
      
      // Redirect to login page
      return "redirect:/";
    }
    @GetMapping("/reception")
    public String reception() {
    	return "reception";
    }

    @GetMapping("/loginPatient1")
    public String loginPatient(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        // Here, you can implement the logic to search for the username and password in the database
        // You can use a service or repository to perform the search
//System.out.println(username+password);
        if (patientIsCredentialsValid(email, password)) {
            // If the credentials are valid, you can add the necessary data to the model or session for the patient's home page
            model.addAttribute("email", email);
            Patient patient = patientRepo.findByEmail(email);
            // Add other required attributes
            model.addAttribute("name", patient.getUsername());
            model.addAttribute("id", patient.getId()); // Pass the patient's name as a model attribute
            // Return the patient's home page
            return "patientHomePage";
        } else {
            // If the credentials are not valid, add an error message to the redirect attributes and redirect back to the login page
            String message = "Invalid email or password";
            redirectAttributes.addFlashAttribute("message", message);
        	//redirectAttributes.addFlashAttribute("errorMessage", "Invalid username or password");
            return "redirect:/patientLogin";
        }
    }

    private boolean patientIsCredentialsValid(String email, String password) {
    	 Patient patient = patientRepo.findByEmail(email);

         if (patient != null && patient.getPassword().equals(password)) {
             return true;
         }

         return false;
    }
    
    
    
    
  
    
    
    
    
    
    //Doctor name and id
    
    @GetMapping("/loginDoctor1")
    public String loginDoctor(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        // Here, you can implement the logic to search for the username and password in the database
        // You can use a service or repository to perform the search
//System.out.println(username+password);
        if (doctorIsCredentialsValid(email, password)) {
            // If the credentials are valid, you can add the necessary data to the model or session for the patient's home page
            model.addAttribute("email", email);
            Doctor doctor = doctorRepo.findByEmail(email);
            // Add other required attributes
            model.addAttribute("name", doctor.getUsername());
            model.addAttribute("id", doctor.getId()); // Pass the patient's name as a model attribute
            // Return the patient's home page
            return "doctorHomePage";
        } else {
            // If the credentials are not valid, add an error message to the redirect attributes and redirect back to the login page
            String message = "Invalid email or password";
            redirectAttributes.addFlashAttribute("message", message);
        	//redirectAttributes.addFlashAttribute("errorMessage", "Invalid username or password");
            return "redirect:/doctorLogin";
        }
    }

    private boolean doctorIsCredentialsValid(String email, String password) {
    	 Doctor doctor = doctorRepo.findByEmail(email);

         if (doctor != null && doctor.getPassword().equals(password)) {
             return true;
         }

         return false;
    }
}


