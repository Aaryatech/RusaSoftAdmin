<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

 
<!DOCTYPE html>
<html class=" ">
    <head>
      
       <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
        <!-- CORE CSS TEMPLATE - END -->
<c:url var="clearSessionAttribute" value="/clearSessionAttribute" />
    </head>
    <!-- END HEAD -->

    <!-- BEGIN BODY -->
    <body class=" " onload="clearSessionAttribute()"><!-- START TOPBAR -->
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

    <div class='col-xs-12'>
        <div class="page-title">

            <div class="pull-left">
                <!-- PAGE HEADING TAG - START --><h1 class="title">IQAC List</h1><!-- PAGE HEADING TAG - END -->                            </div>
			 
                                
        </div>
    </div>
    <div class="clearfix"></div>
    <!-- MAIN CONTENT AREA STARTS -->
 
	 
     
<div class="col-lg-12">
    <section class="box "> 
             <header class="panel_header">
                <h2 class="title pull-left">IQAC List</h2>
                <div class="actions panel_actions pull-right">
                 <a href="${pageContext.request.contextPath}/iqacRegistration"><button type="button" class="btn btn-success">IQAC Registration</button></a>
                	<a class="box_toggle fa fa-chevron-down"></a>
                   <!--  <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a> -->
                     
                </div>
                 
            </header> 
            <div class="content-body">    <div class="row">
            <c:if test="${sessionScope.successMsg!=null}">
            <div class="col-lg-12">
    		          <div class="alert alert-success alert-dismissible fade in">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                <strong>Success : </strong> ${sessionScope.successMsg}</div>
        	                                       </div> 
            </c:if>
            
        <div class="col-xs-12">


            <table id="example-1" class="table table-striped dt-responsive display">
                <thead>
                    <tr>
                   		<th width="5%">Sr No</th>
                        <th>IQAC Name</th> 
                        <th>Designation</th> 
                         <th>Date of Joining</th> 
                        <th>Contact No.</th> 
                          <th>Email ID</th> 
                        <th width="20%">Action</th> 
                    </tr>
                </thead>

                <tfoot>
                   <tr>
                   		<th width="5%">Sr No</th>
                        <th>IQAC Name</th> 
                        <th>Designation</th> 
                         <th>Date of Joining</th> 
                        <th>Contact No.</th> 
                          <th>Email ID</th> 
                        <th width="10%">Action</th> 
                    </tr>
                </tfoot>

                <tbody>
                       <%--  <c:forEach items="${getPagesModuleList}" var="getPagesModuleList" varStatus="count"> --%>
									<tr>
											<td>1</td>
											<td>ABC</td>
											<td>Ph.D.</td>
											
												<td>1-08-2018</td>
												<td>8956231213</td>
												<td>xyz@gmail.com</td>
												<td> <a
											href="${pageContext.request.contextPath}/showFacultyDetails" title="Add HOD"
											 rel="tooltip" data-color-class = "detail" data-animate=" animated fadeIn " data-toggle="tooltip" data-original-title="Add HOD"><span
												class="glyphicon glyphicon-list"></span></a>
												<a
											href="${pageContext.request.contextPath}/showRegAcc" title="Add Account Officer"
											 rel="tooltip" data-color-class = "detail" data-animate=" animated fadeIn " data-toggle="tooltip" data-original-title="Add Account Officer"><span
												class="glyphicon glyphicon-list"></span></a>
												
												<a
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
												class="glyphicon glyphicon-edit"></span></a>
												
												
												
												</td>
											
											
											
											
											</tr>
								<%-- </c:forEach>   --%>
                </tbody>
            </table>




        </div>
    </div>
    </div>
        </section></div>



 


<!-- MAIN CONTENT AREA ENDS -->
    </section>
    </section>
    <!-- END CONTENT -->
     

     


     </div>
    <!-- END CONTAINER -->
<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

   <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
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
