angular.module("accounting").controller('UserController',function($scope, UserService) {
	
	$scope.signup = function() {
		UserService.signup().then(function(response) {
			
		});
	}
	
	$scope.login = function() {
		
		
	}
	
});