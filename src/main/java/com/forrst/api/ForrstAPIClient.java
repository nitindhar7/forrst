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
		
		return http.get(USERS_AUTH_URI, args);
	}

	public JSONObject usersInfo(int id) {
		HttpRequest http = new HttpRequest();
		
		Map<String,String> args = new HashMap<String,String>();
		args.put("id", Integer.toString(id));
		
		return http.get(USERS_INFO_URI, args);
	}
	
	public JSONObject usersInfo(String username) {
		HttpRequest http = new HttpRequest();
		
		Map<String,String> args = new HashMap<String,String>();
		args.put("username", username);
		
		return http.get(USERS_INFO_URI, args);
	}

	public JSONObject userPosts(int id, Map<String,String> options) {
		HttpRequest http = new HttpRequest();
		
		Map<String,String> args = new HashMap<String,String>();
		args.put("id", Integer.toString(id));
		
		if(options != null) {
			if(options.containsKey("type")) {
				args.put("type", options.get("type"));
			}
			if(options.containsKey("limit")) {
				args.put("limit", options.get("limit"));
			}
			if(options.containsKey("after")) {
				args.put("after", options.get("after"));
			}
		}
		
		return http.get(USER_POSTS_URI, args);
	}
	
	public JSONObject userPosts(String username, Map<String,String> options) {
		HttpRequest http = new HttpRequest();
		
		Map<String,String> args = new HashMap<String,String>();
		args.put("username", username);

		if(options != null) {
			if(options.containsKey("type")) {
				args.put("type", options.get("type"));
			}
			if(options.containsKey("limit")) {
				args.put("limit", options.get("limit"));
			}
			if(options.containsKey("after")) {
				args.put("after", options.get("after"));
			}
		}
		
		return http.get(USER_POSTS_URI, args);
	}

	public JSONObject postsShow(int id) {
		HttpRequest http = new HttpRequest();
		
		Map<String,String> args = new HashMap<String,String>();
		args.put("id", Integer.toString(id));
		
		return http.get(POSTS_SHOW_URI, args);
	}
	
	public JSONObject postsShow(String tinyId) {
		HttpRequest http = new HttpRequest();
		
		Map<String,String> args = new HashMap<String,String>();
		args.put("tiny_id", tinyId);
		
		return http.get(POSTS_SHOW_URI, args);
	}

	public JSONObject postsAll() {
		HttpRequest http = new HttpRequest();
		
		return http.get(POSTS_ALL_URI);
	}
	
	public JSONObject postsAll(int after) {
		HttpRequest http = new HttpRequest();
		
		Map<String,String> args = new HashMap<String,String>();
		args.put("after", Integer.toString(after));
		
		return http.get(POSTS_ALL_URI, args);
	}

	public JSONObject postsList(String postType, Map<String,String> options) {
		HttpRequest http = new HttpRequest();

		Map<String,String> args = new HashMap<String,String>();
		args.put("post_type", postType);
		
		if(options != null) {
			if(options.containsKey("sort")) {
				args.put("sort", options.get("sort"));
			}
			if(options.containsKey("after")) {
				args.put("after", options.get("after"));
			}
		}
		
		return http.get(POSTS_LIST_URI, args);
	}

	public JSONObject postComments(int id) {
		HttpRequest http = new HttpRequest();
		
		Map<String,String> args = new HashMap<String,String>();
		args.put("id", Integer.toString(id));
		
		return http.get(POST_COMMENTS_URI, args);
	}
	
	public JSONObject postComments(String tinyId) {
		HttpRequest http = new HttpRequest();
		
		Map<String,String> args = new HashMap<String,String>();
		args.put("tiny_id", tinyId);
		
		return http.get(POST_COMMENTS_URI, args);
	}
}
