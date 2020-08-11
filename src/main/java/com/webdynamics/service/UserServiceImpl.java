package com.webdynamics.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.webdynamics.dao.UserDAO;
import com.webdynamics.model.User;

@Service("UserService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {
	
	@Autowired
	 private UserDAO userDAO;
	 

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addUser(User user) {
		try{
		
		userDAO.addUser(user);
		}catch(Exception e){
			System.out.println("inside service impl exception");
			e.printStackTrace();
		}
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public ArrayList<User> listUsers() {
		return userDAO.listUsers();
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteUser(int userId) {
		userDAO.deleteUser(userId);
		
	}



	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public User editUser(int userId) {
		return userDAO.getUser(userId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<User> verifyLogin(String userName, String password) {
		// TODO Auto-generated method stub
		List<User> custList = userDAO.verifyLogin(userName,password);
		return custList;
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDAO.updateUser(user);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public User getMyAcct(String uName) {
		// TODO Auto-generated method stub
		return userDAO.getMyAcct(uName);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String getUserRole(String userName) {
		// TODO Auto-generated method stub
		return userDAO.getUserRole(userName);
	}

}
