package com.forrst.api;

public class ForrstAPIClient implements ForrstAPI {

	public String stats() {
		HttpRequest http = new HttpRequest();
		return http.get(STATS_URI);
	}

	public String usersAuth(String emailOrUsername, String password) {
		HttpRequest http = new HttpRequest();
		return http.get(USERS_AUTH);
	}

	public String usersInfo(int id, String username) {
		HttpRequest http = new HttpRequest();
		return http.get(USERS_INFO);
	}

	public String userPosts(int id, String username, String type, int limit, int after) {
		HttpRequest http = new HttpRequest();
		return http.get(USER_POSTS);
	}

	public String postsShow(int id, int tinyId) {
		HttpRequest http = new HttpRequest();
		return http.get(POSTS_SHOW);
	}

	public String postsAll(int after) {
		HttpRequest http = new HttpRequest();
		return http.get(POSTS_ALL);
	}

	public String postsList(String postType, String sort, int page) {
		HttpRequest http = new HttpRequest();
		return http.get(POSTS_LIST);
	}

	public String postComments(int id, int tinyId) {
		HttpRequest http = new HttpRequest();
		return http.get(POST_COMMENTS);
	}

}
