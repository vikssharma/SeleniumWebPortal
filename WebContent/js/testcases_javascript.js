
function selectTestCase(testCaseId) {
	location.href = "selectTestCase.action?testCaseId=" + testCaseId;
}



function openModal(modalId) {
	var modal = document.getElementById(modalId);

	var selectedNode = $('#jstree').jstree(true).get_selected('full', true);
	selectedNode = selectedNode[0];

	var id = selectedNode.id;
	var text = selectedNode.text;
	var parentId = selectedNode.parent;
	var path;
	var folderId;

	if (id.charAt(0) == "f") {
		path = $('#jstree').jstree(true).get_path(selectedNode, "/");
		folderId = id.substring(1);
	} else {
		var parent = $('#jstree').jstree().get_node(parentId);
		path = $('#jstree').jstree(true).get_path(parent, "/");
		folderId = parentId.substring(1);
	}

	if (modalId == "add-folder-modal") {
		document.getElementById("addFolderErrorMsgBox").style.display = "none";
		document.getElementById("addFolderModalPath").innerHTML = path;
		document.getElementById("addFolderParentId").value = folderId;
	} else if (modalId == "delete-folder-modal") {
		if (parentId == "#") {
			return;
		}
		document.getElementById("deleteFolderModalPath").innerHTML = path;
		document.getElementById("deleteFolderId").value = folderId;
		doAjaxDelete("folder", folderId);
	} else if (modalId == "add-testcase-modal") {
		document.getElementById("addTestCaseErrorMsgBox").style.display = "none";
		document.getElementById("addTestcaseModalPath").innerHTML = path;
		document.getElementById("addTestcaseParentId").value = folderId;
	} else if (modalId == "delete-testcase-modal") {

		if (id.charAt(0) == "f") {
			return;
		}

		document.getElementById("deleteTestcaseModalPath").innerHTML = path;
		document.getElementById("deleteTestcaseModalTitle").innerHTML = text;

		document.getElementById("deleteTestCaseId").value = id.substring(1);
		doAjaxDelete("testCase", id.substring(1));
	}
	if (modalId == "add-folder-modal" || modalId == "add-testcase-modal") {
		modal.style.display = "block";
	}
}




function closeModal(modalId) {

	var modal = document.getElementById(modalId);
	modal.style.display = "none";
}




function doAjaxAddCheck(type) {
	// alert("addAjax 1"+type);
	xmlhttpObject = getXMLHTTPObject();
	if (xmlhttpObject != null) {
		var URL;
		if (type == "testCase") {
			// alert("addAjax 2");
			var title = document.getElementById("addTestcaseTitle").value;
			URL = "checkTestCaseTitle.action?title=" + title;
			// alert("addAjax 3"+URL);
		} else if (type == "folder") {
			// alert("addAjax 2 folder");
			var title = document.getElementById("addFolderTitle").value;
			URL = "checkFolderTitle.action?title=" + title;
			// alert("addAjax 3"+URL);
		}
		xmlhttpObject.open("POST", URL, true);
		xmlhttpObject.onreadystatechange = handleServerResponse;
		xmlhttpObject.send(null);
	}
}



// Change the value of the outputText field
function setAjaxDeleteOutput() {

	// alert(xmlhttpObject.responseText);
	var jsontext = xmlhttpObject.responseText;

	var data = JSON.parse(jsontext);
	var type = data.type;

	var scenarios = data.scenarios;
	if (type == "testCase") {
		if (scenarios == "0") {

		} else {
			document.getElementById("deleteTestcaseButton").style.display = "none";
			document.getElementById("deleteTestcaseModalMessage").innerHTML = "The test case cannot be deleted as it is used in the following test scenarios : <br/><br/>"
					+ scenarios
					+ "<br/><br/> Please remove the test case from the test scenarios before deletion.";
		}
		var modal = document.getElementById("delete-testcase-modal");
		modal.style.display = "block";
	} else if (type == "folder") {
		if (scenarios == "0") {

		} else {
			document.getElementById("deleteFolderButton").style.display = "none";
			document.getElementById("deleteFolderModalMessage").innerHTML = "The folder cannot be deleted as its test cases are used in the following test scenarios : <br/><br/>"
					+ scenarios
					+ "<br/><br/> Please remove the test cases from the test scenarios before deletion.";
		}
		var modal = document.getElementById("delete-folder-modal");
		modal.style.display = "block";
	}
}





function doAjaxDelete(type, id) {

	xmlhttpObject = getXMLHTTPObject();
	if (xmlhttpObject != null) {
		var URL;
		if (type == "testCase") {
			URL = "getTestCaseScenarios.action?testCaseId=" + id;
		} else if (type == "folder") {
			URL = "getFolderScenarios.action?folderId=" + id;
		}
		xmlhttpObject.open("POST", URL, true);
		xmlhttpObject.onreadystatechange = handleServerResponse;
		xmlhttpObject.send(null);
	}
}



// Get XMLHTTP Object
function getXMLHTTPObject() {
	var xmlhttpObject = null;
	try {
		// For Old Microsoft Browsers
		xmlhttpObject = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		try {
			// For Microsoft IE 6.0+
			xmlhttpObject = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e1) {
			// No Browser accepts the XMLHTTP Object then false
			xmlhttpObject = false;
		}
	}
	if (!xmlhttpObject && typeof XMLHttpRequest != 'undefined') {
		// For Mozilla, Opera Browsers
		xmlhttpObject = new XMLHttpRequest();
	}
	// Mandatory Statement returning the ajax object created
	return xmlhttpObject;
}



function handleServerResponse() {
	if (xmlhttpObject.readyState == 4) {
		if (xmlhttpObject.status == 200) {
			var jsontext = xmlhttpObject.responseText;
			var data = JSON.parse(jsontext);

			var action = data.action;
			if (action == "delete") {
				setAjaxDeleteOutput();
			} else if (action == "add") {
				setAjaxAddOutput();
			}
		} else {
			// alert("Error during AJAX call. Please try again");
		}
	}
}



function setAjaxAddOutput() {
	var jsontext = xmlhttpObject.responseText;
	var data = JSON.parse(jsontext);

	var type = data.type;
	// alert(data.type);
	var isDuplicate = data.isDuplicate;
	if (type == "testCase") {
		// alert("setAjaxAddOutput"+isDuplicate);
		if (isDuplicate) {
			// alert("setAjaxAddOutput"+2);
			document.getElementById("addTestCaseErrorMsgBox").style.display = "block";
			document.getElementById("addTestCaseErrorMessage").innerHTML = "Test Case with this name exists in the Project. Please use another name.";
			// alert("setAjaxAddOutput"+3);
		} else {
			document.getElementById("addTestCaseForm").submit();
		}

	} else if (type == "folder") {
		// alert("setAjaxAddOutput"+isDuplicate);
		if (isDuplicate) {
			// alert("setAjaxAddOutput"+2);
			document.getElementById("addFolderErrorMsgBox").style.display = "block";
			document.getElementById("addFolderErrorMessage").innerHTML = "Test Case Folder with this name exists in the Project. Please use another name.";
			// alert("setAjaxAddOutput"+3);
		} else {
			document.getElementById("addFolderForm").submit();
		}

	}
}

/** ************************** testcase_content javascript ********** */

function openContainer(evt, containerName) {
	document.getElementById("groupContainerDetails").style.display = "none";
	document.getElementById("groupContainerSteps").style.display = "none";
	document.getElementById("groupContainerResults").style.display = "none";

	document.getElementById(containerName).style.display = "block";

	document.getElementById("tablinkDetails").className = "subheader-menu-item";
	document.getElementById("tablinkSteps").className = "subheader-menu-item";
	document.getElementById("tablinkResults").className = "subheader-menu-item";
	document.getElementById(evt).className += " subheader-menu-item-selected";
	var tabIndex = 0;
	if (evt == "tablinkSteps") {
		tabIndex = 1;
	} else if (evt == "tablinkResults") {
		tabIndex = 2;
	}
	document.getElementById("selectedTab").value = tabIndex;
}



function selectTab() {
	// alert(1);
	// alert(document.getElementById("selectedTab").value);
	if (document.getElementById("selectedTab").value == "0") {
		openContainer("tablinkDetails", "groupContainerDetails");
	} else if (document.getElementById("selectedTab").value == "1") {
		openContainer("tablinkSteps", "groupContainerSteps");
	} else {
		openContainer("tablinkSteps", "groupContainerResults");
	}
}



function save() {
	// alert(1);
	var form = document.getElementById("form-content");
	form.action = "saveTestCase";
	form.submit();

}



function run() {
	// alert(1);
	document.getElementById("execute-testcase-modal").style.display = "block";
	var form = document.getElementById("form-content");
	form.action = "runTestCase";
	form.submit();
	location.href = "selectTestCase.action?testCaseId=" + testCaseId;
}



function addStep(tableID) {
	var table = document.getElementById(tableID);

	if (!table)
		return;

	var newRow = table.rows[1].cloneNode(true);

	var len = table.rows.length;
	var index = len - 1;
	var uniqueIndex = Math.floor(Math.random() * 1000);

	// remove the hidden param for id
	newRow.cells[0].innerHTML = "&nbsp;";

	// set the innerHTML of the first row
	newRow.cells[1].innerHTML = len;
	// alert(1);

	// Change the name and value of select
	var selects = newRow.getElementsByTagName('select');
	selects[0].value = "-1";
	selects[0].name = "testCase.stepList[" + uniqueIndex + "].actionId";

	// alert(2);

	// Now get the inputs and modify their names
	var inputs = newRow.getElementsByTagName('input');

	// Change name and value of object property text
	inputs[0].value = "";
	inputs[0].name = "testCase.stepList[" + uniqueIndex + "].objectProperty";

	// Change name and value of object value text
	inputs[1].value = "";
	inputs[1].name = "testCase.stepList[" + uniqueIndex + "].objectValue";

	// alert(3);

	// Now get the checkbox and modify its name and value
	inputs[2].value = "true";
	inputs[2].name = "testCase.stepList[" + uniqueIndex + "].toBeExecuted";

	// var checks = newRow.getElementsByTagName('checkbox');
	// checks[0].value="true";
	// checks[0].name="testCaseStepList["+index+"].toBeExecuted"

	// alert(4 );

	// Add the new row to the tBody (required for IE)
	var tBody = table.tBodies[0];
	tBody.insertBefore(newRow, tBody.lastChild);

	// alert(5);

	setRowsStyle(tableID);

	// alert(6);
}



function removeStep(lineItem) {
	var row = lineItem.parentNode.parentNode.parentNode;
	row.parentNode.removeChild(row);
	setRowsStyle('testcaseSteps_table');
	setRowsStepId('testcaseSteps_table');
}



function setRowsStepId(tableID) {
	var table = document.getElementById(tableID);
	var len = table.rows.length;

	for (var i = 1; i < len; i++) {
		table.rows[i].cells[1].innerHTML = i;

	}
}



function setRowsStyle(tableID) {
	var table = document.getElementById(tableID);
	var len = table.rows.length;
	// alert(len);
	for (var i = 1; i < len; i++) {

		table.rows[i].className = "";

		if (i % 2 == 0) {
			// alert("even");
		} else {
			// alert("odd");
			table.rows[i].className = "oddSelected";
		}
	}
}
