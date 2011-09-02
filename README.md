Forrst
======
forrst is a Java library for the Forrst API(v2). Currently this library is in development and will provide basic support for reading posts. forrst has minimal dependencies and is aimed to be easy to use.

Building
--------
    $ ant

Overview & Example
------------------
Each API method in this library is heavily commented. The descriptions are taken directly from Forrst. Please check the official Forrst API documentation at www.forrst.com/api for up to date API definitions and descriptions.

Forrst API Endpoints
--------------------
This library is build around the Forrt API version 2. At the moment there are 8 API endpoints available and each of them uses 'https://forrst.com/api/v2/' as the base URI. Also at the moment, API calls are rate limited to 150
so keep that in mind.

- stats
  - Returns stats about your API usage. Note: does not count against your rate limit.
  - `curl https://forrst.com/api/v2/stats`

- users/auth
  - User authentication. Provide an email/username and password and get an access token back
  - https://forrst.com/api/v2/users/auth

- users/info
  - Returns user info
  - https://forrst.com/api/v2/users/info

- user/posts
  - Returns a user's posts
  - https://forrst.com/api/v2/user/posts

- posts/show
  - Return data about a single post. Note: For questions, content is the question. For code, content contains the code snippet. For code, snaps, and links, description is the post description; it is not used for questions.
  - https://forrst.com/api/v2/posts/show

- posts/all
  - Returns a list of all posts in reverse-chron order
  - https://forrst.com/api/v2/posts/all

- posts/list
  - Returns a list of posts of a given type
  - https://forrst.com/api/v2/posts/list

- post/comments
  - Returns a post's comments
  - https://forrst.com/api/v2/post/comments

Authors
-------

Our aim is to make Forrst integration into Java applications as easy as possible. We have tried our best to provide in-code documentation for each method.
Tests coming up soon! Until then please help us find bugs.

- Nitin Dhar

  - Forrst: https://forrst.com/people/nitindhar7
  - Site: http://coding-sense.blogspot.com
  - Twitter: @nitin_dhar

- Rene Merino

  - Forrst: https://forrst.com/people/Alxmerino
  - Site: http://www.amayamedia.com
  - Twitter: @renemerino

- Udi Mosayev

  - Forrst: https://forrst.com/people/udiudi
  - Site: http://udiudi.com
  - Twitter: @udiudi

Todo
----

- Versionstamp on dist jar via latest git tag