package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.UserDTO;
import com.bridgelabz.fundoo.entity.UserEntity;

public interface IUserService {

	public String addUser(UserDTO userDetails);
	public boolean userLogin(UserDTO userDetails);
	public boolean forgotpwd(UserDTO userDetails);
	public boolean resetPassword(UserDTO userDetails);
	public void sendVerify(UserEntity userEntity);
	public boolean getVerify(String token);
}
