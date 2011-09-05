package com.forrst.api;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class ForrstAPIClient implements ForrstAPI {

	public JSONObject stats() {
		HttpRequest http = new HttpRequest();
		
		return http.get(STATS_URI);
	}

	public JSONObject usersAuth(String emailOrUsername, String password) {
		HttpRequest http = new HttpRequest();
		
		Map<String,String> args = new HashMap<String,String>();
		args.put("email_or_username", emailOrUsername);
		args.put("password", password);
		
		return http.get(USERS_AUTH, args);
	}

	public JSONObject usersInfo(int id, String username) {
		HttpRequest http = new HttpRequest();
		
		Map<String,String> args = new HashMap<String,String>();
		args.put("id", Integer.toString(id));
		args.put("username", username);
		
		return http.get(USERS_INFO, args);
	}

	public JSONObject userPosts(int id, String username, String type, int limit, int after) {
		HttpRequest http = new HttpRequest();
		
		Map<String,String> args = new HashMap<String,String>();
		args.put("id", Integer.toString(id));
		args.put("username", username);
		args.put("type", type);
		args.put("limit", Integer.toString(limit));
		args.put("after", Integer.toString(after));
		
		return http.get(USER_POSTS, args);
	}

	public JSONObject postsShow(int id, int tinyId) {
		HttpRequest http = new HttpRequest();
		
		Map<String,String> args = new HashMap<String,String>();
		args.put("id", Integer.toString(id));
		args.put("tiny_id", Integer.toString(tinyId));
		
		return http.get(POSTS_SHOW, args);
	}

	public JSONObject postsAll(int after) {
		HttpRequest http = new HttpRequest();
		
		Map<String,String> args = new HashMap<String,String>();
		args.put("after", Integer.toString(after));
		
		return http.get(POSTS_ALL, args);
	}

	public JSONObject postsList(String postType, String sort, int page) {
		HttpRequest http = new HttpRequest();

		Map<String,String> args = new HashMap<String,String>();
		args.put("post_type", postType);
		args.put("sort", sort);
		args.put("after", Integer.toString(page));
		
		return http.get(POSTS_LIST, args);
	}

	public JSONObject postComments(int id, int tinyId) {
		HttpRequest http = new HttpRequest();
		
		Map<String,String> args = new HashMap<String,String>();
		args.put("id", Integer.toString(id));
		args.put("tiny_id", Integer.toString(tinyId));
		
		return http.get(POST_COMMENTS, args);
	}
}
