<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.UUID"%>
<%@ page import="java.security.MessageDigest"%>
<%@ page import="java.math.BigInteger"%>
<!DOCTYPE html>
<html class=" ">
<head>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<!-- CORE CSS TEMPLATE - END -->
<c:url var="clearSessionAttribute" value="/clearSessionAttribute" />
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class=" " onload="clearSessionAttribute()">
	<!-- START TOPBAR -->
	<jsp:include page="/WEB-INF/views/include/topbar.jsp"></jsp:include>
	<!-- END TOPBAR -->
	<!-- START CONTAINER -->
	<div class="page-container row-fluid container-fluid">

		<!-- SIDEBAR - START -->

		<jsp:include page="/WEB-INF/views/include/left.jsp"></jsp:include>
		<!--  SIDEBAR - END -->
		<!-- START CONTENT -->
		<!-- START CONTENT -->
		<section id="main-content" class=" ">
			<section class="wrapper main-wrapper row" style=''>

				<!--    <div class='col-xs-12'>
        <div class="page-title">

            <div class="pull-left">
                PAGE HEADING TAG - START<h1 class="title">IQAC List</h1>PAGE HEADING TAG - END                            </div>
			 
                                
        </div>
    </div> -->
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->



				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">${title}</h2>
							<div class="actions panel_actions pull-right">
								<c:if test="${addAccess == 0}">
									<a
										href="${pageContext.request.contextPath}/rareBookInformation"><button
											type="button" class="btn btn-success">
											<i class="${sessionScope.addIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Add
										</button></a>
								</c:if>

							</div>
						</header>


						<div class="content-body">
							<div class="row">
								<c:if test="${sessionScope.successMsg!=null}">
									<div class="col-lg-12">
										<div class="alert alert-success alert-dismissible fade in">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<strong>Success : </strong> ${sessionScope.successMsg}
										</div>
									</div>
								</c:if>
								<%
									UUID uuid = UUID.randomUUID();
									MessageDigest md = MessageDigest.getInstance("MD5");
									byte[] messageDigest = md.digest(String.valueOf(uuid).getBytes());
									BigInteger number = new BigInteger(1, messageDigest);
									String hashtext = number.toString(16);
									session = request.getSession();
									session.setAttribute("generatedKey", hashtext);
								%>
								<div class="col-xs-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/delSlectedRareBook/0/<%out.println(hashtext);%>"
										method="get" name="form_sample_2" id="form_sample_2">


										<table id="example-1"
											class="table table-striped dt-responsive display">
											<thead>
												<tr>
													<th class="check" style="text-align: center; width: 5%;"><input
														type="checkbox" name="selAll" id="selAll"
														onClick="selectedInst(this)" /> Select All</th>
													<th width="5%">Sr No</th>
													<th>Book Name</th>
													<th>Author Name</th>
													<th>Publisher</th>
													<th>No. Of Copies</th>
													<th>Price</th>
													<th>Publishing Year</th>

													<th width="20%">Action</th>
												</tr>
											</thead>

											<tbody>
												<c:forEach items="${rareBokList}" var="bookList"
													varStatus="count">
													<tr>
														<td align="center"><input type="checkbox" class="chk"
															name="rareBookId" id="rareBookIds${count.index+1}"
															value="${bookList.rareBookInfoId}" /></td>
														<td style="text-align: center">${count.index+1}</td>

														<td style="text-align: left"><c:out
																value="${bookList.rareBookname}" /></td>

														<td style="text-align: left"><c:out
																value="${bookList.exVar1}" /></td>

														<td style="text-align: left"><c:out
																value="${bookList.publisher}" /></td>

														<td style="text-align: right"><c:out
																value="${bookList.bookCopies}" /></td>

														<td style="text-align: right"><c:out
																value="${bookList.costOfBook}" /></td>


														<td style="text-align: center"><c:out
																value="${bookList.publicationYear}" /></td>



														<td align="center"><c:if test="${editAccess == 0}">
																<a
																	href="${pageContext.request.contextPath}/editRareBookInfo/${bookList.rareBookInfoId}"
																	title="Edit" rel="tooltip" data-color-class="detail"
																	data-animate=" animated fadeIn " data-toggle="tooltip"
																	data-original-title="Edit IQAC"><span
																	class="glyphicon glyphicon-edit"></span></a>
															</c:if> &nbsp;&nbsp;&nbsp;&nbsp; <c:if
																test="${deleteAccess == 0}">
																<a
																	href="${pageContext.request.contextPath}/deleteRareBookInfo/${bookList.rareBookInfoId}/<%out.println(hashtext);%>"
																	onClick="return confirm('Are you sure want to delete this record');"
																	rel="tooltip" data-color-class="danger" title="Delete"
																	data-animate=" animated fadeIn " data-toggle="tooltip"
																	data-original-title="Delete  record"><span
																	class="glyphicon glyphicon-remove"></span></a>

															</c:if> &nbsp;&nbsp;&nbsp;&nbsp; <%-- <a
											href="${pageContext.request.contextPath}/showFacultyDetails" title="Add Librarian"
											 rel="tooltip" data-color-class = "detail" data-animate=" animated fadeIn " data-toggle="tooltip" data-original-title="Block"><span
												class="glyphicon glyphicon-list"></span></a>
												
												<a
											href="${pageContext.request.contextPath}/showFacultyDetails" title="Add Dean R&D"
											 rel="tooltip" data-color-class = "detail" data-animate=" animated fadeIn " data-toggle="tooltip" data-original-title="Block"><span
												class="glyphicon glyphicon-List"></span></a>
											
												
												<a
											href="${pageContext.request.contextPath}/showFacultyDetails" title="Add Librarian"
											 rel="tooltip" data-color-class = "detail" data-animate=" animated fadeIn " data-toggle="tooltip" data-original-title="Block"><span
								editIqac				class="glyphicon glyphicon-edit"></span></a> --%></td>




													</tr>
												</c:forEach>
											</tbody>
										</table>
										<c:if test="${deleteAccess==0}">
											<button class="btn btn-primary" id="deleteId"
												onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
												style="align-content: center; width: 113px; margin-left: 40px;">
												<i class="${sessionScope.deleteIcon}" aria-hidden="true"></i>&nbsp;&nbsp;Delete
											</button>
										</c:if>
										<input type="hidden" id="edit_accOff_id" name="edit_accOff_id"
											value="0">


									</form>
								</div>
							</div>
						</div>
					</section>
				</div>






				<!-- MAIN CONTENT AREA ENDS -->
			</section>
		</section>
		<!-- END CONTENT -->





	</div>
	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	<script>

   function selectedInst(source) {

		checkboxes = document.getElementsByName('rareBookId');

		for (var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;

		}

	}
   </script>

	<script>
		function clearSessionAttribute() {

			$.getJSON('${clearSessionAttribute}', {

				ajax : 'true',

			}, function(data) {

			});

		}


		}
		function showEditLibrarian(instId){
			document.getElementById("edit_lib_id").value=instId;//create this 
			var form=document.getElementById("libListForm");
		    form.setAttribute("method", "post");

			form.action=("showEditLibrarian");
			form.submit();
			
		}
	</script>
	<script>
function clearSessionAttribute() {
	 
	 

	$.getJSON('${clearSessionAttribute}', {
  
		ajax : 'true',

	}, function(data) { 
		 
	
	});

}
 </script>
</body>
</html>
