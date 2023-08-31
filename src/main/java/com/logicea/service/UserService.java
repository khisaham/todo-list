package com.logicea.service;

import java.util.List;

import com.logicea.model.User;

public interface UserService {

	List<User> getAll();
	
	List<User> getAll(String text);
	
	User insertUser(User user);
	
	User updateUser(User user);
	
	long deleteUser(Long id);
}
