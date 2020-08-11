package com.webdynamics.dao;

import java.util.ArrayList;

import com.webdynamics.model.Content;

public interface ContentDAO {

	public void addContent(Content content);

	public ArrayList<Content> listContents() ;

	public Content getContent(int contentId);

	public void deleteContent(int contentId);

	public void updateContent(Content content);

	public ArrayList<Content> cateContent(String category);

	public ArrayList<Content> statusContent(String status);

	public ArrayList<Content> getMyContent(String uName);

	public void updateContentStatus(int contentID, String status, String approvedBy);

}
