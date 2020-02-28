package com.bridgelabz.fundoo.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer.JwtConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.impl.JWTParser;
import com.bridgelabz.fundoo.dto.UserDetails;
import com.bridgelabz.fundoo.entity.UserEntity;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.utility.JWTOperations;

@Service
public class RegistrationServiceImpl {

	@Autowired
	private JWTOperations jwt;
	
	@Autowired
	private BCryptPasswordEncoder encryption;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;

	private List<UserDetails> obj = new ArrayList<>(Arrays.asList(new UserDetails()));

	public List<UserDetails> getDetails() {
		UserDetails ob1 = obj.get(0);
		if (ob1.getName() == null) {
			obj.remove(0);
		}
		return obj;
	}

	public String addUser(UserDetails object) {

		UserEntity data = new UserEntity();
		
		BeanUtils.copyProperties(object, data);
		data.setPassword(encryption.encode(data.getPassword()));
		userRepository.save(data);

		return "data processing successful";

	}

	public UserDetails getData(String name) {

		return obj.stream().filter(t -> t.getName().equals(name)).findFirst().get();

	}

	public void updateUser(UserDetails object, String name) {
		for (int i = 1; i < obj.size(); i++) {
			UserDetails ob1 = obj.get(i);

			if (ob1.getName().equals(name)) {
				obj.set(i, object);
				return;
			}
		}
	}

	public void deleteUser(String name) {
		
		obj.removeIf(t -> t.getName().equals(name));
	}

	public boolean userLogin(UserDetails object) {				
		
			UserEntity data=userRepository.loginProcess(object);
			if(sendverify(data))
			{
				return (encryption.matches(object.getPassword(),data.getPassword()));
			}
			return false;
	}

	
	public boolean forgotpwd(UserDetails object) {
		
		if(userRepository.forgetpwd(object))
		{		
			SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(object.getEmail());
	        msg.setSubject("Fundoo Forgot Password");
	        msg.setText("Link for resetting password " + "\r\nhttp://localhost:8080/login/resetpassword");
	        javaMailSender.send(msg);
	        return true;
		}
		return false;
	}

	public boolean resetpassword(UserDetails object) {
	
		UserEntity data = new UserEntity();
		BeanUtils.copyProperties(object, data);
		data.setPassword(encryption.encode(data.getPassword()));	
		userRepository.updatepassword(data);
		return true;
	}

	public boolean sendverify(UserEntity data) {
				
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(data.getEmail());
        msg.setSubject("Fundoo Verification");
        msg.setText("http://localhost:8080/registration/login/verification/"+ jwt.JwtToken(data.getEmail()));
        boolean check=getverify(jwt.JwtToken(data.getEmail()));
        return check;
		
	}
	
	public boolean getverify(String token)
	{
		String email=jwt.parseJWT(token);
		UserEntity data = new UserEntity();
		data.setEmail(email);
		data.setverify("true");
		userRepository.verification(data);
		return true;
	}
		
	
}
