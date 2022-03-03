package com.appsdeveloperblog.app.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import com.appsdeveloperblog.app.ws.ui.model.request.UpdateUserRequest;
import com.appsdeveloperblog.app.ws.ui.model.request.UserRequest;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserController {

	Map<String, UserRest> users = new HashMap<>();
		
	@GetMapping
	public String getUsers(@RequestParam(value="page", defaultValue="1") int p, @RequestParam(value="limit", defaultValue="50") int limit) {
		return String.format("Getting users from page %d, limited to %d", p, limit);
	}
	
	@GetMapping(path="/{id}", produces={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable(name="id") String userId) {
		String test = null;
		test.length();
		if (this.users.containsKey(userId)) {
			return new ResponseEntity<UserRest>(this.users.get(userId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
					produces= { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserRequest newUser) {
		String userId = UUID.randomUUID().toString();
		UserRest addedUser = new UserRest();
		addedUser.setFirstName(newUser.getFirstName());
		addedUser.setLastName(newUser.getLastName());
		addedUser.setEmail(newUser.getEmail());
		addedUser.setUserId(userId);

		users.put(userId, addedUser);
		return new ResponseEntity<UserRest>(addedUser, HttpStatus.OK);
	}
	
	@PutMapping(path="/{userId}", consumes={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
				produces= { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserRequest updatedUser) {
		if (this.users.containsKey(userId)) {
			UserRest user = this.users.get(userId);
			user.setFirstName(updatedUser.getFirstName());
			user.setLastName(updatedUser.getLastName());

			return new ResponseEntity<UserRest>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/{userId}", consumes={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
				   produces= { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> deleteUser(@PathVariable String userId) {
		if (this.users.containsKey(userId)) {
			String deletedId = this.users.remove(userId).getUserId();
			return new ResponseEntity<>(String.format("User %s was deleted", deletedId), HttpStatus.OK);
		}
		return new ResponseEntity<>("Invalid user ID", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
