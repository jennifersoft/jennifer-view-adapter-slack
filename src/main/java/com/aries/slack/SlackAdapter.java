package com.aries.slack;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aries.extension.data.EventData;
import com.aries.extension.handler.EventHandler;
import com.aries.extension.util.LogUtil;
import com.aries.slack.entity.SlackProp;
import com.aries.slack.util.ConfUtil;
import com.aries.slack.util.SlackClinet;

import com.aries.slack.entity.SlackMessage;

/**
 * The main logic for the extension
 *
 */
public class SlackAdapter implements EventHandler{

	/**
	 * Format the date and time
	 */
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public void on(EventData[] eventData) {
		SlackProp slackProperties = ConfUtil.getSlackProperties();

		for (EventData event : eventData) {
			String messageTitle = String.format("[%s] was caught by JENNIFER", event.errorType);
			SlackMessage slackMessage = new SlackMessage(slackProperties, jenniferEventToString(event), messageTitle, messageTitle);
			String result = new SlackClinet(slackMessage).push();
			result = result.trim();
			if(!result.trim().toLowerCase().equals("ok"))
				LogUtil.error("Failed to push message to Slack");
		}
	}


	
	/**
	 * String representation of the event to be used as the slack message body
	 * @param event the EventData model
	 * @return event model as string (Slack message body)
	 */
	private String jenniferEventToString(EventData event){
		StringBuilder messageBody = new StringBuilder();
		messageBody.append(String.format("The following event [%s] was caught by JENNIFER. %n",  event.errorType));
		messageBody.append("Here are some additional details\n");
		messageBody.append(String.format("Domain ID: %d%n", event.domainId));
		messageBody.append(String.format("Instance Name: %s%n", event.instanceName));
		messageBody.append(String.format("Transaction ID: %d%n", event.txid));
		messageBody.append(String.format("Service Name: %s%n", event.serviceName));
		messageBody.append(String.format("Error Type: %s%n", event.errorType));
		messageBody.append(String.format("Error Level: %s%n", event.eventLevel));
		messageBody.append(String.format("Error Time: %s%n", sdf.format(new Date(event.time))));

		messageBody.append("\nThis is an auto generated message");

		return messageBody.toString();
	}
	
	
}
