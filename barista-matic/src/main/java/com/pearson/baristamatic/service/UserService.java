package com.pearson.baristamatic.service;

import java.util.List;

import com.pearson.baristamatic.entity.User;

public interface UserService {
	public User findUser(String userName);
	public List<User> findUsers();
	public void saveOrUpdateUser(User user);
	public void deleteUser(String userName);
	public void clearUsers();
}
