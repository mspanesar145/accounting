angular.module("accounting").service('DocumentService', function($q, $http){
	
	var selectedUserDocument = {};
	
	var setSelectedUserDocument = function(selectedUserDocument) {
		selectedUserDocument = selectedUserDocument;
	}
	var getSelectedUserDocument = function(selectedUserDocument) {
		return selectedUserDocument;
	}
	
	
	var findAllDocuments = function(userId){
	    return $q(function(resolve, reject) {
	    	var documentsAPI = '/find/topTenDocuments?userId='+userId;
		      $http.get(documentsAPI).then(function(resp){
		        resolve(resp);
		      }, function(error){
		        console.log(error)
		      });
	    });
	  };
	
	  
	  var findAllDocumentsByUserId = function(){
		    return $q(function(resolve, reject) {
		    	var userData = JSON.parse(localStorage.getItem("loggedInUser"));
		    	var documentsAPI = '/find/allContentUserDocumentsForNullPdfAndCategoryIdSubCategoryId?userId='+userData.userId;
			      $http.get(documentsAPI).then(function(resp){
			        resolve(resp);
			      }, function(error){
			        console.log(error)
			      });
		    });
	  };
	  
	  var findAllDocumentsByCatSubCatId = function(qryStr){
		    return $q(function(resolve, reject) {
		    	var documentsAPI = '/find/allDocumentsByCategotyIdSubCategoryIdContainsVideo'+qryStr;
			      $http.get(documentsAPI).then(function(resp){
			        resolve(resp);
			      }, function(error){
			        console.log(error)
			      });
		    });
	  };
	  
	  var saveDocumentRatting = function(postData) {
		  return $q(function(resolve, reject) {
		    	var rattingApi = '/save/documentRating';
			      $http.post(rattingApi,postData).then(function(resp){
			        resolve(resp);
			      }, function(error){
			        console.log(error)
			      });
		    });
	  }
	  return {
		  findAllDocuments : findAllDocuments,
		  findAllDocumentsByUserId : findAllDocumentsByUserId,
		  saveDocumentRatting : saveDocumentRatting,
		  findAllDocumentsByCatSubCatId:findAllDocumentsByCatSubCatId,
		  setSelectedUserDocument:setSelectedUserDocument,
		  getSelectedUserDocument:getSelectedUserDocument
	  }
});