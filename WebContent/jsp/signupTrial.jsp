<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="s" uri="/struts-tags" %>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Sel Web Portal: Sign Up for a Free Trial</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

<link rel="stylesheet" href="./css/style.1.css">
<link rel="stylesheet" href="./css/cms.css">

<style>
    .username-help { display: none; }
    .password-help { display: none; }
    .error-container {
        border: solid 1px;
        border-radius: 8px;
        padding: 10px;
        margin-bottom: 20px;
    }

    .error-container > p.error-text, .error-container h3 {
        color: #e9424b;
    }
</style>



<body >
<main class="wrapper">
<header class="navbar wrap">
    <div class="content group">
        <div class="pull-left group">
            <div class="logo pull-left">
                <h3><a href="#" title="Sel Web Portal" class="ir">Sel&nbsp;Web&nbsp;Portal</a></h3>
            </div>
            <nav class="pull-left">
                <ul class="unstyled inline">
                    <li><a href="#">Sel&nbsp;Web&nbsp;Portal</a></li>
                </ul>
            </nav>
        </div>
        <div class="pull-right">
            <nav>
                <ul class="unstyled inline">
                        <li class="signin"><a href="<s:url value='login'/>">Sign&nbsp;in</a></li>
                </ul>
            </nav>
        </div>
    </div>
</header>




<div id="signup">
  <div class="signup-form-wrapper">
  	<s:form action='signupSubmit' method='post' cssClass="free-trial-form">
   
      <h1>Sign Up for a Free Trial</h1>
      <s:if test="hasActionErrors()">
				 <div class="form-group">
		            <div class="alert alert-danger" role="alert">
		              <s:actionerror/>
		            </div>
		          </div>
					</s:if>

  <div class="row">
        <div class="col-md-3">
          <label>First Name</label>
        </div>
        <div class="col-md-9">
          
              <input type="text" name="firstName"  value="${firstName}" class="form-control"  required="true" />
            
        </div>
      </div>
      
      
       <div class="row">
        <div class="col-md-3">
          <label>Last Name</label>
        </div>
        <div class="col-md-9">
              <input type="text" name="lastName"   value="${lastName}" class="form-control"  required="true" />
         </div>    
      </div>
      
      
      
      
      
      <div class="row ">
        <div class="col-md-3">
          <label>Email</label>
        </div>
        <div class="col-md-9">
          <input type="text" name="email"   value="${email}" class="form-control" required="true" />
          
        </div>
      </div>
     
      <div class="row ">
        <div class="col-md-3">
          <label>Username</label>
        </div>
        <div class="col-md-9">
           <input type="text" name="username"  value="${username}" autocomplete="off" id="username"  class="form-control" required="true" />
          
          <div class="username-help username-char"><span class="bullet"></span>4 characters minimum</div>
          <div class="username-help username-first"><span class="bullet"></span>Must start with a letter or number</div>
          <div class="username-help username-special"><span class="bullet"></span>Only letters, numbers, '_' and '-' are allowed</div>
          
        </div>
      </div>
      <div class="row ">
        <div class="col-md-3">
          <label>Password</label>
        </div>
        <div class="col-md-9">
           <input type="password" name="password" autocomplete="off" id="password" secure="true" class="form-control" required="true"/>
          
          <div class="password-help password-char"><span class="bullet"></span>6 characters minimum</div>
          <div class="password-help password-special"><span class="bullet"></span>Only letters, numbers, '_' and '-' are allowed</div>
          
        </div>
      </div>
      
      <div class="row ">
        <div class="col-md-3">
          <label>Application</label>
        </div>
        <div class="col-md-9">
          <input type="text" name="applicationName"   value="${applicationName}" class="form-control" required="true" />
          
        </div>
      </div>
     
      <div class="row">
        <div class="col-md-9 col-md-offset-3 submit-wrapper">
          <p>All fields are required.</p>
          <div class="row">
            <div class="col-md-6">
              <button id="submit-button" class="btn btn-danger btn-sm signup-submit" type="submit">
                Create Account <img alt="Right Arrow Icon" src="<s:url value='/'/>/images/button_arrow.png">
              </button>
            </div>
            <div class="col-md-6">
              <span class="terms-blurb">By signing up, you agree with <br>our <strong><a href="#">Terms of Service</a></strong>.</span>
            </div>
          </div>
           </s:form>
        </div>
      </div>
      
   
    
    
    <div class="plan-box-wrapper">
      <div id="plan-info-box">
        <div class="head bg-20">
          <h3>Sel Web Portal</h3>
          <h2>14 Days - No Commitment</h2>
          <div class="group">
            <div class="plan-icon">
              
            </div>
          </div>
        </div>
        <div class="body bg-90">
          <ul>
            
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>






<script>
let usernameRegex = /^[a-zA-Z0-9][a-zA-Z0-9_-]{3,}$/;
let usernameFirstRegex = /^[a-zA-Z0-9]{1}.*$/;
let specialCharsRegex = /^[a-zA-Z0-9_-]{1,}$/;
let passwordRegex = /^[a-zA-Z0-9][a-zA-Z0-9_-]{5,}$/;

let validateUsername = function() {
  let username = $('#username').val();

  if (usernameRegex.test(username)) {
    $('.username-help').hide();
  } else {
    if (username.length >= 4) {
      $('.username-char').hide();
    } else {
      $('.username-char').show();
    }
    if (usernameFirstRegex.test(username)) {
      $('.username-first').hide();
    } else {
      $('.username-first').show();
    }
    if (specialCharsRegex.test(username)) {
      $('.username-special').hide();
    } else {
      $('.username-special').show();
    }
  }
};

let validatePassword = function() {
  let password = $('#password').val();

  if (passwordRegex.test(password)) {
    $('.password-help').hide();
  } else {
    if (password.length >= 6) {
      $('.password-char').hide();
    } else {
      $('.password-char').show();
    }
    if (specialCharsRegex.test(password)) {
      $('.password-special').hide();
    } else {
      $('.password-special').show();
    }
  }
};

$(document).ready(function() {
  $('form').submit(function() {
      var button = $(this).find("button[type='submit']");
      button.prop('disabled', true);
      button.text('Signing you up ...');
  });

  $('#username').focus(function() {
    $('.username-help').show();
  });

  $('#username').keyup(function() {
    validateUsername();
  });

  $('#password').focus(function() {
    $('.password-help').show();
  });

  $('#password').keyup(function() {
    validatePassword();
  });
});
</script>
</html>