package com.aries.slack.util;

import com.aries.extension.util.PropertyUtil;
import com.aries.slack.entity.SlackProp;

/**
 * Load adapter configuration
 */
public class ConfUtil {

	private static final SlackProp slackProperties = new SlackProp();


	/**
	 * The adapter ID
	 */
	private static final String ADAPTER_ID = "slack";
	
	/**
	 * Get a configuration value using the provided key
	 * @param key configuration key. Set this key value in the adapter configuration menu in JENNIFER client.
	 * @param defaultValue Optional default configuration value
	 * @return String configuration value
	 */
	public static String getValue(String key, String defaultValue){
		return PropertyUtil.getValue(ADAPTER_ID, key, defaultValue);
	}

	/**
	 * Get the slack properties
	 * @return SlackProp slack properties
	 */
	public static SlackProp getSlackProperties() {
		slackProperties.setChannel(getValue("slack_channel", null));
		slackProperties.setIconEmoji(getValue("icon_emoji", ":information_desk_person:"));
		slackProperties.setUserName(getValue("slack_username", "JENNIFER Slack"));
		slackProperties.setWebHookUrl(getValue("slack_webhook", null));
		slackProperties.setShareUrl(getValue("share_url", null));
		slackProperties.setColor(getValue("message_color", "#551A8B"));
		slackProperties.setFooter(getValue("message_footer", "This is an Auto Generated Message by JENNIFER Adapter"));
		slackProperties.setButtonText(getValue("button_text", "View On X-View"));

		return  slackProperties;
	}
}
