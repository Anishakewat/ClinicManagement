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
import com.clinic.entity.Patient;
import com.clinic.entity.repository.PatientRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class PatientController {
		@Autowired
		private PatientRepository patientRepo;
		
		@GetMapping("/patientTable")
		public String pathome(Model m) {
				List<Patient> list = patientRepo.findAll();
				m.addAttribute("all_patient" , list);
				return "patientTable";
			}
			@GetMapping("/load_pat_form")
			public String loadpatForm() {
				return "addPat";
			}
			@GetMapping("/edit_pat_form/{id}")
			public String editpatForm(@PathVariable(value = "id") long id, Model m) {
				Optional<Patient> patient = patientRepo.findById(id);
				Patient pat = patient.get();
				m.addAttribute("patient",pat);
				return "editPat";
			}
			
			
			
			@PostMapping("/save_pat")
			public String savePat(@ModelAttribute Patient patient, HttpSession session) {
				
				patientRepo.save(patient);
				session.setAttribute("msg","Added Successfully");
				
				
			 return "redirect:/load_pat_form";
			}
			@PostMapping("/update_pat")
			public String updatePat(@ModelAttribute Patient patient, HttpSession session) {
				
				patientRepo.save(patient);
				session.setAttribute("msg","Updated Successfully");
				
				
			 return "redirect:/patientTable";
			}
			@GetMapping("/deletepat/{id}")
		public String deletePatient(@PathVariable(value="id") long id, HttpSession session) {
				patientRepo.deleteById(id);
				session.setAttribute("msg","Deleted Successfully");
				
				
				return "redirect:/patientTable";
		}
			@GetMapping("/patientHomePage")
			public String patientHomePage() {
				return "patientHomePage";
			}
		
    
}
