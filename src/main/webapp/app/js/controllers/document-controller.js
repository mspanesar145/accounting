angular.module("accounting").controller('DocumentController',function($scope,$rootScope,$routeParams, DocumentService) {
	
	$scope.initHomePage = function() {
		$rootScope.showNavigationLinks = true;
		$scope.findAllDocuments();
		$scope.findMyAccountDetail();
	}
	
	$scope.findAllDocuments = function(title) {
		showLoader();
		window.localStorage.removeItem("selectedDocument");
		$scope.loggedInUser = JSON.parse(localStorage.getItem('loggedInUser'));
		var qry = "?userId="+$scope.loggedInUser.userId;
		if (title) {
			qry = qry+"&title="+title;
		}
		DocumentService.findAllDocuments(qry).then(function(response){
			hideLoader();
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
	
	$scope.findMyAccountDetail = function(){
		showLoader();
		var qry = "?createdById="+$scope.loggedInUser.userId;
		DocumentService.findMyAccountData(qry).then(function(response){
			hideLoader();
			$scope.myAccountData = response.data;
			
		});
	}
	
	$scope.loadDocumentsByTitle =function(searchTitle){
		showLoader();
		$scope.findAllDocuments(searchTitle);
	}
	
	
	
	$scope.initContentPage = function() {
		$scope.loggedInUser = JSON.parse(localStorage.getItem('loggedInUser'));
		if ($routeParams.type == 'content') {
			var selectedUserDocument = JSON.parse(window.localStorage.getItem("selectedDocument"));
			if (selectedUserDocument && selectedUserDocument['categoryId']) {
				var qryStr = "?categoryId="+selectedUserDocument['categoryId']+"&subCategoryId="+selectedUserDocument['subCategoryId'];
			} else {
				if ($scope.loggedInUser.myAccounts != null && $scope.loggedInUser.myAccounts.length > 0) {
					var myAccount  = $scope.loggedInUser.myAccounts[0];
					var qryStr = "?categoryId="+myAccount.mainCourseId+"&subCategoryId="+myAccount.secondryCourseId+"&containsVideo=false"
				}
			}
		} else if ($routeParams.type == 'video') {
			if ($scope.loggedInUser.myAccounts != null && $scope.loggedInUser.myAccounts.length > 0) {
				var myAccount  = $scope.loggedInUser.myAccounts[0];
				var qryStr = "?categoryId="+myAccount.mainCourseId+"&subCategoryId="+myAccount.secondryCourseId+"&containsVideo=true"
			}
		}
		//qryStr = "?categoryId=1&subCategoryId=10&containsVideo=false"
		$scope.findAllDocumentsByCatSubCatId(qryStr);
	}
	$scope.findAllDocumentsByCatSubCatId = function(qryStr) {
		//showLoader();
		DocumentService.findAllDocumentsByCatSubCatId(qryStr).then(function(response){
			hideLoader();
			$scope.contents = response.data;
		});
	}
	
	$scope.rating = 0;
    $scope.ratings = [{
        current: 1,
        max: 5
    }];

    
    $scope.openContent = function(document) {
   /* 	var url = '';
    	if (document.containsVideo) {
    		url = document.videoLink;
    	} else if (!document.containsVideo) {
    		url = document.contentLinkUrl;
    	}
    	if (url) {
    		window.open(url,'_blank');
    	}*/
    	window.location.href="#!/app/list/content"
    	
    }
    
    $scope.openContentInListPage = function(document) {
    	    	var url = '';
    	    	if (document.containsVideo) {
    	    		url = document.videoLink;
    	    	} else if (!document.containsVideo) {
    	    		url = document.contentLinkUrl;
    	    	}
    	    	if (url) {
    	    		window.open(url,'_blank');
    	    	}
    	    	window.location.href="#!/app/list/content"
    	    	
    	    }

    $scope.openRatingModal = function(document) {
    	$scope.document = document;
    	$("#rating-modal").toggle();
    }
    $scope.closeRatingModal = function() {
    	$scope.document = null;
    	$("#rating-modal").toggle();
    }
    
    $scope.openDescModal = function(document) {
    	$scope.document = document;
    	$(".desc-modal").toggle();
    }
    
    $scope.closeDescModal = function() {
    	$scope.document = null;
    	$(".desc-modal").toggle();
    }
    
    $scope.getSelectedRating = function (rating) {
    	$scope.rating = rating;
        console.log(rating);
    }
    

    $scope.openCommentModal = function(document) {
    	//$scope.document = document;
    	$("#comment-modal").toggle();
    	$scope.documentComment={};

    }
    $scope.closeCommentModal = function() {
    	$("#comment-modal").toggle();
    }
    $scope.saveDocumentComment = function(userDocumentId){
    	showLoader();
    	$scope.documentComment.userDocumentId = userDocumentId;
    	$scope.documentComment.commentedById = JSON.parse(localStorage.getItem('loggedInUser')).userId;
    	DocumentService.saveDocumentComment($scope.documentComment).then(function(response) {
    		hideLoader();
    		$scope.initContentPage();
    		$("#comment-modal").toggle();
    	});
    }
   
    $scope.openCommentListModal = function(userDocumentId) {
    	$scope.documentComments = [];
    	var qryStr = "?documentId="+userDocumentId;
    	DocumentService.findAllDocumentsCommentsByDocumentId(qryStr).then(function(response) {
    		hideLoader();
    		$scope.documentComments = response.data;
    		$("#comment-list-modal").toggle();
    	});
    	

    }
    $scope.closeCommentListModal = function() {
    	$("#comment-list-modal").toggle();
    }
    
    $scope.saveRating = function(userDocumentId) {
    	var userData = JSON.parse(localStorage.getItem("loggedInUser"));
    	var ratingCredentials = {
    		'score': $scope.rating,
    	    'ratedById': userData.userId,
    	    'userDocumentId': userDocumentId
    	}
		showLoader();
    	DocumentService.saveDocumentRatting(ratingCredentials).then(function(response) {
    		hideLoader();
    		$("#rating-modal").toggle();
    	});
    }
    
    $scope.toggleBookmark = function(document){

    	
    	var qryStr = "?userDocumentId="+document.userDocumentId;
    	DocumentService.findBookmarkDocumentByDocumentId(qryStr).then(function(response) {
    		hideLoader();
    		if(response.data == ''){
    			var bookmarkDocument = {
    					'userDocumentId' : 	document.userDocumentId,
    					'bookmarkedById' :  document.createdById
    			}
    		}else{
    			var bookmarkDocument = {
    					'bookmarkDocumentId' :  response.data.bookmarkDocumentId
    			}
    		}
    		DocumentService.saveBookmarkDocument(bookmarkDocument).then(function(response) {
    		
    		console.log(response);
    		});
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