package adapter.jennifer.slack;

import java.text.SimpleDateFormat;
import java.util.Date;

import adapter.jennifer.slack.entity.SlackProp;
import adapter.jennifer.slack.util.ConfUtil;
import adapter.jennifer.slack.util.SlackClinet;
import com.jennifersoft.view.adapter.JenniferAdapter;
import com.jennifersoft.view.adapter.JenniferModel;
import com.jennifersoft.view.adapter.model.JenniferEvent;
import com.jennifersoft.view.adapter.util.LogUtil;
import adapter.jennifer.slack.entity.SlackMessage;

/**
 * The main logic for the extension
 *
 */
public class SlackAdapter implements JenniferAdapter{

	/**
	 * Format the date and time
	 */
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	@Override
	public void on(JenniferModel[] jennfierModel) {
		SlackProp slackProperties = ConfUtil.getSlackProperties();
		for(int i = 0; i < jennfierModel.length;i++){
			JenniferEvent eventModel = (JenniferEvent)jennfierModel[i];
			String messageTitle = eventModel.getErrorType() + " was caught by JENNIFER";
			SlackMessage slackMessage = new SlackMessage(slackProperties, jenniferEventToString(eventModel), messageTitle, messageTitle);
			String result = new SlackClinet(slackMessage).push();
            result = result.trim();
			if(!result.trim().toLowerCase().equals("ok"))
                LogUtil.error("Failed to push message to Slack");
		}
		
	}
	
	/**
	 * String representation of the event to be used as the slack message body
	 * @param eventModel the event model
	 * @return event model as string (Slack message body)
	 */
	private String jenniferEventToString(JenniferEvent eventModel){
		StringBuilder messageBody = new StringBuilder();
		messageBody.append("The following event "+eventModel.getErrorType()+" was caught by JENNIFER\n");
		messageBody.append("Here are some additional details\n");
		messageBody.append("Affected Domain [ID:NAME]: " + eventModel.getDomainId() + ":" + eventModel.getDomainName()+"\n");
		messageBody.append("Affected Instance [ID:NAME]: " + eventModel.getInstanceId() + ":" + eventModel.getInstanceName()+"\n");
		String message = eventModel.getMessage().length() == 0 ? "None" :eventModel.getMessage();
		messageBody.append("Message : " + message + "\n");
		String detailedMessage = eventModel.getDetailMessage().length() == 0 ? "None" :eventModel.getDetailMessage();
		messageBody.append("Detailed Message : " + detailedMessage + "\n");
		String serviceName = eventModel.getServiceName().length() == 0 ? "Not available" : eventModel.getServiceName();
		messageBody.append("Service Name : " + serviceName + "\n");
		String transactionId = eventModel.getTxId() == 0 ? "Not available" : eventModel.getTxId() + "";
		messageBody.append("Transaction Id: " + transactionId + "\n");
		String metricsName = eventModel.getMetricsName().length() == 0 ? "Not available" : eventModel.getMetricsName();
		messageBody.append("Metrics Name : " + metricsName + "\n");
		Date d = new Date(eventModel.getTime());
		messageBody.append("Event Time [Raw:HumanRedable]: " + eventModel.getTime()  + ":" + sdf.format(d)+"\n");
		messageBody.append("\nThis message was created automatically by JENNIFER Adapter");
		
		return messageBody.toString();
	}
	
	
}
