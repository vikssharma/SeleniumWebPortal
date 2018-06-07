<%@include file="header.jsp" %>
<div id="body-container" class="">
	<div id="body" class="table" style="display: table;">
			<div id="content" class="column content">
			
			
			
			
			
			
			
			<!-- Content Header -->
				<div id="content-header" class="content-header content-header-default" >
					<div class="content-header-inner">
						<div class="toolbar content-header-toolbar">
						<a class="toolbar-button content-header-button button-responsive button-start toolbar-button-last"  href="javascript: void(0)" rel="keep-get"> 
							<span class="button-text">Run Test</span>
						</a>
						<a class="toolbar-button toolbar-button-first content-header-button button-responsive button-report dropdownLink" id="reports" href="javascript: void(0)">
							<span class="button-text">Reports</span> 
							
						</a>
						</div>
						
						<span class="content-header-icon">
						<a   rel="print" href="#"  title="Opens a print view of this test case repository."> 
						<img src="./images/print.png" width="16" height="16">
						</a>
						</span> 
						<span class="content-header-icon"> 
						<a  href="#" tooltip-header="Export Cases" title="Exports the sections and test cases into different formats (XML, Excel/CSV).">
						<img src="./images/export.png" width="16" height="16">
						</a>
						</span> 
						<span class="content-header-icon"> 
						<a  href="#" title="Imports sections and test cases from a TestRail XML or CSV file.">
						<img src="./images/import.png" width="16" height="16">
						</a>
						</span>
						<span class="content-header-icon">
						<a class="link-tooltip" href="javascript:void(0)" onclick="this.blur(); App.Suites.copyCases(2); return false;" tooltip-header="Copy or Move Cases" title="Copies or moves sections and test cases from another test suite or project.">
						<img src="./images/copyBlank.png" width="16" height="16">
						</a>
						</span>
						
						<div class="content-header-title">Test Cases</div>
						
					</div>
				</div>
					<div id="content-inner" class="content-inner goals-clear">
								
						<div style="height: 28px;">
							<div id="contentSticky">
							<div class="toolbar " id="contentToolbar">
							<a id="selectColumns-global" href="javascript:void(0)"
								onclick="this.blur(); App.Suites.selectCaseColumns(&#39;global&#39;, 2); return false;"
								class="toolbar-button button-responsive button-columns"> 
								<span class="button-text">Columns</span>
							</a> 
							<a id="deleteCases" href="javascript:void(0)"
								onclick="this.blur(); App.Dialogs.remove(&#39;l:suites_cases_toolbar_delete_cases_confirm&#39;, &#39;l:suites_cases_toolbar_delete_cases_confirm_checkbox&#39;, null, null, function() { App.Cases.removeMany(2); });; return false;"
								class="toolbar-button button-responsive button-delete"> 
								<span class="button-text">Delete</span>
							</a> 
							<a id="deleteCasesDisabled" href="javascript:void(0)"
								class="toolbar-button toolbar-button-disabled button-responsive button-delete-disabled"
								style="display: none;"> 
								<span class="button-text">Delete</span>
							</a> 
							<a id="editCases"
								href="https://octave.testrail.net/index.php?/suites/view/2&amp;group_by=cases:section_id&amp;group_order=asc&amp;group_id=2#editDropdown"
								class="toolbar-button button-responsive button-edit dropdownLink">
								<span class="button-text">Edit</span>
							</a> 
							<a id="addCase"
								href="https://octave.testrail.net/index.php?/cases/add/2"
								style="margin-left: 8px" rel="keep-get"
								class="toolbar-button toolbar-button-last button-responsive button-add">
							<span class="button-text">Add Case</span>
							</a>
							</div>
							</div>
						</div>
					
					
					
						<div id="groupContainer" style="margin-top: 1.5em">
							<div id="groupContent">
								<div id="groups">
										<div id="section-2" rel="2" class="group grid-container">
											<div class="grid-title">
											<span class="title" id="sectionName-2">Test Cases</span>
											<span class="text-secondary" id="sectionCount-2">(2)</span> 
											</div>
											<div id="sectionDesc-2" class="scroll-auto hidden" style="margin-bottom: 15px">
												<div class="scroll-auto-inner markdown"> </div>
											</div>
										</div>
								
										<table id="grid-2" class="grid" style="margin-bottom: 0.75em">
										<colgroup>
										<col class="drag-no">
										<col class="checkbox">
										<col style="width: 65px">
										<col style="">
										<col class="action-edit">
										<col class="action-delete">
										<col class="action columns">
										</colgroup>
										<tbody>
										<tr class="header sectionRow caseDroppable">
										<th class="drag-no"><img src="./images/dragNoBlue.png" alt=""></th>
										<th class="checkbox"><input type="checkbox"
											class="selectionCheckbox" onclick="App.Cases.onToggleAllClick(this)"></th>
										<th> <a class="link-noline" href="javascript:void(0)"
											onclick="this.blur(); App.Suites.setCaseGrouping(&#39;cases:id&#39;); return false;">ID</a>
										</th>
										<th> <a class="link-noline" href="javascript:void(0)"
											onclick="this.blur(); App.Suites.setCaseGrouping(&#39;cases:title&#39;); return false;">Title</a>
										</th>
										<th class="action"></th>
										<th class="action"></th>
										<th class="action columns" id="selectColumns-2"> <span
											class="select"><a href="javascript:void(0)"
											onclick="this.blur(); App.Suites.selectCaseColumns(2, 2); return false;"><img
											src="./images/columns.png" width="16" height="16" alt=""></a></span><span
											class="busy"><img src="./images/progressInline.gif" alt=""></span>
										</th>
										</tr>
										<tr id="row-3" rel="3" class="caseRow row caseDroppable oddSelected">
										<td class="drag"><div class="caseDraggable dragdrop-action"></div></td>
										<td class="checkbox"><input type="checkbox" value="3"
											class="selectionCheckbox" name="selected-3"
											onclick="App.Cases.onRowClick(this)"></td>
										<td class="id"><a class="link-noline" rel="keep-get"
											href="https://octave.testrail.net/index.php?/cases/view/3">C3</a></td>
										<td><a rel="keep-get"
											href="https://octave.testrail.net/index.php?/cases/view/3"><span
											class="title">Test Case 1</span></a> <a href="javascript:void(0)"
											onclick="this.blur(); App.Cases.editTitle(3); return false;"><img
											class="hidden action-hover" src="./images/smallEdit.png" height="10"
											width="10" alt=""></a> </td>
										<td class="action"> <a
											href="https://octave.testrail.net/index.php?/cases/edit/3"
											rel="keep-get"><img class="hidden action-hover"
											src="./images/smallEdit.png" height="10" width="10" alt=""></a>
										</td>
										<td class="action"> <a class="deleteLink" href="javascript:void(0)"
											onclick="this.blur(); App.Dialogs.remove(&#39;l:cases_delete_confirm&#39;, null, null, null, function() { App.Cases.remove(3); });; return false;"><img
											class="hidden action-hover" src="./images/smallDelete.png" alt=""
											height="10" width="10"></a> <span class="busy deleteBusy"><img
											src="./images/progressInline.gif" width="16" height="9" alt=""></span>
										</td>
										<td class="action"> <a href="javascript:void(0)"
											onclick="this.blur(); App.QPane.toggleRow(3); return false;"><span
											class="action-expand"><img class="hidden action-hover"
											src="./images/expandRow.png" alt="" height="10" width="10"></span><span
											class="action-collapse hidden"><img
											src="./images/collapseRow.png" alt="" height="10" width="10"></span><span
											class="action-expanding busy"><img
											src="./images/progressInline.gif" alt="" width="16" height="9"></span></a>
										</td>
										</tr>
										<tr id="row-4" rel="4" class="caseRow row even caseDroppable">
										<td class="drag"><div class="caseDraggable dragdrop-action"></div></td>
										<td class="checkbox"><input type="checkbox" value="4"
											class="selectionCheckbox" name="selected-4"
											onclick="App.Cases.onRowClick(this)"></td>
										<td class="id"><a class="link-noline" rel="keep-get"
											href="https://octave.testrail.net/index.php?/cases/view/4">C4</a></td>
										<td><a rel="keep-get"
											href="https://octave.testrail.net/index.php?/cases/view/4"><span
											class="title">Test Case 2</span></a> <a href="javascript:void(0)"
											onclick="this.blur(); App.Cases.editTitle(4); return false;"><img
											class="hidden action-hover" src="./images/smallEdit.png" height="10"
											width="10" alt=""></a> </td>
										<td class="action"> <a
											href="https://octave.testrail.net/index.php?/cases/edit/4"
											rel="keep-get"><img class="hidden action-hover"
											src="./images/smallEdit.png" height="10" width="10" alt=""></a>
										</td>
										<td class="action"> <a class="deleteLink" href="javascript:void(0)"
											onclick="this.blur(); App.Dialogs.remove(&#39;l:cases_delete_confirm&#39;, null, null, null, function() { App.Cases.remove(4); });; return false;"><img
											class="hidden action-hover" src="./images/smallDelete.png" alt=""
											height="10" width="10"></a> <span class="busy deleteBusy"><img
											src="./images/progressInline.gif" width="16" height="9" alt=""></span>
										</td>
										<td class="action"> <a href="javascript:void(0)"
											onclick="this.blur(); App.QPane.toggleRow(4); return false;"><span
											class="action-expand"><img class="hidden action-hover"
											src="./images/expandRow.png" alt="" height="10" width="10"></span><span
											class="action-collapse hidden"><img
											src="./images/collapseRow.png" alt="" height="10" width="10"></span><span
											class="action-expanding busy"><img
											src="./images/progressInline.gif" alt="" width="16" height="9"></span></a>
										</td>
										</tr>
										</tbody>
										</table>
								</div>
							</div>
						</div>
						<!-- end of group container -->		
					</div>
					<!-- end of content-inner -->
				</div>
				<!-- end of content header -->
			
			
				<%@include file="sidePanel.jsp" %>
			
			
			
			
			
			
			
			
			
			
			</div>
	</div>
</body>
</html>