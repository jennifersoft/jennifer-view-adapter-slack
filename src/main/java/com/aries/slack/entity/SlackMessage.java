package com.aries.slack.entity;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Slack Message class
 *
 */

public class SlackMessage {

	/**
	 * JSON Key for username
	 */
	private final String KEY_USERNAME	= "username";
	/**
	 * JSON key for channel name
	 */
	private final String KEY_CHANNEL	= "channel";
	/**
	 * JSON key for attachments (Message contnets)
	 */
	private final String KEY_ATTACHMENT	= "attachments";
	/**
	 * JSON key for emoji
	 */
	private final String KEY_ICON	 	= "icon_emoji";
	/**
	 * JSON key for fallback string (pop-up Notification title)
	 */
	private final String KEY_FALLBACK   = "fallback";
	/**
	 * JSON key for message color
	 */
	private final String KEY_COLOR		= "color";
	/**
	 * JSON key for message title
	 */
	private final String KEY_TITLE		= "title";
	/**
	 * JSON Key for message body
	 */
	private final String KEY_TEXT		= "text";
	
	private String text,title,fallback;
	
	
	/**
	 * The JSON object that will be used to construct the message payload
	 */
	private JSONObject slackMessage = new JSONObject();
	
	/**
	 * Slack properties instnace
	 */
	private SlackProp prop;
	
	/**
	 * Default constructor
	 * @param prop SlackProp object
	 * @param text Message Body
	 * @param title Message Title
	 * @param fallback Fallback title used for pop up notifications
	 */
	public SlackMessage(SlackProp prop,String text,String title,String fallback){
		this.prop = prop;
		this.text    = text;
		this.title = title;
		this.fallback = fallback;
	}
	
	/**
	 * Parse the payload into JSON format
	 * @return JSONObject representing the message payload
	 */
	public JSONObject toJson(){
		slackMessage.put(KEY_USERNAME, prop.getUserName());
		slackMessage.put(KEY_CHANNEL, prop.getChannel());
		slackMessage.put(KEY_ICON, prop.getIconEmoji());
		
		JSONObject attachments = new JSONObject();
		attachments.put(KEY_FALLBACK, getFallback());
		attachments.put(KEY_COLOR, prop.getColor());
		attachments.put(KEY_TITLE, getTitle());
		attachments.put(KEY_TEXT, getText());
		
		JSONArray attachmentsArray = new JSONArray();
		attachmentsArray.put(attachments);
		slackMessage.put(KEY_ATTACHMENT, attachmentsArray);
		return slackMessage;
	}
	
	public String getFallback() {
		return fallback;
	}
	public void setFallback(String fallback) {
		this.fallback = fallback;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public SlackProp getProp() {
		return prop;
	}
	
}
