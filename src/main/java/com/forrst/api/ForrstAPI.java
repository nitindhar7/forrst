package com.forrst.api;

import org.json.JSONObject;

public interface ForrstAPI {

    /**
     * All API calls (with the exception of stats) are rated
     * limited at 150 calls per hour. In the future, we will
     * offer whitelisting by request. When making unauthenticated
     * calls, each request counts against the total made by
     * the calling IP address; when authenticating, calls
     * count against the authenticated user's limit. You may
     * request rate limiting stats at api/v2/stats (it won't
     * count against your total). Your limit resets each hour.
     */
    public int RATE_LIMIT = 150;

    /**
     * All calls should be made using https://forrst.com/api/v2/
     * as the endpoint (e.g. calling posts/all would be
     * https://forrst.com/api/v2/posts/all). Making calls over
     * SSL is required, and calls over regular http may be
     * disabled at some point in the future.
     */
    public String BASE_URI = "https://forrst.com/api/v2/";

    /**
     * ENDPOINTS
     */
    public String STATS_URI = BASE_URI + "stats";
    public String USERS_AUTH_URI = BASE_URI + "users/auth";
    public String USERS_INFO_URI = BASE_URI + "users/info";
    public String USER_POSTS_URI = BASE_URI + "user/posts";
    public String POSTS_SHOW_URI = BASE_URI + "posts/show";
    public String POSTS_ALL_URI = BASE_URI + "posts/all";
    public String POSTS_LIST_URI = BASE_URI + "posts/list";
    public String POST_COMMENTS_URI = BASE_URI + "post/comments";

    /**
     * Returns stats about your API usage. Note: does
     * not count against your rate limit.
     *
     * @return JSON response containing:
     *         rate_limit,
     *         calls_made
     */
    public JSONObject stats();

    /**
     * User authentication. Provide an email/username
     * and password and get an access token back
     *
     * @param emailOrUsername Email/Username
     * @param password Password
     * @return JSON response containing:
     *         token
     */
    public JSONObject usersAuth(String emailOrUsername, String password);

    /**
     * Returns user info
     * 
     * @param id User ID
     * @param username Username
     * @return JSON response containing:
     *         id,
     *         username,
     *         name,
     *         url,
     *         posts,
     *         comments,
     *         likes,
     *         followers,
     *         following,
     *         photos {
     *             xl_url,
     *             large_url,
     *             medium_url,
     *             small_url,
     *             thumb_url
     *         },
     *         bio,
     *         is_a,
     *         homepage_url,
     *         twitter,
     *         in_directory,
     *         tag_string
     */
    public JSONObject usersInfo(int id, String username);

    /**
     * Returns a user's posts
     * 
     * @param id User ID
     * @param username Username
     * @param type [optional] Post type (code, snap, link, question)
     * @param limit [optional, default = 10, max = 25] number of posts
     *              to return per page
     * @param after [optional] if given, return posts with an ID lower
     *              than after
     * @return JSON response containing:
     *         posts [{
     *             id,
     *             tiny_id,
     *             post_type,
     *             post_url,
     *             created_at,
     *             updated_at,
     *             user {
     *                 ...
     *             },
     *             published,
     *             public,
     *             title,
     *             url,
     *             content,
     *             description,
     *             formatter_description,
     *             like_count,
     *             comment_count,
     *             snaps {
     *                 mega_url,
     *                 keith_url,
     *                 large_url,
     *                 medium_url,
     *                 small_url,
     *                 thumb_url,
     *                 original_url
     *             }
     *         }]
     */
    public JSONObject userPosts(int id, String username, String type, int limit, int after);

    /**
     * Return data about a single post. Note: For questions,
     * content is the question. For code, content contains
     * the code snippet. For code, snaps, and links, description
     * is the post description; it is not used for questions.
     * 
     * @param id Post ID
     * @param tinyId Post Tiny ID
     * @return JSON response containing:
     *         id,
     *         tiny_id,
     *         post_type,
     *         post_url,
     *         created_at,
     *         updated_at,
     *         user: {
     *             ...
     *         },
     *         published,
     *         public,
     *         title,
     *         url,
     *         content,
     *         description,
     *         formatted_description,
     *         like_count,
     *         comment_count,
     *         tag_string,
     *         tags [ ... ]
     */
    public JSONObject postsShow(int id, int tinyId);

    /**
     * Returns a list of all posts in reverse-chron order
     * 
     * @param after [optional] if passed, only return posts posted after this ID
     * @return JSON response containing:
     *         posts [{
     *             id,
     *             tiny_id,
     *             post_type,
     *             post_url,
     *             created_at,
     *             updated_at,
     *             user: {
     *                 ...
     *             },
     *             published,
     *             public,
     *             title,
     *             url,
     *             content,
     *             description,
     *             formatted_content,
     *             formatted_description,
     *             like_count,
     *             comment_count,
     *             snaps {
     *                 mega_url,
     *                 keith_url,
     *                 large_url,
     *                 medium_url,
     *                 small_url,
     *                 thumb_url,
     *                 original_url
     *             }
     *         }],
     *         page
     */
    public JSONObject postsAll(int after);

    /**
     * Returns a list of posts of a given type
     * 
     * @param postType Post type (code, snap, link, question)
     * @param sort [optional, default = recent] Sort by recent, popular, best (staff picks)
     * @param page [optional, default = 1] Page of results to return
     * @return JSON response containing:
     *         posts [{
     *             id,
     *             tiny_id,
     *             post_type,
     *             post_url,
     *             created_at,
     *             updated_at,
     *             user: {
     *                 ...
     *             },
     *             published,
     *             public,
     *             title,
     *             url,
     *             content,
     *             description,
     *             formatted_content,
     *             formatted_description,
     *             like_count,
     *             comment_count,
     *             snaps {
     *                 mega_url,
     *                 keith_url,
     *                 large_url,
     *                 medium_url,
     *                 small_url,
     *                 thumb_url,
     *                 original_url
     *             }
     *         }],
     *         page
     */
    public JSONObject postsList(String postType, String sort, int page);

    /**
     * Returns a post's comments
     * 
     * @param id Post ID
     * @param tinyId Post Tiny ID
     * @return JSON response containing:
     *         comments [{
     *             id,
     *             user {
     *                 ...
     *             },
     *             body,
     *             created_at,
     *             updated_at
     *         }],
     *         count
     */
    public JSONObject postComments(int id, int tinyId);

    // TODO: helper method to get all endpoints as Map<String,String>
}
