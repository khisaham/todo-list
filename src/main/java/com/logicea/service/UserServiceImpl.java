package com.logicea.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.logicea.model.User;
import com.logicea.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> getAll(String text) {
		List<String> attributes = Arrays.asList("id", "email", "password", "role");        
        if (!text.contains("%")) {
            text = "%" + text + "%";
        }
        String finalText = text;
        Specification<User> spec = (root, query, builder) -> 
        		builder.or(root.getModel().getDeclaredSingularAttributes().stream()
        				.filter(a -> attributes.contains(a.getName()))
        				.map(a -> {
        					if(a.getJavaType().getSimpleName().equalsIgnoreCase("Long")) {
        						return builder.like(root.get(a.getName()).as(String.class), finalText);
        					} else if(a.getJavaType().getSimpleName().equalsIgnoreCase("int")) {
        						return builder.like(root.get(a.getName()).as(String.class), finalText);
        					}
        					return builder.like(root.get(a.getName()), finalText);
        				})
        				.toArray(Predicate[]::new)
        );       
        return userRepository.findAll(Specification.where(spec));
	}
	
	@Override
	public User insertUser(User user) {
		user = userRepository.save(user);
		return user;
	}

	@Override
	public User updateUser(User user) {
		Optional<User> projectDb = userRepository.findById(user.getId());
		if(projectDb.isPresent()) {
			User existingUser = projectDb.get();
			existingUser.setId(user.getId());
            existingUser.setEmail(user.getEmail());
			existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
			return userRepository.save(existingUser);
		}
		return null;
	}

	@Override
	public long deleteUser(Long id) {
		Optional<User> projectDb = userRepository.findById(id);
		if(projectDb.isPresent()) {
			userRepository.delete(projectDb.get());
			return id;
		}
		return -1;
	}
}
