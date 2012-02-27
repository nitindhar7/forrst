package com.nitindhar.forrst;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nitindhar.forrst.model.Auth;
import com.nitindhar.forrst.model.Comment;
import com.nitindhar.forrst.model.Notification;
import com.nitindhar.forrst.model.Post;
import com.nitindhar.forrst.model.Stat;
import com.nitindhar.forrst.model.User;
import com.nitindhar.forrst.util.ForrstAuthenticationException;

public class ForrstAPIClient implements ForrstAPI {

	private static final HttpRequest http = new HttpRequest();
	private static final ObjectMapper mapper = new ObjectMapper();

	public Stat stats() {
        Stat stat = null;
        
        try {
            stat = mapper.readValue(http.get(Endpoint.getInstance().STATS_URI).toString(), Stat.class);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching stats from Forrst", e);
        }

        return stat;
    }

	@SuppressWarnings("unchecked")
    public List<Notification> notifications(String accessToken, Map<String,String> options) {
	    List<Notification> notifications = new ArrayList<Notification>();
		Map<String,String> params = new HashMap<String,String>();
		params.put("access_token", accessToken);
		
		if(options != null) {
			if(options.containsKey("grouped")) {
				params.put("grouped", options.get("grouped"));
			}
		}
		
		try {
		    JSONObject json = http.get(Endpoint.getInstance().NOTIFICATIONS_URI, params);
		    
		    if(json.get("items") instanceof JSONArray) {
    		    JSONArray jsonItemsArray = (JSONArray) json.get("items");
    		    if(jsonItemsArray.length() == 0)
    		        return notifications;
		    }
		    
		    JSONObject itemsJson = json.getJSONObject("items");
		    
		    Iterator<String> itemsIterator = itemsJson.keys();
		    while(itemsIterator.hasNext()) {
		        String itemKey = (String) itemsIterator.next();
		        JSONObject notificationTypeJson = (JSONObject) itemsJson.get(itemKey);

		        Iterator<String> notificationTypeIterator = notificationTypeJson.keys();
		        while(notificationTypeIterator.hasNext()) {
		            String notificationTypeKey = (String) notificationTypeIterator.next();
		            Notification notification = mapper.readValue(notificationTypeJson.get(notificationTypeKey).toString(), Notification.class);
                    notifications.add(notification);
		        }
		    }
		} catch (Exception e) {
            throw new RuntimeException("Error fetching notifications from Forrst", e);
        }

		return notifications;
	}
	
	public Auth usersAuth(String emailOrUsername, String password) throws ForrstAuthenticationException {
        Auth auth = null;
        
        try {
            Map<String,String> params = new HashMap<String,String>();
            params.put("email_or_username", emailOrUsername);
            params.put("password", password);
            
            auth = mapper.readValue(http.post(Endpoint.getInstance().USERS_AUTH_URI, params).toString(), Auth.class);
        } catch (Exception e) {
            throw new RuntimeException("Error authenticating with Forrst", e);
        }
        
        return auth;
    }

	public User usersInfo(Map<String,String> userInfo) {
		User user = null;
		
		try {
		    if(userInfo.containsKey("id") || userInfo.containsKey("username"))
		        user = mapper.readValue(http.get(Endpoint.getInstance().USERS_INFO_URI, userInfo).toString(), User.class);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching user data", e);
        }

		return user;
	}

	public List<Post> userPosts(Map<String,String> userInfo, Map<String,String> options) {
	    List<Post> posts = new ArrayList<Post>();
	    
		Map<String,String> params = new HashMap<String,String>();
		if(userInfo.containsKey("id"))
		    params.put("id", userInfo.get("id"));
		if(userInfo.containsKey("username"))
            params.put("username", userInfo.get("username"));
		if(options != null) {
			if(options.containsKey("type"))
				params.put("type", options.get("type"));
			if(options.containsKey("limit"))
				params.put("limit", options.get("limit"));
			if(options.containsKey("after"))
				params.put("after", options.get("after"));
		}
		
		try {
	        JSONObject json = http.get(Endpoint.getInstance().USER_POSTS_URI, params);
            JSONArray postsJsonArray = (JSONArray) json.get("posts");
            
            for(int i = 0; i < postsJsonArray.length(); i++) {
                Post post = mapper.readValue(postsJsonArray.get(i).toString(), Post.class);
                posts.add(post);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching users posts", e);
        }
		
		return posts;
	}

	public Post postsShow(int id) {
	    Post post = null;
	    
	    try {
	        Map<String,String> params = new HashMap<String,String>();
	        params.put("id", Integer.toString(id));
	        
	        post = mapper.readValue(http.get(Endpoint.getInstance().POSTS_SHOW_URI, params).toString(), Post.class);            
	    } catch (Exception e) {
            throw new RuntimeException("Error fetching post", e);
        }
		
		return post;
	}

	public List<Post> postsAll(Map<String,String> options) {
	    List<Post> posts = new ArrayList<Post>();
	    Map<String,String> params = new HashMap<String,String>();

	    if(options != null) {
            if(options.containsKey("after")) {
                params.put("after", options.get("after"));
            }
        }

	    try {
            JSONObject json = http.get(Endpoint.getInstance().POSTS_ALL_URI, params);
            JSONArray postsJsonArray = (JSONArray) json.get("posts");
            
            for(int i = 0; i < postsJsonArray.length(); i++) {
                Post post = mapper.readValue(postsJsonArray.get(i).toString(), Post.class);
                posts.add(post);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching users posts", e);
        }
	    
		return posts;
	}

	public List<Post> postsList(String postType, Map<String,String> options) {
	    List<Post> posts = new ArrayList<Post>();
	    
		Map<String,String> params = new HashMap<String,String>();
		params.put("post_type", postType);
		
		if(options != null) {
			if(options.containsKey("sort")) {
				params.put("sort", options.get("sort"));
			}
			if(options.containsKey("page")) {
				params.put("page", options.get("page"));
			}
		}
		
		try {
            JSONObject json = http.get(Endpoint.getInstance().POSTS_LIST_URI, params);
            JSONArray postsJsonArray = (JSONArray) json.get("posts");
            
            for(int i = 0; i < postsJsonArray.length(); i++) {
                Post post = mapper.readValue(postsJsonArray.get(i).toString(), Post.class);
                posts.add(post);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching users posts", e);
        }
		
		return posts;
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
            throw new RuntimeException("Error fetching comments from Forrst", e);
        }

		return comments;
	}
}
