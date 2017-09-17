package com.loc8r.biketrackserver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SmsReceiver extends BroadcastReceiver {

	private static final String LOG_TAG="SmsReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
			for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
				String messageBody = smsMessage.getMessageBody();
				String phoneNumber = smsMessage.getOriginatingAddress();
				Log.d(LOG_TAG, "onReceive: " + messageBody); // e.g. "56.029349,34.659283"
				String[] coords = messageBody.split(",");
				try {
					double lat = Double.parseDouble(coords[0]);
					double lon = Double.parseDouble(coords[1]);

					DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
							.child("users").child(phoneNumber).child("locationHistory").push();
					ref.child("lat").setValue(lat);
					ref.child("lon").setValue(lon);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
