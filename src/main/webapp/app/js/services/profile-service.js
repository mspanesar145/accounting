angular.module("accounting").service('ProfileService', function($q, $http){
	var validateDocument = function(document) {
		  var validateResponse = {valid:true};
		  if (!document.fileType) {
			  validateResponse['valid'] = false;
			  validateResponse['message'] = 'Please choose file type.';
		  }
		  return validateResponse;
	  }
	  
	var getMyAccountData = function(userId){
	    return $q(function(resolve, reject) {
	    	var myAccountAPI = '/find/myAccountByCreatedById?createdById='+userId;
		      $http.get(myAccountAPI).then(function(resp){
		        resolve(resp);
		      }, function(error){
		        console.log(error)
		      });
	    });
	  };
	  
	  var findAllCategories = function() {
		  return $q(function(resolve, reject) {
		    	var categoriesAPI = '/find/categories';
			      $http.get(categoriesAPI).then(function(resp){
			        resolve(resp);
			      }, function(error){
			        console.log(error)
			      });
		    });
		  
	  }
	  
	  var findSubCategoriesByCategoryId = function(parentCategoryId) {
		  return $q(function(resolve, reject) {
		    	var categoriesAPI = '/find/subCategoriesByParentId?parentCategoryId='+parentCategoryId;
			      $http.get(categoriesAPI).then(function(resp){
			        resolve(resp);
			      }, function(error){
			        console.log(error)
			      });
		    });
		  
	  }
	  
	  var saveMyAccountData = function(myAccountData){
		    return $q(function(resolve, reject) {
		    	var myAccountSaveAPI = '/api/users/update/';
		    	$http.post(myAccountSaveAPI,myAccountData).then(function(resp){
			        resolve(resp);
			      }, function(error){
			        console.log(error)
			      });
		    });
		  };
		  
		  var saveUserDocument = function(userDocumentData){
			    return $q(function(resolve, reject) {
			    	var userDocumentSaveAPI = '/save/userDocument';
			    	$http.post(userDocumentSaveAPI,userDocumentData).then(function(resp){
				        resolve(resp);
				      }, function(error){
				        console.log(error)
				      });
			    });
			  };
			    
		  
	
	  return {
		  validateDocument:validateDocument,
		  findAllCategories:findAllCategories,
		  findSubCategoriesByCategoryId:findSubCategoriesByCategoryId,
		  getMyAccountData:getMyAccountData,
		  saveMyAccountData:saveMyAccountData,
		  saveUserDocument:saveUserDocument
	  }
});  