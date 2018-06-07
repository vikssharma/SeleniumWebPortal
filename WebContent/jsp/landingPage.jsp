<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="s" uri="/struts-tags" %>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Sel Web Portal</title>
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
                    <li><a style="font: inherit;" href="#">Sel&nbsp;Web&nbsp;Portal</a></li>
                </ul>
            </nav>
         </div>
         
         <div class="pull-right">
            <nav class="pull-right">
            	<ul class="unstyled inline">
                        <li class="signin"><a style="font: inherit;" href="<s:url value='freetrial'/>">Free&nbsp;Trial</a></li>
                        <li class="signin"><a href="<s:url value='login'/>">Sign&nbsp;in</a></li>
                        <li class="signin"><a href="<s:url value='help'/>">Functions Detail</a></li>
                </ul>
            </nav>
         </div>
    </div>
</header>

<br>
<br>
<br><br><br>

<div >
  <div >
   	<br>				
      <div >
        <div >
          <div class="selwebportal">
          <!--  
          <h3>SEL WEB PORTAL</h3><br>
          -->
          <h3 style="padding-left:100px">LESS TEST AUTOMATION SCRIPTING. <br>
							MORE FUNCTIONAL SCRIPTS.</h3>
     	  <br>
     	  <h3 style="padding-left:100px">
		    	Accelerate your test automation script development process using our platform <br>
			    with available actions list, web testing infra for Chrome and IE, step by step results.
    	  </h3>
						<br>
           	 <!--  
           	 <div class="button-container">
							<a href="<s:url value='freetrial'/>" class="button red">Free&nbsp;Trial <img
								src="./images/button_arrow.png" height="20" width="20" alt=" "></a>
							<a href="<s:url value='login'/>" class="button light-grey video-player link-overlay"
								rel="#pb_1" style="cursor: pointer;">Sign&nbsp;in<img
								src="./images/button_arrow.png" height="20" width="20" alt=" "></a>
			</div>
			-->
			<iframe style="display:inline; padding-left:100px" width="620" height="345" src="https://www.youtube.com/embed/rsbXLn9zrgk">
			</iframe>
			<iframe style="display:inline" width="520" height="345" src="https://www.youtube.com/embed/c33yn4431Qo">
			</iframe>
			<br>
			<iframe style="padding-left:100px" width="620" height="345" src="https://www.youtube.com/embed/OxSiw8jDEtA">
			</iframe>
			<iframe style="display:inline" width="520" height="345" src="https://www.youtube.com/embed/KWXaptjanAs">
			</iframe>
          </div>
        </div>
      </div>
  </div>
</div>



</html>