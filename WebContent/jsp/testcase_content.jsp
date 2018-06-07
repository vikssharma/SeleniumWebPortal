<div id="folder_content" style="display: none"></div>

<div id="testcase_content" style="display: block">
	<s:form id="form-content" theme="simple" method="post">
		<input type="hidden" name="testCase.testCaseId"
			value="<s:property value='testCase.testCaseId' />" />
		<input type="hidden" id="selectedTab" name="selectedTab"
			value="<s:property value='selectedTab' />" />
		<!-- Content Header -->
		<div id="content-header" class="content-header content-header-default">
			<div class="content-header-inner">

				<div class="toolbar content-header-toolbar">
					<!-- 
						<a class="toolbar-button content-header-button button-responsive button-start toolbar-button-last"  href="javascript: void(0)" rel="keep-get"> 
							<span class="button-text">Reports</span>
						</a>
						 -->
					<a onClick="javascript:run()"
						class="toolbar-button toolbar-button-first content-header-button button-responsive button-report dropdownLink"
						href="javascript: void(0)"> <span class="button-text">Execute
							Test</span>
					</a>
				</div>


				<!--
				<span class="content-header-icon"> <a class="link-tooltip"
					href="javascript:void(0)"
					onclick="this.blur(); App.Suites.copyCases(2); return false;"
					tooltip-header="Copy or Move Cases"
					title="Copies or moves sections and test cases from another test suite or project.">
						<img src="./images/document-delete.png" width="16" height="16">
				</a>
				</span> <span class="content-header-icon"> <a class="link-tooltip"
					href="javascript:void(0)"
					onclick="this.blur(); App.Suites.copyCases(2); return false;"
					tooltip-header="Copy or Move Cases"
					title="Copies or moves sections and test cases from another test suite or project.">
						<img src="./images/copy.ico" width="16" height="16">
				</a>
				</span>
				 -->


				<div>&nbsp;</div>


			</div>

		</div>
		<div id="content-inner" class="content-inner goals-clear">

			<s:if test="hasActionErrors()">
				<div class="form-group">
					<div class="alert alert-danger" role="alert">
						<s:actionerror />
					</div>
				</div>
			</s:if>

			<s:if test="hasActionMessages()">
				<div class="form-group">
					<div class="alert alert-success" role="alert">
						<s:actionmessage />
					</div>
				</div>
			</s:if>

			<div style="height: 28px;">
				<div id="contentSticky">


					<div class="toolbar " id="contentToolbar">

						<ul class="subheader-menu">
							<li id="tablinkDetails"
								class="subheader-menu-item subheader-menu-item-selected"><a
								href="#"
								onclick="openContainer('tablinkDetails','groupContainerDetails')">
									Test Case Details </a></li>
							<li id="tablinkSteps" class="subheader-menu-item"><a
								id="tablink" href="#"
								onclick="openContainer('tablinkSteps','groupContainerSteps')">
									Test Case Steps </a></li>
							<li id="tablinkResults" class="subheader-menu-item"><a
								id="tablink" href="#"
								onclick="openContainer('tablinkResults','groupContainerResults')">
									Last Run Result </a></li>
						</ul>





					</div>
					<div id="separator" style="background-color: #a5a2a2"></div>
				</div>
			</div>



			<div id="groupContainerSteps" style="margin-top: 1.5em">
				<div id="groupContent">
					<div id="groups">
						<div id="section-2" rel="2" class="group grid-container">
							<div class="grid-title">
								<span class="title" id="sectionName-2">
								<s:property value="testCase.title" />
								</span> <input type="button"
									onClick="save();" value="Save Test"
									class="toolbar-button content-header-button button-responsive" />
								<input type="button" onClick="addStep('testcaseSteps_table')"
									value="Add Step"
									class="toolbar-button content-header-button button-responsive" />

							</div>
							<div id="sectionDesc-2" class="scroll-auto hidden"
								style="margin-bottom: 15px">
								<div class="scroll-auto-inner markdown"></div>
							</div>
						</div>
						<label>Do you want to stop the execution on failure of any step</label>
						<s:checkbox name = "testCase.onError" value="testCase.onError" theme="simple" />
						<br>
						<table id="testcaseSteps_table" class="grid"
							style="margin-bottom: 0.75em">

							<tbody>
								<tr class="header sectionRow caseDroppable">

									<td width="20px">&nbsp;</td>

									<th width="75px">Step No.</th>
									<th>Action</th>
									<th>Object Property</th>
									<th>Input</th>
									<th style="text-align: center;" width="125px">To be
										Executed</th>
									<th width="50px"><img src="./images/cancel-grey.png"
										alt="" height="16" width="16"></th>
								</tr>

								<s:iterator var="step" value="testCase.stepList"
									status="listStatus">


									<tr id="row-3" rel="3" class="oddSelected">

										<td>&nbsp; <s:hidden
												name="testCase.stepList[%{#listStatus.index}].id"
												value="%{#step.id}" />

										</td>
										<td class="id"><s:property value='#step.stepNumber' /></td>
										<td><s:select headerKey="-1" headerValue="Select Action"
												list="actionsList" listKey="actionId" listValue="display"
												name="testCase.stepList[%{#listStatus.index}].actionId"
												value="%{#step.actionId}" style="max-width:90%;" />
										</td>

										<td><s:textfield
												name="testCase.stepList[%{#listStatus.index}].objectProperty"
												value="%{#step.objectProperty}" maxlength="45" /></td>

										<td><s:textfield
												name="testCase.stepList[%{#listStatus.index}].objectValue"
												value="%{#step.objectValue}" maxlength="45" /></td>

										<td style="text-align: center; vertical-align: middle;">

											<s:checkbox
												name="testCase.stepList[%{#listStatus.index}].toBeExecuted"
												value="%{#step.toBeExecuted}" theme="simple" />
										</td>
										<td><a class="deleteLink" href="javascript:void(0)"><img
												class="hidden action-hover" onClick="removeStep(this)"
												src="./images/cancel.png" alt="" height="16" width="16"></a>
										</td>
									</tr>

								</s:iterator>


							</tbody>
						</table>
					</div>
					<div>

						<input type="button" onClick="save();" value="Save Test"
							class="toolbar-button content-header-button button-responsive" />

						<input type="button" onClick="addStep('testcaseSteps_table')"
							value="Add Step"
							class="toolbar-button content-header-button button-responsive" />



					</div>
				</div>
			</div>
			<!-- end of group container Steps-->




			<div id="groupContainerDetails" style="margin-top: 1.5em">
				<div id="groupContent">
					<div id="groups">
						<div id="section-2" rel="2" class="group grid-container">
							<div class="grid-title">
								<input type="button" onClick="save();" value="Save Test"
									class="toolbar-button content-header-button button-responsive" />
							</div>
							<div id="sectionDesc-2" class="scroll-auto hidden"
								style="margin-bottom: 15px">
								<div class="scroll-auto-inner markdown"></div>
							</div>
						</div>

						<table id="testcase" class="grid" style="margin-bottom: 0.75em">

							<tbody>

								<tr id="row-3" rel="3"
									class="caseRow row caseDroppable oddSelected">

									<th width="20%"><strong>Test Case Name</strong></th>
									<td class="id"><s:property value="testCase.title" /></td>

								</tr>

								<tr id="row-3" rel="3" class="caseRow row caseDroppable ">

									<th><strong>Created By </strong></th>
									<td><s:property value="testCase.createdBy" /></td>

								</tr>

								<tr id="row-3" rel="3"
									class="caseRow row caseDroppable oddSelected">

									<th><strong>Modified By </strong></th>
									<td><s:property value="testCase.modifiedBy" /></td>

								</tr>

								<tr id="row-3" rel="3" class="caseRow row caseDroppable ">

									<th><strong>Created Date </strong></th>
									<td><s:property value="testCase.createdDate" /></td>

								</tr>

								<tr id="row-3" rel="3"
									class="caseRow row caseDroppable oddSelected">

									<th><strong>Modified Date </strong></th>
									<td><s:property value="testCase.modifiedDate" /></td>

								</tr>

								<tr id="row-3" rel="3" class="caseRow row caseDroppable ">

									<th><strong>Last Run Status </strong></th>
									<td><s:property value="testCase.LastRunStatus" /></td>
								</tr>

								<tr id="row-3" rel="3"
									class="caseRow row caseDroppable oddSelected">

									<th><strong>Browser </strong></th>
									<td><s:select list="browsersList" name="testCase.browser" />

									</td>

								</tr>

								<tr id="row-3" rel="3" class="caseRow row caseDroppable ">

									<th><strong>Description </strong></th>
									<td><s:textarea name="testCase.description" rows="8"
											cols="100" maxlength="500"></s:textarea></td>

								</tr>


							</tbody>
						</table>
					</div>
					<div>
						<input type="button" onClick="save();" value="Save Test"
							class="toolbar-button content-header-button button-responsive" />



					</div>
				</div>
			</div>


			<div id="groupContainerResults" style="margin-top: 1.5em">
				<div id="groupContent">
					<div id="groups">


						<table id="testcase" class="grid" style="margin-bottom: 0.75em">

							<tbody>

								<tr id="row-3" rel="3"
									class="caseRow row caseDroppable oddSelected">

								</tr>

							</tbody>
						</table>
						<iframe id="testcase" style="height: 100vh;" width="100%"
							src="<s:property value="testCase.resultPath"/>">
							<p>display</p>
						</iframe>

					</div>
					<div></div>
				</div>
			</div>




		</div>
		<!-- end of content-inner -->

		<!-- end of content header -->

	</s:form>

</div>
<!-- end of testcase content -->




<script>
	
	setRowsStyle("testcaseSteps_table");

	selectTab();
</script>

<div id="execute-testcase-modal" class="modal"
	style="display: none; opacity: 0.8">

	<div class="modal-content">
		<div class="modal-header">

			<h4>Executing Test Case</h4>
		</div>
		<div class="modal-body">

			<table width="100%">
				<tr>
					<td align="center"><img src="./images/page-loader.gif">
					</td>
				</tr>
			</table>

		</div>
		<div class="modal-footer">&nbsp;</div>

		<!-- /.modal-content -->
	</div>

	<!-- /.modal-dialog -->
</div>


