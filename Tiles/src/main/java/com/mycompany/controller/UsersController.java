package com.mycompany.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.mapper.UserMapper;
import com.mycompany.vo.User;

@Controller
public class UsersController {

	@Autowired
	private UserMapper userMapper;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute User user,  BindingResult result, RedirectAttributes flash) {

		if (result.hasErrors()) {
			List<FieldError> fieldErrors = result.getFieldErrors();
			flash.addFlashAttribute("fieldErrors", fieldErrors);
			flash.addFlashAttribute("user", user);
			return "redirect:/users/signup";
		}

		userMapper.insertUser(user);
		userMapper.insertAuthority(user.getEmail(), "ROLE_USER");
		return "redirect:/users/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "users/login";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "users/signup";
	}
}