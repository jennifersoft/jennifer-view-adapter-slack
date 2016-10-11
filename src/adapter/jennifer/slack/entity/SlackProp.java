package adapter.jennifer.slack.entity;

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
	 * Message color. use Hex values
	 */
	private String color;

	
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
	
	
}
