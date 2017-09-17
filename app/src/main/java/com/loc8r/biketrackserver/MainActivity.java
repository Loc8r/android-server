package com.loc8r.biketrackserver;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class MainActivity extends Activity {

	private static final String LOG_TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.d(LOG_TAG, "onCreate: hello!");
		Log.d(LOG_TAG, "onCreate: " + ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS));
		Log.d(LOG_TAG, "onCreate: " + ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS));
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 201);
		}
	}
//
//	@Override
//	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//		switch (requestCode) {
//			case 201:
//				registerReceiver(new SmsReceiver(), new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION));
//				break;
//		}
//	}
}
