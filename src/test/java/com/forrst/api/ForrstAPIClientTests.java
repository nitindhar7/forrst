package com.forrst.api;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.forrst.api.model.Auth;
import com.forrst.api.model.Comment;
import com.forrst.api.model.Post;
import com.forrst.api.model.Stat;
import com.forrst.api.model.User;
import com.forrst.api.util.ForrstAuthenticationException;

public class ForrstAPIClientTests {

	private ForrstAPI forrst = new ForrstAPIClient();
	
	@Test (groups={"ready"})
	public void testStats() throws MalformedURLException {
		Stat stat = forrst.stats();
		TestCase.assertNotNull(stat);
	}
	
	/*
	 * Before testing,
	 * 
	 * Replace "skip" with "ready"
	 * Replace EMAIL_OR_USERNAME with email/username
	 * Replace PASSWORD with password
	 */
	@Test (groups={"skip"})
	public void testNotifications() throws MalformedURLException, JSONException, ForrstAuthenticationException {
		Auth auth = forrst.usersAuth("EMAIL_OR_USERNAME", "PASSWORD");
		JSONObject json = forrst.notifications(auth.getAccessToken(), null);
		TestCase.assertEquals(true, json.has("items"));
		TestCase.assertEquals(true, json.has("view_url_format"));
	}
	
	/*
	 * Before testing,
	 * 
	 * Replace "skip" with "ready"
	 * Replace EMAIL_OR_USERNAME with email/username
	 * Replace PASSWORD with password
	 */
	@Test (groups={"skip"})
	public void testUsersAuth() throws MalformedURLException, JSONException, ForrstAuthenticationException {
		Auth auth = forrst.usersAuth("EMAIL_OR_USERNAME", "PASSWORD");
		TestCase.assertNotNull(auth);
	}
	
	@Test (groups={"ready"})
	public void testUsersInfo() throws MalformedURLException, JSONException {
	    Map<String,String> params = new HashMap<String,String>();
	    params.put("id", "29470");
		User user = forrst.usersInfo(params);
		TestCase.assertNotNull(user);
		TestCase.assertNotNull(user.getPhoto());
		
		params.put("username", "nitindhar7");
        user = forrst.usersInfo(params);
        TestCase.assertNotNull(user);
        TestCase.assertNotNull(user.getPhoto());
	}
	
	@Test (groups={"ready"})
	public void testUsersPostsById() throws MalformedURLException, JSONException {
	    Map<String,String> params = new HashMap<String,String>();
	    params.put("id", "29470");
		List<Post> posts = forrst.userPosts(params, null);
		TestCase.assertNotNull(posts);
		TestCase.assertTrue(posts.size() > 0 ? true : false);
	}
	
	@Test (groups={"ready"})
	public void testPostsShowById() throws MalformedURLException {
		Post post = forrst.postsShow(45114);
		TestCase.assertNotNull(post);
		TestCase.assertNotNull(post.getUser());
		TestCase.assertNotNull(post.getUser().getPhoto());
	}

	@Test (groups={"ready"})
	public void testPostsAll() throws MalformedURLException, JSONException {
		JSONObject json = forrst.postsAll(null);
		TestCase.assertEquals(true, json.has("posts"));
		JSONArray jsonArray = json.getJSONArray("posts");
		TestCase.assertEquals(true, jsonArray.length() > 0 ? true : false);
	}
	
	@Test (groups={"ready"})
	public void testPostsList() throws MalformedURLException, JSONException {
		JSONObject json = forrst.postsList("question", null);
		TestCase.assertEquals(true, json.has("posts"));
		JSONArray jsonArray = json.getJSONArray("posts");
		TestCase.assertEquals(true, jsonArray.length() > 0 ? true : false);
	}

	/*
	 * Before testing,
	 * 
	 * Replace "skip" with "ready"
	 * Replace EMAIL_OR_USERNAME with email or username
	 * Replace PASSWORD with password
	 */
	@Test (groups={"skip"})
	public void testPostCommentsByID() throws MalformedURLException, JSONException, ForrstAuthenticationException {
		Auth auth = forrst.usersAuth("EMAIL_OR_USERNAME", "PASSWORD");
		List<Comment> comments = forrst.postComments(auth.getAccessToken(), 124269);
		TestCase.assertNotNull(comments);
		TestCase.assertTrue((comments.size() > 0) ? true : false);
	}
	
	@Test (groups={"toBeFixed"})
	public void testRateLimit() {
	}
	
	@Test (groups={"ready"})
	public void testGetEndpointURIs() {		
		Map<String,String> endpoints = new HashMap<String,String>();
		endpoints.put("stats", Endpoint.getInstance().STATS_URI);
		endpoints.put("users/auth", Endpoint.getInstance().USERS_AUTH_URI);
		endpoints.put("users/info", Endpoint.getInstance().USERS_INFO_URI);
		endpoints.put("user/posts", Endpoint.getInstance().USER_POSTS_URI);
		endpoints.put("posts/show", Endpoint.getInstance().POSTS_SHOW_URI);
		endpoints.put("posts/all", Endpoint.getInstance().POSTS_ALL_URI);
		endpoints.put("posts/list", Endpoint.getInstance().POSTS_LIST_URI);
		endpoints.put("post/comments", Endpoint.getInstance().POST_COMMENTS_URI);
		TestCase.assertEquals(endpoints, forrst.getEndpointsURIs());
	}

}
