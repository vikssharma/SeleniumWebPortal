<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<title>Sel Web Portal | Login</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="<s:url value='~'/>/css/css" rel="stylesheet" type="text/css">

<link rel="stylesheet" href="./css/select.css">
<link rel="stylesheet" href="./css/styleguide.css">

<link rel="stylesheet" href="./css/panel.css">
<style type="text/css">
.message {
	background-color:#DDFFDD;
	border:1px solid #009900;
	
}
.message li{ 
	list-style: none; 
}
</style>

<body>



	<div class="login-page">
		<div class="login-form-wrapper">
		
			<div class="row">
				<h3 class="login-title">Sign In</h3>
			</div>

			<div class="row" ng-controller="loginController as ctrl">
				<s:form action='loginSubmit' method='post'>
				
				<s:if test="hasActionErrors()">
				 <div class="form-group">
		            <div class="alert alert-danger" role="alert">
		              <s:actionerror/>
		            </div>
		          </div>
				</s:if>
				
				<s:if test="hasActionMessages()">
				<div class="form-group">
				   <div class="alert alert-danger" style="background-color:#DDFFDD;" role="alert">
				      <s:actionmessage/>
				   </div>
				   </div>
				</s:if>
					
					<div class="form-group">

						<input type="text" name="username" class="form-control"
							placeholder="Username or email address" value="${username}" required=""/>
					</div>

					<div class="form-group form-group-password">

						<input type="password" name="password" class="form-control"
							placeholder="Password" required=""> 
							<!--  
							<a href="#"
							class="forgot-link" tabindex="-1">Forgot Password ?</a>
							-->
					</div>

					<div class="form-group login-form-buttons row">
						<div class="col-sm-6">
							<label class="remember"> <input name="remember_me"
								type="checkbox">Remember Me
							</label>
						</div>
						<div class="col-sm-6">
							<button type="submit" id="submit" class="btn btn-action"
								ng-disabled="ctrl.loading">Sign In</button>
						</div>
					</div>
				</s:form>

				<p class="form-group create-account">
					New to Selenium Web Portal? <a class="account-create"
						href="<s:url value='freetrial'/>">Create an Account</a>
				</p>


			</div>
		</div>
	</div>
</html>