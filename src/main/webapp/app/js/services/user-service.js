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
	    	var signupApi = 'api/users/';
		      $http.post(signupApi,postData).then(function(resp){
		        resolve(resp);
		      }, function(error){
		        console.log(error)
		      });
	    });
	  };
});
