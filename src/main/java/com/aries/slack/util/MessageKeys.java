package com.aries.slack.util;

/**
 * Slack message payload object keys
 * @author Khalid
 * @Created 11/27/18 2:15 PM.
 */
public class MessageKeys {

    /**
     * JSON Key for username
     */
    public static final String USERNAME	= "username";
    /**
     * JSON key for channel name
     */
    public static final String CHANNEL	= "channel";
    /**
     * JSON key for attachments
     */
    public static final String ATTACHMENTS   = "attachments";
    /**
     * JSON key for emoji
     */
    public static final String ICON_EMOJI	 = "icon_emoji";
    /**
     * JSON key for fallback string (pop-up Notification title)
     */
    public static final String FALLBACK = "fallback";
    /**
     * JSON key for actions array
     */
    public static final String ACTIONS  = "actions";
    /**
     * JSON Key for message body
     */
    public static final String TEXT	= "text";

    /**
     * Message Color
     */
    public static final String COLOR = "color";

    /**
     * Message Footer
     */
    public static final String FOOTER = "footer";

    /**
     * Pretext Field
     */
    public static final String PRETEXT = "pretext";
}
