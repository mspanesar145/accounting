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
	});
	
	$routeProvider.otherwise('/app/home');
}); 
