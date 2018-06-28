angular.module("accounting").service('DocumentService', function($q, $http){
	
	var selectedUserDocument = {};
	
	var setSelectedUserDocument = function(selectedUserDocument) {
		selectedUserDocument = selectedUserDocument;
	}
	var getSelectedUserDocument = function(selectedUserDocument) {
		return selectedUserDocument;
	}
	
	
	var findAllDocuments = function(qry){
	    return $q(function(resolve, reject) {
	    	var documentsAPI = '/find/topTenDocuments'+qry;
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
	  
	  var saveDocumentComment = function(documentComment){
		    return $q(function(resolve, reject) {
		    	var documentsCommentAPI = '/save/documentComment';
		    	 $http.post(documentsCommentAPI,documentComment).then(function(resp){
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
	  
	  var findAllDocumentsCommentsByDocumentId = function(qryStr){
		    return $q(function(resolve, reject) {
		    	var documentsAPI = '/find/documentsCommentsByDocumentId'+qryStr;
			      $http.get(documentsAPI).then(function(resp){
			        resolve(resp);
			      }, function(error){
			        console.log(error)
			      });
		    });
	  };
	  
	  var findBookmarkDocumentByDocumentId = function(qryStr){
		    return $q(function(resolve, reject) {
		    	var documentsAPI = '/find/bookmarkDocument'+qryStr;
			      $http.get(documentsAPI).then(function(resp){
			        resolve(resp);
			      }, function(error){
			        console.log(error)
			      });
		    });
	  };
	  
		  var saveBookmarkDocument = function(postData){
			    return $q(function(resolve, reject) {
			    	var documentsAPI = '/save/updateBookmarkDocument';
				      $http.post(documentsAPI,postData).then(function(resp){
				        resolve(resp);
				      }, function(error){
				        console.log(error)
				      });
			    });
		  };
		  

		  var findMyAccountData = function(qry){
			    return $q(function(resolve, reject) {
			    	var documentsAPI = '/find/myAccountByCreatedById'+qry;
				      $http.get(documentsAPI).then(function(resp){
				        resolve(resp);
				      }, function(error){
				        console.log(error)
				      });
			    });
		  };
		  
		  var updateDocumentStats = function(userDocumentId,source){
			 
		  
			    return $q(function(resolve, reject) {
			    	var documentStatsSource = {};
			    	var documentsAPI = '/save/documentStats?userDocumentId='+userDocumentId+'&source='+source;
				      $http.get(documentsAPI,).then(function(resp){
				        resolve(resp);
				      }, function(error){
				        console.log(error)
				      });
			    });
		  };
		  
	
	  
	  

	  return {
		  findAllDocuments : findAllDocuments,
		  findAllDocumentsByUserId : findAllDocumentsByUserId,
		  saveDocumentRatting : saveDocumentRatting,
		  findAllDocumentsByCatSubCatId:findAllDocumentsByCatSubCatId,
		  setSelectedUserDocument:setSelectedUserDocument,
		  getSelectedUserDocument:getSelectedUserDocument,
		  saveDocumentComment:saveDocumentComment,
		  findAllDocumentsCommentsByDocumentId:findAllDocumentsCommentsByDocumentId,
		  findBookmarkDocumentByDocumentId:findBookmarkDocumentByDocumentId,
		  saveBookmarkDocument:saveBookmarkDocument,
		  findMyAccountData:findMyAccountData,
		  updateDocumentStats:updateDocumentStats,
	  }
});