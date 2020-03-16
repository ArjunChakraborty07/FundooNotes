package com.bridgelabz.fundoo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoo.dto.UserDTO;
import com.bridgelabz.fundoo.entity.UserEntity;
import com.bridgelabz.fundoo.exception.UserCustomException.TypeOfException;
import com.bridgelabz.fundoo.response.UserResponse;
import com.bridgelabz.fundoo.service.AmazonS3ClientService;
import com.bridgelabz.fundoo.service.IUserService;

@RestController
public class UserController {
	@Autowired
	private IUserService userService;
     @Autowired
     private AmazonS3ClientService amazonS3ClientService;

	@PostMapping("/users/register")
	public ResponseEntity<UserResponse> register(@RequestBody UserDTO userDetails) {
		String result = userService.addUser(userDetails);
		return ResponseEntity.status(HttpStatus.OK).body(new UserResponse(result, "Registration Successful please verify",null));
	}

	@GetMapping("/users/login")
	public ResponseEntity<UserResponse> login(@RequestParam("emailId") String email, @RequestParam("password") String password) {
		if (userService.userLogin(email, password)) {
			return ResponseEntity.status(HttpStatus.OK).body(new UserResponse(null, "Login Successfull",null));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse(null, "Login Denied",TypeOfException.FORBIDDEN));
	}

	@PutMapping("/users/forgotPassword")
	public ResponseEntity<UserResponse> forgotPassword(@RequestBody UserDTO userDetails) {
		if (userService.forgotpwd(userDetails).equals("true")) {
			return ResponseEntity.status(HttpStatus.OK).body(new UserResponse(null, "Verification Mail send",null));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse(null, "Invalid Email Id",TypeOfException.FORBIDDEN));
	}

	@PutMapping("/users/resetPassword/{token}")
	public ResponseEntity<UserResponse> resetPassword(@PathVariable("token") String token,
			@RequestBody UserDTO userDetails) {

		if (userService.resetPassword(token, userDetails)) {
			return ResponseEntity.status(HttpStatus.OK).body(new UserResponse(token, "Password Reset Successful",null));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse(null, "Update Denied",TypeOfException.FORBIDDEN));
	}

	@GetMapping("/users/userVerification/{token}")
	public ResponseEntity<UserResponse> userVerification(@PathVariable("token") String token) {
		if (userService.getVerify(token)) {
			return ResponseEntity.status(HttpStatus.OK).body(new UserResponse(token, "Verification Successfull",null));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse(null, "Verification Unsucessful",TypeOfException.USER_NOT_FOUND));
	}
	@GetMapping("/users/getAllUsers")
	public UserEntity getAllUser()
	{
		return userService.getUsers();
	}
	
	
	 @PostMapping
	   public Map<String, String> uploadFile(@RequestPart(value = "file") MultipartFile file)
	   {
	       this.amazonS3ClientService.uploadFileToS3Bucket(file, true);
	       Map<String, String> response = new HashMap<>();
	       response.put("message", "file [" + file.getOriginalFilename() + "] uploading request submitted successfully.");
	       return response;
	   }

	   @DeleteMapping
	   public Map<String, String> deleteFile(@RequestParam("file_name") String fileName)
	   {
	       this.amazonS3ClientService.deleteFileFromS3Bucket(fileName);
	       Map<String, String> response = new HashMap<>();
	       response.put("message", "file [" + fileName + "] removing request submitted successfully.");
	       return response;
	   }

}
