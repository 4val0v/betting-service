/**
 * 
 */
		var app = angular.module("app", []);
		app.controller("ctrl", function($scope, $http){	
			
			$scope.submitLoginForm = function(valid)
			{
				if(valid)
				{
					$http({
						
						method : "POST",
						url: 'http://localhost:8080/webapp/login',
      				    headers: {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'text/javascript'},
      				    data: 'username='+encodeURIComponent($scope.user.username)+'&password='+encodeURIComponent($scope.user.password)
						
					}).success(function(data) { 
						
						if(data == "success")
						{
							window.location.href = "profile";	
						}
						else if(data == "usernamefail")
						{
							$scope.errorMessageLogin = "User doesn't exist";	
						}
						else if(data == "passwordfail")
						{
							$scope.errorMessageLogin = "Wrong password";
						}
						else if(data == "fail")
						{
							$scope.errorMessageLogin = "Wrong username or password";
						}
						
					});
				}
			};
			
			$scope.submitRegisterForm = function(valid)
			{
				if(valid)
				{
					$http({
						
						method : "POST",
						url : "http://localhost:8080/webapp/register",
						headers : { "Content-Type" : "application/x-www-form-urlencoded", "Accept" : "text/javascript" },
						data : "username=" + encodeURIComponent($scope.register.username) + "&password=" + encodeURIComponent($scope.register.password) + "&type=" + encodeURIComponent($scope.register.type)
	
					}).success(function(data){
						
						if(data == "success")
						{
							window.location.href = "profile";
						}
						else if(data == "fail")
						{
							$scope.errorMessageRegister = "Username already in use";
						}
						
					});
				}
			};
			
			
		});