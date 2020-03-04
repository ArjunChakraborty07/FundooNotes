package com.bridgelabz.fundoo.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.dto.UserDTO;
import com.bridgelabz.fundoo.entity.UserEntity;
import com.bridgelabz.fundoo.exception.UserServiceExceptionHandler;
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

	

	public String addUser(UserDTO userDetails) {

		UserEntity userEntity = new UserEntity();		
		BeanUtils.copyProperties(userDetails, userEntity);
		userEntity.setPassword(encryption.encode(userEntity.getPassword()));		
		userEntity.setVerify(false);		
		if(userRepository.save(userEntity))
		{
			sendVerify(userEntity);
			return "Data processing successful";
		}
		throw new UserServiceExceptionHandler("User Already Exist....", 401);
	}


	public boolean userLogin(UserDTO userDetails) {				
		
			UserEntity userEntity=userRepository.loginProcess(userDetails);	
			if(userEntity==null)
				throw new UserServiceExceptionHandler("Email Id not registered...", 402);
			if(userEntity.getVerify())
			{
				return (encryption.matches(userDetails.getPassword(),userEntity.getPassword()));
			}
			throw new UserServiceExceptionHandler("User Verification Pending...", 402);
		
		
	}
	
	public boolean forgotpwd(UserDTO userDetails) {
		
		if(userRepository.forgetpwd(userDetails)!=null)
		{		
			SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(userDetails.getEmail());
	        msg.setSubject("Fundoo Forgot Password");
	        msg.setText("Link for resetting password " + "\r\nhttp://localhost:8080/users/resetPassword/"+jwt.jwtToken(userDetails.getEmail()));
	        javaMailSender.send(msg);
	        return true;
		}
		throw new UserServiceExceptionHandler("User Not Found....", 403);
	}

	public boolean resetPassword(String token, UserDTO userDetails) {
	
		UserEntity userEntity = new UserEntity();
		userDetails.setEmail(jwt.parseJWT(token));
		BeanUtils.copyProperties(userDetails, userEntity);
		userEntity.setPassword(encryption.encode(userEntity.getPassword()));	
		userRepository.updatepassword(userEntity);
		return true;
	}

	public void sendVerify(UserEntity userEntity) {
				
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(userEntity.getEmail());
        msg.setSubject("Fundoo Verification");
        msg.setText("http://localhost:8080/users/userVerification/"+ jwt.tokenGenerater(userRepository.getUserByMail(userEntity.getEmail()).getId())); 
        javaMailSender.send(msg);
	}
	
	public boolean getVerify(String token){
	
		int id=jwt.tokenDecoder(token);				
		UserEntity userEntity = new UserEntity();
		userEntity.setId(id);
		userEntity.setVerify(true);
		userRepository.verification(userEntity);	
		return true;		
	}

	
	
}
