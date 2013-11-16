# Forrst

forrst is a Java library for the Forrst API(v2) providing basic support for reading posts. Download the latest jar [here](https://github.com/nitindhar7/forrst/raw/master/dist/forrst-7.0.0.jar).

### Examples
* * *

The official [Forrst API documentation](http://forrst.com/api) has full API definitions and descriptions.

    import com.nitindhar.forrst;

    ForrstAPI forrst = new ForrstAPIClient();

    forrst.authenticateUser("EMAIL_OR_USERNAME", "PASSWORD");

    forrst.getNotifications("ACCESS_TOKEN", options)

    forrst.getUser("USERNAME");

    forrst.getUserPosts("USERNAME", options)

Copyright (c) 2013 Nitin Dhar. See [MIT-LICENSE](https://github.com/nitindhar7/forrst/blob/master/MIT-LICENSE) for details.

- Tumblr: http://nitindhar.tumblr.com
- Forrst: https://forrst.com/people/nitindhar7
- Twitter: [@nitin_dhar](https://twitter.com/nitin_dhar)