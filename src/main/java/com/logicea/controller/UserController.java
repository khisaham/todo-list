package com.logicea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logicea.model.User;
import com.logicea.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok().body(userService.getAll());
	}

	@GetMapping(params = "search")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(value = "search", required = true) String search) {
		return ResponseEntity.ok().body(userService.getAll(search));
	}
	
	@PostMapping
	public ResponseEntity<User> insertUser(@RequestBody User user) {
		return ResponseEntity.ok().body(userService.insertUser(user));
	}

	@PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.updateUser(user));
    }
	
	@DeleteMapping
	public ResponseEntity<Long> deleteUser(@RequestParam(name="id", value="id") long id) {
        return ResponseEntity.ok().body(userService.deleteUser(id));
    }
}
