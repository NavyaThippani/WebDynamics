package com.webdynamics.service;

import java.util.ArrayList;
import java.util.List;

import com.webdynamics.model.User;

public interface UserService {
	
	public void addUser(User p);

	public ArrayList<User> listUsers();

	public void deleteUser(int userId);

	public User editUser(int userId);
	
	public List<User> verifyLogin(String userName, String password);

	public void updateUser(User user);

	public User getMyAcct(String uName);

	public String getUserRole(String userName);


}
