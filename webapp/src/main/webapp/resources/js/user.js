/**
 * 
 */
		var app = angular.module("app", []);
		app.controller("ctrl", function($scope, $http){
			
			$scope.home = true;
			$scope.history = false;
			$scope.bets = false;
			
			$scope.index = 0;
			$scope.selBetting = "";
			
			$scope.offers = [];
			$scope.tickets = [];
			$scope.bettings = [];
			$scope.matches = [];
			
			$scope.errorMessage = "";

			
			$scope.playTicket = function(id)
			{
				offer = angular.toJson($scope.offers[id]);	
					
				$http({
						
						method : "POST",
						url : "http://localhost:8080/webapp/playticket",
						headers : { "Content-Type" : "application/x-www-form-urlencoded", "Accept" : "text/javascript" },
						data : "offer=" + encodeURIComponent(offer)
	
				}).success(function(data) { 
					window.alert(data);
					if(data == "success")
					{
						window.alert("Ticket played");
					}
					else
					{
						window.alert("Something went wrong");
					}
				});
				
			};
			
			$scope.submitSearchForm = function(valid)
			{
				if(valid)
				{
					if($scope.stake <= 0 || $scope.profit <= 0)
					{
						$scope.errorMessage = "Stake and profit must be positive numbers";
					}
					else
					{
						$scope.errorMessage = "";
						$http.get("http://localhost:8080/webapp/search", {
							params : {
								stake : $scope.stake,
								profit : $scope.profit
							}
						}).success(function(data, status, headers, config) { $scope.offers = data; });
					}
				}
			};
			
			$scope.switchHome = function()
			{
				$scope.history = false;
				$scope.bets = false;
				$scope.home = true;
			};
			
			$scope.switchBets = function()
			{
				$http.get("http://localhost:8080/webapp/bettings").success(function(data) { $scope.bettings = data; });
				
				
				$scope.history = false;
				$scope.home = false;
				$scope.bets = true;
			};
			
			$scope.switchHistory = function(user)
			{
				$http.get("http://localhost:8080/webapp/tickets/user", {
					params : {
						user : user
					}
				}).success(function(data) { 
					$scope.tickets = data;
				});
				$scope.home = false;
				$scope.bets = false;
				$scope.history = true;
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