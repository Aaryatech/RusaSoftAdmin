<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<!DOCTYPE html>
<html class=" ">
<head>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<!-- CORE CSS TEMPLATE - END -->
</head>
<!-- END HEAD -->

<style>
.image-preview-input {
	position: relative;
	overflow: hidden;
	margin: 0px;
	color: #333;
	background-color: #fff;
	border-color: #ccc;
}

.image-preview-input input[type=file] {
	position: absolute;
	top: 0;
	right: 0;
	margin: 0;
	padding: 0;
	font-size: 20px;
	cursor: pointer;
	opacity: 0;
	filter: alpha(opacity = 0);
}

.image-preview-input-title {
	margin-left: 2px;
}
</style>


<!-- BEGIN BODY -->
<body class=" ">
	<!-- START TOPBAR -->
<%-- 	<jsp:include page="/WEB-INF/views/include/topbar.jsp"></jsp:include> --%>
	<!-- END TOPBAR -->
	<!-- START CONTAINER -->
	<div class="page-container row-fluid container-fluid">

		<!-- SIDEBAR - START -->

		<%-- <jsp:include page="/WEB-INF/views/include/left.jsp"></jsp:include> --%>
		<!--  SIDEBAR - END -->
		<!-- START CONTENT -->
		<section id="main-content" class=" ">
			<section class="wrapper main-wrapper row" style="">

				<div class="col-xs-12">
					<div class="page-title">

						<div class="pull-left">
							<!-- PAGE HEADING TAG - START -->
							<h1 class="title">${title}</h1>
							<!-- PAGE HEADING TAG - END -->
						</div>


					</div>
				</div>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->

				<div class="col-lg-12"></div>



				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>
							<br/>
							<p style="color:red; ">Notice : This form strictly need to be filled by Institutes coming under RUSA Maharashtra Only.
You can access RUSA portal only after authorisation done by RUSA officials.</p>

							<div class="actions panel_actions pull-right">
								<%-- <a href="${pageContext.request.contextPath}/showInstituteList"><button
										type="button" class="btn btn-info">Back</button></a> <a
									class="box_toggle fa fa-chevron-down"></a> --%>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertCmsForm"
										method="post" enctype="multipart/form-data"
										name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">

										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Register Form
											</a></li>


										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>



													<div class="col-xs-12">
														<div class="form-group">
															<label class="control-label col-sm-2" for="page_name">Institute
																Name<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="inst_name"
																	name="inst_name" placeholder="Institute Name"
																	value="${page.pageName}">
															</div>
														</div>



														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Institute
																Address :<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="inst_add"
																	name="page_order" placeholder="Institute Address"
																	value="" required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Trust/Society
																Name :<span class="text-danger">*</span>
												</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="trust"
																	name="page_order" placeholder="Trust/Society Name"
																	value="" required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Trust/Society
																Address :<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="trust_address"
																	name="page_order" placeholder="Trust/Society Address"
																	value="" required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Contact
																No :<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" pattern="^[1-9]{1}[0-9]{9}$"
																	maxlength="10" class="form-control" id="contact_no"
																	name="page_order" placeholder="Landline No" required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">
																Chairman/President Name(Board of Governance) :<span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="chairman"
																	name="page_order"
																	placeholder="Name of Chairman/President(Board of Governance)"
																	required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Contact
																No :<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" pattern="^[1-9]{1}[0-9]{9}$"
																	maxlength="10" class="form-control" id="contact_no1"
																	name="page_order" placeholder="Landline No" required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Email ID(Official):
															<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="email" class="form-control" id="email_id"
																	name="page_order" placeholder="abc@xyz.com" required>
															</div>
														</div>



											<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">
															Principal Name :<span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="prici_name"
																	name="page_order"
																	placeholder="Name of Principal"
																	required>
															</div>
														</div>

														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Contact
																No :<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="text" pattern="^[1-9]{1}[0-9]{9}$"
																	maxlength="10" class="form-control" id="princi_contact"
																	name="page_order" placeholder="Landline No" required>
															</div>
															<label class="control-label col-sm-7" for="page_order" style="color:red; ">Note: OTP will be sent on this mobile number for verification<span class="text-danger">*</span>
															</label>
														</div>
														
														
	
														<div class="form-group">
															<label class="control-label col-sm-2" for="page_order">Email ID(Official):
															<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<input type="email" class="form-control" id="princi_email"
																	name="page_order" placeholder="abc@xyz.com" required>
															</div>
															
															<label class="control-label col-sm-6" for="page_order" style="color:red; ">Note: Verification mail will be sent on this Email id<span class="text-danger"></span>
															</label>
														</div>
											


													</div>

												</div>


												<div class="form-group">
													<div class="col-sm-offset-2 col-sm-10">
													<a href="#myModal2"
														data-toggle="modal"><button type="submit" onclick="getCOPO()"
																class="btn btn-info">Submit</button></a>
														<!-- <button type="submit" class="btn btn-primary"></button> -->
														<button type="reset" class="btn btn-default">Reset</button>
													</div>
												</div>




												<div class="clearfix"></div>

											</div>

										</div>

									</form>
								</div>

							</div>

						</div>
					</section>
				</div>

			</section>
		</section>

	</div>
	<!-- MAIN CONTENT AREA ENDS -->

	<!-- END CONTENT -->

	<div aria-hidden="true" role="dialog" tabindex="-1" id="myModal2"
		class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">×</button>
					<h4 class="modal-title">Institute Details Confirmation</h4>
				</div>
				<div class="modal-body">
					<%-- <form role="form"
						action="${pageContext.request.contextPath}/showModuleForm"
						method="get"> --%>
						<input type="hidden" class="form-control" id="pageId"
							name="pageId" >
						
								
								
						<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Institute Name:
							</label>
							<label  id="iqacName1" for="page_name"> 
							</label>
						</div>	
				
				<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Institute Address:
							</label>
							<label  id="instAdd" for="page_name"> 
							</label>
						</div>	
				
				<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Trust/Society Name :
							</label>
							<label  id="trustName" for="page_name"> 
							</label>
						</div>	
				
				<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Trust/Society Address :
							</label>
							<label  id="trustAdd" for="page_name"> 
							</label>
						</div>	
				
				<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Contact No :
							</label>
							<label  id="trustCon" for="page_name"> 
							</label>
						</div>	
				
				<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Chairman/President Name:
							</label>
							<label  id="chairmanName" for="page_name"> 
							</label>
						</div>	
				
				<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Contact No :
							</label>
							<label  id="ChairCon" for="page_name"> 
							</label>
						</div>	
				
				<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Email ID(Official):
							</label>
							<label  id="chairEmail" for="page_name"> 
							</label>
						</div>	
				
				<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Principal Name :
							</label>
							<label  id="princiName1" for="page_name"> 
							</label>
						</div>	
			<!-- 	-->
				<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Contact No :
							</label>
							<label  id="princiCon1" for="page_name"> 
							</label>
						</div>	
				
				<div class="form-group">
						
							<label class="control-label col-sm-6" for="page_name">Email ID(Official): 
							</label>
							<label  id="princiEmail1" for="page_name"> 
							</label>
						</div>	
				
	
					 <button type="submit" class="btn btn-primary" onclick="getOpt()">Confirm</button>
				<!-- 	</form> -->
				</div>
			</div>
		</div>
</div>
<script type="text/javascript">
	
	function getCOPO() {
		//alert("hii");
			
			var iqacName=document.getElementById("inst_name").value
			
			
			 var inst_add=document.getElementById("inst_add").value
				
				var trust_address=document.getElementById("trust_address").value
				var trust=document.getElementById("trust").value
				//var trust_address=document.getElementById("trust_address").value
				var contact_no=document.getElementById("contact_no").value
				var chairman=document.getElementById("chairman").value
				var contact_no1=document.getElementById("contact_no1").value
				var email_id=document.getElementById("email_id").value
				var prici_name=document.getElementById("prici_name").value
				var princi_contact=document.getElementById("princi_contact").value
				var princi_email=document.getElementById("princi_email").value
			
					
			
			$('#iqacName1').html(iqacName);
			$('#instAdd').html(inst_add);
			$('#trustName').html(trust);
			$('#trustAdd').html(trust_address);
			$('#trustCon').html(contact_no);
			$('#chairmanName').html(chairman);
			$('#ChairCon').html(contact_no1);
			$('#chairEmail').html(email_id);
			$('#princiName1').html(prici_name);
			$('#princiCon1').html(princi_contact);
			$('#princiEmail1').html(princi_email); 
		
			
			
		}

	
	
	
	</script>




	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>



</body>
</html>