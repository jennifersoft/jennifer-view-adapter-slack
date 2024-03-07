package com.aries.slack.entity;

import com.aries.extension.data.EventData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Slack Message class
 */

public class SlackData extends JSONObject{
	private String message;
	private String pretext;
	private EventData event;
	private SlackProp prop;

	public SlackData(SlackProp prop, String message, String pretext, EventData event) {
		//event.errorType, event.eventLevel
		this.prop = prop;
		this.message = message;
		this.pretext = pretext;
		this.event = event;

		this.addDefaultInfo();
		this.addAttachments();
	}

	private void addDefaultInfo() {
		this.put("username", getProp().getUserName());
		this.put("channel", getProp().getChannel());
		this.put("icon_emoji", getProp().getIconEmoji());
	}

	private void addAttachments() {
		JSONArray attachments = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("fallback", this.event.errorType);

		if (this.event.eventLevel.equals("FATAL"))
			obj.put("color", "#ff384d");
		else if (this.event.eventLevel.equals("WARNING"))
			obj.put("color", "#ffdd00");
		else
			obj.put("color", "#497eff");

		obj.put("pretext", this.pretext);
		obj.put("text", this.message);
		obj.put("footer", getProp().getFooter());
		attachments.put(obj);

		if (this.prop.getShareUrl() != null && this.event.txid != -1) {
			String popupUrl = "/popup/xviewAnalysisV2?domainId=" + this.event.domainId +
					"&transactionId=" + this.event.txid + "&searchTime=" + this.event.time;
			String link = this.prop.getShareUrl() + popupUrl + "&redirect=" + encodeURIComponent(popupUrl);
			this.addButtonInAttachments(attachments, link);
		}
		this.put("attachments", attachments);
	}

	private void addButtonInAttachments(JSONArray attachments, String link) {
		JSONObject actions = new JSONObject();

		JSONObject button = new JSONObject();
		button.put("type", "section");

		JSONObject buttonText = new JSONObject();
		buttonText.put("type", "mrkdwn");
		buttonText.put("text", "View Transaction (JENNIFER5 Pop-Up)");
		button.put("text", buttonText);

		JSONObject accessory = new JSONObject();
		accessory.put("type", "button");
		accessory.put("url", link);
		accessory.put("action_id", "button-action");
		JSONObject accessoryButton = new JSONObject();
		accessoryButton.put("type", "plain_text");
		accessoryButton.put("text", "Click Me");
		accessoryButton.put("emoji", true);
		accessory.put("text", accessoryButton);
		button.put("accessory", accessory);

		JSONArray list = new JSONArray();
		list.put(button);
		actions.put("blocks", list);

		attachments.put(actions);
	}

//	private void addButtonInAttachments(JSONArray attachments) {
//		JSONObject actions = new JSONObject();
//
//
//		JSONObject button = new JSONObject();
//		button.put("text", "Associated transaction");
//		button.put("type", "button");
//		button.put("link", "https://google.com");
//
//		JSONArray list = new JSONArray();
//		list.put(button);
//		actions.put("actions", list);
//
//		attachments.put(actions);
//	}

	public static String encodeURIComponent(String s)
	{
		String result = null;

		try
		{
			result = URLEncoder.encode(s, "UTF-8")
					.replaceAll("\\+", "%20")
					.replaceAll("\\%21", "!")
					.replaceAll("\\%27", "'")
					.replaceAll("\\%28", "(")
					.replaceAll("\\%29", ")")
					.replaceAll("\\%7E", "~");
		}

		// This exception should never occur.
		catch (UnsupportedEncodingException e)
		{
			result = s;
		}

		return result;
	}

	public SlackProp getProp() {
		return prop;
	}
}
