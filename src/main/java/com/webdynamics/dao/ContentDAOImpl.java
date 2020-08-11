package com.webdynamics.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webdynamics.model.Content;

@Repository("ContentDAO")
public class ContentDAOImpl implements ContentDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
    @Transactional
	public void addContent(Content content) {
		sessionFactory.getCurrentSession().saveOrUpdate(content);		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<Content> listContents() {
		return (ArrayList<Content>) sessionFactory.getCurrentSession().createCriteria(Content.class).list();
	}

	@Override
	@Transactional
	public Content getContent(int contentId) {
		return (Content) sessionFactory.getCurrentSession().get(Content.class, contentId);
	}

	@Override
	@Transactional
	public void deleteContent(int contentId) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM Content WHERE contentId = "+contentId).executeUpdate();
		
	}

	@Override
	@Transactional
	public void updateContent(Content content) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE Content set contentTitle='" + content.getContentTitle()
								+ "', contentBody='" + content.getContentBody()
								+ "', author='" + content.getAuthor()
								+ "', category='"+content.getCategory()
								+ "', status='"+content.getStatus()
								+ "', createdOn='"+content.getCreatedOn()
								+ "', modifiedOn='"+content.getModifiedOn()
								+ "', approvedBy='"+content.getApprovedBy()
								+ "' WHERE contentId = " +content.getContentId()).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<Content> cateContent(String category) {
		System.out.println("category in daoimpl : "+category);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Content.class);
		crit.add(Restrictions.eq("category",category));
		crit.add(Restrictions.eq("status","approve"));
		 ArrayList<Content> results = (ArrayList<Content>)crit.list();
		 return results;
	}

	@Override
	@Transactional
	public ArrayList<Content> statusContent(String status) {
		System.out.println("status in daoimpl : "+status);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Content.class);
		crit.add(Restrictions.eq("status",status));
		 @SuppressWarnings("unchecked")
		ArrayList<Content> results = (ArrayList<Content>)crit.list();
		 return results;
	}

	@Override
	public ArrayList<Content> getMyContent(String uName) {
		// TODO Auto-generated method stub
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Content.class);
		crit.add(Restrictions.eq("author",uName));
		 @SuppressWarnings("unchecked")
		ArrayList<Content> results = (ArrayList<Content>)crit.list();
		 return results;
	}

	@Override
	public void updateContentStatus(int contentID, String status, String approvedBy) {
		sessionFactory
		.getCurrentSession()
		.createQuery(
				"UPDATE Content set status='"+status+ "', approvedBy='"+approvedBy+"' WHERE contentId = " +contentID).executeUpdate();
}
}
