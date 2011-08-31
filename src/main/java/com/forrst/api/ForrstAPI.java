package main.java.com.forrst.api;

public interface ForrstAPI {
	
	String BASE_URI = "https://forrst.com/api/v2/";
	
	void stats();
	
	void usersAuth();
	
	void usersInfo();
	
	void userPosts();
	
	void postsShow();
	
	void postsAll();
	
	void postsList();
	
	void postComments();

}
