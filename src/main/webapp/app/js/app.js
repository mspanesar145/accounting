'use strict';

var app = angular.module("accounting", ['ngRoute','angular-loading-bar', 'facebook', 'googleplus']);

app.config(['$routeProvider', 'FacebookProvider', 'GooglePlusProvider', function($routeProvider, FacebookProvider, GooglePlusProvider) {
	
	console.log($routeProvider);
	//FacebookProvider.init('1800088453391367');
	//GooglePlusProvider.init({
	//	clientId: '1079274333856-ljvunn45oc3snfu2v2u0obbb3va1vi8s.apps.googleusercontent.com',
     //   apiKey: 'AIzaSyA-H3R9B0LalWi7mHkK7KOI2Xl7v1TUOX0'
	//});
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
	}).when('/app/content', {
		templateUrl: "app/content.html"
	}).when('/app/about', {
		templateUrl: "app/about.html"
	}).when('/app/contact', {
		templateUrl: "app/contact.html"
	}).when('/app/upload', {
		templateUrl: "app/upload.html"
	}).when('/app/my-account', {
		templateUrl: "app/my-account.html"
	});
	
	if (localStorage.getItem('loggedInUser')) {
		var loggedInUser = JSON.parse(localStorage.getItem('loggedInUser'));
    	if (loggedInUser.myAccounts != null && loggedInUser.myAccounts.length > 0) {
    		window.location.href = "#!app/home";
    	} else {
    		window.location.href = "#!app/my-account";
    	}
	} else {
		$routeProvider.otherwise('/app/login');
	}
}]); 
