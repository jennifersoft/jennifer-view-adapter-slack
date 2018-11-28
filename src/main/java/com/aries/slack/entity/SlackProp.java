package com.aries.slack.entity;

/**
 * Slack Configuration and Properties class
 *
 */
public class SlackProp {

	/**
	 * Slack Incoming Webhook URL
	 */
	private String webHookUrl;
	
	/**
	 * Slack Channel
	 * Use # to send message to channel and @ to send message to user
	 * 
	 */
	private String channel;
	
	/**
	 * The username to be shown when sending message 
	 */
	private String userName;
	
	/**
	 * Icon emoji to be displayed
	 */
	private String iconEmoji;

	/**
	 * Message Color
	 */
	private String color;
	
	/**
	 * JENNIFER X-View Share URL Used to open X-View pop-up
	 */
	private String shareUrl;

	/**
	 * Message Footer Text
	 */
	private String footer;

	/**
	 * View On Xivew Button
	 */
	private String buttonText;
	
	public String getWebHookUrl() {
		return webHookUrl;
	}

	public void setWebHookUrl(String webHookUrl) {
		this.webHookUrl = webHookUrl;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIconEmoji() {
		return iconEmoji;
	}

	public void setIconEmoji(String iconEmoji) {
		this.iconEmoji = iconEmoji;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public String getFooter() {
		return footer;
	}

	public String getButtonText() {
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}
}
