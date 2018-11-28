# Overview
This adapter will send EVENT notification to a slack channel or a slack user.

## Getting started

### 1- Adapter Installation
The first step is to register the adapter: 
1. In JENNIFER Dashboard, open the management area and Navigate to  **Extension and Notice** > **Adapter and Plugin**
2. Make sure the **Adapter** tab is selected then click the **[+Add]** button
3. Click on the **Path** text field to display list of available adapters.
4. Select **Event Adapter for Slack Messenger]** from drop down list.
4. Click the **Save** button to add the adapter. 

<img width="700" alt="slack_adapter_registration" src="https://user-images.githubusercontent.com/3861725/45069269-a15dcf80-b106-11e8-8f3f-5ad5d7e305b4.png"/>


### 2- Adapter Options

Next step is to add the adapter options. There are 2 required options that you must configure, the **slack_webhook** and the **slack_channel* option. 
The rest of the options are optional. Refer the table below for the full list of available options for this adapter.

<img width="700" alt="slack_adapter_options" src="https://user-images.githubusercontent.com/3861725/27722333-eef01af0-5da1-11e7-8235-c993c88580af.png">

The following table shows the available options for this adapter

| Key           | Required      | Description |  Default Value 
| ------------- |:-------------:|:-------------:|:-------------:|
| slack_webhook | YES           | Set Slack Incoming Webhook URL | None 
| slack_channel | YES           | Set target destination for message. <br>You can either send messages to a Slack Channel (use #)  or to a Slack User (use @).  | None 
| message_color | NO            | Optional: Value to set message color using color's hex value | #551A8B| #551A8B 
| slack_username| NO            | Option: This will change the "From" username when receiving slack message| JENNIFER Extension| JENNIFER Slack 
| icon_emoji    | NO            | Optional: Set the emoji icon for the sender. Refer to the slack emoji directory for more information about using slack emojis | :information_desk_person: 
| message_footer| NO            | Optional: Set the message footer of the slack message. | This is an Auto Generated Message by JENNIFER Adapter 
| share_url     | NO            | Optional: Set JENNIFER Share URL for the X-View pop-up plugin. If the URL value is set, then this adapter will attempt to generate link to view the transactions in X-View and display the link in the slack message| None  
| button_text   | NO            | Optional: only used if the share_url option is enabled. Customize the Slack interactive button text| View On X-View 

**IMPORTANT: In order to use the `share_url` functionality, [XView Pop-up Plugin](https://github.com/jennifersoft/jennifer-view-plugin-xviewpopup) must be configured and enabled.**

The following is an example of the slack message received from this adapter.

![slackexample](https://user-images.githubusercontent.com/3861725/49065611-d723d780-f261-11e8-98ee-8073638b9f7a.png)
