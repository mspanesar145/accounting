angular.module("accounting").controller('UserController',function($scope, UserService, Facebook, GooglePlus) {
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
			localStorage.setItem("loggedInUser", JSON.stringify(response.data));

			window.location.href="#!/app/home";
		});
	}
	

	$scope.facebookLogin = function() {
		debugger;
		Facebook.login(function(response) {
			debugger;
			if (response.status === 'connected') {
				$scope.socialLogin(response);
			}
	        // Do something with response.
			console.log("testinggggggggg");
	    });
	}
	
	$scope.socialLogin = function(response) {
		debugger;
		var facebookLoginCredential = {
			'authType': 'facebook',
			'facebookAuthToken': response.authResponse.accessToken,
			'facebookID': response.authResponse.userID
		}
		UserService.signup(facebookLoginCredential).then(function(response) {
			debugger;
			console.log(JSON.stringify(response));
			if (response.data.errorCode != 0) {
				toastr["error"](response.data.errorDetail);
				return;
			}
			localStorage.setItem("loggedInUser", JSON.stringify(response.data));
			window.location.href="#!/app/home";
		});
	}
	
	$scope.googleplusLogin = function() {
		debugger;
		GooglePlus.login({}).then(function (authResult) {
			debugger;
            console.log(authResult);
            GooglePlus.getUser().then(function (user) {
                console.log(user);
            });
        }, function (err) {
        	debugger;
            console.log(err);
        });
	}
	
});