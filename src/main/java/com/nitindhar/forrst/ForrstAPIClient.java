package com.nitindhar.forrst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.common.base.Optional;
import com.nitindhar.forrst.http.HttpProvider;
import com.nitindhar.forrst.http.HttpRequest;
import com.nitindhar.forrst.model.Auth;
import com.nitindhar.forrst.model.Comment;
import com.nitindhar.forrst.model.CommentWrapper;
import com.nitindhar.forrst.model.ForrstData;
import com.nitindhar.forrst.model.Notification;
import com.nitindhar.forrst.model.Post;
import com.nitindhar.forrst.model.PostWrapper;
import com.nitindhar.forrst.model.Stat;
import com.nitindhar.forrst.model.User;

public class ForrstAPIClient implements ForrstAPI {

    private static final ObjectMapper mapper = new ObjectMapper();

    private final HttpRequest http;
    private final HttpProvider httpProvider;

    public ForrstAPIClient() {
        this.httpProvider = HttpProvider.ASYNC_HTTP_CLIENT;
        http = new HttpRequest(httpProvider);
    }

    public ForrstAPIClient(HttpProvider httpProvider) {
        this.httpProvider = httpProvider;
        http = new HttpRequest(httpProvider);
    }

    @Override
    public Stat stats() {
        ForrstData<Stat> data = null;

        try {
            JSONObject json = http.get(Endpoint.getInstance().STATS_URI, Optional.<Map<String,String>>absent());
            data = mapper.readValue(json.toString(), new TypeReference<ForrstData<Stat>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error fetching stats from Forrst", e);
        }

        return data.getResp();
    }

    @Override
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
            JSONObject json = http.get(Endpoint.getInstance().NOTIFICATIONS_URI, Optional.fromNullable(params));
            json = json.getJSONObject("resp");

            if(json.get("items") instanceof JSONArray) {
                JSONArray jsonItemsArray = (JSONArray) json.get("items");
                if(jsonItemsArray.length() == 0) {
                    return notifications;
                }
            }

            JSONObject itemsJson = json.getJSONObject("items");

            Iterator<String> itemsIterator = itemsJson.keys();
            while(itemsIterator.hasNext()) {
                String itemKey = itemsIterator.next();
                JSONObject notificationTypeJson = (JSONObject) itemsJson.get(itemKey);

                Iterator<String> notificationTypeIterator = notificationTypeJson.keys();
                while(notificationTypeIterator.hasNext()) {
                    String notificationTypeKey = notificationTypeIterator.next();
                    Notification notification = mapper.readValue(notificationTypeJson.get(notificationTypeKey).toString(), Notification.class);
                    notifications.add(notification);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching notifications from Forrst", e);
        }

        return notifications;
    }

    @Override
    public Optional<Auth> usersAuth(String emailOrUsername, String password) {
        Optional<Auth> auth = Optional.absent();

        try {
            Map<String,String> params = new HashMap<String,String>();
            params.put("email_or_username", emailOrUsername);
            params.put("password", password);

            auth = Optional.fromNullable(mapper.readValue(http.post(Endpoint.getInstance().USERS_AUTH_URI, params).toString(), Auth.class));
        } catch (Throwable t) {}

        return auth;
    }

    @Override
    public User usersInfo(Map<String,String> userInfo) {
        ForrstData<User> data = null;

        try {
            if(userInfo.containsKey("id") || userInfo.containsKey("username")) {
                JSONObject json = http.get(Endpoint.getInstance().USERS_INFO_URI, Optional.fromNullable(userInfo));
                data = mapper.readValue(json.toString(), new TypeReference<ForrstData<User>>() {});
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching user data", e);
        }

        return data.getResp();
    }

    @Override
    public List<Post> userPosts(Map<String,String> userInfo, Map<String,String> options) {
        ForrstData<PostWrapper> data = null;

        Map<String,String> params = new HashMap<String,String>();
        if(userInfo.containsKey("id")) {
            params.put("id", userInfo.get("id"));
        }
        if(userInfo.containsKey("username")) {
            params.put("username", userInfo.get("username"));
        }
        if(MapUtils.isNotEmpty(options)) {
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

        try {
            JSONObject json = http.get(Endpoint.getInstance().USER_POSTS_URI, Optional.fromNullable(params));
            data = mapper.readValue(json.toString(), new TypeReference<ForrstData<PostWrapper>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error fetching users posts", e);
        }

        return data.getResp().getPosts();
    }

    @Override
    public Post postsShow(int id) {
        ForrstData<Post> data = null;

        try {
            Map<String,String> params = new HashMap<String,String>();
            params.put("id", Integer.toString(id));
            JSONObject json = http.get(Endpoint.getInstance().POSTS_SHOW_URI, Optional.fromNullable(params));
            data = mapper.readValue(json.toString(), new TypeReference<ForrstData<Post>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error fetching post", e);
        }

        return data.getResp();
    }

    @Override
    public List<Post> postsAll(Map<String,String> options) {
        ForrstData<PostWrapper> data = null;
        Map<String,String> params = new HashMap<String,String>();

        if(MapUtils.isNotEmpty(options)) {
            if(options.containsKey("after")) {
                params.put("after", options.get("after"));
            }
        }

        try {
            JSONObject json = http.get(Endpoint.getInstance().POSTS_ALL_URI, Optional.fromNullable(params));
            data = mapper.readValue(json.toString(), new TypeReference<ForrstData<PostWrapper>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error fetching users posts", e);
        }

        return data.getResp().getPosts();
    }

    @Override
    public List<Post> postsList(String postType, Map<String,String> options) {
        ForrstData<PostWrapper> data = null;

        Map<String,String> params = new HashMap<String,String>();
        params.put("post_type", postType);

        if(MapUtils.isNotEmpty(options)) {
            if(options.containsKey("sort")) {
                params.put("sort", options.get("sort"));
            }
            if(options.containsKey("page")) {
                params.put("page", options.get("page"));
            }
        }

        try {
            JSONObject json = http.get(Endpoint.getInstance().POSTS_LIST_URI, Optional.fromNullable(params));
            data = mapper.readValue(json.toString(), new TypeReference<ForrstData<PostWrapper>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error fetching users posts", e);
        }

        return data.getResp().getPosts();
    }

    @Override
    public List<Comment> postComments(String accessToken, int id) {
        ForrstData<CommentWrapper> data = null;

        try {
            Map<String,String> params = new HashMap<String,String>();
            params.put("access_token", accessToken);
            params.put("id", Integer.toString(id));

            JSONObject json = http.get(Endpoint.getInstance().POST_COMMENTS_URI, Optional.fromNullable(params));
            data = mapper.readValue(json.toString(), new TypeReference<ForrstData<CommentWrapper>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error fetching comments from Forrst", e);
        }

        return data.getResp().getComments();
    }
}