/**
 * 
 */
	var app = angular.module("app", []);
	app.controller("ctrl", function($scope, $http){
		
		$scope.bets = false;
		$scope.home = true;
		
		$scope.tickets = [];
		$scope.matches = [];
		$scope.bettings = [];
		$scope.usersearch = "";
		$scope.bettingsearch = "";
		$scope.errorMessage = "";
		
		$http.get("http://localhost:8080/webapp/tickets/all").success(function(data, status, headers, config){ $scope.tickets = data; });

		
  		$scope.getAllTickets = function()
  		{
  			$http.get("http://localhost:8080/webapp/tickets/all").success(function(data, status, headers, config){ $scope.tickets = data; });
  		};
		
  		$scope.getTicketsForUser = function(user)
		{
	  		$http.get("http://localhost:8080/webapp/tickets/user", { 
	  			params : {
	  				user : user 
	  			}
	  		}).success(function(data, status, headers, config) { $scope.tickets = data; });
		};
		
		$scope.getTicketsForBetting = function(betting)
		{
			$http.get("http://localhost:8080/webapp/tickets/betting", {
				params : {
					name : betting
				}
			}).success(function(data, status, headers, config) { $scope.tickets = data; });
		};
		
		$scope.submitSearchForm = function(valid)
		{
			if(valid)
			{	
				if($scope.usersearch == "" && $scope.bettingsearch == "")
				{
					$http.get("http://localhost:8080/webapp/tickets/all").success(function(data, status, headers, config){ $scope.tickets = data; });
				}
				else if($scope.usersearch == "")
				{
					$http.get("http://localhost:8080/webapp/tickets/betting", {
						params : {
							name : $scope.bettingsearch
						}
					}).success(function(data) { 
						if(data.message == "Betting doesn't exist")
						{
							$scope.errorMessage = data.message;
							$scope.tickets = [];
						}
						else
						{
							$scope.errorMessage = "";
							$scope.tickets = data; 
						}
					});
				}
				else if($scope.bettingsearch == "")
				{
					$http.get("http://localhost:8080/webapp/tickets/user", {
						params : {
							user : $scope.usersearch
						}
					}).success(function(data) { 
						if(data.message == "User doesn't exist")
						{
							$scope.errorMessage = data.message;
							$scope.tickets = [];
						}
						else
						{
							$scope.errorMessage = "";
							$scope.tickets = data; 
						}
					});
				}
				else
				{
					$http.get("http://localhost:8080/webapp/tickets/mix", {
						params : {
							user : $scope.usersearch,
							betting : $scope.bettingsearch
						},
						headers : {
							"Accept" : "text/javascript"
						}
					}).success(function(data) { 
						if(data.status == "error")
						{
							$scope.errorMessage = data.message;
							$scope.tickets = [];
						}
						else
						{
							$scope.tickets = data; 
							$scope.errorMessage = "";
						}
					});
					
				}		

			}
		};
		
		$scope.switchHome = function()
		{
			$scope.home = true;
			$scope.bets = false;
		};
		
		$scope.switchBets = function()
		{
			$http.get("http://localhost:8080/webapp/bettings").success(function(data) { $scope.bettings = data; });
			
			$scope.bets = true;
			$scope.home = false;
		};
		
		$scope.loadMatches = function(betting)
		{
			$http.get("http://localhost:8080/webapp/matchesforbetting", {
				params : {
					id : betting
				}
			}).success(function(data) { $scope.matches = data; });
		};
		
	});