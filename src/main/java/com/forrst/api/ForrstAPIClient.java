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
		return "";
	}

	public String userPosts() {
		return "";
	}

	public String postsShow() {
		return "";
	}

	public String postsAll() {
		return "";
	}

	public String postsList() {
		return "";
	}

	public String postComments() {
		return "";
	}

}
