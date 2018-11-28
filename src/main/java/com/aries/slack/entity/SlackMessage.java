package com.aries.slack.entity;

import com.aries.slack.util.MessageKeys;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Slack Message class
 */

public class SlackMessage extends JSONObject{

	/**
	 * Message body
	 */
	private String message;

	/**
	 * Message Pretext
	 */
	private String pretext;

	/**
	 * Notification title
	 */
	private String notificationsTitle;

	/**
	 * Slack properties instance
	 */
	private SlackProp prop;

	/**
	 * Default constructor
	 * @param prop SlackProp object
	 * @param message Message Body
	 * @param pretext Message pretext
	 */
	public SlackMessage(SlackProp prop,String message, String pretext, String notificationsTitle){
		this.prop = prop;
		this.message = message;
		this.pretext = pretext;
		this.notificationsTitle = notificationsTitle;

		this.constructMessage();
	}

	/**
	 * Construct the Slack Message
	 */
	private void constructMessage() {

		//Basic Message Components
		this.put(MessageKeys.USERNAME, getProp().getUserName());
		this.put(MessageKeys.CHANNEL, getProp().getChannel());
		this.put(MessageKeys.ICON_EMOJI, getProp().getIconEmoji());

		//Message Body
		JSONObject attachments = new JSONObject();
		attachments.put(MessageKeys.FALLBACK, this.notificationsTitle);
		attachments.put(MessageKeys.COLOR, getProp().getColor());
		attachments.put(MessageKeys.PRETEXT, pretext);
		attachments.put(MessageKeys.TEXT, message);
		attachments.put(MessageKeys.FOOTER, getProp().getFooter());


		JSONArray attachmentsArray = new JSONArray();
		attachmentsArray.put(attachments);

		this.put(MessageKeys.ATTACHMENTS, attachmentsArray);
	}

	/**
	 * Add Slack Link to X-View pop-up
	 * This will only work if the X-View Pop-up Plugin is installed and configured
	 * @param domainId
	 * @param txid
	 * @param time
	 */
	public void addXviewButton(short domainId, long txid, long time) {
		String url = String.format("%s&domainId=%d&txId=%d&searchTime=%d",  getProp().getShareUrl(), domainId, txid, time);

		//View on X-View action Button
		JSONObject actionsObject = new JSONObject();
		actionsObject.put("type", "button");
		actionsObject.put("text", getProp().getButtonText());
		actionsObject.put("url", url);

		JSONArray actionsArray = new JSONArray();
		actionsArray.put(actionsObject);


		JSONArray attachmentsArray = this.getJSONArray(MessageKeys.ATTACHMENTS);

		JSONObject buttonObject = new JSONObject();
		buttonObject.put(MessageKeys.FALLBACK, getProp().getButtonText());
		buttonObject.put(MessageKeys.ACTIONS, actionsArray);
		attachmentsArray.put(buttonObject);

		this.put(MessageKeys.ATTACHMENTS, attachmentsArray);
	}




	public SlackProp getProp() {
		return prop;
	}
}
