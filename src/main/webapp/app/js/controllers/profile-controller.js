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
	
	$scope.uploadInit = function() {
		$scope.loggedInUser = JSON.parse(localStorage.getItem('loggedInUser'));
		$scope.findAllCategories();
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
	
	$scope.saveUserDocument = function(userDocumentData) {
		userDocumentData['createdById'] = $scope.loggedInUser.userId;
		ProfileService.saveUserDocument(userDocumentData).then(function(response) {
			console.log(response.data);
			window.location.href = "#!/app/home"
		},function(error){console.log(error);});	
	}
	
});