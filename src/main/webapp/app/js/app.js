'use strict';

var app = angular.module("accounting", ['ngRoute']);

app.config(function($routeProvider) {
	
	console.log($routeProvider);
	// Now set up the states 
	$routeProvider
	.when('/app', {
		templateUrl: ''
	}).when('/app/home', {
		templateUrl: "app/home.html"
	}).when('/app/login', {
		templateUrl: "app/signin.html"
	}).when('/app/signup', {
		templateUrl: "app/signup.html"
	}).when('/app/profile', {
		templateUrl: "app/profile.html"
	}).when('/app/about', {
		templateUrl: "app/about.html"
	}).when('/app/contact', {
		templateUrl: "app/contact.html"
	}).when('/app/my-account', {
		templateUrl: "app/my-account.html"
	});
	
	$routeProvider.otherwise('/app/home');
}); 
