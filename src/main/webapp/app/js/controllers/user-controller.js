angular.module("accounting").controller('UserController',function($scope, UserService) {
	
	$scope.signup = function(signupData) {
		var validateResponse = UserService.validateSignup(signupData);
		if (!validateResponse['valid']) {
			return;
		}
		
		signupData['authType'] = 'email';
		UserService.signup(signupData).then(function(response) {
			console.log(response);
			if (response.data.errorCode != 0) {
				toastr["error"](response.data.errorDetail);
				return;
			}
			window.location.href="#!/app/login";
		});
	}
	
	$scope.login = function(loginData) {
		loginData['authType'] = 'email';
		UserService.login(loginData).then(function(response) {
			console.log(response);
			if (response.data.errorCode != 0) {
				//console.log("error");
				toastr["error"](response.data.errorDetail);
				return;
			}
			window.location.href="#!/app/home";
		});
	}
	
});