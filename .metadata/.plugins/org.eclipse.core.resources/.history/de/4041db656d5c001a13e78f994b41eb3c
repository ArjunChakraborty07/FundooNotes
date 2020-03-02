package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.UserDetails;
import com.bridgelabz.fundoo.entity.UserEntity;

public interface IUserService {

	public String addUser(UserDetails userDetails);
	public boolean userLogin(UserDetails userDetails);
	public boolean forgotpwd(UserDetails userDetails);
	public boolean resetPassword(UserDetails userDetails);
	public void sendVerify(UserEntity userEntity);
	public boolean getVerify(String token);
}
