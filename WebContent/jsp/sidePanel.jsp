
			
			<!-- <div id="sidebar-splitter" class="column splitter sidebar-splitter"></div> -->
				<div id="sidebar" class="sidebar " style="width: 250px;min-height: 520px;overflow:auto;">
					
					
					<div class="sidebar-inner" >
						
						<div id="sidebarInfo" class="content-header-title" >
							<p style="margin: 0">
								Test Cases
							</p>
						</div>
						
					</div>
					
							
					<div id="sidebar-inner" class="sidebar-inner " style="padding-top: 0.5em">
								<div style="height: 43px;" >
									<div id="sidebarSticky">
										<div  id="sidebarToolbar" >
											
											<a id="addFolder" onClick="openModal('add-folder-modal')" title="Add Folder"  class="link-tooltip" style="margin-left: 24px;" href="#" > 
												<img src="./images/folder-add.png" width="20" height="20">
											</a>
											
											 
											<a id="deleteFolder" onClick="openModal('delete-folder-modal')" title="Delete Folder" class="link-tooltip" style="margin-left: 16px;" href="javascript:void(0)" onclick="this.blur(); App.Suites.copyCases(2); return false;"> 
												<img id="deleteFolderImg" src="./images/folder_delete.png" width="20" height="20">
											</a>
											
											 
											<a  id="addTestCase" onClick="openModal('add-testcase-modal')" title="Add Test Case" class="link-tooltip" style="margin-left: 16px;" href="javascript:void(0)" onclick="this.blur(); App.Suites.copyCases(2); return false;"> 
												<img src="./images/document-add.png" width="20" height="20">
											</a> 
											
											
											<a id="deleteTestCase" onClick="openModal('delete-testcase-modal')" title="Delete Test Case" class="link-tooltip" style="margin-left: 16px;" href="javascript:void(0)" onclick="this.blur(); App.Suites.copyCases(2); return false;"> 
												<img  id="deleteTestCaseImg" src="./images/document-delete.png" width="20" height="20">
											</a> 
											
											
											
										<div class="toolbar-inner">
											
										</div>
									</div>
									<div class="clear">&nbsp;</div>
									
										<div id="jstree">
										    <!-- in this example the tree is populated from inline HTML -->
										 
										    
										    <s:property value="treeString" escapeHtml="false" />
										    
										   
										   
										   
										  </div>
										 
										  
									</div>
								</div>
							</div>
				</div>
				
				
				
				
<div id="add-folder-modal" class="modal"  style="display:none">
    <s:form action="addFolder" id="addFolderForm">
        <div class="modal-content">
            <div class="modal-header">
             <span class="close" onClick="closeModal('add-folder-modal')">×</span>
                <h4>New folder</h4>
            </div>
            <div class="modal-body">
                <div class="col-md-12">
			           <div class="alert alert-danger" id="addFolderErrorMsgBox" style="display:none">
			              <label id="addFolderErrorMessage" >&nbsp;</label>
			            </div>
			          </div>
                    <div class="row">
                       <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label col-md-3">Path</label>
                                <div class="col-md-9">
                                    <label id="addFolderModalPath"> Folder 1</label>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label col-md-3">Folder name</label>
                                <div class="col-md-9">
                                    <input type="text" name="folderName" id="addFolderTitle" class="form-control" maxlength="45" autofocus="autofocus">
                                </div>
                            </div>
                        </div>
                    </div>
               
            </div>
            <div class="modal-footer">
                <input type="hidden" name="parentId" id="addFolderParentId"/>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onClick="doAjaxAddCheck('folder')">Add folder</button>
            </div>
       
        <!-- /.modal-content -->
    </div>
    </s:form>
    <!-- /.modal-dialog -->
</div>






<div id="delete-folder-modal" class="modal"  style="display:none">
    <s:form action="deleteFolder">
        <div class="modal-content">
            <div class="modal-header">
             <span class="close" onClick="closeModal('delete-folder-modal')">×</span>
                <h4>Delete folder</h4>
            </div>
            <div class="modal-body">
               
                    <div class="row">
                    	<div class="form-group">
                                <label class="control-label col-md-3">Path</label>
                                <div class="col-md-9">
                                    <label id="deleteFolderModalPath"> Folder 1</label>
                                </div>
                            </div>
                       
                        <div class="col-md-12">
                            <div class="form-group">
                                <label id="deleteFolderModalMessage">Deleting the selected folder will also delete all the sub-folders and the testcases inside the folder. Are you sure you want to delete the folder?</label>
                                
                            </div>
                        </div>
                    </div>
               
            </div>
            <div class="modal-footer">
                 <input type="hidden" name="folderId" id="deleteFolderId"/>
                <button type="button" id="deleteFolderButton" class="btn btn-primary" data-dismiss="modal" onClick="this.form.submit();">Delete Folder</button>
                 <button type="button" class="btn btn-primary" data-dismiss="modal" onClick="closeModal('delete-folder-modal')">Cancel</button>
            </div>
       
        <!-- /.modal-content -->
    </div>
    </s:form>
    <!-- /.modal-dialog -->
</div>



<div id="add-testcase-modal" class="modal"  style="display:none">
    <s:form action="addTestCase" id="addTestCaseForm">
        <div class="modal-content">
            <div class="modal-header">
             <span class="close" onClick="closeModal('add-testcase-modal')">×</span>
                <h4>Add New Test Case</h4>
            </div>
            <div class="modal-body">
               
                    <div class="row">
                    
                    <div class="col-md-12">
			           <div class="alert alert-danger" id="addTestCaseErrorMsgBox" style="display:none">
			              <label id="addTestCaseErrorMessage" >&nbsp;</label>
			            </div>
			          </div>
                    
                       <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label col-md-3">Folder</label>
                                <div class="col-md-9">
                                    <label id="addTestcaseModalPath"> Folder 1</label>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label col-md-3">Test Case Title</label>
                                <div class="col-md-9">
                                    <input name="testcaseTitle" id="addTestcaseTitle" type="text" class="form-control" maxlength="45" autofocus="autofocus">
                                </div>
                            </div>
                        </div>
                    </div>
               
            </div>
            <div class="modal-footer">
                <input type="hidden" name="folderId" id="addTestcaseParentId"/>
                <button type="button" class="btn btn-primary"  onClick="doAjaxAddCheck('testCase')" >Add Test Case</button>
            </div>
       
        <!-- /.modal-content -->
    </div>
    </s:form>
    <!-- /.modal-dialog -->
</div>






<div id="delete-testcase-modal" class="modal"  style="display:none">
    <s:form action="deleteTestCase" method="post">
        <div class="modal-content">
            <div class="modal-header">
             <span class="close" onClick="closeModal('delete-testcase-modal')">×</span>
                <h4>Delete Test Case</h4>
            </div>
            <div class="modal-body">
               
                    <div class="row">
                    	<div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label col-md-3">Path</label>
                                <div class="col-md-9">
                                    <label id="deleteTestcaseModalPath"> Folder 1</label>
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label col-md-3">Test Case Title</label>
                                <div class="col-md-9">
                                    <label id="deleteTestcaseModalTitle"> Folder 1</label>
                                </div>
                            </div>
                        </div>
                       
                        <div class="col-md-12">
                            <div class="form-group">
                                <label id="deleteTestcaseModalMessage" >Are you sure you want to delete the selected Test Case ?</label>
                                
                            </div>
                        </div>
                    </div>
               
            </div>
            <div class="modal-footer">
                <input type="hidden" name="testCaseId" id="deleteTestCaseId"/>
                <button type="button" id="deleteTestcaseButton" class="btn btn-primary" data-dismiss="modal"  onClick="this.form.submit();">Delete Test Case</button>
                 <button type="button" class="btn btn-primary" data-dismiss="modal" onClick="closeModal('delete-testcase-modal')">Cancel</button>
            </div>
       
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
    </s:form>
</div>
				
				
				
				 <script>
				 
				  $(function () {

						
					  
				    // 6 create an instance when the DOM is ready
				    $('#jstree').jstree();

				   
				    
				    // 7 bind to events triggered on the tree
				    $('#jstree').on("changed.jstree", function (e, data) {

						
				    	 var path = data.instance.get_path(data.node,'/');
				    	 // alert('Selected: ' + path); 
				    	 
				    	 var selectedNode= $('#jstree').jstree(true).get_selected('full',true);
				    	 selectedNode = selectedNode[0];

				    	 var id = selectedNode.id;
				    	 var text = selectedNode.text;
				    	 var parent = selectedNode.parent;

				    	

				    	 if(id.charAt(0)=="f"){
					    	
				    		 document.getElementById("folder_content").style.display = "block"; 
							 document.getElementById("testcase_content").style.display = "none"; 
							if(parent=="#"){
								document.getElementById("deleteFolderImg").style.opacity="0.3";
							}
							else{
								document.getElementById("deleteFolderImg").style.opacity="1";
							}
							document.getElementById("deleteTestCaseImg").style.opacity="0.3";
 
					    }
				    	 else{
					    	if(id == '<s:property value="selectedNode"/>'){ 
				    		 document.getElementById("folder_content").style.display = "none"; 
							 document.getElementById("testcase_content").style.display = "block"; 
							 document.getElementById("deleteFolderImg").style.opacity="1";
							 document.getElementById("deleteTestCaseImg").style.opacity="1";
					    	}
					    }
				          

				    	 //alert("id:"+id+" text:"+text+" parent:"+parent);
				    	
				    });
				    // 8 interact with the tree - either way is OK
				  
				      $('#jstree').jstree(true).select_node('<s:property value="selectedNode"/>');
				      
				   
				  });

				
				  </script>