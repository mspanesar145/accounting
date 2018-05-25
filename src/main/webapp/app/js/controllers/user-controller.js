angular.module("accounting").controller('UserController',function($scope, UserService, Facebook) {
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
	$scope.gmail = {
		'username': '',
		'email': ''
	}
	
	$scope.googleCredentials = {
		'email': '',
		'firstName': '',
		'photo': '',
		'authType': 'google',
		'googleAuthToken': ''
	};
	$scope.googleplusLogin = function() {
		debugger;
		var params = {
			'clientid': '1079274333856-75lr5nqj06os9aee7vtl14o5k9it48bi.apps.googleusercontent.com',
			'cookiepolicy': 'single_host_origin',
			'callback': function(result) {
				debugger;
				if(result['status']['signed_in']) {
					$scope.googleCredentials.googleAuthToken = result.access_token;
					var request = gapi.client.plus.people.get({'userId': 'me'});
					request.execute(function(resp){
						debugger;
						$scope.googleCredentials.email = resp.emails[0].value;
						$scope.googleCredentials.firstName = resp.displayName;
						$scope.googleCredentials.photo = resp.image.url;
						UserService.signup($scope.googleCredentials).then(function(response) {
							debugger;
							console.log(JSON.stringify(response));
							if (response.data.errorCode != 0) {
								toastr["error"](response.data.errorDetail);
								return;
							}
							localStorage.setItem("loggedInUser", JSON.stringify(response.data));
							window.location.href="#!/app/home";
						});
						/*$scope.$apply(function() {
							debugger;
							$scope.gmail.username = resp.displayName;
							$scope.gmail.email = resp.emails[0].value;
						});*/
					});
				}
			},
			'approvalprompt': 'force',
			'scope': 'https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.profile.emails.read'
		};
		gapi.auth.signIn(params);
		
		debugger;
		/*GooglePlus.login().then(function (authResult) {
			debugger;
            console.log(authResult);
            GooglePlus.getUser().then(function (user) {
                console.log(user);
            });
        }, function (err) {
        	debugger;
            console.log(err);
        });*/
	}
	
});