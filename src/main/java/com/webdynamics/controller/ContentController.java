package com.webdynamics.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.webdynamics.model.Content;
import com.webdynamics.model.User;
import com.webdynamics.service.ContentService;

@Controller
public class ContentController {
	String message = "Welcome to Content Controller";

	@Autowired
	private ContentService contentService;

	@RequestMapping(value = "/addContent", method = RequestMethod.GET)
	public ModelAndView saveContent(@ModelAttribute("content") Content content,
			BindingResult result, HttpServletRequest req) {
		ModelAndView model = new ModelAndView("addContent");
		HttpSession session = req.getSession(true);
		String username = (String) session.getAttribute("username");
		String role = (String) session.getAttribute("role");
		model.addObject("role", role);
		model.addObject("username", username);
		return model;
	}


	@RequestMapping(value = "/addContent", method = RequestMethod.POST)
	public ModelAndView saveContent(@ModelAttribute("content") Content content,
			BindingResult result, HttpServletRequest req, HttpServletResponse res)
			throws HibernateException, SQLException, IOException {
		ModelAndView model = new ModelAndView("addContent");
		try {
			HttpSession session = req.getSession(false);
			if (session != null) {
				content.setAuthor((String) session.getAttribute("username"));
			}
			SimpleDateFormat sdfDate = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");// dd/MM/yyyy
			Date now = new Date();
			String strDate = sdfDate.format(now);
			String username = (String) session.getAttribute("username");
			content.setCreatedOn(strDate);
			content.setStatus("pending");
			contentService.addContent(content);
			ArrayList<Content> listContents = new ArrayList<Content>();
			listContents = contentService.listContents();
			model.addObject("contents", listContents);
			model.addObject("username", username);
			String role = (String) session.getAttribute("role");
			model.addObject("role", role);
			model.addObject("status", "Added successfully");
		} catch (Exception e) {
			System.out.println("in controller addcontent exception");
			e.printStackTrace();
			model.addObject("status", "Duplicate Content Name");
		}
		return model;
	}

	@RequestMapping(value = "/listContents", method = RequestMethod.GET)
	public ModelAndView listContent(@ModelAttribute("command") Content content,
			BindingResult result, HttpServletRequest req)
			throws HibernateException, SQLException, IOException {
		ModelAndView model = new ModelAndView("listContents");
		ArrayList<Content> listContents = new ArrayList<Content>();
		listContents = contentService.listContents();
		model.addObject("contents", listContents);
		HttpSession session = req.getSession(true);
		String username = (String) session.getAttribute("username");
		model.addObject("username", username);
		String role = (String) session.getAttribute("role");
		model.addObject("role", role);
		return model;
	}

	@RequestMapping(value = "/deleteContent", method = RequestMethod.GET)
	public ModelAndView deleteContent(
			@ModelAttribute("command") Content content, BindingResult result,
			HttpServletRequest req) throws HibernateException, SQLException,
			IOException {
		int contentId = Integer.parseInt(req.getParameter("id"));
		contentService.deleteContents(contentId);
		HttpSession session = req.getSession(true);
		ModelAndView model = new ModelAndView();
		String username = (String) session.getAttribute("username");
		content = contentService.editContent(contentId);
		ArrayList<Content> listContents = new ArrayList<Content>();
		listContents = contentService.listContents();
		if (username.equalsIgnoreCase("admin")) {
			model = new ModelAndView("adminPage");
		} else {
			model = new ModelAndView("listContents");
		}
		model.addObject("contents", listContents);
		model.addObject("username", username);
		String role = (String) session.getAttribute("role");
		model.addObject("role", role);
		return model;
	}

	@RequestMapping(value = "/editContent", method = RequestMethod.GET)
	public ModelAndView editContent(@ModelAttribute("command") Content content,
			BindingResult result, HttpServletRequest req)
			throws HibernateException, SQLException, IOException {
		int contentId = Integer.parseInt(req.getParameter("id"));
		HttpSession session = req.getSession(true);
		ModelAndView model = new ModelAndView();
		String username = (String) session.getAttribute("username");
		content = contentService.editContent(contentId);
		model = new ModelAndView("addContent");
		model.addObject("content", content);
		model.addObject("message", "update");
		model.addObject("username", username);
		String role = (String) session.getAttribute("role");
		model.addObject("role", role);
		return model;
	}

	@RequestMapping(value = "/editContent", method = RequestMethod.POST)
	public ModelAndView editContent(@ModelAttribute("command") Content content,
			BindingResult result, HttpServletRequest req,
			HttpServletResponse res) throws HibernateException, SQLException,
			IOException {

		ModelAndView model = new ModelAndView("addContent");
		HttpSession session = req.getSession(false);
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		content.setModifiedOn(strDate);
		content.setStatus("pending");
		contentService.updateContent(content);
		model.addObject("content", content);
		model.addObject("message", "update");
		String username = (String) session.getAttribute("username");
		model.addObject("username", username);
		String role = (String) session.getAttribute("role");
		model.addObject("role", role);
		model.addObject("status", "updated successfully");
		return model;
	}

	@RequestMapping(value = "/approveOrReject", method = RequestMethod.GET)
	public ModelAndView approveOrReject(
			@ModelAttribute("command") Content content, BindingResult result,
			HttpServletRequest req, HttpServletResponse res)
			throws HibernateException, SQLException, IOException {
		String status = req.getParameter("value");
		ModelAndView model = new ModelAndView("adminPage");
		HttpSession session = req.getSession(false);
		String username = (String) session.getAttribute("username");
		String approvedBy = null;
		if (session != null) {
			approvedBy = (String) session.getAttribute("username");
		}
		int contentID = Integer.parseInt(req.getParameter("id"));
		contentService.updateContentStatus(contentID,status,approvedBy);
		ArrayList<Content> listContents = new ArrayList<Content>();
		listContents = contentService.listContents();
		model.addObject("contents", listContents);
		model.addObject("message", "update");
		model.addObject("username", username);
		String role = (String) session.getAttribute("role");
		model.addObject("role", role);
		model.addObject("status", "updated successfully");
		return model;
	}

	@RequestMapping(value = "/viewContent", method = RequestMethod.GET)
	public ModelAndView viewContent(@ModelAttribute("command") Content content,
			BindingResult result, HttpServletRequest req)
			throws HibernateException, SQLException, IOException {
		String category = req.getParameter("value");
		HttpSession session = req.getSession(true);
		String username = (String) session.getAttribute("username");
		ModelAndView model = new ModelAndView();
		if (username == null && category.equalsIgnoreCase("protected")) {
			model = new ModelAndView("login");
			model.addObject("protected", "login");
			return model;
		} 
			model = new ModelAndView("viewContent");
			ArrayList<Content> catContent = new ArrayList<Content>();
			catContent = contentService.cateContent(category);
			model.addObject("contents", catContent);
			model.addObject("username", username);
			String role = (String) session.getAttribute("role");
			model.addObject("role", role);
		return model;
	}

	@RequestMapping(value = "/statusContent", method = RequestMethod.GET)
	public ModelAndView statusContent(
			@ModelAttribute("command") Content content, BindingResult result,
			HttpServletRequest req) throws HibernateException, SQLException,
			IOException {
		String status = req.getParameter("status");
		HttpSession session = req.getSession(true);
		String username = (String) session.getAttribute("username");
		ModelAndView model = new ModelAndView();
		ArrayList<Content> satusContent = new ArrayList<Content>();
		satusContent = contentService.satusContent(status);
		model = new ModelAndView("adminPage");
		model.addObject("contents", satusContent);
		model.addObject("username", username);
		String role = (String) session.getAttribute("role");
		model.addObject("role", role);
		return model;
	}
	
	@RequestMapping(value = "/adminPage", method = RequestMethod.GET)
	public ModelAndView getAdminPage(@ModelAttribute("command") Content content,
			BindingResult result, HttpServletRequest req) {
		ModelAndView model = new ModelAndView("adminPage");
		HttpSession session = req.getSession(true);
		String username = (String) session.getAttribute("username");
		model.addObject("username", username);
		String role = (String) session.getAttribute("role");
		model.addObject("role", role);
		ArrayList<Content> listContents = new ArrayList<Content>();
		listContents = contentService.listContents();
		model.addObject("contents", listContents);
		return model;
	}

}
