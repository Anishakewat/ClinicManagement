package com.clinic.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.clinic.entity.Appointment;
import com.clinic.entity.repository.AppointmentRepository;

import jakarta.servlet.http.HttpSession;
@Controller
public class AppointmentController {
	@Autowired
	private AppointmentRepository appointRepo;
	 
	@GetMapping("/appointmentPage")
	public String home(Model m) {
			List<Appointment> list = appointRepo.findAll();
			m.addAttribute("all_appointment" , list);
			return "appointmentPage";
		}
		@GetMapping("/load_form")
		public String loadForm() {
			return "add";
		}
		@GetMapping("/edit_form/{id}")
		public String editForm(@PathVariable(value = "id") long id, Model m) {
			Optional<Appointment> appointment = appointRepo.findById(id);
			Appointment app = appointment.get();
			m.addAttribute("appointment",app);
			return "edit";
		}
		
		
		
		@PostMapping("/save_app")
		public String saveApp(@ModelAttribute Appointment appoint, HttpSession session) {
			
			appointRepo.save(appoint);
			session.setAttribute("msg2","Added Successfully");
			
			
		 return "redirect:/load_form";
		}
		@PostMapping("/update_app")
		public String updateApp(@ModelAttribute Appointment appoint, HttpSession session) {
			
			appointRepo.save(appoint);
			session.setAttribute("msg2","Updated Successfully");
			
			
		 return "redirect:/appointmentPage";
		}
		@GetMapping("/delete/{id}")
	public String deleteAppointment(@PathVariable(value="id") long id, HttpSession session) {
			appointRepo.deleteById(id);
			session.setAttribute("msg2","Deleted Successfully");
			
			
			return "redirect:/appointmentPage";
	}
	
}
