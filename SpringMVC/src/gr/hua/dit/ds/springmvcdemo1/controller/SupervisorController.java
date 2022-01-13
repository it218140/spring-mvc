package gr.hua.dit.ds.springmvcdemo1.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.hua.dit.ds.springmvcdemo1.dao.SubjectsDAO;
import gr.hua.dit.ds.springmvcdemo1.entity.Subjects;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gr.hua.dit.ds.springmvcdemo1.dao.SubjectsDAO;
import gr.hua.dit.ds.springmvcdemo1.entity.Subjects;

@Controller
@RequestMapping("/supervisor")
public class SupervisorController {

	@Autowired
	private SubjectsDAO SubjectsDAO;

	@GetMapping("/requests")
	public String GetRequests(Model model) {

		List<Subjects> subjects = SubjectsDAO.getSubjects();

		model.addAttribute("reqlist",subjects);

		return "getrequests";
	}

	@PostMapping("/status")
	public String AcceptRequests(HttpServletRequest request, HttpServletResponse response, Model model) {

		int id = Integer.parseInt(request.getParameter("id"));

		System.out.println(id);

		model.addAttribute("Subject", SubjectsDAO.getSubjects());

		String button = request.getParameter("decline");

		if (button != null) {

			SubjectsDAO.UpdateSubject(id, "Declined");

		} else {

			SubjectsDAO.UpdateSubject(id, "Accepted");

		}

		return "redirect:/supervisor/requests";
	}



}
