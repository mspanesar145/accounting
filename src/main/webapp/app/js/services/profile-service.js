angular.module("accounting").service('ProfileService', function($q, $http){
	var validateDocument = function(document) {
		  var validateResponse = {valid:true};
		  if (!document.fileType) {
			  validateResponse['valid'] = false;
			  validateResponse['message'] = 'Please choose file type.';
		  }
		  return validateResponse;
	  }
	  
	  return {
		  validateDocument:validateDocument
	  }
	
});  