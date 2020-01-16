package com.magister.garbagecollector.controllers;

import java.util.List;

import com.magister.garbagecollector.entities.User;
import com.magister.garbagecollector.exceptions.ApplicationNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.magister.garbagecollector.entities.Application;
import com.magister.garbagecollector.entities.Status;
import com.magister.garbagecollector.entities.jpa.ApplicationRepository;
import com.magister.garbagecollector.services.UserService;


@Controller
public class GarbageCollectorStaticController {

	private UserService userService;
	private ApplicationRepository applicationRepository;
	
	public GarbageCollectorStaticController(UserService userService,
											ApplicationRepository applicationRepository) {
		this.userService = userService;
		this.applicationRepository = applicationRepository;
	}

	@GetMapping("/")
	public String home(Model model) {
		String username = userService.getLoggedInUsername();
		Boolean isAdmin = userService.isAdmin(username);
		List<Application> applications = applicationRepository.findAll();
		model.addAttribute("username", username);
		model.addAttribute("admin", isAdmin);
		model.addAttribute("applications", applications);
		return "index";
	}
	
	@PostMapping("/")
	public String addApplication(
			Model model, 
			@RequestParam String address,
			@RequestParam Double volume,
			@RequestParam Long deleteId,
			@RequestParam Long updateId,
			@RequestParam String comment,
			@RequestParam String tip)
	{
		String username = userService.getLoggedInUsername();
		if (deleteId != null) {
			Application application = applicationRepository.findById(deleteId)
					.orElseThrow(() -> new ApplicationNotFoundException(String.valueOf(deleteId)));
			applicationRepository.delete(application);
		} else if (updateId != null) {
			Application application = applicationRepository.findById(updateId)
					.orElseThrow(() -> new ApplicationNotFoundException(String.valueOf(updateId)));
			application.setStatus(Status.COMPLETED);
			applicationRepository.save(application);
		} else {

			User user = userService.getUser(username);
			Application application = new Application(user, address, volume, comment,  tip);
			applicationRepository.save(application);
		}
		List<Application> applications = applicationRepository.findAll();
		model.addAttribute("username", username);
		model.addAttribute("applications", applications);
	    return "redirect:/";
	}

	@GetMapping("/users")
	public String users(Model model) {
		String username = userService.getLoggedInUsername();
		List<User> users = userService.getAll();
		model.addAttribute("username", username);
		model.addAttribute("users", users);
		return "users";
	}
}
