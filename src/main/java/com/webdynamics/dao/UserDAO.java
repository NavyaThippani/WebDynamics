package com.webdynamics.dao;

import java.util.ArrayList;
import java.util.List;

import com.webdynamics.model.User;

public interface UserDAO {
	
        public void addUser(User p);

		public ArrayList<User> listUsers();

		public User getUser(int userId);

		public List<User> verifyLogin(String userName, String password);

		public void deleteUser(int userId);

		public void updateUser(User user);

		public User getMyAcct(String uName);

		public String getUserRole(String userName);
}
