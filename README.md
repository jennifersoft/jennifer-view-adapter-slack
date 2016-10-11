## Overview
This adapter will send EVENT notification to slack channel or slack user.

## Getting started
You must modify the configuration file of the view server. (conf/server_view.conf)
```
adapter_class_path = ${ADAPTER_PROJECT_PATH}/dist/jennifer-view-adapter-slack.jar
adapter_config_path = ${ADAPTER_PROJECT_PATH}/dist/adapter.properties
adapter_event_class_name = adapter.jennifer.slack.SlackAdapter
```

## Configuration file
The configuration file looks like the following
```
# Set Slack Incoming Webhook URL
# For more information on how to use Slack incoming Webhooks please refer to Slack documentation
slack_webhook=SLACK_WEBHOOK_URL_HERE

# Set target destination for message. You can either send messages to a Slack Channel  or to a Slack User
# to send message to channel use the # followed by the channel name. To send the messages to a user
# use the @  followed by the user name
# Example sending notification to channel : #monitoring
# Example sending notification to  user: @bob
slack_channel=SLACK_CHANNEL_NAME_HERE

#Optional value to set message color using color's hex value
# Example  value::
#message_color=#551A8B
message_color=COLOR_HEX_CODE_HERE

#Optional value to use specific emoji when sending message. Note the emoji code must be one of the default codes provided by slack or customer code
#Example value:
#icon_emoji=:information_desk_person:
icon_emoji=ICON_EMOJI_CODE_HERE

#Set the name of the user. this can be any value you want
#Example value:
#slack_username=JENNIFER Extension
slack_username=DISPLAY_USERNAME_HERE
```
* Make sure to replace the slack_webhook property with the correct value