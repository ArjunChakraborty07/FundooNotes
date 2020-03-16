package com.bridgelabz.fundoo.repository;

import java.util.Optional;

import com.bridgelabz.fundoo.entity.UserEntity;

public interface IUserRepository {

	public Optional<UserEntity> getUserById(int id);
	public Optional<UserEntity> getUserByMail(String emailId);
	public Optional<UserEntity> save(UserEntity userEntity);
	public Optional<UserEntity> loginProcess(String email, String password);
	public void updatepassword(UserEntity userEntity);
	public void verification(UserEntity userEntity);
}
