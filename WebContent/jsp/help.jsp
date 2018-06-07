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
                <h3><a href="<s:url value='welcome'/>" title="Sel Web Portal" class="ir">Sel&nbsp;Web&nbsp;Portal</a></h3>
            </div>
            <nav class="pull-left">
                <ul class="unstyled inline">
                    <li><a style="font: inherit;" href="<s:url value='welcome'/>">Sel&nbsp;Web&nbsp;Portal</a></li>
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
          </div>
        </div>
      </div>
  </div>
</div>

			<div class="pull-left">
				<table class="grid" style="margin-bottom: 0.75em;border: 5px; border-color: black">

							<tbody>
								<tr class="header sectionRow caseDroppable">
									<td width="40px">&nbsp;1:</td>
									<th>Function Name: </th>
									<th><b>Load Application</b></th>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Description: </td>
									<td>This function will open the application in the selected browser</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Object Property: </td>
									<td>NA</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Input: </td>
									<td>URL of the Application</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Output: </td>
									<td>NA</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Mandatory: </td>
									<td>Input</td>
								</tr>
								<tr>
									<td width="40px">&nbsp;</td>
								</tr>
								<tr>
									<td width="40px">&nbsp;2:</td>
									<th>Function Name: </th>
									<th><b>Is Element Displayed</b></th>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Description: </td>
									<td>It will check the whether the object is displayed in the application or not</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Object Property: </td>
									<td>Any property of the object like ID, Name, XPATH, CSS selector or text</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Input: </td>
									<td>NA</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Output: </td>
									<td>NA</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Mandatory: </td>
									<td>Object Property</td>
								</tr>
								<tr>
									<td width="40px">&nbsp;</td>
								</tr>
								<tr>
									<td width="40px">&nbsp;3:</td>
									<th>Function Name: </th>
									<th><b>Enter Text</b></th>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Description: </td>
									<td>It will enter the input value to the object whose property is given.</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Object Property: </td>
									<td>Any property of the object like ID, Name, XPATH, CSS selector or text</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Input: </td>
									<td>Text which needs to be entered in the object</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Output: </td>
									<td>NA</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Mandatory: </td>
									<td>Object Property and Input</td>
								</tr>
								<tr>
									<td width="40px">&nbsp;</td>
								</tr>
								<tr>
									<td width="40px">&nbsp;4:</td>
									<th>Function Name: </th>
									<th><b>Click</b></th>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Description: </td>
									<td>It will click on the object whose property is given</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Object Property: </td>
									<td>Any property of the object like ID, Name, XPATH, CSS selector or text</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Input: </td>
									<td>NA</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Output: </td>
									<td>NA</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Mandatory: </td>
									<td>Object Property</td>
								</tr>
								<tr>
									<td width="40px">&nbsp;</td>
								</tr>
								<tr>
									<td width="40px">&nbsp;5:</td>
									<th>Function Name: </th>
									<th><b>Wait Until Object Available</b></th>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Description: </td>
									<td>It will wait for 60 seconds until the object whose property is given becomes available in the application</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Object Property: </td>
									<td>Any property of the object like ID, Name, XPATH, CSS selector or text</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Input: </td>
									<td>NA</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Output: </td>
									<td>NA</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Mandatory: </td>
									<td>Object Property</td>
								</tr>
								<tr>
									<td width="40px">&nbsp;</td>
								</tr>
								<tr>
									<td width="40px">&nbsp;6:</td>
									<th>Function Name: </th>
									<th><b>Double Click</b></th>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Description: </td>
									<td>It will Doubleclick on the object whose property is given</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Object Property: </td>
									<td>Any property of the object like ID, Name, XPATH, CSS selector or text</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Input: </td>
									<td>NA</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Output: </td>
									<td>NA</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Mandatory: </td>
									<td>Object Property</td>
								</tr>
								<tr>
									<td width="40px">&nbsp;</td>
								</tr>
								<tr>
									<td width="40px">&nbsp;7:</td>
									<th>Function Name: </th>
									<th><b>Select Value from List</b></th>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Description: </td>
									<td>It will select the value mentioned in the Input from the list whose property is mentioned in Object Property Column</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Object Property: </td>
									<td>Any property of the object like ID, Name, XPATH, CSS selector or text</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Input: </td>
									<td>Text which needs to be selected from the list</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Output: </td>
									<td>NA</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Mandatory: </td>
									<td>Object Property and Input</td>
								</tr>
								<tr>
									<td width="40px">&nbsp;</td>
								</tr>
								<tr>
									<td width="40px">&nbsp;8:</td>
									<th>Function Name: </th>
									<th><b>Verify Object Text</b></th>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Description: </td>
									<td>It will verify the object text with the text mentioned in Input.</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Object Property: </td>
									<td>Any property of the object like ID, Name, XPATH, CSS selector or text</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Input: </td>
									<td>Text which needs to be verified with object text in application</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Output: </td>
									<td>NA</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Mandatory: </td>
									<td>Object Property and Input</td>
								</tr>
								<tr>
									<td width="40px">&nbsp;</td>
								</tr>
								<tr>
									<td width="40px">&nbsp;9:</td>
									<th>Function Name: </th>
									<th><b>Switch Windows</b></th>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Description: </td>
									<td>It will switch to the new window opened in the application</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Object Property: </td>
									<td>NA</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Input: </td>
									<td>NA</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Output: </td>
									<td>NA</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Mandatory: </td>
									<td>NA</td>
								</tr>
								<tr>
									<td width="40px">&nbsp;</td>
								</tr>
								<tr>
									<td width="40px">&nbsp;10:</td>
									<th>Function Name: </th>
									<th><b>WaitForTime</b></th>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Description: </td>
									<td>It will pause the execution for the time given in the Input</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Object Property: </td>
									<td>NA</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Input: </td>
									<td>Number of seconds</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Output: </td>
									<td>NA</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Mandatory: </td>
									<td>Input</td>
								</tr>
								<tr>
									<td width="40px">&nbsp;</td>
								</tr>
								<tr>
									<td width="40px">&nbsp;11:</td>
									<th>Function Name: </th>
									<th><b>Is Element Enabled</b></th>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Description: </td>
									<td>It will check the whether the object is enabled in the application or not</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Object Property: </td>
									<td>Any property of the object like ID, Name, XPATH, CSS selector or text</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Input: </td>
									<td>NA</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Output: </td>
									<td>NA</td>
								</tr>
								<tr>
								    <td width="40px">&nbsp;</td>
									<td>Mandatory: </td>
									<td>Object Property</td>
								</tr>
							</tbody>
						</table>
				</div>

</html>