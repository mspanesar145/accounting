angular.module("accounting").controller('DocumentController',function($scope, DocumentService) {
	
	$scope.initHomePage = function() {
		
		$scope.findAllDocuments();
	}
	
	$scope.findAllDocuments = function() {
		$scope.loggedInUser = JSON.parse(localStorage.getItem('loggedInUser'));
		DocumentService.findAllDocuments($scope.loggedInUser.userId).then(function(response){
			console.log(response);
			
			$scope.images = [];
			$scope.videos = [];
			$scope.contents = [];
			
			var documentsMap = response.data;
			if (documentsMap['image']) {
				$scope.images = documentsMap['image'];
			}
			if (documentsMap['video']) {
				$scope.videos = documentsMap['video'];
			}
			if (documentsMap['content']) {
				$scope.contents = documentsMap['content'];
			}
		});
	}
	$scope.findAllDocumentsByUserId = function() {
		DocumentService.findAllDocumentsByUserId().then(function(response){
			console.log(response);
			$scope.contents = response.data;
		});
	}
});