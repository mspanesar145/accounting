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
			debugger;
			console.log(response);
			$scope.contents = response.data;
		});
	}
	
	$scope.rating = 0;
    $scope.ratings = [{
        current: 1,
        max: 5
    }];

    $scope.getSelectedRating = function (rating) {
    	$scope.rating = rating;
    	debugger;
        console.log(rating);
    }
    
    $scope.saveRating = function(userDocumentId) {
    	var userData = JSON.parse(localStorage.getItem("loggedInUser"));
    	debugger;
    	var ratingCredentials = {
    		'score': $scope.rating,
    	    'ratedById': userData.userId,
    	    'userDocumentId': userDocumentId
    	}
    	DocumentService.saveDocumentRatting(ratingCredentials).then(function(response) {
    		debugger;
    		console.log(response);
    	});
    }
}).directive('starRating', function () {
    return {
        restrict: 'A',
        template: '<ul class="rating">' +
            '<li ng-repeat="star in stars" ng-class="star" ng-click="toggle($index)">' +
            '\u2605' +
            '</li>' +
            '</ul>',
        scope: {
            ratingValue: '=',
            max: '=',
            onRatingSelected: '&'
        },
        link: function (scope, elem, attrs) {

            var updateStars = function () {
                scope.stars = [];
                for (var i = 0; i < scope.max; i++) {
                    scope.stars.push({
                        filled: i < scope.ratingValue
                    });
                }
            };

            scope.toggle = function (index) {
                scope.ratingValue = index + 1;
                scope.onRatingSelected({
                    rating: index + 1
                });
            };

            scope.$watch('ratingValue', function (oldVal, newVal) {
                if (newVal) {
                    updateStars();
                }
            });
        }
    }
});