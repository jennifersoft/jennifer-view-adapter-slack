package com.aries.slack.util;

import com.aries.extension.util.LogUtil;
import com.aries.slack.entity.SlackData;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Slack Client for pushing message to slack
 *
 */
public class SlackClient {
	
	/**
	 * Default connection time out value
	 */
	private final int CONNECTION_TIME_OUT	= 1000;
	
	/**
	 * Default encoding value
	 */
	private final String ENCODING			= "UTF-8";
	
	/**
	 * SlackMessage instance
	 */
	private SlackData slackData;
	
	/**
	 * Default constructor
	 * @param message SlackMessage object
	 */
	public SlackClient(SlackData message) {
		this.slackData = message;
	}
	
	/**
	 * Push message to slack using simple URLConnection
	 * @return Return either "ok" if message was sent, or null if message was not sent or an exception occured.
	 */
	public String push() {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(slackData.getProp().getWebHookUrl());
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(CONNECTION_TIME_OUT);
			connection.setRequestProperty("Content-Type", "application/json; utf-8");
			connection.setRequestProperty("Accept", "application/json");
			connection.setUseCaches(false);
			connection.setDoOutput(true);

			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.write(slackData.toString().getBytes(StandardCharsets.UTF_8));
			out.flush();
			out.close();

			InputStream in = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			StringBuilder response = new StringBuilder();
			while ( (line = reader.readLine()) != null )
				response.append(line + "\n");
			
			reader.close();
			return response.toString();
		} catch(Exception ex){
			LogUtil.error("Error while pushing message. Reason : " + ex);
			return "";
		} finally{
			if(connection != null)
				connection.disconnect();
		}
	}
}
