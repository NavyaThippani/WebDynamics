package com.webdynamics.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.webdynamics.model.Content;
import com.webdynamics.model.User;
import com.webdynamics.service.ContentService;
import com.webdynamics.service.UserService;

@Controller
public class UserController {
	String message = "Welcome to Spring MVC!";

	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller");

		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;
	}

	@Autowired
	private UserService userService;

	@Autowired
	private ContentService contentService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute("command") User user,
			BindingResult result) {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("command") User user,
			BindingResult result, HttpServletRequest req) {

		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		ModelAndView model = new ModelAndView();
		HttpSession session = req.getSession();
		List<User> userList = userService.verifyLogin(userName, password);
		if (userList.size() != 0) {
			String role = userService.getUserRole(userName);
			System.out.println(" getting the role of the user :" + role);
			if (role.equalsIgnoreCase("admin")) {
				model = new ModelAndView("adminPage");
				ArrayList<Content> listContents = new ArrayList<Content>();
				listContents = contentService.listContents();
				model.addObject("contents", listContents);
				model.addObject("message", "Welcome Admin");

			} else {
				model = new ModelAndView("viewContent");
				String category = "technical";
				ArrayList<Content> catContent = new ArrayList<Content>();
				catContent = contentService.cateContent(category);
				model.addObject("contents", catContent);
				model.addObject("message", "Welcome : " + userName);
			}
			session.setAttribute("username", userName);
			session.setAttribute("role", role);
			model.addObject("username", userName);
			model.addObject("role", role);
		} else {
			model = new ModelAndView("login");
			model.addObject("status", "Please enter the correct details..!");
		}

		return model;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(@ModelAttribute("command") User user,
			BindingResult result, HttpServletRequest req, SessionStatus status) {
		ModelAndView model = new ModelAndView();
		HttpSession session = req.getSession();
		session.setAttribute("userName", " ");
		session.setAttribute("role", " ");
		session.invalidate();
		status.setComplete();
		model = new ModelAndView("login");
		model.addObject("message", "successfully logged out");
		return model;
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelAndView saveUser(@ModelAttribute("command") User user,
			BindingResult result, HttpServletRequest req) {
		ModelAndView model = new ModelAndView("addUser");
		HttpSession session = req.getSession(true);
		String username = (String) session.getAttribute("username");
		String role = (String) session.getAttribute("role");
		model.addObject("role", role);
		model.addObject("username", username);
		return model;
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute("command") User user,
			BindingResult result, HttpServletRequest req,
			HttpServletResponse res) throws HibernateException, SQLException,
			IOException {
		ModelAndView model = new ModelAndView("addUser");
		HttpSession session = req.getSession(true);
		try {
			if (user.getRole() == null) {
				user.setRole("User");
			}
			userService.addUser(user);
			String username = (String) session.getAttribute("username");
			String role = (String) session.getAttribute("role");
			model.addObject("role", role);
			model.addObject("username", username);
			model.addObject("status", "Added successfully");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("status", "Duplicate User Name");
		}

		return model;
	}

	@RequestMapping(value = "/listUsers", method = RequestMethod.GET)
	public ModelAndView listUsers(@ModelAttribute("command") User user,
			BindingResult result, HttpServletRequest req)
			throws HibernateException, SQLException, IOException {
		ModelAndView model = new ModelAndView("listUsers");
		ArrayList<User> listUsers = new ArrayList<User>();
		listUsers = userService.listUsers();
		model.addObject("users", listUsers);
		HttpSession session = req.getSession(true);
		String username = (String) session.getAttribute("username");
		model.addObject("username", username);
		String role = (String) session.getAttribute("role");
		model.addObject("role", role);
		return model;
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteUser(@ModelAttribute("command") User user,
			BindingResult result, HttpServletRequest req)
			throws HibernateException, SQLException, IOException {
		int userId = Integer.parseInt(req.getParameter("id"));
		userService.deleteUser(userId);
		HttpSession session = req.getSession(true);
		ModelAndView model = new ModelAndView();
		String username = (String) session.getAttribute("username");
		String role = (String) session.getAttribute("role");
		ArrayList<User> listUsers = new ArrayList<User>();
		listUsers = userService.listUsers();
		if (role.equalsIgnoreCase("admin")) {
			model = new ModelAndView("adminPage");
		} else {
			model = new ModelAndView("listUsers");
		}
		model.addObject("users", listUsers);
		model.addObject("username", username);
		model.addObject("role", role);
		model.addObject("status", "Deleted successfully");
		return model;
	}

	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView editUser(@ModelAttribute("command") User user,
			BindingResult result, HttpServletRequest req)
			throws HibernateException, SQLException, IOException {
		int userId = Integer.parseInt(req.getParameter("id"));
		HttpSession session = req.getSession(true);
		ModelAndView model = new ModelAndView();
		String username = (String) session.getAttribute("username");
		user = userService.editUser(userId);
		/*
		 * if(username!=null && username.equalsIgnoreCase("admin")){ model = new
		 * ModelAndView("adminPage"); }else{
		 */
		model = new ModelAndView("addUser");
		// }
		model.addObject("user", user);
		model.addObject("username", username);
		String role = (String) session.getAttribute("role");
		model.addObject("role", role);
		model.addObject("message", "update");
		return model;
	}

	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public ModelAndView editUser(@ModelAttribute("command") User user,
			BindingResult result, HttpServletRequest req,
			HttpServletResponse res) throws HibernateException, SQLException,
			IOException {
		String value = req.getParameter("value");
		HttpSession session = req.getSession(true);
		String username = (String) session.getAttribute("username");
		ModelAndView model = new ModelAndView();
		if (value != null && value.equalsIgnoreCase("myacct")) {
			model = new ModelAndView("myAcct");
		} else {
			model = new ModelAndView("addUser");
		}
		if (user.getRole() == null) {
			user.setRole("User");
		}
		userService.updateUser(user);
		model.addObject("user", user);
		model.addObject("message", "update");
		model.addObject("username", username);
		String role = (String) session.getAttribute("role");
		model.addObject("role", role);
		model.addObject("status", "updated successfully");
		return model;
	}

	@RequestMapping(value = "/myAcct", method = RequestMethod.GET)
	public ModelAndView myAcct(@ModelAttribute("command") User user,
			Content content, BindingResult result, HttpServletRequest req)
			throws HibernateException, SQLException, IOException {
		String uName = req.getParameter("uname");
		HttpSession session = req.getSession(true);
		ModelAndView model = new ModelAndView();
		String username = (String) session.getAttribute("username");
		user = userService.getMyAcct(uName);
		ArrayList<Content> catContent = new ArrayList<Content>();
		catContent = contentService.getMyContent(uName);
		model = new ModelAndView("myAcct");
		model.addObject("user", user);
		model.addObject("contents", catContent);
		model.addObject("username", username);
		String role = (String) session.getAttribute("role");
		model.addObject("role", role);
		model.addObject("message", "update");
		return model;
	}

}
