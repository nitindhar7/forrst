package com.forrst.api;

import java.util.Map;

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
     * NOTE: CURRENTLY BROKEN
     *
     * @param emailOrUsername Email/Username
     * @param password Password
     * @return JSON response containing:
     *         token
     */
    public JSONObject usersAuth(String emailOrUsername, String password);

    /**
     * Returns user info by User ID
     * 
     * @param id User ID
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
    public JSONObject usersInfo(int id);
    
    /**
     * Returns user info
     * 
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
    public JSONObject usersInfo(String username);

    /**
     * Returns a user's posts by User ID
     * 
     * @param id User ID
     * @param options is a map & can contain:
     *        type [optional] Post type (code, snap, link, question)
     *        limit [optional, default = 10, max = 25] number of posts to return per page
     *        after [optional] if given, return posts with an ID lower than after
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
    public JSONObject userPosts(int id, Map<String,String> options);
    
    /**
     * Returns a user's posts by Username
     * 
     * @param username Username
     * @param options is a map & can contain:
     *        type [optional] Post type (code, snap, link, question)
     *        limit [optional, default = 10, max = 25] number of posts to return per page
     *        after [optional] if given, return posts with an ID lower than after
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
    public JSONObject userPosts(String username, Map<String,String> options);

    /**
     * Return data about a single post by Post ID. Note: For questions,
     * content is the question. For code, content contains
     * the code snippet. For code, snaps, and links, description
     * is the post description; it is not used for questions.
     * 
     * @param id Post ID
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
    public JSONObject postsShow(int id);
    
    /**
     * Return data about a single post by Post Tiny ID. Note: For questions,
     * content is the question. For code, content contains
     * the code snippet. For code, snaps, and links, description
     * is the post description; it is not used for questions.
     * 
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
    public JSONObject postsShow(String tinyId);

    /**
     * Returns a list of all posts in reverse-chron order
     * 
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
    public JSONObject postsAll();
    
    /**
     * Returns a list of all posts in reverse-chron order after given ID
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
     * @param options Map containing
     *        sort [optional, default = recent] Sort by recent, popular, best (staff picks)
     *        page [optional, default = 1] Page of results to return
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
    public JSONObject postsList(String postType, Map<String,String> options);

    /**
     * Returns a post's comments by Post ID
     * 
     * @param id Post ID
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
    public JSONObject postComments(int id);
    
    /**
     * Returns a post's comments by Post Tiny ID
     * 
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
    public JSONObject postComments(String tinyId);

    /**
     * Returns a map containing the name of the endpoint and its URI
     * @return A map of endpoint name and uri
     */
    public Map<String,String> getEndpointsURIs();
}
