package com.bridgelabz.fundoo.serviceimpl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.dto.UserDTO;
import com.bridgelabz.fundoo.entity.UserEntity;
import com.bridgelabz.fundoo.exception.UserCustomException;
import com.bridgelabz.fundoo.exception.UserCustomException.TypeOfException;
import com.bridgelabz.fundoo.repositoryimpl.UserRepositoryImpl;
import com.bridgelabz.fundoo.service.IUserService;
import com.bridgelabz.fundoo.utility.JWTOperations;
import com.bridgelabz.fundoo.utility.SimpleMail;

@Service
public class UserServiceImpl implements IUserService {

	
	@Autowired
	private JWTOperations jwt;
	
	@Autowired
	private BCryptPasswordEncoder encryption;

	@Autowired
	private UserRepositoryImpl userRepository;

	@Autowired
	private SimpleMail simpleMail;
	//throw new UserCustomException("Email Id already exist...", TypeOfException.FORBIDDEN,null

	public String addUser(UserDTO userDetails) {

		
		UserEntity userEntity = new UserEntity();		
		BeanUtils.copyProperties(userDetails, userEntity);
		userEntity.setPassword(encryption.encode(userEntity.getPassword()));		
		userEntity.setVerify(false);
		if(userRepository.getUserByMail(userEntity.getEmail()).isPresent())
			throw new UserCustomException("Email Id already exist...", TypeOfException.FORBIDDEN,null);
		userRepository.save(userEntity);
		sendVerify(userEntity);
		return jwt.tokenGenerater(userRepository.getUserByMail(userEntity.getEmail()).get().id);				
	}


	public boolean userLogin(String email, String password) {				
		
		
			UserEntity userEntity=userRepository.loginProcess(email,password).orElseThrow(()-> new UserCustomException("Email Id not registered...", TypeOfException.USER_NOT_FOUND,null));																							
			System.out.println(userEntity.getVerify());
			if(userEntity.getVerify().equals(Optional.of(false)))
				throw new UserCustomException("User Verification Pending...", TypeOfException.FORBIDDEN,null);								
			
			return (encryption.matches(password,userEntity.getPassword()));
											
	}
	
	public String forgotpwd(UserDTO userDetails) {
				
		userRepository.loginProcess(userDetails.getEmail(),userDetails.getPassword()).orElseThrow(()-> new UserCustomException("User Not Found....", TypeOfException.USER_NOT_FOUND,null));					
		String message="Link for resetting password " + "\r\nhttp://localhost:8080/users/resetPassword/"+jwt.jwtToken(userDetails.getEmail());
		String subject="Fundoo Forgot Password";
		simpleMail.simpleMail(userDetails.getEmail(),subject, message);
	    return "true";				
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
				

		String email=userEntity.getEmail();
		String subject="Fundoo Verification";
		
		String message="http://localhost:8080/users/userVerification/"+ jwt.tokenGenerater(userRepository.getUserByMail(userEntity.getEmail()).get().id);
		simpleMail.simpleMail(email, subject, message);
	}
	
	public boolean getVerify(String token){
	
		int id=jwt.tokenDecoder(token);				
		UserEntity userEntity = new UserEntity();
		userEntity.setId(id);
		userEntity.setVerify(true);
		userRepository.verification(userEntity);	
		return true;		
	}
	
	public UserEntity getUsers()
	{
		
		return userRepository.getUserById(34).orElseThrow((()->new UserCustomException("User Not Found....", TypeOfException.USER_NOT_FOUND,null)));
	}
	
}
