package com.accounting.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	public final static String AUTH_KEY_FCM = "AIzaSyAwiAcLIXMYjS5B1dQTLe3yBP48I7-cuNA";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	public static void pushFCMNotification(String postDataStr) throws Exception {

		JSONObject jObject = null;
		try {
			URL obj = new URL(API_URL_FCM);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
			con.setRequestProperty("Content-Type", "application/json");

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			try {
				wr.writeBytes(postDataStr);
				wr.flush();
				wr.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			con.connect();

			int responseCode = con.getResponseCode();
			System.out.println(con.getContent().toString());
			if (responseCode == 200) {

				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				jObject = new JSONObject(response.toString());
				System.out.println(response.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			jObject = new JSONObject();
			try {
				jObject.put("ErrorCode", 102);
				jObject.put("ErrorDetail", e.getMessage());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	/*public static void main(String[] args) {
		String DeviceIdKey = "f8fEsiTbmxg:APA91bE4whX0yCnIAGKmrpKKdixqw7Ct70YDYVOqbsSq2gE9qraqSCj8CNfOZG7XlwFKFPXl_wAtJL2eMS6D2s9Y8vT_KVBWYP3fUy1P7YkSPPJSKEByK3cj5rSdYMuH1Jdm1H-sJ8Zy";
		try {
	
			JSONArray toArr = new JSONArray();
			try {
				toArr.put(DeviceIdKey);
				JSONObject notifyObj = new JSONObject();
				notifyObj.put("title", "mandeep");
				notifyObj.put("body", " invited you to his event Party");
				try {
					JSONObject json = new JSONObject();
				    json.put("notification",notifyObj);
				    json.put("registration_ids", toArr);
				    
				} catch(Exception e) {
					e.printStackTrace();
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}*/
}
