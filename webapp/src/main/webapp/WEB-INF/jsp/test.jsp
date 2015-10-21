<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.3/angular.min.js"></script>
</head>

<body ng-controller="ctrl">



	<div class="container">
	
		<p>Header</p>
		<a href = "#test1">test1</a>
		<a href = "#test2">test2</a>
	
	</div>
	<div class = "container" ng-view>
	
	
	</div>

	<script>
		var app = angular.module('app', ['ngRoute']);
		
		app.config(function($routeProvider){
			
			$routeProvider
				
				.when('/', {
					templateUrl : "test1.jsp",
					controller : "test1ctrl"
				})
				
				.when('/test2', {
					templateUrl : "test2.jsp",
					controller : "test2ctrl"
				});
			
			
		});
		
		app.controller('ctrl', function($scope) {
			$scope.message = "default";
		});
		app.controller('test1ctrl', function($scope){
			$scope.message = "test1";
		});
		app.controller('test2ctrl', function($scope){
			$scope.message = "test2";
		});
		
	</script>

</body>
</html>