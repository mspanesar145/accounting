angular.module("accounting").controller('ProfileController',function($scope, ProfileService) {
	
	$scope.createDocument = function(document) {
		var validateResponse = ProfileService.validateDocument(document);
		if (!validateResponse['valid']) {
			toastr["error"](validateResponse.message);
			return;
		}
		
			
	}
	
});