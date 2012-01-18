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
import com.forrst.api.util.ForrstAuthenticationException;

public class ForrstAPIClientTests {

	private ForrstAPI forrst = new ForrstAPIClient();
	
	@Test (groups={"ready"})
	public void testStats() throws MalformedURLException {
		JSONObject json = forrst.stats();
		TestCase.assertEquals(true, json.has("rate_limit"));
		TestCase.assertEquals(true, json.has("calls_made"));
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
	public void testUsersInfoById() throws MalformedURLException, JSONException {
		JSONObject json = forrst.usersInfo(29470);
		TestCase.assertEquals(true, json.has("id"));
		TestCase.assertEquals(true, json.has("username"));
		TestCase.assertEquals(true, json.has("name"));
		TestCase.assertEquals(true, json.has("url"));
		TestCase.assertEquals(true, json.has("posts"));
		TestCase.assertEquals(true, json.has("comments"));
		TestCase.assertEquals(true, json.has("likes"));
		TestCase.assertEquals(true, json.has("followers"));
		TestCase.assertEquals(true, json.has("following"));
		TestCase.assertEquals(true, json.has("photos"));
		TestCase.assertEquals(true, json.has("bio"));
		TestCase.assertEquals(true, json.has("is_a"));
		TestCase.assertEquals(true, json.has("homepage_url"));
		TestCase.assertEquals(true, json.has("twitter"));
		TestCase.assertEquals(true, json.has("in_directory"));
		TestCase.assertEquals(true, json.has("tag_string"));
		json = json.getJSONObject("photos");
		TestCase.assertEquals(true, json.has("xl_url"));
		TestCase.assertEquals(true, json.has("large_url"));
		TestCase.assertEquals(true, json.has("medium_url"));
		TestCase.assertEquals(true, json.has("small_url"));
		TestCase.assertEquals(true, json.has("thumb_url"));
	}
	
	@Test (groups={"ready"})
	public void testUsersInfoByUsername() throws MalformedURLException, JSONException {
		JSONObject json = forrst.usersInfo("nitindhar7");
		TestCase.assertEquals(true, json.has("id"));
		TestCase.assertEquals(true, json.has("username"));
		TestCase.assertEquals(true, json.has("name"));
		TestCase.assertEquals(true, json.has("url"));
		TestCase.assertEquals(true, json.has("posts"));
		TestCase.assertEquals(true, json.has("comments"));
		TestCase.assertEquals(true, json.has("likes"));
		TestCase.assertEquals(true, json.has("followers"));
		TestCase.assertEquals(true, json.has("following"));
		TestCase.assertEquals(true, json.has("photos"));
		TestCase.assertEquals(true, json.has("bio"));
		TestCase.assertEquals(true, json.has("is_a"));
		TestCase.assertEquals(true, json.has("homepage_url"));
		TestCase.assertEquals(true, json.has("twitter"));
		TestCase.assertEquals(true, json.has("in_directory"));
		TestCase.assertEquals(true, json.has("tag_string"));
		json = json.getJSONObject("photos");
		TestCase.assertEquals(true, json.has("xl_url"));
		TestCase.assertEquals(true, json.has("large_url"));
		TestCase.assertEquals(true, json.has("medium_url"));
		TestCase.assertEquals(true, json.has("small_url"));
		TestCase.assertEquals(true, json.has("thumb_url"));
	}
	
	@Test (groups={"ready"})
	public void testUsersPostsById() throws MalformedURLException, JSONException {
		JSONObject json = forrst.userPosts(29470, null);
		TestCase.assertEquals(true, json.has("posts"));
		JSONArray jsonArray = json.getJSONArray("posts");
		TestCase.assertEquals(true, jsonArray.length() > 0 ? true : false);
		json = (JSONObject) jsonArray.get(0);
		TestCase.assertEquals(true, json.has("id"));
		TestCase.assertEquals(true, json.has("tiny_id"));
		TestCase.assertEquals(true, json.has("post_type"));
		TestCase.assertEquals(true, json.has("post_url"));
		TestCase.assertEquals(true, json.has("created_at"));
		TestCase.assertEquals(true, json.has("updated_at"));
		TestCase.assertEquals(true, json.has("user"));
		TestCase.assertEquals(true, json.has("published"));
		TestCase.assertEquals(true, json.has("public"));
		TestCase.assertEquals(true, json.has("title"));
		TestCase.assertEquals(true, json.has("url"));
		TestCase.assertEquals(true, json.has("content"));
		TestCase.assertEquals(true, json.has("description"));
		TestCase.assertEquals(true, json.has("formatted_description"));
		TestCase.assertEquals(true, json.has("like_count"));
		TestCase.assertEquals(true, json.has("comment_count"));
	}

	@Test (groups={"ready"})
	public void testUsersPostsByUsername() throws MalformedURLException, JSONException {
		JSONObject json = forrst.userPosts("nitindhar7", null);
		TestCase.assertEquals(true, json.has("posts"));
		JSONArray jsonArray = json.getJSONArray("posts");
		TestCase.assertEquals(true, jsonArray.length() > 0 ? true : false);
		json = (JSONObject) jsonArray.get(0);
		TestCase.assertEquals(true, json.has("id"));
		TestCase.assertEquals(true, json.has("tiny_id"));
		TestCase.assertEquals(true, json.has("post_type"));
		TestCase.assertEquals(true, json.has("post_url"));
		TestCase.assertEquals(true, json.has("created_at"));
		TestCase.assertEquals(true, json.has("updated_at"));
		TestCase.assertEquals(true, json.has("user"));
		TestCase.assertEquals(true, json.has("published"));
		TestCase.assertEquals(true, json.has("public"));
		TestCase.assertEquals(true, json.has("title"));
		TestCase.assertEquals(true, json.has("url"));
		TestCase.assertEquals(true, json.has("content"));
		TestCase.assertEquals(true, json.has("description"));
		TestCase.assertEquals(true, json.has("formatted_description"));
		TestCase.assertEquals(true, json.has("like_count"));
		TestCase.assertEquals(true, json.has("comment_count"));
	}
	
	@Test (groups={"ready"})
	public void testPostsShowById() throws MalformedURLException {
		JSONObject json = forrst.postsShow(45114);
		TestCase.assertEquals(true, json.has("id"));
		TestCase.assertEquals(true, json.has("tiny_id"));
		TestCase.assertEquals(true, json.has("post_type"));
		TestCase.assertEquals(true, json.has("post_url"));
		TestCase.assertEquals(true, json.has("created_at"));
		TestCase.assertEquals(true, json.has("updated_at"));
		TestCase.assertEquals(true, json.has("user"));
		TestCase.assertEquals(true, json.has("published"));
		TestCase.assertEquals(true, json.has("public"));
		TestCase.assertEquals(true, json.has("title"));
		TestCase.assertEquals(true, json.has("url"));
		TestCase.assertEquals(true, json.has("content"));
		TestCase.assertEquals(true, json.has("description"));
		TestCase.assertEquals(true, json.has("formatted_description"));
		TestCase.assertEquals(true, json.has("like_count"));
		TestCase.assertEquals(true, json.has("comment_count"));
		TestCase.assertEquals(true, json.has("tag_string"));
		TestCase.assertEquals(true, json.has("tags"));
	}
	
	@Test (groups={"ready"})
	public void testPostsShowByTinyID() throws MalformedURLException {
		JSONObject json = forrst.postsShow("BMH");
		TestCase.assertEquals(true, json.has("id"));
		TestCase.assertEquals(true, json.has("tiny_id"));
		TestCase.assertEquals(true, json.has("post_type"));
		TestCase.assertEquals(true, json.has("post_url"));
		TestCase.assertEquals(true, json.has("created_at"));
		TestCase.assertEquals(true, json.has("updated_at"));
		TestCase.assertEquals(true, json.has("user"));
		TestCase.assertEquals(true, json.has("published"));
		TestCase.assertEquals(true, json.has("public"));
		TestCase.assertEquals(true, json.has("title"));
		TestCase.assertEquals(true, json.has("url"));
		TestCase.assertEquals(true, json.has("content"));
		TestCase.assertEquals(true, json.has("description"));
		TestCase.assertEquals(true, json.has("formatted_description"));
		TestCase.assertEquals(true, json.has("like_count"));
		TestCase.assertEquals(true, json.has("comment_count"));
		TestCase.assertEquals(true, json.has("tag_string"));
		TestCase.assertEquals(true, json.has("tags"));
	}
	
	@Test (groups={"ready"})
	public void testPostsAll() throws MalformedURLException, JSONException {
		JSONObject json = forrst.postsAll();
		TestCase.assertEquals(true, json.has("posts"));
		JSONArray jsonArray = json.getJSONArray("posts");
		TestCase.assertEquals(true, jsonArray.length() > 0 ? true : false);
	}
	
	@Test (groups={"ready"})
	public void testPostsAllAfter() throws MalformedURLException, JSONException {
		JSONObject json = forrst.postsAll(46000);
		TestCase.assertEquals(true, json.has("posts"));
		JSONArray jsonArray = json.getJSONArray("posts");
		TestCase.assertEquals(true, jsonArray.length() > 0 ? true : false);
		json = jsonArray.getJSONObject(0);
		TestCase.assertEquals(true, json.has("id"));
		TestCase.assertEquals(true, json.has("tiny_id"));
		TestCase.assertEquals(true, json.has("post_type"));
		TestCase.assertEquals(true, json.has("post_url"));
		TestCase.assertEquals(true, json.has("created_at"));
		TestCase.assertEquals(true, json.has("updated_at"));
		TestCase.assertEquals(true, json.has("user"));
		TestCase.assertEquals(true, json.has("published"));
		TestCase.assertEquals(true, json.has("public"));
		TestCase.assertEquals(true, json.has("title"));
		TestCase.assertEquals(true, json.has("url"));
		TestCase.assertEquals(true, json.has("content"));
		TestCase.assertEquals(true, json.has("description"));
		TestCase.assertEquals(true, json.has("formatted_description"));
		TestCase.assertEquals(true, json.has("like_count"));
		TestCase.assertEquals(true, json.has("comment_count"));
		// TODO: ensure that no post has an ID of < 46000
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
