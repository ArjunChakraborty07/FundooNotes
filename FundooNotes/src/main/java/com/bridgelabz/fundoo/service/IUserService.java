package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.UserDTO;
import com.bridgelabz.fundoo.entity.UserEntity;

public interface IUserService {

	public String addUser(UserDTO userDetails) ;
	public boolean userLogin(String email, String password);
	public String forgotpwd(UserDTO userDetails);
	public boolean resetPassword(String token, UserDTO userDetails);
	public void sendVerify(UserEntity userEntity);
	public boolean getVerify(String token);
	public UserEntity getUsers();
}
