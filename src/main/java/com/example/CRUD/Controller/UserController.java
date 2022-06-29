package com.example.CRUD.Controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.CRUD.Entity.UserDto;
import com.example.CRUD.Service.UserService;

@RestController
@RequestMapping("/crudControle")
public class UserController {
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping("/addUser")
	public ResponseEntity<Map<String, Object>> Saveuser(@RequestBody UserDto user) {
		try {
			logger.info("save user : ");
			return new ResponseEntity<>(userService.saveuser(user), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("problem occur : ", e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/getuser")
	public ResponseEntity<Map<String, Object>> GetUserDetail(@RequestParam(value = "id") Long id) {
		try {
			logger.info("Get User Detail : ");
			return new ResponseEntity<>(userService.getuser(id), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("problem occur : ", e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping("/updateUserEmail")
	public ResponseEntity<Map<String, Object>> updateUserEmail(@RequestParam(value = "id") Long id,
			@RequestParam(value = "email") String email) {
		try {
			logger.info("update user : ");
			return new ResponseEntity<>(userService.updateUserEmail(id, email), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("problem occur : ", e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping("/deleteUser")
	public ResponseEntity<Map<String, Object>> DeleteUser(@RequestParam(value = "id") Long id) {
		try {
			logger.info("Delete user : ");
			return new ResponseEntity<>(userService.deleteuser(id), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("problem occur : ", e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
