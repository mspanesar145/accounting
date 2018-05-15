angular.module("accounting").controller('DocumentController',function($scope, DocumentService) {
	
	$scope.findAllDocuments = function() {
		DocumentService.findAllDocuments().then(function(response){
			console.log(response);
			
			$scope.images = [];
			$scope.videos = [];
			$scope.contents = [];
			
			var documents = response.data;
			for (var idx in documents) {
				var document = documents[idx];
				if (document.containsVideo) {
					$scope.videos.push(document);
				} else if (document.contentLinkUrl) {
					$scope.images.push(document);
				} else {
					$scope.contents.push(document);
				}
			}
			
		});
	}
});