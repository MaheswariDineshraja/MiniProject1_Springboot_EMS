package org.user.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.user.app.model.User;
import org.user.app.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name="User Controller",description = "CRUD operations on User Entity")
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Operation(
	        summary = "Retrieve all users",
	        description = "This endpoint returns a list of all users.",
	        responses = {
	            @ApiResponse(
	                responseCode = "200",
	                description = "Successfully retrieved list of users",
	                content = @Content(
	                    mediaType = "application/json",
	                    schema = @Schema(implementation = User.class)
	                )
	            )
	        }
	    )
	@GetMapping("/users")
	public String userList(Model model)
	{
		model.addAttribute("users", this.userService.getUsers());
		return "page";
	}
	
	@Operation(
			summary = "Retrieve all users", 
			description = "This endpoint returns all users in a json array ", 
			tags = {"user","get"})
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = User.class),mediaType = "application/json")})
	})
	@GetMapping("/user")
	public String showAddUserForm(Model model)
	{
		model.addAttribute("user", new User());
		return "add-user";
		
	}
	
	
	@Operation(
	        summary = "Add a new user",
	        description = "This endpoint processes the form submission to add a new user.",
	        responses = {
	            @ApiResponse(
	                responseCode = "302",
	                description = "Successfully added the user and redirected"
	            ),
	            @ApiResponse(
	                responseCode = "400",
	                description = "Validation error occurred while adding the user"
	            )
	        }
	    )
	@PostMapping("/process")
	public String addUserProcess(@Valid User user, BindingResult result, Model model )
	{
		if(result.hasErrors())
		{
			return "add-user";
		}
		
		this.userService.addUser(user);
		return "redirect:users";	
		
	}
	
	@Operation(
	        summary = "View a specific user",
	        description = "This endpoint retrieves and displays details of a specific user by ID.",
	        responses = {
	            @ApiResponse(
	                responseCode = "200",
	                description = "Successfully retrieved user details",
	                content = @Content(
	                    mediaType = "application/json",
	                    schema = @Schema(implementation = User.class)
	                )
	            ),
	            @ApiResponse(
	                responseCode = "404",
	                description = "User not found"
	            )
	        }
	    )
	@GetMapping("/view/{id}")
	public String viewUser(@PathVariable("id") long id, Model model)
	{
		model.addAttribute("user", this.userService.getUserById(id).get());
		return "user";
		
	}
	
	
	@Operation(
	        summary = "Delete a user",
	        description = "This endpoint deletes a user by ID.",
	        responses = {
	            @ApiResponse(
	                responseCode = "302",
	                description = "Successfully deleted the user and redirected"
	            ),
	            @ApiResponse(
	                responseCode = "404",
	                description = "User not found"
	            )
	        }
	    )
	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id)
	{
		this.userService.deleteUserById(id);
		return "redirect:/users";
		
	}
	
	@Operation(
	        summary = "Show update user form",
	        description = "This endpoint displays the form for updating a user's details.",
	        responses = {
	            @ApiResponse(
	                responseCode = "200",
	                description = "Successfully retrieved update user form",
	                content = @Content(
	                    mediaType = "application/json",
	                    schema = @Schema(implementation = User.class)
	                )
	            ),
	            @ApiResponse(
	                responseCode = "404",
	                description = "User not found"
	            )
	        }
	    )
	@GetMapping("/update/{id}")
	public ModelAndView showUpdateUserForm(@PathVariable("id")long id)
	{
		Optional<User> user=this.userService.getUserById(id);
		ModelAndView modelAndView=new ModelAndView();
		if(user.isPresent())
		{
			
			modelAndView.setViewName("update-user");
			modelAndView.addObject("user", this.userService.getUserById(id).get());
			return modelAndView;
		}
		else
		{
			modelAndView.setViewName("page");
			return modelAndView;
		}
		
	}

}
