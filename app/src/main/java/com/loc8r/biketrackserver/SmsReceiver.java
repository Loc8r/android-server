package com.loc8r.biketrackserver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

	private static final String LOG_TAG="SmsReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
			for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
				String messageBody = smsMessage.getMessageBody();
				Log.d(LOG_TAG, "onReceive: " + messageBody); // e.g. "56.029349,34.659283"
				String[] coords = messageBody.split(",");
			}
		}
	}
}
