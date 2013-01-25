package com.nitindhar.forrst;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.json.JSONException;
import org.testng.annotations.Test;

import com.google.common.base.Optional;
import com.nitindhar.forrst.model.Auth;
import com.nitindhar.forrst.model.Comment;
import com.nitindhar.forrst.model.Notification;
import com.nitindhar.forrst.model.Post;
import com.nitindhar.forrst.model.Stat;
import com.nitindhar.forrst.model.User;
import com.nitindhar.forrst.util.ForrstAuthenticationException;

public class ForrstAPIClientTests {

    private final ForrstAPI forrst = new ForrstAPIClient();

    @Test (groups={"skip"})
    public void testStats() throws MalformedURLException {
        Stat stat = forrst.stats();
        TestCase.assertNotNull(stat);
        TestCase.assertTrue(stat.getRateLimit() == ForrstAPI.RATE_LIMIT);
        TestCase.assertTrue(stat.getCallsMade() >= 0);
    }

    /*
     * Before testing,
     *
     * Replace "skip" with "ready"
     * Replace EMAIL_OR_USERNAME with email/username
     * Replace PASSWORD with PASSWORD
     */
    @Test (groups={"skip"})
    public void testNotifications() throws MalformedURLException, JSONException, ForrstAuthenticationException {
        Optional<Auth> auth = forrst.usersAuth("EMAIL_OR_USERNAME", "PASSWORD");
        List<Notification> notifications = forrst.notifications(auth.get().getAccessToken(), null);
        TestCase.assertNotNull(notifications);
        TestCase.assertTrue(notifications.size() == 1 ? true : false);
    }

    /*
     * Before testing,
     *
     * Replace "skip" with "ready"
     * Replace EMAIL_OR_USERNAME with email/username
     * Replace PASSWORD with PASSWORD
     */
    @Test (groups={"skip"})
    public void testUsersAuth() throws MalformedURLException, JSONException, ForrstAuthenticationException {
        Optional<Auth> auth = forrst.usersAuth("EMAIL_OR_USERNAME", "PASSWORD");
        TestCase.assertNotNull(auth);
        TestCase.assertNotNull(auth.get().getUserId());
        TestCase.assertTrue(auth.get().getAccessToken() != null && auth.get().getAccessToken().length() > 0);
    }

    @Test (groups={"ready"})
    public void testUsersInfo() throws MalformedURLException, JSONException {
        Map<String,String> params = new HashMap<String,String>();
        params.put("id", "29470");
        User user = forrst.usersInfo(params);
        TestCase.assertNotNull(user);
        TestCase.assertNotNull(user.getPhoto());

        params.remove("id");
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
        Map<String,String> params = new HashMap<String,String>();
        List<Post> posts = forrst.postsAll(params);
        TestCase.assertNotNull(posts);
        TestCase.assertTrue(posts.size() > 0 ? true : false);
    }

    @Test (groups={"ready"})
    public void testPostsList() throws MalformedURLException, JSONException {
        Map<String,String> params = new HashMap<String,String>();
        List<Post> posts = forrst.postsList("question", params);
        TestCase.assertNotNull(posts);
        TestCase.assertTrue(posts.size() > 0 ? true : false);
    }

    /*
     * Before testing,
     *
     * Replace "skip" with "ready"
     * Replace EMAIL_OR_USERNAME with email or username
     * Replace PASSWORD with PASSWORD
     */
    @Test (groups={"skip"})
    public void testPostCommentsByID() throws MalformedURLException, JSONException, ForrstAuthenticationException {
        Optional<Auth> auth = forrst.usersAuth("EMAIL_OR_USERNAME", "PASSWORD");
        List<Comment> comments = forrst.postComments(auth.get().getAccessToken(), 124269);
        TestCase.assertNotNull(comments);
        TestCase.assertTrue((comments.size() > 0) ? true : false);
    }

}