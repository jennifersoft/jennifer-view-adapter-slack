## Important Notice

## Overview
This adapter will send EVENT notification to a slack channel or a slack user.

## Getting started

The first step is to register the adapter: 
1. In JENNIFER Client, open the management area and Navigate to  **Extension and Notice** > **Adapter and Plugin**
2. Make sure the adapter tab is selected then click the **[+Add]** button
3. Select **[Event]** from the classifications dropdown.
4. Enter **``slack``** as the adapter ID.
5. Enter the path to the adapter JAR file.
6. Enter the adapter class name ``com.aries.slack.SlackAdapter`` and save the settings.
 

<img width="799" alt="slack_adapter_registration" src="https://user-images.githubusercontent.com/3861725/45069269-a15dcf80-b106-11e8-8f3f-5ad5d7e305b4.png"/>


### Options ##

The following table shows the required options for this adapter

| Key           | Required      | Description | Example |
| ------------- |:-------------:|:-------------:|:-------------:|
| slack_webhook | YES           | Set Slack Incoming Webhook URL ||
| slack_channel | YES           | Set target destination for message. <br>You can either send messages to a Slack Channel (use #)  or to a Slack User (use @).  |1. Example sending notification to channel : #monitoring <br>2.Example sending notification to  user: @bob|
| message_color | NO            | Optional: Value to set message color using color's hex value | #551A8B
| slack_username| NO            | Option: This will change the "From" username when receiving slack message| JENNIFER Extension


<img width="802" alt="slack_adapter_options" src="https://user-images.githubusercontent.com/3861725/27722333-eef01af0-5da1-11e7-8235-c993c88580af.png">


### Adapter Version ##

Different Adapter Version Support Different versions of the server 

| Adapter Version           | JENNIFER Server Version  |
| ------------- |:-------------:|
| 1.0.0 | JENNIFER  Version < 5.2.3           |
| 1.0.1 | JENNIFER version >= 5.2.3           |
| 1.1.0 | JENNIFER version >= 5.4.0 | 