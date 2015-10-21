<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app = "app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<script src = "https://ajax.googleapis.com/ajax/libs/angularjs/1.3.3/angular.min.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/test.css"/>

<title>Insert title here</title>

</head>
<body ng-controller = "ctrl">
	<div class="container">
		<div class="page-header">
			<h1>Title</h1>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-sm-6">
			
				<form role="form" name = "loginForm" ng-submit = "submitLoginForm(loginForm.$valid)" novalidate>
					<h2 class = "text-center">Login</h2>
					<div class="form-group" ng-class="{ 'has-error' : loginForm.username.$invalid && !loginForm.username.$pristine }">
					
						<label for="username">Username:</label> 
						<input type="text" id = "usernamelogin" class="form-control" name="username" placeholder="username" ng-model = "user.username" ng-maxlength = "20" required>
						<p ng-show="loginForm.username.$invalid && !loginForm.username.$pristine" class="help-block">Username is required.</p>
					
					</div>
					
					<div class="form-group" ng-class="{ 'has-error' : loginForm.password.$invalid && !loginForm.password.$pristine }">
					
						<label for="password">Password:</label> 
						<input type="password" id = "passwordlogin" class="form-control" name="password" placeholder="password" ng-model = "user.password" ng-maxlength = "20" required>
						<p ng-show="loginForm.password.$invalid && !loginForm.password.$pristine" class="help-block">Password is required.</p>
					
					</div>
					
					<p class = "help-block" id = "errorMessageLogin" ng-bind = "errorMessageLogin"></p>
					<button type = "submit" class = "btn btn-primary">Login</button>
				</form>
				
			</div>
			<div class="col-sm-6">
				<h2 class = "text-center">Register</h2>
				<form role="form" name = "registerForm" ng-submit = "submitRegisterForm(registerForm.$valid)" novalidate>
					
					<div class="form-group"  ng-class="{ 'has-error' : registerForm.usernamereg.$invalid && !registerForm.usernamereg.$pristine }">
						<label for="username_reg">Username:</label> 
						<input type="text" class="form-control" id="username_reg" placeholder="username" name = "usernamereg" ng-model = "register.username" ng-maxlength = "20" required>
						<p ng-show="registerForm.usernamereg.$invalid && !registerForm.usernamereg.$pristine" class="help-block">Username is required.</p>
					</div>
					
					<div class="form-group"  ng-class="{ 'has-error' : registerForm.passwordreg.$invalid && !registerForm.passwordreg.$pristine }">
						<label for="password_reg">Password:</label>
						<input type="password" class="form-control" id="password_reg" placeholder="password" name = "passwordreg" ng-model = "register.password" ng-maxlength = "20" required>
						<p ng-show="registerForm.passwordreg.$invalid && !registerForm.passwordreg.$pristine" class="help-block">password is required.</p>
					</div>
					
					<div class="form-group">
						<label for="type">Type:</label> 
						<select class="form-control" id="type" ng-model = "register.type" required>
							<option>user</option>
							<option>admin</option>
						</select>
					</div>
					
					<p class = "help-block" id = "errorMessageRegister" ng-bind = "errorMessageRegister"></p>
					<button type = "submit" class = "btn btn-primary">Register</button>
				
				</form>
			</div>
		</div>
	</div>
	
	<script type = "text/javascript" src = "resources/js/index.js"></script>


</body>
</html>