package com.bridgelabz.fundoo.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.dto.UserDetails;
import com.bridgelabz.fundoo.entity.UserEntity;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.service.IUserService;
import com.bridgelabz.fundoo.utility.JWTOperations;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private JWTOperations jwt;
	
	@Autowired
	private BCryptPasswordEncoder encryption;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;

	

	public String addUser(UserDetails userDetails) {

		UserEntity userEntity = new UserEntity();		
		BeanUtils.copyProperties(userDetails, userEntity);
		userEntity.setPassword(encryption.encode(userEntity.getPassword()));		
		userEntity.setVerify(false);		
		if(userRepository.save(userEntity))
		{
			sendVerify(userEntity);
			return "Data processing successful";
		}
		return "user already exists";
	}


	public boolean userLogin(UserDetails userDetails) {				
		
			UserEntity userEntity=userRepository.loginProcess(userDetails);			
			return (encryption.matches(userDetails.getPassword(),userEntity.getPassword()));
	}
	
	public boolean forgotpwd(UserDetails userDetails) {
		
		if(userRepository.forgetpwd(userDetails))
		{		
			SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(userDetails.getEmail());
	        msg.setSubject("Fundoo Forgot Password");
	        msg.setText("Link for resetting password " + "\r\nhttp://localhost:8080/users/resetPassword/"+jwt.jwtToken(userDetails.getEmail()));
	        javaMailSender.send(msg);
	        return true;
		}
		return false;
	}

	public boolean resetPassword(UserDetails userDetails) {
	
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDetails, userEntity);
		userEntity.setPassword(encryption.encode(userEntity.getPassword()));	
		userRepository.updatepassword(userEntity);
		return true;
	}

	public void sendVerify(UserEntity userEntity) {
				
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(userEntity.getEmail());
        msg.setSubject("Fundoo Verification");
        msg.setText("http://localhost:8080/users/userVerification/"+ jwt.jwtToken(userEntity.getEmail())); 
        javaMailSender.send(msg);
	}
	
	public boolean getVerify(String token) {
	
		String email=jwt.parseJWT(token);
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(email);
		userEntity.setVerify(true);
		userRepository.verification(userEntity);	
		return true;
	}

	        	
	
}