package com.accounting.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.accounting.bo.FacebookProfile;

public class FacebookService {
	
	String facebook_profile_api = "https://graph.facebook.com/me";
	public FacebookProfile facebookProfile(String accessToken) {
		String api = facebook_profile_api+"?fields=picture.height(961),name,email&access_token="+accessToken;
		FacebookProfile fp = doFacebookProfileRestRequest(api,HttpMethod.GET,null);
	    return fp;
	}
	
	
	
	public <T> FacebookProfile doFacebookProfileRestRequest(String url, HttpMethod method, T body){
		System.out.println("["+new Date()+" Making Request,url : "+url);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
		HttpEntity<T> entity = new HttpEntity<T>(body, headers);
		FacebookProfile fbProfile = null;
		try{
			fbProfile = restTemplate.exchange(url,method, entity, FacebookProfile.class).getBody();
		}catch (HttpClientErrorException e) {
		      System.out.println("FacebookService ErrorCode: "+e.getStatusCode());
		      System.out.println("FacebookService ErrorMessage: "+e.getResponseBodyAsString());
		      throw e;
		}catch (HttpServerErrorException e) {
		      System.out.println("FacebookService ErrorCode: "+e.getStatusCode());
		      System.out.println("FacebookService ErrorMessage: "+e.getResponseBodyAsString());
		      throw e;
		}
		System.out.println("["+new Date()+" Request Complete");
		return fbProfile;
	}
}
