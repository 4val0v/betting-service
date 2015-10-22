<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app = "app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
   <script src = "https://ajax.googleapis.com/ajax/libs/angularjs/1.3.3/angular.min.js"></script>
   
<link rel="stylesheet" type="text/css" href="resources/css/test.css"/>
   
</head>

<body ng-controller = "ctrl">

	<div class="container">
		<div class="page-header">
			<h1>${user.username }</h1>
			
			    <ul class="nav navbar-nav">
       	 		<li><a href="#" ng-click = "switchHome()">Home</a></li>
       	 	 	<li><a href="#" ng-click = "switchBets()">Bets</a>
      		</ul>
			
		</div>
		
		<div class = "container" ng-show = "home">
			
			<h3 class = "text-center">
				<p ng-bind = "errorMessage" class = "help-block"></p>
				<small>Search results for user: </small>
				<kbd ng-if = 'usersearch != ""'>{{usersearch}}</kbd>
				<kbd ng-if = 'usersearch == ""'>all</kbd>
				<small> and betting:</small> 
				<kbd ng-if = 'bettingsearch != ""'>{{bettingsearch}}</kbd>
				<kbd ng-if = 'bettingsearch == ""'>all</kbd>
			</h3>
			
		<div class = "jumbotron cont">
			
			<table class = "table" ng-repeat = "ticket in tickets">
				<thead>
					<tr>
						<th>{{ticket.idUser}}</th>
						<th>{{ticket.betting.name}}</th>
						<th>{{ticket.id}}</th>
					</tr>
					<tr>
						<th>Match</th>
						<th>Guess</th>
						<th>Odds</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat = "match in ticket.matches">
						<td>{{match.match.tip.name}}</td>
						<td>{{match.guess}}</td>
						<td ng-if = "match.guess == 1">{{match.match.oddsHome}}</td>
						<td ng-if = "match.guess == 2">{{match.match.oddsAway}}</td>
					</tr>
					<tr>
						<td>{{ticket.stake}}</td>
						<td>{{ticket.outcome}}</td>
					</tr>
				</tbody>
			
			</table>
		
		
		</div>
		
		<form class = "form-inline search-form" role = "form" name = "searchForm" ng-submit = "submitSearchForm(searchForm.$valid)" novalidate>
		
			<div class = "form-group" ng-class="{ 'has-error' : searchForm.user.$invalid && !searchForm.user.$pristine }">
				<input class = "form-control" type = "text" ng-model = "usersearch" name = "user" placeholder = "user">
			</div>
			<div class = "form-group" ng-class="{ 'has-error' : searchForm.betting.$invalid && !searchForm.betting.$pristine }">
				<input class = "form-control" type = "text" ng-model = "bettingsearch" name = "betting" placeholder = "betting">
			</div>
			<button type = "submit" class = "btn btn-default">Refresh</button>
		
		</form>
		
	</div>
	
	<div ng-show = "bets">
		
		<nav class="navbar navbar-default">
 			<div class="container-fluid">
    		<div class="navbar-header">
      			<a class="navbar-brand" href="#">Bettings:</a>
    		</div>
    		<div>
      			<ul class="nav navbar-nav">
        			<li ng-repeat = "betting in bettings"><a href="#" ng-click = "loadMatches(betting.id)">{{betting.name}}</a></li>
      			</ul>
   		 	</div>
  			</div>
		</nav>	
		
		<div class = "container cont">
		<table class = "table">
			<thead>
				<tr>
					<th>Id:</th>
					<th>Name:</th>
					<th>Odds Home:</th>
					<th>Odds Away:</th>
					<th>Max Bet:</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat = "match in matches">
					<td>{{match.idMatch}}</td>
					<td>{{match.tip.name}}</td>
					<td>{{match.oddsHome}}</td>
					<td>{{match.oddsAway}}</td>
					<td>{{match.maxBet}}</td>
				<tr>
			</tbody>
		</table>
		</div>
				
	</div>
	
		<a href = "logout">Logout</a>
	</div>
	
	<script type = "text/javascript" src = "resources/js/admin.js"></script>
	
</body>
</html>