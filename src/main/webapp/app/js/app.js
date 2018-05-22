'use strict';

var app = angular.module("accounting", ['ngRoute','angular-loading-bar']);

app.config(function($routeProvider) {
	
	console.log($routeProvider);
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
    	if (loggedInUser.myAccounts.length > 0) {
    		$routeProvider.otherwise('/app/home');
    	} else {
    		$routeProvider.otherwise('/app/my-account');
    	}
	} else {
		$routeProvider.otherwise('/app/login');
	}
}); 
