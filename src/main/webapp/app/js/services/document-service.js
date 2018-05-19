angular.module("accounting").service('DocumentService', function($q, $http){
	var findAllDocuments = function(){
	    return $q(function(resolve, reject) {
	    	var documentsAPI = '/find/allUserDocuments';
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
		  return {
			  findAllDocuments : findAllDocuments,
			  findAllDocumentsByUserId : findAllDocumentsByUserId,
			  
		  }
});