package com.forrst.api;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.forrst.api.model.Auth;
import com.forrst.api.model.Comment;
import com.forrst.api.util.ForrstAuthenticationException;

public class ForrstAPIClient implements ForrstAPI {

	private static HttpRequest http;
	
	public ForrstAPIClient() {
		http = new HttpRequest();
	}

	public JSONObject stats() {
		return http.get(Endpoint.getInstance().STATS_URI);
	}

	public JSONObject notifications(String accessToken, Map<String,String> options) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("access_token", accessToken);
		
		if(options != null) {
			if(options.containsKey("grouped")) {
				params.put("grouped", options.get("grouped"));
			}
		}
		
		return http.get(Endpoint.getInstance().NOTIFICATIONS_URI, params);
	}
	
	public Auth usersAuth(String emailOrUsername, String password) throws ForrstAuthenticationException {
	    Auth auth = null;

		try {
		    Map<String,String> params = new HashMap<String,String>();
	        params.put("email_or_username", emailOrUsername);
	        params.put("password", password);
	        
	        JSONObject json = http.post(Endpoint.getInstance().USERS_AUTH_URI, params);

	        auth = new Auth();
	        auth.setAccessToken(json.getString("token"));
	        auth.setUserId(json.getInt("user_id"));
        } catch (JSONException e) {
            auth = null;
            throw new RuntimeException("Error authenticating with Forrst", e);
        }
		
		return auth;
	}

	public JSONObject usersInfo(int id) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("id", Integer.toString(id));
		
		return http.get(Endpoint.getInstance().USERS_INFO_URI, params);
	}
	
	public JSONObject usersInfo(String username) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("username", username);
		
		return http.get(Endpoint.getInstance().USERS_INFO_URI, params);
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
		
		return http.get(Endpoint.getInstance().USER_POSTS_URI, params);
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
		
		return http.get(Endpoint.getInstance().USER_POSTS_URI, params);
	}

	public JSONObject postsShow(int id) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("id", Integer.toString(id));
		
		return http.get(Endpoint.getInstance().POSTS_SHOW_URI, params);
	}
	
	public JSONObject postsShow(String tinyId) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("tiny_id", tinyId);
		
		return http.get(Endpoint.getInstance().POSTS_SHOW_URI, params);
	}

	public JSONObject postsAll() {
		return http.get(Endpoint.getInstance().POSTS_ALL_URI);
	}
	
	public JSONObject postsAll(int after) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("after", Integer.toString(after));
		
		return http.get(Endpoint.getInstance().POSTS_ALL_URI, params);
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
		
		return http.get(Endpoint.getInstance().POSTS_LIST_URI, params);
	}

	public List<Comment> postComments(String accessToken, int id) {
	    List<Comment> comments = null;

		try {
		    Map<String,String> params = new HashMap<String,String>();
	        params.put("access_token", accessToken);
	        params.put("id", Integer.toString(id));
		    
		    JSONObject json = http.get(Endpoint.getInstance().POST_COMMENTS_URI, params);
		    JSONArray commentsJSONArray = (JSONArray) json.get("comments");
		    
		    comments = new ArrayList<Comment>();
		    
		    for(int commentCount = 0; commentCount < commentsJSONArray.length(); commentCount++) {
                JSONObject commentJSON = commentsJSONArray.getJSONObject(commentCount);
                
                Comment comment = new Comment();
                comment.setId(commentJSON.getInt("id"));
                comment.setUserName(commentJSON.getJSONObject("user").getString("name"));
                comment.setBody(commentJSON.getString("body"));
                comment.setCreatedAt(Timestamp.valueOf(commentJSON.getString("created_at")));
                comment.setUserIconUrl(commentJSON.getJSONObject("user").getJSONObject("photos").getString("thumb_url"));
                comments.add(comment);
                
                if (commentJSON.has("replies")) {
                    JSONArray repliesJSONArray = (JSONArray) commentJSON.get("replies");
                    for(int replyCount = 0; replyCount < repliesJSONArray.length(); replyCount++) {
                        JSONObject replyJSON = repliesJSONArray.getJSONObject(replyCount);

                        Comment replyComment = new Comment();
                        replyComment.setId(replyJSON.getInt("id"));
                        replyComment.setUserName(replyJSON.getJSONObject("user").getString("name"));
                        replyComment.setBody(replyJSON.getString("body"));
                        replyComment.setCreatedAt(Timestamp.valueOf(replyJSON.getString("created_at")));
                        replyComment.setUserIconUrl(replyJSON.getJSONObject("user").getJSONObject("photos").getString("thumb_url"));
                        comments.add(replyComment);
                    }
                }
            }
        } catch (JSONException e) {
            comments = null;
            throw new RuntimeException("Error fetching comments from Forrst", e);
        }

		return comments;
	}
	
	public Map<String,String> getEndpointsURIs() {
		Map<String,String> endpoints = new HashMap<String,String>();
		
		endpoints.put("stats", Endpoint.getInstance().STATS_URI);
		endpoints.put("users/auth", Endpoint.getInstance().USERS_AUTH_URI);
		endpoints.put("users/info", Endpoint.getInstance().USERS_INFO_URI);
		endpoints.put("user/posts", Endpoint.getInstance().USER_POSTS_URI);
		endpoints.put("posts/show", Endpoint.getInstance().POSTS_SHOW_URI);
		endpoints.put("posts/all", Endpoint.getInstance().POSTS_ALL_URI);
		endpoints.put("posts/list", Endpoint.getInstance().POSTS_LIST_URI);
		endpoints.put("post/comments", Endpoint.getInstance().POST_COMMENTS_URI);
		
		return endpoints;
	}
}
