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
/* .txt {
				background-color: #FEFFB0;
				font-weight: bold;
				text-align: right;
			} */
</style>


<!-- BEGIN BODY -->
<body class=" " onload="getProgramTypeByProgram()">
	<c:url value="/checkUniqueField" var="checkUniqueField"></c:url>

	<c:url value="/getProgramTypeByProgram" var="getProgramTypeByProgram"></c:url>
	<c:url value="/getStudAdmCatwiseByProgType"
		var="getStudAdmCatwiseByProgType"></c:url>



	<!-- START TOPBAR -->
	<jsp:include page="/WEB-INF/views/include/topbar.jsp"></jsp:include>
	<!-- END TOPBAR -->
	<!-- START CONTAINER -->
	<div class="page-container row-fluid container-fluid">

		<!-- SIDEBAR - START -->

		<jsp:include page="/WEB-INF/views/include/left.jsp"></jsp:include>
		<!--  SIDEBAR - END -->
		<!-- START CONTENT -->
		<section id="main-content" class=" ">
			<section class="wrapper main-wrapper row" style="">

				<%-- 	<div class="col-xs-12">
					<div class="page-title">

						<div class="pull-left">
							<!-- PAGE HEADING TAG - START -->
							<h1 class="title">${title}</h1>
							<!-- PAGE HEADING TAG - END -->
						</div>


					</div>
				</div> --%>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->

				<div class="col-lg-12"></div>



				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>

							<%-- <div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/showStudAddmit"><button
										type="button" class="btn btn-info">Back</button></a>
							</div> --%>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">


									<!-- <ul class="nav nav-tabs">
										<li class="active"><a href="#home" data-toggle="tab">
												<i class="fa fa-home"></i> Register Form
										</a></li>


									</ul>

									<div class="tab-content">
										<div class="tab-pane fade in active" id="home"> -->


									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertStudAdmCatwise"
										method="post" name="form_sample_2" id="form_sample_2">
										<c:if test="${isEdit==0}">
											<div class="form-group">
												<label class="control-label col-sm-2" for="programType">Program
													<span class="text-danger">*</span>
												</label>
												<div class="col-sm-4">

													<select id="programType" name="programType"
														class="form-control" onchange="getProgramTypeByProgram()"
														required>
														<c:forEach items="${progTypeList}" var="progTypeList">
															<c:choose>
																<c:when
																	test="${progTypeList.programId==editProgram.programType}">
																	<option selected value="${progTypeList.programId}">${progTypeList.programName}</option>
																</c:when>
																<c:otherwise>
																	<option value="${progTypeList.programId}">${progTypeList.programName}</option>

																</c:otherwise>

															</c:choose>

														</c:forEach>
													</select>


												</div>

												<label class="control-label col-sm-2" for="programTypeId">Program
													Type <span class="text-danger">*</span>
												</label>
												<div class="col-sm-4">

													<select id="programTypeId" name="programTypeId"
														class="form-control" onchange="getStudAdmByProgType()"
														required>

													</select>


												</div>
											</div>
										</c:if>
										<c:if test="${isEdit==1}">
											<div align="center">
												Program :<b>${progName}-${progType}</b>
											</div>
											<input type="hidden" id="programTypeId" name="programTypeId"
												value="${programType}">
										</c:if>
										<div class="row">
											<div class="col-md-12">
												<table class="table table-striped dt-responsive display">
													<thead>
														<tr>
															<th width="10%">Sr No</th>
															<th width="30%">Category</th>


														</tr>
														<tr>

															<th width="10%"></th>
															<th width="30%"></th>
															<th width="20%">No. of Seats Available</th>
															<th width="20%">Male</th>
															<th width="20%">Female</th>
															<th width="20%">Transgender</th>




														</tr>
													</thead>
													<tbody>
														<c:choose>
															<c:when test="${isEdit==0}">
																<c:forEach items="${castList}" var="cast"
																	varStatus="count">
																	<tr>
																		<td>${count.index+1 }</td>
																		<td>${cast.castName}</td>

																		<td><input type="number" min="0" max="99999"
																			onkeyup="seatCount()" class="sit"
																			onchange="validateSeatCnt(${cast.castId})"
																			id="seats${cast.castId}" name="seats${cast.castId}"
																			value="0" required></td>

																		<td><input type="number" min="0" max="99999"
																			onkeyup="calculateSum()" class="txt"
																			onchange="validateSeatCnt(${cast.castId})"
																			id="cast_m${cast.castId}" name="cast_m${cast.castId}"
																			value="0" required></td>
																		<td><input type="number" min="0" max="99999"
																			onkeyup="calculateSum()" class="txt"
																			onchange="validateSeatCnt(${cast.castId})"
																			id="cast_f${cast.castId}" name="cast_f${cast.castId}"
																			value="0" required></td>
																		<td><input type="number" min="0" max="99999"
																			onkeyup="calculateSum()" class="txt"
																			onchange="validateSeatCnt(${cast.castId})"
																			id="cast_t${cast.castId}" name="cast_t${cast.castId}"
																			value="0" required></td>

																	</tr>
																</c:forEach>
															</c:when>
															<c:otherwise>

																<c:forEach items="${studAdmCastList}" var="cast"
																	varStatus="count">
																	<tr>
																		<td>${count.index+1 }R</td>
																		<td>${cast.castName}</td>

																		<td><input type="number" min="0" max="99999"
																			onkeyup="calculateSum()" class="txt"
																			id="cast_m${cast.studentCatId}"
																			onchange="validateSeatCnt(${cast.castId})"
																			name="cast_m${cast.studentCatId}"
																			value="${cast.maleStudent}" required></td>
																		<td><input type="number" min="0" max="99999"
																			onkeyup="calculateSum()" class="txt"
																			id="cast_f${cast.studentCatId}"
																			onchange="validateSeatCnt(${cast.castId})"
																			name="cast_f${cast.studentCatId}"
																			value="${cast.femaleStudent}" required></td>
																		<td><input type="number" min="0" max="99999"
																			onkeyup="calculateSum()" class="txt"
																			id="cast_t${cast.studentCatId}"
																			onchange="validateSeatCnt(${cast.castId})"
																			name="cast_t${cast.studentCatId}"
																			value="${cast.transStudent}" required></td>


																	</tr>
																</c:forEach>
															</c:otherwise>
														</c:choose>
													</tbody>
												</table>
											</div>

											<input type="hidden" id="isEdit" name="isEdit"
												value="${isEdit}"> <input type="hidden" id="is_view"
												name="is_view" value="0">

											<div class="form-group">
												<div class="col-sm-offset-2 col-sm-10">
													<button type="submit" id="sub1" class="btn btn-primary"
														onclick="submit_f(0)">
														<i class="${sessionScope.saveIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Save
													</button>
													<a href="${pageContext.request.contextPath}/showStudAddmit"><button
															type="button" id="sub2" class="btn btn-primary">
															<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Cancel
														</button></a> 
														
													

													<label class="control-label" for="fPassingYear"><b>Total
															Seats</b><span class="text-danger"></span> </label> <input
														type="text"
														style="color: black; border-radius: 15px; align-items: center;"
														readonly placeholder="Total Seats" id="total_seat">
														
														
													<label class="control-label" for="fPassingYear"><b>Total
															Students</b><span class="text-danger"></span> </label> <input
														type="text"
														style="color: black; border-radius: 15px; align-items: center;"
														readonly placeholder="Total Student" id="total_stud">
														
														
														
													<span class="error_form text-danger"
														id="total_seat_count_field" style="display: none;">All
														fields can not be 0</span>

												</div>
											</div>

											<div class="clearfix"></div>



										</div>
									</form>
									<!-- </div>

									</div>
 -->
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

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	<script>
function validateSeatCnt(castId){
	
	var male = $("#cast_m"+castId).val();
	var female = $("#cast_f"+castId).val();
	var tgendr = $("#cast_t"+castId).val();
	
	var seat = $("#seats"+castId).val();
	
	//alert("seat "+seat);
	
	var cal = parseInt(male)+parseInt(female)+parseInt(tgendr);   
	
	//alert("values "+cal);
	
	if(seat<cal){
		alert("No. of students enroled cannot be greater than seats available ");
		//document.getElementById("seats"+castId).value=0;
		document.getElementById("cast_m"+castId).value=0;
		document.getElementById("cast_f"+castId).value=0;
		document.getElementById("cast_t"+castId).value=0;
		
		
	}
	calculateSum();
}
</script>

	<script>
		function validateEmail(email) {
			var eml = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
			if (eml.test($.trim(email)) == false) {
				return false;
			}
			return true;
		}
		function validateNo(mobile) {
			var mob = /^[1-9]{1}[0-9]{0,9}$/;
			if (mob.test($.trim(mobile)) == false) {
				return false;
			}
			return true;
		}
		$(document).ready(function($) {
			
			$("#form_sample_2").submit(function(e) {
				var isError = false;
				var errMsg = "";
				var x = $("#total_stud").val();
				if ($("#total_stud").val() < 0) {
					isError = true;

					$("#total_stud").addClass("has-error")
					$("#total_stud_count_field").show()
				} else {
					$("#total_stud_count_field").hide()
				}
				
				if ($("#total_seat").val() < 0) {
					isError = true;

					$("#total_seat").addClass("has-error")
					$("#total_seat_count_field").show()
				} else {
					$("#total_seat_count_field").hide()
				}

				if (!isError) {
					var x = confirm("Do you really want to submit the form?");
					if (x == true) {
						document.getElementById("sub1").disabled = true;
						document.getElementById("sub2").disabled = true;
						return true;
					}
				}
				return false;
			});
		});
	</script>
	<script type="text/javascript">
		function checkUnique(inputValue, valueType) {
			//alert(inputValue);

			var primaryKey = $
			{
				editInst.librarianId
			}
			;
			//alert("Primary key"+primaryKey);
			var isEdit = 0;
			if (primaryKey > 0) {
				isEdit = 1;
			}
			//alert("Is Edit " +isEdit);

			var valid = false;
			if (valueType == 1) {
				//alert("Its Mob no");
				if (inputValue.length == 10) {
					valid = true;
					//alert("Len 10")
				} else {
					//alert("Not 10");
				}
			} else if (valueType == 2) {
				//alert("Its Email " );

				var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
				if (inputValue.match(mailformat)) {
					valid = true;
					//alert("Valid Email Id");
				} else {
					valid = false;
					//alert("InValid Email Id");
				}
			}
			if (valid == true)
				$
						.getJSON(
								'${checkUniqueField}',
								{

									inputValue : inputValue,
									valueType : valueType,
									primaryKey : primaryKey,
									isEdit : isEdit,
									tableId : 4,

									ajax : 'true',

								},
								function(data) {

									//	alert("Data  " +JSON.stringify(data));
									if (data.error == true) {
										if (valueType == 2) {

											alert("This email id already exist in system please enter unique");
											$('#librarian_email').val('');
											//document.getElementById("stud_contact_no").value=" ";

										} else {

											alert("This contact no  already exist in system please enter unique");
											$('#lib_con_num').val('');
											//document.getElementById("student_email").value=" ";
										}
									}
								});
		}
	</script>

	<script type="text/javascript">
		function showExtraField() {
			//alert("hii");
			//document.getElementById("abc").style = "display:none"
			var qualType = document.getElementById("approveValue").value
			//alert("qualType::"+qualType);

			if (qualType == 7) {

				document.getElementById("abc").style = "visible"

			} else {
				document.getElementById("abc").style = "display:none"
			}

		}

		function hideText() {
			//alert("hii");
			document.getElementById("abc").style = "display:none"

		}
	</script>

	<script type="text/javascript">
		var wasSubmitted = false;
		function checkBeforeSubmit() {
			if (!wasSubmitted) {
				var x = confirm("Do you really want to submit the form?");
				if (x == true) {
					wasSubmitted = true;
					document.getElementById("sub1").disabled = true;
					document.getElementById("sub2").disabled = true;

					return wasSubmitted;
				}
			}
			return false;
		}
		function submit_f(view) {

			document.getElementById("is_view").value = view;//create this 
			
		}
	</script>


	<script type="text/javascript">
		$(document).ready(function() {

			$(".txt").each(function() {
				$(this).keyup(function() {
					calculateSum();

				});
			});

		});

		function calculateSum() {
			var sum = 0;
			$(".txt").each(function() {

				if (!isNaN(this.value) && this.value.length != 0) {
					sum += parseFloat(this.value);
				}
				//getProgramTypeByProgram();

			});
			document.getElementById("total_stud").value = sum;
		}
		
		
		$(document).ready(function() {

			$(".sit").each(function() {
				$(this).keyup(function() {
					seatCount();

				});
			});

		});
		
		function seatCount() {
			var sum = 0;
			$(".sit").each(function() {

				if (!isNaN(this.value) && this.value.length != 0) {
					sum += parseFloat(this.value);
				}
				//getProgramTypeByProgram();

			});
			document.getElementById("total_seat").value = sum;
		}
	</script>



	<script type="text/javascript">
		function getProgramTypeByProgram() {
			var isEdit=${isEdit};
			if(isEdit==1){
				seatCount();
				calculateSum();
			
			}
			var programType = document.getElementById("programType").value;
			//alert("programType" + programType);

			var valid = true;

			if (programType == null || programType == "") {
				valid = false;
				alert("Please Select Program");
			}

			if (valid == true) {

				$.getJSON('${getProgramTypeByProgram}', {
					programType : programType,
					ajax : 'true',
				},

				function(data) {
					//alert(data);
					var html;
					var len = data.length;
					for (var i = 0; i < len; i++) {

						html += '<option value="' + data[i].programId + '">'
								+ data[i].nameOfProgram + '</option>';

					}
					html += '</option>';

					$('#programTypeId').html(html);
					$("#programTypeId").trigger("chosen:updated");
					getStudAdmByProgType();
				});
			}//end of if
			
		}
		
		function getStudAdmByProgType(){
			var total=0;
			var ttlSeat=0;

			var progType = document.getElementById("programTypeId").value;
			//alert("programType" + programType);
		//	alert("progType "+progType);

			var valid = true;

			if (progType == null || progType == "") {
				valid = false;
				alert("Please Select Program");
			}

			if (valid == true) {

				$.getJSON('${getStudAdmCatwiseByProgType}', {
					programType : progType,
					ajax : 'true',
				},

				function(data) {
					//alert(JSON.stringify(data));
					document.getElementById("isEdit").value="0";
					if(data.length>0){
						//alert("not null");

					for(var i=0;i<data.length;i++){
						//alert(JSON.stringify(data[i]));
						document.getElementById("cast_m"+data[i].castId).value=data[i].maleStudent;
						document.getElementById("cast_f"+data[i].castId).value=data[i].femaleStudent;
						document.getElementById("cast_t"+data[i].castId).value=data[i].transStudent;
						document.getElementById("seats"+data[i].castId).value=data[i].seats;
						total=total + parseInt(data[i].catTotStudent);
						ttlSeat=ttlSeat+parseInt(data[i].seats);
						document.getElementById("isEdit").value="1";
					}
					}else{
						//alert("null");
						$(".txt").each(function() {
							this.value="0";

						});
					}
					document.getElementById("total_seat").value=ttlSeat;
					document.getElementById("total_stud").value=total;

					
				});

			}//end of if
			
		}
	</script>


	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->




</body>
</html>