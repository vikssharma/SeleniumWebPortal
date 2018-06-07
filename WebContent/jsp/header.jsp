<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Sel Web Portal</title>
<link type="text/css" rel="stylesheet" href="./css/app-combined.css" media="all" />


<script type="text/javascript" src="./js/jquery.js"></script>

<!-- Adding for tree -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>

<script type="text/javascript">
$(window).load(function() {
	$(".loader").fadeOut("slow");
})
</script>

		

</head>


<body style="min-width: 810px" >
<div class="loader"></div>

<!-- Top panel above tabs-->
<div id="top">
	<div class="top-panel">
		<div class="top-panel-inner">
		<ul class="top-menu pull-right">
		 <s:if test="%{#session.USER.getLoggedIn()}">
		<li class="top-menu-item">
		
		
		
                <s:property value="%{#session.USER.firstName}" />&nbsp;
                <s:property value="%{#session.USER.lastName}" />
                
		
		
		</li>
		<li class="top-menu-item"><a class="link-noline" href="<s:url action='logout.action'/>">Logout</a></li>
		 </s:if>
		</ul>
		</div>
	</div>

	<div class="top-section top-section-with-return text-ppp">
	<a class="link-noline" href="#">SelWebPortal</a>
	</div>
</div>
<!-- End of top panel -->


