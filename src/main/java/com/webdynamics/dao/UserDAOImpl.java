package com.webdynamics.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webdynamics.model.Content;
import com.webdynamics.model.User;
import com.webdynamics.dao.UserDAO;

@Repository("UserDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void addUser(User user) {
		try{
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		}catch(Exception e){
			System.out.println("In Exception");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<User> listUsers() {
		return (ArrayList<User>) sessionFactory.getCurrentSession()
				.createCriteria(User.class).list();
	}

	@Override
	@Transactional
	public User getUser(int userId) {
		return (User) sessionFactory.getCurrentSession()
				.get(User.class, userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> verifyLogin(String userName, String password) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				User.class);
		criteria.add(Restrictions.ilike("userName", userName, MatchMode.END));
		criteria.add(Restrictions.ilike("userPassword", password, MatchMode.END));
		return criteria.list();
	}

	@Override
	@Transactional
	public void deleteUser(int userId) {
		sessionFactory.getCurrentSession()
				.createQuery("DELETE FROM User WHERE userId = " + userId)
				.executeUpdate();

	}

	@Override
	@Transactional
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		System.out.println("coming in updateuser daoimpl");
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE User set userName='" + user.getUserName()
								+ "', userPassword='" + user.getUserPassword()
								+ "', name='" + user.getName()
								+ "', phone="+user.getPhone()
								+ ", email='"+user.getEmail()
								+ "', role='"+user.getRole()
								+ "' WHERE userId = " + user.getUserId()).executeUpdate();
	}

	@Override
	@Transactional
	public User getMyAcct(String uName) {		
		 Query query = sessionFactory.getCurrentSession().createQuery("FROM User u WHERE u.userName = :uname");
	     query.setString("uname", uName);
	     User userdetails = (User) query.uniqueResult();
		 return  userdetails ;
	}

	@Override
	@Transactional
	public String getUserRole(String userName) {
		 Query query = sessionFactory.getCurrentSession().createQuery("select u.role FROM User u WHERE u.userName = :uname");
	     query.setString("uname", userName);
	     String userRole = (String) query.uniqueResult();
		 return  userRole ;
	}
	

}
