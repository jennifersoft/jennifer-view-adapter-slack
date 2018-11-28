package com.aries.slack.util;

import com.aries.extension.util.LogUtil;
import com.aries.slack.entity.SlackMessage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Slack Client for pushing message to slack
 *
 */
public class slackClinet {
	
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
	private SlackMessage slackMessage;
	
	/**
	 * Default constructor
	 * @param message SlackMessage object
	 */
	public slackClinet(SlackMessage message){
		this.slackMessage = message;
	}
	
	/**
	 * Push message to slack using simple URLConnection
	 * @return Return either "ok" if message was sent, or null if message was not sent or an exception occured.
	 */
	public String push(){
		HttpURLConnection connection = null;
		try{
			URL url = new URL(slackMessage.getProp().getWebHookUrl());
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(CONNECTION_TIME_OUT);
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			String payload = "payload=" + URLEncoder.encode(slackMessage.toString(),ENCODING);
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(payload);
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
		}catch(Exception ex){
			LogUtil.error("Error while pushing message. Reason : " + ex.toString());
			return null;
		}finally{
			if(connection != null)
				connection.disconnect();
		}
	}
	
}
