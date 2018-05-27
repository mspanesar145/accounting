var coverImageFile = null;
function onSelectCoverImage(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            
            reader.onload = function (e) {
                $('#cover-img-tag').attr('src', e.target.result);
            }
            
            coverImageFile = input.files[0];
            reader.readAsDataURL(input.files[0]);
        }
    }
var contetFile = null;
function onSelectContentFile(input) {
    if (input.files && input.files[0]) {
    	contetFile = input.files[0];
    }
}
angular.module("accounting").controller('ProfileController',function($scope, ProfileService) {
	
	$scope.createDocument = function(document) {
		var validateResponse = ProfileService.validateDocument(document);
		if (!validateResponse['valid']) {
			toastr["error"](validateResponse.message);
			return;
		}
	}
	
	$scope.isCategorySelected = function(document) {
		if (!document.categoryId) {
			toastr["error"]("Please select category.");
			document.categorySubId = '';
			return false;
		}
	}
	
	$scope.myAccountInit = function() {
		$scope.myAccountData = {};
		$scope.loggedInUser = JSON.parse(localStorage.getItem('loggedInUser'));
		$scope.myAccountDataRequest();
	}
	
	$scope.myAccountDataRequest = function() {
		ProfileService.getMyAccountData($scope.loggedInUser.userId).then(function(response){
			console.log(response.data);
			if (response.data) {
				$scope.myAccountData = response.data;
				$scope.findSubCategoriesByParentId($scope.myAccountData.mainCourseId);
			} 
			$scope.findAllCategories();
		},function(error){
			console.log(error);
		});
		if (!$scope.$$phase) $scope.$apply();
	}
	
	$scope.showSelectedCategory = function(cat,myAccountData) {
		setTimeout(function(){
			return (cat.profileCategoryId == myAccountData.mainCourseId);
		},4000);
	}
	
	$scope.findAllCategories = function() {
		ProfileService.findAllCategories().then(function(response) {
			console.log(response.data);
			$scope.categories = response.data;
		},function(error){console.log(error);});	
	}
	
	$scope.findSubCategoriesByParentId = function(parentCategoryId) {
		ProfileService.findSubCategoriesByCategoryId(parentCategoryId).then(function(response) {
			console.log(response.data);
			$scope.subCategories = response.data;
		},function(error){console.log(error);});	
	}
	
	$scope.saveMyaccount = function(myAccountData) {
		myAccountData['createdById'] = $scope.loggedInUser.userId;
		ProfileService.saveMyAccountData(myAccountData).then(function(response) {
			console.log(response.data);
			var myAccounts = [];
			myAccounts.push(response.data);
			$scope.loggedInUser.myAccounts = myAccounts;
			localStorage.setItem("loggedInUser", JSON.stringify($scope.loggedInUser));
			window.location.href = "#!/app/home"
		},function(error){console.log(error);});	
	}
	
	/************* Upload Content Screen ***********/
	
	$scope.uploadInit = function() {
		$scope.userDocument = {};
		$scope.loggedInUser = JSON.parse(localStorage.getItem('loggedInUser'));
		$scope.findAllCategories();
	}
	
	$scope.saveFinalDocument = function(userDocumentData) {
		$scope.userDocument = userDocumentData;
		
		if (!$scope.userDocument.categoryId) {
			toastr["error"]("Please select Main Course");
			return;
		}
		if (!$scope.userDocument.subCategoryId) {
			toastr["error"]("Please select Secondary Course");
			return;
		}
		if (!$scope.userDocument.title) {
			toastr["error"]("Please enter title");
			return;
		}
		if (!coverImageFile) {
			toastr["error"]("Please upload Cover Image");
			return;
		}
		if ($scope.userDocument.containsVideo && !$scope.userDocument.videoLink) {
			toastr["error"]("Please paste video link");
			return;
		}
		
		if (coverImageFile) {
			$scope.uploadCoverImage();
		}
	}
	
	$scope.uploadCoverImage = function() {
        var data = new FormData();
        data.append("mediaFile", coverImageFile);
        data.append("userId", $scope.loggedInUser.userId);
		
        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/save/coverimage",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                console.log("SUCCESS : ", data);
                $scope.userDocument['coverImageUrl'] = data.coverImageUrl;
                
                if (contetFile) {
                	$scope.uploadContentFile();
                    
                } else {
                	$scope.saveUserDocument($scope.userDocument);
                }
                
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
	}
	
	$scope.uploadContentFile = function() {
		
        var data = new FormData();
        data.append("mediaFile", contetFile);
        data.append("userId", $scope.loggedInUser.userId);
		
        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/save/coverimage",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                console.log("SUCCESS : ", data);
                $scope.userDocument['contentLinkUrl'] = data.coverImageUrl;
                $scope.saveUserDocument($scope.userDocument);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
	}
	
	$scope.saveUserDocument = function(userDocumentData) {
				
		userDocumentData['createdById'] = $scope.loggedInUser.userId;
		ProfileService.saveUserDocument(userDocumentData).then(function(response) {
			console.log(response.data);
			window.location.href = "#!/app/home"
		},function(error){console.log(error);});	
	}
	
	$scope.containsVideoFun = function(containsVideo) {
		$scope.userDocument.containsVideo = containsVideo;
	}
});