package com.forrst.api;

import java.util.List;
import java.util.Map;

import com.forrst.api.model.Auth;
import com.forrst.api.model.Comment;
import com.forrst.api.model.Notification;
import com.forrst.api.model.Post;
import com.forrst.api.model.Stat;
import com.forrst.api.model.User;
import com.forrst.api.util.ForrstAuthenticationException;

public interface ForrstAPI {

    /**
     * Maximum calls per hour throttled by Forrst (resets each hour).
     */
    public int RATE_LIMIT = 10000;

    /**
     * Returns stats about your API usage. Note: does
     * not count against your rate limit.
     *
     * @return Stat object containing current rate limit & calls made
     */
    public Stat stats();
    
    /**
     * Return notification items for the authenticating user.
     * Most items have an associated redirect URL that will
     * take the user to the appropriate post/comment/etc. and
     * clear the associated notification. Construct the URLs
     * using the view_url_format format (provided in the
     * response), replacing ID with the ID of the desired
     * notification. Also note that not every type of
     * notification (currently for likes, comments [replies,
     * subscription-based, on your post], mentions, jobs, and
     * follows) will have the same fields present for data.
     * 
     * @param accessToken Token obtained when the user is authenticated
     * @param options is a map & can contain
     *        grouped: [optional] Boolean indicating whether
     *                 to return items logically grouped by
     *                 type 
     * @return List of notifications
     */
    public List<Notification> notifications(String accessToken, Map<String,String> options);

    /**
     * User authentication. Provide an email/username
     * and password and get the access token back
     *
     * @param emailOrUsername Email/Username
     * @param password Password
     * @return Auth object containing access token and user id
     * @throws ForrstAuthenticationException when authentication fails
     */
    public Auth usersAuth(String emailOrUsername, String password) throws ForrstAuthenticationException;
    
    /**
     * Given a property identifying a user return a user
     * 
     * @param userInfo Map containing user id or username
     * @return User data
     */
    public User usersInfo(Map<String,String> userInfo);

    /**
     * Returns a user's posts
     * 
     * @param id User identifier - id or username
     * @param options is a map & can contain:
     *        type [optional] Post type (code, snap, link, question)
     *        limit [optional, default = 10, max = 25] number of posts to return per page
     *        after [optional] if given, return posts with an ID lower than after
     * @return list of posts
     */
    public List<Post> userPosts(Map<String,String> userInfo, Map<String,String> options);

    /**
     * Return data about a single post. Note: For questions,
     * content is the question. For code, content contains
     * the code snippet. For code, snaps, and links, description
     * is the post description; it is not used for questions.
     * 
     * @param id Post ID
     * @return post object containing user (+ users photos) and
     *          post snaps if available.
     */
    public Post postsShow(int id);

    // TODO: return page # also
    /**
     * Returns a list of all posts in reverse-chron order
     * 
     * @return List of posts
     */
    public List<Post> postsAll(Map<String,String> options);

    // TODO: return page # also
    /**
     * Returns a list of posts of a given type
     * 
     * @param postType Post type (code, snap, link, question)
     * @param options Map containing
     *        sort [optional, default = recent] Sort by recent, popular, best (staff picks)
     *        page [optional, default = 1] Page of results to return
     * @return List of posts of given type
     */
    public List<Post> postsList(String postType, Map<String,String> options);

    // TODO: return count for comments
    /**
     * Returns a post's comments
     * 
     * @param accessToken Token obtained when the user is authenticated
     * @param id Post ID
     * @return List of comments for given post
     */
    public List<Comment> postComments(String accessToken, int id);

}
