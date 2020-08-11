package com.webdynamics.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.webdynamics.dao.ContentDAO;
import com.webdynamics.model.Content;

@Service("ContentService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ContentServieImpl implements ContentService{


	@Autowired
	 private ContentDAO contentDAO;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public ArrayList<Content> listContents() {
		return contentDAO.listContents();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addContent(Content content) {
		contentDAO.addContent(content);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteContents(int contentId) {
		contentDAO.deleteContent(contentId);
		
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Content editContent(int contentId) {
		return contentDAO.getContent(contentId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateContent(Content content) {
		// TODO Auto-generated method stub
		contentDAO.updateContent(content);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public ArrayList<Content> cateContent(String category) {
		return contentDAO.cateContent(category);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public ArrayList<Content> satusContent(String status) {
		return contentDAO.statusContent(status);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public  ArrayList<Content> getMyContent(String uName) {
		// TODO Auto-generated method stub
		return contentDAO.getMyContent(uName);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateContentStatus(int contentID, String status, String approvedBy) {
		// TODO Auto-generated method stub
	    contentDAO.updateContentStatus(contentID,status,approvedBy);
	}

	

}
