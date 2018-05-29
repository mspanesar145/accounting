package com.accounting.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Message.Builder;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

@Service
public class NotificationService {

	public final static String AUTH_KEY_FCM = "your key ";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	public static void sendPushNotification(List<String> deviceTokenList,Map<String,Object> payload) {
		Sender sender = new Sender(AUTH_KEY_FCM);
		Builder messageBuilder = new Message.Builder();
		for (Entry<String,Object> payloadSet : payload.entrySet()) {
			messageBuilder.addData(payloadSet.getKey(), payloadSet.getValue().toString());
		}
		Message msg = messageBuilder.build();
		
		try {
			MulticastResult result = sender.send(msg, deviceTokenList, 5);
			for (Result r : result.getResults()) {
				if (r.getMessageId() != null)
					System.out.println("Push Notification Sent Successfully");
				else
					System.out.println("ErrorCode " + r.getErrorCodeName());
			}
		} catch (Exception e) {
			System.out.println("Error " + e.getLocalizedMessage());
		}
	}
}
