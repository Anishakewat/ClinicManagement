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
import com.clinic.entity.Doctor;
import com.clinic.entity.repository.DoctorRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class DoctorController {
	@Autowired
	private DoctorRepository doctorRepo;
	
	@GetMapping("/doctorTable")
	public String dochome(Model m) {
			List<Doctor> list = doctorRepo.findAll();
			m.addAttribute("all_doctor" , list);
			return "doctorTable";
		}

	@GetMapping("/doctorList")
	public String docList(Model m) {
			List<Doctor> list = doctorRepo.findAll();
			m.addAttribute("all_doctor" , list);
			return "doctorList";
		}
		@GetMapping("/load_doc_form")
		public String loaddocForm() {
			return "addDoc";
		}
		@GetMapping("/edit_doc_form/{id}")
		public String editdocForm(@PathVariable(value = "id") long id, Model m) {
			Optional<Doctor> doctor = doctorRepo.findById(id);
			Doctor doc = doctor.get();
			m.addAttribute("doctor",doc);
			return "editDoc";
		}
		
		
		
		@PostMapping("/save_doc")
		public String saveDoc(@ModelAttribute Doctor doctor, HttpSession session) {
			
			doctorRepo.save(doctor);
			session.setAttribute("msg","Added Successfully");
			
			
		 return "redirect:/load_doc_form";
		}
		@PostMapping("/update_doc")
		public String updateDoc(@ModelAttribute Doctor doctor, HttpSession session) {
			
			doctorRepo.save(doctor);
			session.setAttribute("msg","Updated Successfully");
			
			
		 return "redirect:/doctorTable";
		}
		@GetMapping("/deletedoc/{id}")
	public String deleteDoctor(@PathVariable(value="id") long id, HttpSession session) {
			doctorRepo.deleteById(id);
			session.setAttribute("msg","Deleted Successfully");
			
			
			return "redirect:/doctorTable";
	}
	
	
}