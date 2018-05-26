angular.module("accounting").controller('DocumentController',function($scope,$rootScope,$routeParams, DocumentService) {
	
	$scope.initHomePage = function() {
		
		$scope.findAllDocuments();
	}
	
	$scope.findAllDocuments = function() {
		window.localStorage.removeItem("selectedDocument");
		$scope.loggedInUser = JSON.parse(localStorage.getItem('loggedInUser'));
		DocumentService.findAllDocuments($scope.loggedInUser.userId).then(function(response){
			
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
	
	
	$scope.initContentPage = function() {
		$scope.loggedInUser = JSON.parse(localStorage.getItem('loggedInUser'));
		if ($routeParams.type == 'content') {
			var selectedUserDocument = JSON.parse(window.localStorage.getItem("selectedDocument"));
			if (selectedUserDocument && selectedUserDocument['categoryId']) {
				var qryStr = "?categoryId="+selectedUserDocument['categoryId']+"&subCategoryId="+selectedUserDocument['subCategoryId']+"&containsVideo=false"
			} else {
				if ($scope.loggedInUser.myAccounts != null && $scope.loggedInUser.myAccounts.length > 0) {
					var myAccount  = $scope.loggedInUser.myAccounts[0];
					var qryStr = "?categoryId="+myAccount.mainCourseId+"&subCategoryId="+myAccount.secondryCourseId+"&containsVideo=false"
				}
			}
		} else if ($routeParams.type == 'video') {
			console.log("videosss");
		}
		
		$scope.findAllDocumentsByCatSubCatId(qryStr);
	}
	$scope.findAllDocumentsByCatSubCatId = function(qryStr) {
		DocumentService.findAllDocumentsByCatSubCatId(qryStr).then(function(response){
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
    
    $scope.moveToListing = function(seelctedDocument) {
    	window.localStorage.setItem("selectedDocument",JSON.stringify(seelctedDocument));
    	window.location.hash = "#!/app/list/content"
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