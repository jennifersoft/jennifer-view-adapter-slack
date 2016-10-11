package adapter.jennifer.slack.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.jennifersoft.view.adapter.util.LogUtil;
import com.jennifersoft.view.config.ConfigValue;
import adapter.jennifer.slack.entity.SlackProp;

/**
 * Load adapter configuration from configuration file
 */
public class ConfUtil {

	/**
	 * Properties instances to load the configuration
	 */
	private static Properties properties = null;
	
	/**
	 * Initialize the Properties object and load the configuration from the configuration file 
	 * The configuration file name and path must be set in <b>VIEW_SERVER_HOME/conf/server_view.conf<b> using the
	 * <b>adapter_config_path</b> option
	 */
	public static void load(){
		properties = new Properties();
		FileInputStream in = null;
		String path = ConfigValue.adapter_config_path;
		try{
			if(path != null){
				in = new FileInputStream(path);
				properties.load(in);
			}
		}catch(IOException io){
			LogUtil.error("Failed to load configuration file: " + io.toString());
		}
	}
	
	/**
	 * Get a configuration value using the provided key
	 * @param key configuration key
	 * @return String configuration value
	 */
	public static String getValue(String key){
		if(properties == null)
			load();
		return properties.getProperty(key);
	}
	
	/**
	 * Getter for the properties object.
	 * @return the properties object
	 */
	public static Properties getProperties() {
		if(properties == null)
			load();
		return properties;
	}
	
	/**
	 * Cast the properties into <b>SlackProp</b> class
	 * The following properties must be set in the configuration file
	 * <b>slack_webhook</b> The incoming WebHook URL
	 * <b>slack_channel</b> The Slack Channel Name (Or username) where message will be delivered
	 * <b>slack_username</b> The user name to be shown when sending message
	 * <b>icon_emoji</b> The Emoji to be used when sending message
	 * <b>message_color</b> Optional value to set message color
	 * @return
	 */
	public static SlackProp getSlackProperties(){
		SlackProp prop = new SlackProp();
		prop.setChannel(getValue("slack_channel"));
		prop.setColor(getValue("message_color"));
		prop.setIconEmoji(getValue("icon_emoji"));
		prop.setUserName(getValue("slack_username"));
		prop.setWebHookUrl(getValue("slack_webhook"));
		return prop;
	}
}
