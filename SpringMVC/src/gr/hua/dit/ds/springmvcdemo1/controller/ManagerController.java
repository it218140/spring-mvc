package gr.hua.dit.ds.springmvcdemo1.controller;

import gr.hua.dit.ds.springmvcdemo1.dao.SubjectsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gr.hua.dit.ds.springmvcdemo1.dao.SubjectsDAO;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private SubjectsDAO SubjectsDAO ;

	@GetMapping(value = { "/", "" })
	public String viewRequests(Model model) {

		model.addAttribute("reqlist", SubjectsDAO.getSubjects());

		return "getrequests";
	}

}
