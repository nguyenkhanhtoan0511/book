(function () {
    'use strict';

	var app = angular.module('myApp', ['ui.router', 'ui.bootstrap.pagination']);

    // Constants
    app.constant('constant', {
//        'REST_BASE_PATH' : 'http://192.168.95.132:8181/cxf/api'
         'REST_BASE_PATH' : window.location.protocol + '//' + window.location.hostname + ':' + window.location.port + '/cxf/api'
    });

	app.config(function($stateProvider, $urlRouterProvider, $locationProvider) {

		$locationProvider.hashPrefix('');
		$urlRouterProvider.otherwise('/');

		var bookState = {
			name: 'book',
			url: '/',
			controller: "bookCtrl",
			templateUrl: 'src/modules/book/book.html'
		}

		var authorState = {
			name: 'author',
			url: '/author',
			controller: "authorCtrl",
			templateUrl: 'src/modules/author/author.html'
		}

		$stateProvider.state(bookState);
		$stateProvider.state(authorState);
	});

	app.controller('myCtrl', function($scope) {
		$scope.toast_msg = '';
        $scope.showToast = function(msg) {
            $scope.toast_msg = msg;
            $(document ).ready(function() {
                $('.toast').toast('show');
            });
        }
	});

    app.factory('dataFactory', function($http, constant) {
    	var dataFactory = {};
        var REST_BASE_PATH = constant.REST_BASE_PATH;

		dataFactory.getBooks = function () {
	        return $http.get(REST_BASE_PATH + '/book');
	    };

	    dataFactory.addBook = function(authorID, book) {
	    	var url = REST_BASE_PATH + '/book/author/' + authorID;
			return $http({
			    method : "POST",
			    url : url,
			    headers: {
			        'Content-Type': 'application/json'
			    },
		    	data: book
		  	});
		}

		dataFactory.updateBook = function(id, book) {
			var url = REST_BASE_PATH + '/book/' + id;
		  	return $http({
	                method: 'PUT',
	                url: url,
	                timeout : 600000,
	                headers: {
	                    'Content-Type': 'application/json'
	                },
	                data: book
	        });
		}

		dataFactory.deleteBook = function(id){
			return $http.delete(REST_BASE_PATH + '/book/' + id);
		}

	    dataFactory.getAuthors = function(){
	    	return $http.get(REST_BASE_PATH + '/author');
	    }

		dataFactory.addAuthor = function(author) {
			var url = REST_BASE_PATH + '/author';
			return $http({
			    method : "POST",
			    url : url,
			    headers: {
			        'Content-Type': 'application/json'
			    },
		    	data: author
		  	});
		}

		dataFactory.updateAuthor = function(id, author) {
			var url = REST_BASE_PATH + '/author/' + id;
		  	return $http({
	                method: 'PUT',
	                url: url,
	                timeout : 600000,
	                headers: {
	                    'Content-Type': 'application/json'
	                },
	                data: author
	            });
		}

		dataFactory.deleteAuthor = function(id){
			return $http.delete(REST_BASE_PATH + '/author/' + id);
		}

		return dataFactory;
	});

	app.controller('bookCtrl', function($scope, dataFactory) {
	  	$scope.propertyName     = ''; // set the default sort type
        $scope.reverse = false;  // set the default sort order
        $scope.searchBook  = '';     // set the default search/filter term

        $scope.sortBy = function(propertyName) {
            $scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : false;
            $scope.propertyName = propertyName;
        };

        $scope.filteredBooks = [];
		$scope.numPerPage = 5;
        $scope.totalPages = 0;
        $scope.currentPage = 1;

        $scope.filterBooks = function(currentPage) {
            $scope.currentPage = currentPage;
            var begin = ($scope.currentPage - 1)*$scope.numPerPage;
            var end = begin + $scope.numPerPage;
            $scope.filteredBooks = $scope.books.slice(begin, end);
        }

        $scope.previousPage = function(){
        	if($scope.currentPage > 1){
        		$scope.currentPage -= 1;
        		$scope.filterBooks($scope.currentPage);
        	}
        }

         $scope.nextPage = function(){
         	if($scope.currentPage < $scope.totalPages){
         		$scope.currentPage += 1;
        		$scope.filterBooks($scope.currentPage);
         	}
        }

		$scope.books = [];
		$scope.authors = [];
		$scope.select_author = null;
		$scope.today = new Date();

		var convertBookDate = function() {
			if ($scope.books.length > 0) {
				for (var i = 0; i < $scope.books.length; i++) {
					$scope.books[i].publicationDate = new Date($scope.books[i].publicationDate);
				}
			}
		}

		var getBooks = function() {
			dataFactory.getBooks().then(function(response) {
				$scope.books = response.data;
	            convertBookDate();
             	$scope.totalPages = Math.ceil($scope.books.length / $scope.numPerPage);
                $scope.filterBooks(1);
			}).catch(function(error){
				console.log(error);
			});
		}

		var getAuthors = function() {
			dataFactory.getAuthors().then(function (response) {
	            $scope.authors = response.data;
	        }), (function (error) {

	        })
		}

		// init
		getBooks();
		getAuthors();

		$scope.addBook = function(book){
			book.publicationDate = $scope.today;
			var authorID = $scope.select_author;
			dataFactory.addBook(authorID, book).then(function (response) {
	            getBooks();
                $scope.toast_msg = $scope.showToast(response.data.message);
	        })
	        .catch(function(error) {
	            console.log(error);
	             $scope.toast_msg = $scope.showToast(error.data.message);
	        });
		}

		$scope.updateBook = function(id, book){
			const author = $scope.authors.find((author) => author.lastName === book.author);
            if (null != author) {
                book.author = author.id;
            }
			dataFactory.updateBook(id, book).then(function(response) {
				getBooks();
				console.log(response);
                $scope.toast_msg = $scope.showToast(response.data.message);
			}).catch(function(error) {
                console.log(error);
                $scope.toast_msg = $scope.showToast(error.data.message);
			});
		}

		$scope.deleteBook = function(id){
			dataFactory.deleteBook(id).then(function(response) {
				getBooks();
            	$scope.toast_msg = $scope.showToast(response.data.message);
			}).catch(function(error) {
			    console.log(error);
			    $scope.toast_msg = $scope.showToast(error.data.message);
			});
		}

		$scope.refreshBook = function() {
	        getBooks();
	    }

	});

	app.controller('authorCtrl', function($scope, dataFactory) {

		$scope.propertyName     = ''; // set the default sort type
        $scope.reverse = false;  // set the default sort order
        $scope.searchAuthor   = '';     // set the default search/filter term

        $scope.sortBy = function(propertyName) {
            $scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : false;
            $scope.propertyName = propertyName;
        };

     	$scope.filteredAuthors = [];
        $scope.totalAuthorPages = 0;
		$scope.numPerAuthorPage = 5;
        $scope.currentAuthorPage = 1;

        $scope.filterAuthors = function(currentPage) {
            $scope.currentAuthorPage = currentPage;
            var begin = ($scope.currentAuthorPage - 1)*$scope.numPerAuthorPage;
            var end = begin + $scope.numPerAuthorPage;
            $scope.filteredAuthors = $scope.authors.slice(begin, end);
            console.log($scope.filteredAuthors);
        }

        $scope.previousAuthorPage = function(){
        	if($scope.currentAuthorPage > 1){
        		$scope.currentAuthorPage -= 1;
        		$scope.filterAuthors($scope.currentAuthorPage);
        	}
        }

         $scope.nextAuthorPage = function(){
         	if($scope.currentAuthorPage < $scope.totalAuthorPages){
         		$scope.currentAuthorPage += 1;
        		$scope.filterAuthors($scope.currentAuthorPage);
         	}
        }

		$scope.authors = [];
		$scope.dateDefault = new Date(1970, 0, 1);

		var convertAuthorDate = function() {
			if ($scope.authors.length > 0) {
				for (var i = 0; i < $scope.authors.length; i++) {
					$scope.authors[i].dob = new Date($scope.authors[i].dob);
				}
			}
		}

		var getAuthors = function() {
			dataFactory.getAuthors().then(function (response) {
	            $scope.authors = response.data;
	            convertAuthorDate();
	            $scope.totalAuthorPages = Math.ceil($scope.authors.length / $scope.numPerAuthorPage);
                $scope.filterAuthors(1);
	        }), (function (error) {
	        })
		}

		// init
		getAuthors();

		$scope.addAuthor = function(author){
			author.dob = $scope.dateDefault;
			dataFactory.addAuthor(author).then(function (response) {
	            getAuthors();
            	$scope.toast_msg = $scope.showToast(response.data.message);

	        })
	        .catch(function(error) {
	            console.log(error);
	            $scope.toast_msg = $scope.showToast(error.data.message);
	        });
		}

		$scope.updateAuthor = function(id, author){
			dataFactory.updateAuthor(id, author).then(function(response) {
				getAuthors();
            	$scope.toast_msg = $scope.showToast(response.data.message);

			}).catch(function(error) {
			    console.log(error);
				$scope.toast_msg = $scope.showToast(error.data.message);
			});
		}

		$scope.deleteAuthor = function(id){
			dataFactory.deleteAuthor(id).then(function(response) {
				getAuthors();
				$scope.toast_msg = $scope.showToast(response.data.message);
			}).catch(function(error) {
			    console.log(error);
				$scope.toast_msg = $scope.showToast(error.data.message);
			});
		}

		$scope.refreshAuthor = function() {
	        getAuthors();
	    }
		
	});

})();