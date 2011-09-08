package com.forrst.api;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class ForrstAPIClient implements ForrstAPI {
	
	private static HttpRequest http;
	
	public ForrstAPIClient() {
		http = new HttpRequest();
	}

	public JSONObject stats() {
		return http.get(STATS_URI);
	}

	public JSONObject usersAuth(String emailOrUsername, String password) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("email_or_username", emailOrUsername);
		params.put("password", password);
		
		return http.get(USERS_AUTH_URI, params);
	}

	public JSONObject usersInfo(int id) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("id", Integer.toString(id));
		
		return http.get(USERS_INFO_URI, params);
	}
	
	public JSONObject usersInfo(String username) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("username", username);
		
		return http.get(USERS_INFO_URI, params);
	}

	public JSONObject userPosts(int id, Map<String,String> options) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("id", Integer.toString(id));
		
		if(options != null) {
			if(options.containsKey("type")) {
				params.put("type", options.get("type"));
			}
			if(options.containsKey("limit")) {
				params.put("limit", options.get("limit"));
			}
			if(options.containsKey("after")) {
				params.put("after", options.get("after"));
			}
		}
		
		return http.get(USER_POSTS_URI, params);
	}
	
	public JSONObject userPosts(String username, Map<String,String> options) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("username", username);

		if(options != null) {
			if(options.containsKey("type")) {
				params.put("type", options.get("type"));
			}
			if(options.containsKey("limit")) {
				params.put("limit", options.get("limit"));
			}
			if(options.containsKey("after")) {
				params.put("after", options.get("after"));
			}
		}
		
		return http.get(USER_POSTS_URI, params);
	}

	public JSONObject postsShow(int id) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("id", Integer.toString(id));
		
		return http.get(POSTS_SHOW_URI, params);
	}
	
	public JSONObject postsShow(String tinyId) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("tiny_id", tinyId);
		
		return http.get(POSTS_SHOW_URI, params);
	}

	public JSONObject postsAll() {
		return http.get(POSTS_ALL_URI);
	}
	
	public JSONObject postsAll(int after) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("after", Integer.toString(after));
		
		return http.get(POSTS_ALL_URI, params);
	}

	public JSONObject postsList(String postType, Map<String,String> options) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("post_type", postType);
		
		if(options != null) {
			if(options.containsKey("sort")) {
				params.put("sort", options.get("sort"));
			}
			if(options.containsKey("after")) {
				params.put("after", options.get("after"));
			}
		}
		
		return http.get(POSTS_LIST_URI, params);
	}

	public JSONObject postComments(int id) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("id", Integer.toString(id));
		
		return http.get(POST_COMMENTS_URI, params);
	}
	
	public JSONObject postComments(String tinyId) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("tiny_id", tinyId);
		
		return http.get(POST_COMMENTS_URI, params);
	}
}
