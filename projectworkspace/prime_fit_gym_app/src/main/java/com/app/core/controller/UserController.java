package com.app.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.core.custom_exception.GlobalExceptionHandler;
import com.app.core.dto.LoginRequest;
import com.app.core.dto.LoginResponse;
import com.app.core.dto.ResponseDTO;
import com.app.core.pojos.GymBranch;
import com.app.core.pojos.User;
import com.app.core.pojos.UserRole;
import com.app.core.service.IUserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService userService;

	public UserController() {
		// TODO Auto-generated constructor stub
		System.out.println("In constructor of " + getClass());
	}

	// adding request handlig method to send all the Member-users to the caller
	@GetMapping("/members")
	public ResponseDTO<?> getAllMembers() {
		System.out.println("in get all Members");
		return new ResponseDTO<>(HttpStatus.OK,"Users Found ",userService.getAllMembers());
	}

	@GetMapping("/trainers")
	public ResponseDTO<?> getAllTrainers() {
		System.out.println("in get all Trainers");
		return new ResponseDTO<>(HttpStatus.OK,"Trainers found",userService.getAllTrainers());
	}

	@GetMapping("/localadmins")
	public ResponseDTO<?> getAllLocalAdmins() {
		System.out.println("in get all LocalAdmins");
		return new ResponseDTO<>(HttpStatus.OK,"Local-Admins found",userService.getAllLocalAdmins());
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUser(@PathVariable int id) {
		System.out.println("In get User details " + id);
		return new ResponseEntity<>(userService.getUserDetails(id), HttpStatus.OK);
	}

	@PostMapping("/add/{userBranchId}")
	public ResponseDTO<?> addUser(@RequestBody User u,@PathVariable int userBranchId) {
		System.out.println("in Add users" + u + " userBranchId "+ userBranchId);
		User newUser = userService.register(u,userBranchId);
		return new ResponseDTO<>(HttpStatus.OK, "User Added Successfully", newUser);
	}
//	
//	@PostMapping("/add")
//	public ResponseDTO<?> addUser(@RequestBody User u) {
//		System.out.println("in Add users" + u );
//		User newUser = userService.register(u);
//		return new ResponseDTO<>(HttpStatus.OK, "User Added Successfully", newUser);
//	}
/////////////////////////////////////////////////////
	@PutMapping("/update/{id}")
	public ResponseDTO<?> updateUser(@RequestBody User u,@PathVariable int id) {
		System.out.println("In update user" + u+ " id "+id);
		
		User user = userService.updateUser(u,id);
		return new ResponseDTO<>(HttpStatus.OK, "user updated successfully", user);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseDTO<?> deleteUser(@PathVariable int id) {
		System.out.println("in delete user " + id);
		String deleteUser = userService.deleteUser(id);
		return new ResponseDTO<>(HttpStatus.OK, "User deleted successfully", deleteUser);
	}
	
	//@SuppressWarnings("unused")
	@PostMapping("/signin")
	public LoginResponse<?> authenticateUser(@RequestBody LoginRequest request){
		System.out.println("in user authentication "+request);
		
		User user = userService.authenticateUserLogin(request);
		if(user != null) {
		UserRole role=user.getRole();
		System.out.println("User "+user+ " "+" Role "+role);
//		return new LoginResponse<>(HttpStatus.OK,"user found ",user,role);
		
		
            return new LoginResponse<>(HttpStatus.OK,"user found ",user,role);
        }else {
            return new LoginResponse<>(HttpStatus.NOT_FOUND,"user not found",null,null);
        	
        }
		
	}
	
	@PostMapping("/usersbyid/{id}")
	public ResponseDTO<?> getUsersByBranchId(@RequestBody User u,@PathVariable int id){
		System.out.println("In get Users by branch id "+id+ "Role "+u.getRole());
		List<User> users=userService.getUserByBranchId(u.getRole(), id);
		return new ResponseDTO<>(HttpStatus.OK,"All users of the branch id "+id ,users);
		
		
	}
//	@GetMapping("/branchmembers/{id}")
//	public ResponseDTO<?>getMyBranchUsers(@RequestBody User u,@PathVariable int id){
//		System.out.println("In MyBranch Users "+u + " id "+id);
//		List<User> users=userService.getMyBranchUsers(u.)
//		return null;
//	}
}
