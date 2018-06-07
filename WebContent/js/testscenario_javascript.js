function selectTestScenario(testScenarioId) {
	location.href = "selectTestScenario.action?testScenarioId="
			+ testScenarioId;
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
	} else if (modalId == "add-testscenario-modal") {
		document.getElementById("addTestScenarioErrorMsgBox").style.display = "none";
		document.getElementById("addTestscenarioModalPath").innerHTML = path;
		document.getElementById("addTestscenarioParentId").value = folderId;
	} else if (modalId == "delete-testscenario-modal") {

		if (id.charAt(0) == "f") {
			return;
		}

		document.getElementById("deleteTestscenarioModalPath").innerHTML = path;

		document.getElementById("deleteTestscenarioModalTitle").innerHTML = text;

		document.getElementById("deleteTestscenarioId").value = id.substring(1);

	}

	modal.style.display = "block";

}

function closeModal(modalId) {
	var modal = document.getElementById(modalId);
	modal.style.display = "none";
}

/** ***************** testscenario_content.jsp ******** */

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
	form.action = "saveTestScenario";
	form.submit();

}

function run() {
	// alert(1);
	document.getElementById("execute-testscenario-modal").style.display = "block";
	var form = document.getElementById("form-content");
	form.action = "runTestScenario";
	form.submit();

	location.href = "selectTestScenario.action?testScenarioId=" + testScenarioId;
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
	selects[0].name = "testScenario.stepList[" + uniqueIndex
			+ "].testCase.testCaseId";
	selects[0].setAttribute("onchange", "doAjaxGetTestCaseCall(this,"
			+ uniqueIndex + ")");

	var spans = newRow.getElementsByTagName('span');
	spans[0].id = "span_testcase_" + uniqueIndex;
	spans[0].innerHTML = "&nbsp;";

	var divs = newRow.getElementsByTagName('div');
	divs[0].id = "div_testcase_" + uniqueIndex;
	divs[0].innerHTML = "&nbsp;";

	// newRow.cells[2].innerHTML = selects[0].outerHTML;

	// Now get the inputs and modify their names
	var inputs = newRow.getElementsByTagName('input');

	// Now get the checkbox and modify its name and value
	inputs[0].value = "true";
	inputs[0].name = "testScenario.stepList[" + uniqueIndex + "].toBeExecuted";

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
	setRowsStyle('testscenarioSteps_table');
	setRowsStepId('testscenarioSteps_table');
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

function expand(index) {
	var div = document.getElementById("div_testcase_" + index);
	var expandImg = document.getElementById("img_expand[" + index + "]");
	var collapseImg = document.getElementById("img_collapse[" + index + "]");
	div.style.display = "block";
	expandImg.style.display = "none";
	collapseImg.style.display = "block";
}

function collapse(index) {
	var div = document.getElementById("div_testcase_" + index);
	var expandImg = document.getElementById("img_expand[" + index + "]");
	var collapseImg = document.getElementById("img_collapse[" + index + "]");
	div.style.display = "none";
	expandImg.style.display = "block";
	collapseImg.style.display = "none";
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

// Change the value of the outputText field
function setAjaxGetOutput() {

	// alert(xmlhttpObject.responseText);
	var jsontext = xmlhttpObject.responseText;

	var stepData = JSON.parse(jsontext);
	addToggleButtons(stepData.stepIndex);
	addTestCaseTable(stepData);
}

function handleServerResponse() {
	if (xmlhttpObject.readyState == 4) {
		if (xmlhttpObject.status == 200) {
			var jsontext = xmlhttpObject.responseText;
			var data = JSON.parse(jsontext);
			var action = data.action;
			if (action == "getTestCase") {
				setAjaxGetOutput();
			} else if (action == "add") {
				setAjaxAddOutput();
			}

		} else {
			// alert("Error during AJAX call. Please try again");
		}
	}
}

// Implement business logic
function doAjaxGetTestCaseCall(element, index) {
	// alert("ajax "+index);
	xmlhttpObject = getXMLHTTPObject();
	if (xmlhttpObject != null) {
		var URL = "getTestCaseData.action?testCaseId=" + element.value
				+ "&stepIndex=" + index;
		xmlhttpObject.open("POST", URL, true);
		xmlhttpObject.onreadystatechange = handleServerResponse;
		xmlhttpObject.send(null);
	}
}

function addToggleButtons(index) {
	var mySpan = document.getElementById("span_testcase_" + index);

	// alert(index);

	var htmlStr = '<a class="link-tooltip" href="javascript:void(0)" onclick="expand('
			+ index + ')" title="Expand Test Case Details">';
	// alert(htmlStr);
	htmlStr += '<img src="./images/toggle-expand.ico" id="img_expand['
			+ index
			+ ']" width="18" height="18" align="right" style="display:none"></a>';
	// alert(1 + htmlStr);
	htmlStr += '<a class="link-tooltip" href="javascript:void(0)" onclick="collapse('
			+ index + ')" title="Collapse Test Case Details">';
	// alert(2 + htmlStr);
	htmlStr += '<img src="./images/toggle-collapse.ico" id="img_collapse['
			+ index
			+ ']" width="18" height="18" align="right" style="display:block"></a>';
	// alert(3 + htmlStr);
	htmlStr += '<hr style="border: 0.5px #e0e0e0" />';
	// alert(4 + htmlStr);
	mySpan.innerHTML = htmlStr;
	// alert(mySpan.innerHTML);
}

function addTestCaseTable(stepData) {
	// alert("addTable");
	var myTableDiv = document.getElementById("div_testcase_"
			+ stepData.stepIndex);
	myTableDiv.style = "display : block";

	if (stepData.steps == 'undefined' || stepData.steps == null) {
		myTableDiv.innerHTML = "&nbsp;";
		return;
	}
	var table = document.createElement('TABLE');
	table.className = "grid";
	table.style = "margin-bottom: 0.75em";

	var tableBody = document.createElement('TBODY');
	table.appendChild(tableBody);

	// header row
	var tr = document.createElement('TR');
	tr.className = "header sectionRow caseDroppable"
	tableBody.appendChild(tr);

	var td = document.createElement('TH');
	td.appendChild(document.createTextNode("Action"));
	tr.appendChild(td);

	td = document.createElement('TH');
	td.appendChild(document.createTextNode("Object Property"));
	tr.appendChild(td);

	td = document.createElement('TH');
	td.appendChild(document.createTextNode("Input"));
	tr.appendChild(td);

	td = document.createElement('TH');
	td.style = "text-align: center;";
	td.width = "50px";
	tr.appendChild(td);

	for (var i = 0; i < stepData.steps.length; i++) {
		var tr = document.createElement('TR');
		tableBody.appendChild(tr);

		var td1 = document.createElement('TD');
		td1.appendChild(document
				.createTextNode(stepData.steps[i].actionDisplay));
		var hiddenInput = document.createElement("input");
		hiddenInput.type = "hidden";
		hiddenInput.value = stepData.steps[i].stepNumber;
		hiddenInput.name = "testScenario.stepList[" + stepData.stepIndex
				+ "].testCase.stepList[" + i + "].stepNumber";
		// alert(hiddenInput.name);
		td1.appendChild(hiddenInput);
		tr.appendChild(td1);

		var td2 = document.createElement('TD');

		td2.appendChild(document
				.createTextNode(stepData.steps[i].objectProperty));
		tr.appendChild(td2);

		var td3 = document.createElement('TD');
		var input = document.createElement("input");
		input.type = "text";
		input.setAttribute('maxlength', 45);
		input.value = stepData.steps[i].objectValue;
		input.name = "testScenario.stepList[" + stepData.stepIndex
				+ "].testCase.stepList[" + i + "].objectValue";

		td3.appendChild(input);
		tr.appendChild(td3);

		var td4 = document.createElement('TD');
		var check = document.createElement("input");
		check.type = "checkbox";
		check.setAttribute("disabled", "true");

		td4.appendChild(check);
		tr.appendChild(td4);
		if (stepData.steps[i].toBeExecuted) {

			check.checked = "true";
		}
	}
	myTableDiv.innerHTML = "&nbsp;";
	myTableDiv.appendChild(table);
}

function doAjaxAddCheck(type) {
	// alert("addAjax 1"+type);
	xmlhttpObject = getXMLHTTPObject();
	if (xmlhttpObject != null) {
		var URL;
		if (type == "testScenario") {
			// alert("addAjax 2");
			var title = document.getElementById("addTestScenarioTitle").value;
			URL = "checkScenarioTitle.action?title=" + title;
			// alert("addAjax 3"+URL);
		} else if (type == "folder") {
			// alert("addAjax 2 folder");
			var title = document.getElementById("addFolderTitle").value;
			URL = "checkScenarioFolderTitle.action?title=" + title;
			// alert("addAjax 3"+URL);
		}
		xmlhttpObject.open("POST", URL, true);
		xmlhttpObject.onreadystatechange = handleServerResponse;
		xmlhttpObject.send(null);
	}
}

function setAjaxAddOutput() {
	var jsontext = xmlhttpObject.responseText;
	var data = JSON.parse(jsontext);

	var type = data.type;
	// alert(data.type);
	var isDuplicate = data.isDuplicate;
	if (type == "testScenario") {
		// alert("setAjaxAddOutput"+isDuplicate);
		if (isDuplicate) {
			// alert("setAjaxAddOutput"+2);
			document.getElementById("addTestScenarioErrorMsgBox").style.display = "block";
			document.getElementById("addTestScenarioErrorMessage").innerHTML = "Test Scenario with this name exists in the Project. Please use another name.";
			// alert("setAjaxAddOutput"+3);
		} else {
			document.getElementById("addTestScenarioForm").submit();
		}

	} else if (type == "folder") {
		// alert("setAjaxAddOutput"+isDuplicate);
		if (isDuplicate) {
			// alert("setAjaxAddOutput"+2);
			document.getElementById("addFolderErrorMsgBox").style.display = "block";
			document.getElementById("addFolderErrorMessage").innerHTML = "Test Scenario Folder with this name exists in the Project. Please use another name.";
			// alert("setAjaxAddOutput"+3);
		} else {
			document.getElementById("addFolderForm").submit();
		}

	}
}