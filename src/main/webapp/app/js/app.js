'use strict';

//Google login
function onLoadFunction() {
	gapi.client.setApiKey('AIzaSyC86kvbRqA7b6SWZddrqzB89dOJIDAv9os');
	gapi.client.load('plus', 'v1', function() {});
}

var app = angular.module("accounting", ['ngRoute','angular-loading-bar', 'facebook', 'googleplus','angucomplete-alt']);

app.config(['$routeProvider', 'FacebookProvider', function($routeProvider, FacebookProvider) {
	
	console.log($routeProvider);
	FacebookProvider.init('1800088453391367');
	// Now set up the states 
	$routeProvider
	.when('/app', {
		templateUrl: ''
	}).when('/app/home', {
		templateUrl: "app/home.html"
	}).when('/app/login', {
		templateUrl: "app/login.html"
	}).when('/app/signup', {
		templateUrl: "app/register.html"
	}).when('/app/list/:type', {
		templateUrl: "app/content.html"
	}).when('/app/about', {
		templateUrl: "app/about.html"
	}).when('/app/contact', {
		templateUrl: "app/contact.html"
	}).when('/app/upload', {
		templateUrl: "app/upload.html"
	}).when('/app/my-account', {
		templateUrl: "app/my-account.html"
	}).when('/app/admin', {
		templateUrl: "app/admin/upload-banner.html"
	});
	
	if (localStorage.getItem('loggedInUser')) {
		var loggedInUser = JSON.parse(localStorage.getItem('loggedInUser'));
    	if (loggedInUser.myAccounts != null && loggedInUser.myAccounts.length > 0) {
    		if (window.location.hash) {
    			window.location.href = window.location.hash;
    		} else {
    			window.location.href = "#!app/home";
    		}
    		
    	} else {
    		window.location.href = "#!app/my-account";
    	}
	} else {
		window.location.href = "#!app/login";
	}
}]); 
