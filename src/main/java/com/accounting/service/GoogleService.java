package com.accounting.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.primefaces.json.JSONObject;

public class GoogleService {
	//private String user_info_api = "https://www.googleapis.com/oauth2/v1/userinfo";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
	private String user_info_api = "https://www.googleapis.com/oauth2/v3/tokeninfo";
	
	/*public static void main(String[] args) {
		GoogleService gs = new GoogleService();
		gs.getUserInfo("eyJhbGciOiJSUzI1NiIsImtpZCI6IjljMzdiZjczMzQzYWRiOTM5MjBhN2FlODAyNjBiMGU1NzY4NDU1MWUifQ.eyJhenAiOiI5MjMwMjg2MjIyNDktYTZzMzN0bmVqaHVibDEydjE1OGVpY2Q1MmVqa2ZkcHUuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI5MjMwMjg2MjIyNDktMGsyMmJtc2FqZWJhYmg4dTRmb2Zlamcwajk0cXA4aGYuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDgzMTMxNzY2MTIyNDQ0ODE0NjEiLCJlbWFpbCI6InRlc3QucmVkYi4xNEBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZXhwIjoxNTE2NDMyNDQxLCJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJpYXQiOjE1MTY0Mjg4NDEsIm5hbWUiOiJQcmVybmEgUmVkQmxpbmsiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy02RmJubjc1WHNWZy9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BQTZaUFQ3T09WNkNWdWc3blU3ei1kMFV3TUF2dXMyRVJ3L3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJQcmVybmEiLCJmYW1pbHlfbmFtZSI6IlJlZEJsaW5rIn0.KI5KAl1obk6lvb-rJFbTUPS6_yBLsPevy-Ts3-55Mapk1ek2GSglTv4L6NiJ9_2dBIFL8s4Lz6SMcRcwrdffjFgaD_RefiFnw6wJi_bLfesYvbYS4xsr9edram9XDcxMuEVT9AMzF6H9MqpiincshYyrPgVlqZ4FYYf75TAbNCXLWQux3GvnlvBiOc552R9H5rpCY0e27YdiG-vfzBWcIssR-NP1W282mOJacCD_WFRHoSMV42_zCEXy6TdK-3uQOoh6heC2zDhoKGcPpJOGpvhUMILCxbT_OZtm0O-eSjeA1rf9sAV3oi-3ZaDdaInK18Sz04-rqo6eg5yfGMa24w");
	}*/

	public JSONObject getUserInfo(String accessToken) {
		System.out.println("Google Token : "+accessToken);
		JSONObject googleUserInfo = doGoogleCalendarRestRequestWithAccessToken(user_info_api, "GET",accessToken);
		if (googleUserInfo.has("ErrorCode")) {
			googleUserInfo = doGoogleCalendarRestRequestWithIdToken(user_info_api, "GET",accessToken);
		}		
		return googleUserInfo;
	}
	
	//This will run in case of ios
	public JSONObject doGoogleCalendarRestRequestWithAccessToken(String url, String method,String accessToken){
		System.out.println("["+new Date()+" Making Google Calendar List Request,url : "+url);
		JSONObject jObject = null;
		try {
			URL obj = new URL(url+"?access_token="+accessToken);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod(method);
			int responseCode = con.getResponseCode();
			System.out.println(con.getContent().toString());
			if (responseCode == 200) {

				System.out.println("Response Code : " + responseCode);

				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				jObject = new JSONObject(response.toString());
				
				return jObject;
			}
		} catch(Exception e) {
			e.printStackTrace();
			jObject = new JSONObject();
			try {
				jObject.put("ErrorCode",102);
				jObject.put("ErrorDetail",e.getMessage());
			} catch(Exception e1){
				e1.printStackTrace();
			}
		}
		System.out.println("["+new Date()+" Google Calendar List Request Complete");
		return jObject;
	}
	
	//This will run in case of android
	public JSONObject doGoogleCalendarRestRequestWithIdToken(String url, String method,String accessToken){
		System.out.println("["+new Date()+" Making Google Calendar List Request,url : "+url);
		JSONObject jObject = null;
		try {
			URL obj = new URL(url+"?id_token="+accessToken);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod(method);
			int responseCode = con.getResponseCode();
			System.out.println(con.getContent().toString());
			if (responseCode == 200) {

				System.out.println("Response Code : " + responseCode);

				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				jObject = new JSONObject(response.toString());
				
				return jObject;
			}
		} catch(Exception e) {
			e.printStackTrace();
			jObject = new JSONObject();
			try {
				jObject.put("ErrorCode",102);
				jObject.put("ErrorDetail",e.getMessage());
			} catch(Exception e1){
				e1.printStackTrace();
			}
		}
		System.out.println("["+new Date()+" Google Calendar List Request Complete");
		return jObject;
	}
}
