package com.forrst.api;

public interface ForrstAPI {
	
	public int RATE_LIMIT = 150;
	
	public String BASE_URI = "https://forrst.com/api/v2/";
	
	public String stats();
	
	public String usersAuth();
	
	public String usersInfo();
	
	public String userPosts();
	
	public String postsShow();
	
	public String postsAll();
	
	public String postsList();
	
	public String postComments();

}
