angular.module("accounting").controller('UserController',function($scope, UserService) {
	
	$scope.signup = function(signupData) {
		var validateResponse = UserService.validateSignup(signupData);
		if (!validateResponse['valid']) {
			return;
		}
		
		signupData['authType'] = 'email';
		UserService.signup(signupData).then(function(response) {
			if (response.data.errorCode != 0) {
				console.log("error");
				return;
			}
			window.location.href="#!/app/login";
		});
	}
	
	$scope.login = function(loginData) {
		login['authType'] = 'email';
		UserService.login(loginData).then(function(response) {
			if (response.data.errorCode != 0) {
				console.log("error");
				return;
			}
			window.location.href="#!/app/home";
		});
	}
	
});