package com.nitindhar.forrst;

public class Endpoint {
	
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
    public String STATS_URI = BASE_URI + "stats";
    public String NOTIFICATIONS_URI = BASE_URI + "notifications";
    public String USERS_AUTH_URI = BASE_URI + "users/auth";
    public String USERS_INFO_URI = BASE_URI + "users/info";
    public String USER_POSTS_URI = BASE_URI + "user/posts";
    public String POSTS_SHOW_URI = BASE_URI + "posts/show";
    public String POSTS_ALL_URI = BASE_URI + "posts/all";
    public String POSTS_LIST_URI = BASE_URI + "posts/list";
    public String POST_COMMENTS_URI = BASE_URI + "post/comments";
    
    /**
     * Singleton
     */
    private static Endpoint endpoint = new Endpoint();
    
    /**
     * Returns an instance of the endpoint singleton
     * @return endpoint
     */
    public static Endpoint getInstance() {
    	return endpoint;
    }

}
