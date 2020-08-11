package com.webdynamics.service;

import java.util.ArrayList;

import com.webdynamics.model.Content;

public interface ContentService {

	public ArrayList<Content> listContents();

	public void addContent(Content content);

	public void deleteContents(int userId);

	public Content editContent(int contentId) ;

	public void updateContent(Content content);

	public ArrayList<Content> cateContent(String category);

	public ArrayList<Content> satusContent(String status);

	public ArrayList<Content> getMyContent(String uName);

	public void updateContentStatus(int contentID, String status, String approvedBy);


}
