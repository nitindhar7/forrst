Forrst
======
forrst is a Java library for the Forrst API(v2). Currently this library is in development and will provide basic support for reading posts. forrst has minimal dependencies and is aimed to be easy to use.

Android
-------
Android platform to be tested soon!

Download Latest
---------------
Download the latest jar [here](https://github.com/nitindhar7/forrst/raw/master/dist/forrst-5.1.1.jar)

Build & Use
-----------
Each API method in this library is heavily commented. The descriptions are taken directly from Forrst. Please check the official [Forrst API documentation](http://forrst.com/api) for up to date API definitions and descriptions.
To begin, build Forrst and import it in your code. __To run the tests for the `users/auth`, `notifications` and `post/comments` endpoints update your credentials in `ForrstAPIClientTests.java`.__

    $ ant

    import com.forrst.api;

    ForrstAPI forrst = new ForrstAPIClient();

    // API stats
    forrst.stats();

    // Authentication
    forrst.usersAuth("EMAIL_OR_USERNAME", "PASSWORD");

    // Notifications
    forrst.notifications("ACCESS_TOKEN", options)

    // User information
    forrst.usersInfo("USERNAME");

    // User posts
    forrst.userPosts("USERNAME", options)

Note that each library API endpoint returns a JSONObject. For more information on JSONObject visit [json.org/java](http://json.org/java/). The dependency json-java jar file is also included in the `lib` folder.
Also, at the moment the rate limit threshold is soft, which means that it is up to the client application to ensure that it sticks close to the 150 calls/hour limit. This limit may become strictly
enforced eventually.

Details for the rest of the available API endpoints are given inline.

Contribute
------------
Submit well documented code with unit tests and I will merge your changes in
as long as your code does not break the build.

API Endpoints
-------------
This library is built around the Forrst API version 2. At the moment there are 9 API endpoints available and each of them uses `https://forrst.com/api/v2/` as the base URI. Also at the moment, API calls are rate limited to 150
calls per hour, so keep that in mind when designing your applications. Here's the list:

- `stats()`
- `notifications(String accessToken, Map<String,String> options)`
- `usersAuth(String emailOrUsername, String password)`
- `usersInfo(Map<String,String> userInfo)`
- `userPosts(Map<String,String> userInfo, Map<String,String> options)`
- `postsShow(int id)`
- `postsAll(Map<String,String> options)`
- `postsList(String postType, Map<String,String> options)`
- `postComments(String accessToken, int id)`

TODO
----

- Improve deserialization for Notifications
- Upload to the [maven repository](http://maven.apache.org/guides/mini/guide-central-repository-upload.html)

Copyright
---------
Copyright (c) 2013 Nitin Dhar. See MIT-LICENSE for details.

Author
------

My aim is to make Forrst integration into Java applications as easy as possible. I have tried my best to provide in-code documentation for each method.

- Nitin Dhar

  - Forrst: https://forrst.com/people/nitindhar7
  - Site: http://softwarebynitin.com
  - Twitter: @nitin_dhar