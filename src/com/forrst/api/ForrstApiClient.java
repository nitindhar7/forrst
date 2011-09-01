package com.forrst.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;


public class ForrstApiClient implements ForrstAPI {
	
	@Override
	public void stats() {
		// TODO Auto-generated method stub

		// example
		this.read_exec("stats", "udiudi");
	}

	@Override
	public void usersAuth() {
		// TODO Auto-generated method stub

	}

	@Override
	public void usersInfo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void userPosts() {
		// TODO Auto-generated method stub

	}

	@Override
	public void postsShow() {
		// TODO Auto-generated method stub

	}

	@Override
	public void postsAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void postsList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void postComments() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Gets a command like stats or users/posts for certain username and returns
	 * the json object of that reponse.
	 * @param String command
	 * @param String username
	 * @return JSONObject response
	 * @author Udi Mosayev <udi.mosayev@gmail.com>
	 */
	private JSONObject read_exec(String command, String username) {
		String commandUrl = ForrstApiClient.BASE_URI+command+"/?username="+username;
		String jsonResult = ForrstApiClient.getData(commandUrl);
		JSONObject jsonData;
		
		try {
			jsonData = new JSONObject(jsonResult);
			JSONObject response = jsonData.getJSONObject("resp");
			
			return response;
			/**
			 * @TODO need to reconsider to current code structure.
			 * Probably should have an for each result type (stats, user/posts and etc.)
			 * and create out of every response (the json object) an object we can work with easily.
			 */			
		} catch (JSONException e) {
			
		}
		
		return new JSONObject();
	}
	
	/**
	 * This method actually gets the data from the request URI
	 * @param request the URI of the request
	 * @return response string - JSON
	 * @author Udi Mosayev <udi.mosayev@gmail.com>
	 */
	private static String getData(String request) {
		String jString = "";
		try {
			URL url = new URL(request);

			// Read all the text returned by the server
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String str;
			
			while ((str = in.readLine()) != null) {
				jString += str;
			}
			
			in.close();
		} catch (MalformedURLException e) {
			// TODO Handle this
		} catch (IOException e) {
			// TODO Handle this */
		}

		return jString;
	}

}
