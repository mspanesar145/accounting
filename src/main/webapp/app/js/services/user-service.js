angular.module("accounting").service('UserService', function($q, $http){
	var signup = function(postData){
	    return $q(function(resolve, reject) {
	    	var signupApi = 'api/users/';
		      $http.post(signupApi,postData).then(function(resp){
		        resolve(resp);
		      }, function(error){
		        console.log(error)
		      });
	    });
	  };
	  
	  var login = function(postData){
	    return $q(function(resolve, reject) {
	    	var signupApi = 'auth/user/authenticate';
		      $http.post(signupApi,postData).then(function(resp){
		        resolve(resp);
		      }, function(error){
		        console.log(error)
		      });
	    });
	  };
	  
	  var validateSignup = function(signup) {
		  var validateResponse = {valid:true};
		  if (signup.password != signup.confirmPassword) {
			  validateResponse['valid'] = false;
			  validateResponse['message'] = 'Password/Confirm Password did not match.';
		  }
		  return validateResponse;
	  }
	  
	  return {
		  signup:signup,
		  login:login,
		  validateSignup:validateSignup
	  }
});
