package com.forrst.api;

public interface ForrstAPI {
	
	/**
	 * All API calls (with the exception of stats) are rated
	 * limited at 150 calls per hour. In the future, we will
	 * offer whitelisting by request. When making unauthenticated
	 * calls, each request counts against the total made by
	 * the calling IP address; when authenticating, calls
	 * count against the authenticated user's limit. You may
	 * request rate limiting stats at api/v2/stats (it won't
	 * count against your total). Your limit resets each hour.
	 */
	public int RATE_LIMIT = 150;
	
	/**
	 * All calls should be made using https://forrst.com/api/v2/
	 * as the endpoint (e.g. calling posts/all would be
	 * https://forrst.com/api/v2/posts/all). Making calls over
	 * SSL is required, and calls over regular http may be
	 * disabled at some point in the future.
	 */
	public String BASE_URI = "https://forrst.com/api/v2/";
	
	/**
	 * ENDPOINTS
	 */
	public String STATS_URI = "stats";
	public String USERS_AUTH = "users/auth";
	public String USERS_INFO = "users/info";
	public String USER_POSTS = "user/posts";
	public String POSTS_SHOW = "posts/show";
	public String POSTS_ALL = "posts/all";
	public String POSTS_LIST = "posts/list";
	public String POST_COMMENTS = "post/comments";
	
	/**
	 * Returns stats about your API usage. Note: does
	 * not count against your rate limit.
	 *
	 * @return JSON response containing:
	 *         rate_limit,
	 *         calls_made
	 */
	public String stats();
	
	/**
	 * User authentication. Provide an email/username
	 * and password and get an access token back
	 *
	 * @param emailOrUsername Email/Username
	 * @param password Password
	 * @return JSON response containing:
	 *         token
	 */
	public String usersAuth(String emailOrUsername, String password);
	
	/**
	 * Returns user info
	 * 
	 * @param id 
	 * @return
	 */
	public String usersInfo(int id, String username);
	
	/**
	 * Returns a user's posts
	 * 
	 * @return
	 */
	public String userPosts();
	
	/**
	 * Return data about a single post. Note: For questions,
	 * content is the question. For code, content contains
	 * the code snippet. For code, snaps, and links, description
	 * is the post description; it is not used for questions.
	 * 
	 * @return
	 */
	public String postsShow();
	
	/**
	 * Returns a list of all posts in reverse-chron order
	 * 
	 * @return
	 */
	public String postsAll();
	
	/**
	 * Returns a list of posts of a given type
	 * 
	 * @return
	 */
	public String postsList();
	
	/**
	 * Returns a post's comments
	 * 
	 * @return
	 */
	public String postComments();

}
