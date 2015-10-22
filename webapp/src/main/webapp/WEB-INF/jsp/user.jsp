<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app = "app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/test.css"/>


</head>
<body ng-controller = "ctrl">
	<div class="container">
		<div class="page-header">
			<h1>${user.username }</h1>
    	
    		<ul class="nav navbar-nav">
       	 		<li><a href="#" ng-click = "switchHome()">Home</a></li>
       	 	 	<li><a href="#" ng-click = "switchHistory('${user.username }')">History</a></li>
       	 	 	<li><a href="#" ng-click = "switchBets()">Bets</a>
      		</ul>
      	
		</div>
	</div>
	<div class = "container">
	
	<!-- *****************************HOME VIEW********************************************** -->
	<div class="container" ng-show = "home">	
		<form role="form" name = "searchForm" ng-submit = "submitSearchForm(searchForm.$valid)" novalidate>
			
			<div class="form-group" ng-class = "{ 'has-error' : searchForm.stake.$invalid && !searchForm.stake.$pristine }">
				
				<label for="stake">Stake:</label> 
				<input ng-model = "stake" type="number" class="form-control" id="stake" name = "stake" 
					placeholder="stake" required>
				<p ng-show="searchForm.stake.$invalid && !searchForm.stake.$pristine" class="help-block">Stake is required.</p>
			
			</div>
			
			<div class="form-group" ng-class = "{ 'has-error' : searchForm.profit.$invalid && !searchForm.profit.$pristine }">
				
				<label for="profit">Profit:</label> 
				<input type="number" ng-model = "profit"
					class="form-control" id="profit" name = "profit" placeholder="desired profit" required>
				<p ng-show="searchForm.profit.$invalid && !searchForm.profit.$pristine" class="help-block">Profit is required.</p>
			
			</div>
			
			<p ng-bind = "errorMessage" class = "help-block"></p>

			<button type = "submit" class = "btn btn-default">search</button>
	
		</form>
		
		<div class = "container cont">
		<div class = "jumbotron" ng-repeat = "offer in offers">
			<table class = "table">
				<thead>
					<tr>
						<th>Ticket 1</th>
						<th>{{offer.match1.betting.name}}</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>{{offer.stake1}}</td>
						<td>{{offer.match1.tip.name}}</td>
						<td>1</td>
						<td>{{offer.match1.oddsHome}}</td>
					</tr>
				</tbody>
			</table>
			
			<table class = "table">
				<thead>
					<tr>
						<th>Ticket 2</th>
						<th>{{offer.match2.betting.name}}</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>{{offer.stake2}}</td>
						<td>{{offer.match1.tip.name}}</td>
						<td>2</td>
						<td>{{offer.match2.oddsAway}}</td>
					</tr>
				</tbody>			
			</table>
			<table class = "table">
				<thead>
					<tr>
						<th>{{offer.stake1 + offer.stake2}}</th>
						<th>{{offer.profit}}</th>
						<th><button class = "btn btn-default" id = {{$index}} ng-click = "playTicket($index)">bet</button></th>
					</tr>
				</thead>
			</table>
			
		</div>
		</div>
		
	</div>
	
	<!-- *****************************HISTORY VIEW********************************************** -->
	<div class = "container" ng-show = "history">
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
	</div>
	
	<!-- *****************************BETS VIEW********************************************** -->
	<div class = "container cont" ng-show = "bets">
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
	
	
	<a href="logout">Logout</a>
	</div>
	<script type = "text/javascript" src = "resources/js/user.js"></script>

</body>
</html>