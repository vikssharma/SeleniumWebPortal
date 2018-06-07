<div id="folder_content" style="display: none"></div>

<div id="testscenario_content" style="display: block">
	<s:form id="form-content" theme="simple" method="post">
		<input type="hidden" name="testScenario.testScenarioId"
			value="<s:property value='testScenario.testScenarioId' />" />
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
							Test Scenario</span>
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
									Test Scenario Details </a></li>
							<li id="tablinkSteps" class="subheader-menu-item"><a
								id="tablink" href="#"
								onclick="openContainer('tablinkSteps','groupContainerSteps')">
									Test Scenario Steps </a></li>
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
								<span class="title" id="sectionName-2"><s:property
										value="testScenario.title" /></span> <input type="button"
									onClick="save();" value="Save Test Scenario"
									class="toolbar-button content-header-button button-responsive" />
								<input type="button"
									onClick="addStep('testscenarioSteps_table')" value="Add Step"
									class="toolbar-button content-header-button button-responsive" />

							</div>
							<div id="sectionDesc-2" class="scroll-auto hidden"
								style="margin-bottom: 15px">
								<div class="scroll-auto-inner markdown"></div>
							</div>
						</div>
						<br>
						<label>Do you want to stop the execution on failure of any step or test case</label>
						<s:checkbox name = "testScenario.onError" value="testScenario.onError" theme="simple" />
						<br>
						<table id="testscenarioSteps_table" class="grid"
							style="margin-bottom: 0.75em">

							<tbody>
								<tr class="header sectionRow caseDroppable">

									<td width="20px">&nbsp;</td>

									<th width="75px">Step No.</th>
									<th>Test Case</th>

									<th style="text-align: center;" width="125px">To be
										Executed</th>
									<th width="50px"><img src="./images/cancel-grey.png"
										alt="" height="16" width="16"></th>
								</tr>

								<s:iterator var="step" value="testScenario.stepList"
									status="listStatus">


									<tr id="row-3" rel="3" class="oddSelected">

										<td>&nbsp; <s:hidden
												name="testScenario.stepList[%{#listStatus.index}].id"
												value="%{#step.id}" />

										</td>
										<td class="id"><s:property value='#step.stepNumber' /></td>
										<td><s:select headerKey="-1"
												headerValue="Select Test Case" list="testCaseList"
												listKey="testCaseId" listValue="title"
												name="testScenario.stepList[%{#listStatus.index}].testCase.testCaseId"
												value="%{#step.testCase.testCaseId}" style="max-width:90%;"
												onChange="doAjaxGetTestCaseCall(this,%{#listStatus.index})" />

											<span
											id="span_testcase_<s:property value='%{#listStatus.index}' />">
												<s:if test="%{#step.testCase==null}"></s:if> <s:else>
													<a class="link-tooltip" href="javascript:void(0)"
														onclick="expand(<s:property value='%{#listStatus.index}' />)"
														title="Expand Test Case Details"> <img
														src="./images/toggle-expand.ico"
														id="img_expand[<s:property value='%{#listStatus.index}' />]"
														width="18" height="18" align="right"
														style="display: block">
													</a>

													<a class="link-tooltip" href="javascript:void(0)"
														onclick="collapse(<s:property value='%{#listStatus.index}' />)"
														title="Collapse Test Case Details"> <img
														src="./images/toggle-collapse.ico"
														id="img_collapse[<s:property value='%{#listStatus.index}' />]"
														width="18" height="18" #step.stepNumber align="right"
														style="display: none">
													</a>

													<hr style="border: 0.5px #e0e0e0" />
												</s:else>
										</span>


											<div
												id="div_testcase_<s:property value='%{#listStatus.index}' />"
												style="display: none">



												<s:if test="%{#step.testCase==null}"></s:if>
												<s:else>

													<table id="testcaseSteps_table" class="grid"
														style="margin-bottom: 0.75em">

														<tbody>
															<tr class="header sectionRow caseDroppable">

																<th width="20px">&nbsp;
																</td>


																<th>Action</th>
																<th>Object Property</th>
																<th>Input</th>
																<th style="text-align: center;" width="50px"></th>

															</tr>


															<s:iterator var="testcase_step"
																value="#step.testCase.stepList" status="a">

																<tr id="row-3" rel="3" class="oddSelected">

																	<td>&nbsp; <s:hidden
																			name="testScenario.stepList[%{#listStatus.index}].testCase.stepList[%{#a.index}].stepNumber"
																			value="%{#testcase_step.stepNumber}" />

																	</td>

																	<td><s:property
																			value='#testcase_step.actionDisplay' /></td>

																	<td><s:property
																			value='#testcase_step.objectProperty' /></td>

																	<td><s:textfield
																			name="testScenario.stepList[%{#listStatus.index}].testCase.stepList[%{#a.index}].objectValue"
																			value="%{#testcase_step.objectValue}" /></td>

																	<td style="text-align: center; vertical-align: middle;">
																		<s:if test="%{#testcase_step.toBeExecuted}">
																			<input type="checkbox" checked="checked" disabled />
																		</s:if> <s:else>
																			<input type="checkbox" disabled />
																		</s:else>

																	</td>

																</tr>

															</s:iterator>

														</tbody>
													</table>











												</s:else>

											</div></td>


































										<td style="text-align: center; vertical-align: top;"><s:checkbox
												name="testScenario.stepList[%{#listStatus.index}].toBeExecuted"
												value="%{#step.toBeExecuted}" theme="simple" /></td>
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

						<input type="button" onClick="save();" value="Save Test Scenario"
							class="toolbar-button content-header-button button-responsive" />

						<input type="button" onClick="addStep('testscenarioSteps_table')"
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
								<input type="button" onClick="save();"
									value="Save Test Scenario"
									class="toolbar-button content-header-button button-responsive" />
							</div>
							<div id="sectionDesc-2" class="scroll-auto hidden"
								style="margin-bottom: 15px">
								<div class="scroll-auto-inner markdown"></div>
							</div>
						</div>

						<table id="testscenario" class="grid"
							style="margin-bottom: 0.75em">

							<tbody>

								<tr id="row-3" rel="3"
									class="caseRow row caseDroppable oddSelected">

									<th width="20%"><strong>Test Scenario Name</strong></th>
									<td class="id"><s:property value="testScenario.title" /></td>

								</tr>

								<tr id="row-3" rel="3" class="caseRow row caseDroppable ">

									<th><strong>Created By </strong></th>
									<td><s:property value="testScenario.createdBy" /></td>

								</tr>

								<tr id="row-3" rel="3"
									class="caseRow row caseDroppable oddSelected">

									<th><strong>Modified By </strong></th>
									<td><s:property value="testScenario.modifiedBy" /></td>

								</tr>

								<tr id="row-3" rel="3" class="caseRow row caseDroppable ">

									<th><strong>Created Date </strong></th>
									<td><s:property value="testScenario.createdDate" /></td>

								</tr>

								<tr id="row-3" rel="3"
									class="caseRow row caseDroppable oddSelected">

									<th><strong>Modified Date </strong></th>
									<td><s:property value="testScenario.modifiedDate" /></td>

								</tr>



								<tr id="row-3" rel="3"
									class="caseRow row caseDroppable oddSelected">

									<th><strong>Browser </strong></th>
									<td><s:select list="browsersList"
											name="testScenario.browser" /></td>

								</tr>

								<tr id="row-3" rel="3" class="caseRow row caseDroppable ">

									<th><strong>Description </strong></th>
									<td><s:textarea name="testScenario.description" rows="8"
											cols="100" maxlength="500"></s:textarea></td>

								</tr>


							</tbody>
						</table>
					</div>
					<div>
						<input type="button" onClick="save();" value="Save Test Scenario"
							class="toolbar-button content-header-button button-responsive" />



					</div>
				</div>
			</div>


			<div id="groupContainerResults" style="margin-top: 1.5em">
				<div id="groupContent">
					<div id="groups">


						<table id="testScenario" class="grid" style="margin-bottom: 0.75em">

							<tbody>

								<tr id="row-3" rel="3"
									class="caseRow row caseDroppable oddSelected">

								</tr>

							</tbody>
						</table>
						<iframe id="testScenario" style="height: 100vh;" width="100%"
							src="<s:property value="testScenario.resultPath"/>">
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
				
				setRowsStyle("testscenarioSteps_table");

				selectTab(); 
				
				
				</script>

<div id="execute-testscenario-modal" class="modal"
	style="display: none; opacity: 0.8">

	<div class="modal-content">
		<div class="modal-header">

			<h4>Executing Test Scenario</h4>
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




