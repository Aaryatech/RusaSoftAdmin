<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html class=" ">
<head>
<c:url var="getGraph" value="/getGraph"></c:url>
<c:url var="getTotSancIntakeProgramwiseGraph"
	value="/getTotSancIntakeProgramwiseGraph"></c:url>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<!-- CORE CSS TEMPLATE - END -->

</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="" onload="">
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
			<section class="wrapper main-wrapper row" style=''>

				<div class='col-xs-12'>
					<div class="page-title">

						<div class="content-wrapper">
							<!-- Content Header (Page header) -->
							<section class="content-header">
								<h1>Dashboard</h1>

								<div class="top_slt_bx">
									<div class="slt_one">
										<div class="select-style">
											<select>
												<option value="All">Academic Year</option>
												<option value="All">2015</option>
												<option value="All">2016</option>
												<option value="All">2017</option>
											</select>
										</div>
									</div>
									<div class="slt_one">
										<div class="select-style">
											<select>
												<option value="All">Financial Year</option>
												<option value="All">2015</option>
												<option value="All">2016</option>
												<option value="All">2017</option>
											</select>
										</div>
									</div>
								</div>


								<!-- <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Dashboard</li>
      </ol>-->
							</section>

							<!-- Main content -->
							<section class="content">

								<div class="row">
									<div class="dashboard_list">
										<div class="col-md-3">
											<div class="dash_one">
												<h2 class="desig_nm">Students Admitted</h2>
												<span class="count_num">2050</span>
												<!--<a href="#" class="dash_button">Button</a>-->
												<!-- 						<p class="dash_note"><span>Note :</span> Some Note Write Here</p>
 -->
											</div>
										</div>
										<div class="col-md-3">
											<div class="dash_one">
												<h2 class="desig_nm">No of Books</h2>
												<span class="count_num">890</span>
												<!--<a href="#" class="dash_button">Button</a>-->
												<!-- 						<p class="dash_note"><span>Note :</span> Some Note Write Here</p>
 -->
											</div>
										</div>
										<div class="col-md-3">
											<div class="dash_one">
												<h2 class="desig_nm">Total Faculty</h2>
												<span class="count_num">350</span>
												<!--<a href="#" class="dash_button">Button</a>-->
												<!-- 						<p class="dash_note"><span>Note :</span> Some Note Write Here</p>
 -->
											</div>
										</div>
										<div class="col-md-3">
											<div class="dash_one">
												<h2 class="desig_nm">Total Awards</h2>
												<span class="count_num">91</span>
												<!--<a href="#" class="dash_button">Button</a>-->
												<!-- 						<p class="dash_note"><span>Note :</span> Some Note Write Here</p>
 -->
											</div>
										</div>


									</div>
								</div>
								<input name="isPrincipal" id="isPrincipal"
									value="${sessionScope.userObj.staff.isPrincipal}" type="hidden">
								<input name="isIqac" id="isIqac"
									value="${sessionScope.userObj.staff.isIqac}" type="hidden">
								<input name="isHod" id="isHod"
									value="${sessionScope.userObj.staff.isHod}" type="hidden">
								<input name="isFaculty" id="isFaculty"
									value="${sessionScope.userObj.staff.isFaculty}" type="hidden">
								<input name="isAccOff" id="isAccOff"
									value="${sessionScope.userObj.staff.isAccOff}" type="hidden">
								<input name="isDean" id="isDean"
									value="${sessionScope.userObj.staff.isDean}" type="hidden">
								<input name="isLibrarian" id="isLibrarian"
									value="${sessionScope.userObj.staff.isLibrarian}" type="hidden">
								<input name="isStudent" id="isStudent"
									value="${sessionScope.userObj.staff.isStudent}" type="hidden">
								<input name="isTpo" id="isTpo"
									value="${sessionScope.userObj.staff.isTpo}" type="hidden">
								<input name="isExtActOff" id="isExtActOff"
									value="${sessionScope.userObj.staff.isExtActOff}" type="hidden">

								<c:if
									test="${sessionScope.userObj.staff.isPrincipal==1 || sessionScope.userObj.staff.isIqac==1}">
									<div class="row">

										<!-- left boxes -->
										<div class="col-md-6">

											<div class="box box-primary">
												<div class="box-header with-border">
													<h3 class="box-title">Sanctioned Intake and No. of
														Students Admitted</h3>

												</div>
												<div class="box-body chart-responsive">
													<div class="chart" id="intake_chart" style="height: 300px;"></div>
												</div>

											</div>
											<c:if test="${sessionScope.userObj.staff.isIqac==1}">
												<div class="box box-primary">
													<div class="box-header with-border">
														<h3 class="box-title">Student Support Schemes</h3>

													</div>
													<div class="box-body chart-responsive">
														<div class="chart" id="student_support_scheme"
															style="height: 300px;"></div>
													</div>

												</div>

											</c:if>


										</div>
										<!-- end left boxes -->

										<!-- right boxes -->
										<div class="col-md-6">

											<div class="box box-primary">
												<div class="box-header with-border">
													<h3 class="box-title">Sanctioned Intake and No. of
														Students Admitted Program Wise</h3>

												</div>
												<div class="box-body chart-responsive">
													<div class="chart" id="intake_chart_byprogram"
														style="height: 300px;"></div>
												</div>

											</div>

										</div>
										<!-- end right boxes -->
									</div>
									<!-- /.row -->
								</c:if>

								<!-- <div class="row">

									left boxes
									<div class="col-md-6">

										<div class="box box-primary">
											<div class="box-header with-border">
												<h3 class="box-title">Sanctioned Intake and No. of
													Students Admitted</h3>

											</div>
											<div class="box-body chart-responsive">
												<div class="chart" id="intake_chart" style="height: 300px;"></div>
											</div>

										</div>



										<div class="box box-primary">
											<div class="box-header with-border">
												<h3 class="box-title">No of Programs</h3>

											</div>
											<div class="box-body chart-responsive">
												<div class="chart" id="sales-chart"
													style="height: 300px; position: relative;"></div>
											</div>

										</div>


									</div>
									end left boxes

									right boxes
									<div class="col-md-6">

										<div class="box box-primary">
											<div class="box-header with-border">
												<h3 class="box-title">Sanctioned Intake and No. of
													Students Admitted Program Wise</h3>

											</div>
											<div class="box-body chart-responsive">
												<div class="chart" id="line-chart" style="height: 300px;"></div>
											</div>

										</div>

										<div class="box box-primary">
											<div class="box-header with-border">
												<h3 class="box-title">Sanctioned Intake and No. of
													Students Admitted</h3>

											</div>
											<div class="box-body chart-responsive">
												<div class="chart" id="intake_chart1" style="height: 300px;"></div>
											</div>

										</div>

									</div>
									end right boxes
								</div> -->
								<!-- /.row -->

							</section>
							<!-- /.content -->
						</div>


					</div>
				</div>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->







				<!-- MAIN CONTENT AREA ENDS -->
			</section>
		</section>
		<!-- END CONTENT -->



	</div>
	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

	<script type="text/javascript"
		src="https://www.gstatic.com/charts/loader.js"></script>
	<!-- Morris.js charts -->
	<script
		src="${pageContext.request.contextPath}/resources/dashb/raphael.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/resources/dashb/morris.min.js"
		type="text/javascript"></script>
	<script>
		$(function() {
			"use strict";

			// AREA CHART
			/*   var area = new Morris.Area({
			    element: 'revenue-chart',
			    resize: true,
			    data: [
			      {y: '2011 Q1', item1: 2666, item2: 2666},
			      {y: '2011 Q2', item1: 2778, item2: 2294},
			      {y: '2011 Q3', item1: 4912, item2: 1969},
			      {y: '2011 Q4', item1: 3767, item2: 3597},
			      {y: '2012 Q1', item1: 6810, item2: 1914},
			      {y: '2012 Q2', item1: 5670, item2: 4293},
			      {y: '2012 Q3', item1: 4820, item2: 3795},
			      {y: '2012 Q4', item1: 15073, item2: 5967},
			      {y: '2013 Q1', item1: 10687, item2: 4460},
			      {y: '2013 Q2', item1: 8432, item2: 5713}
			    ],
			    xkey: 'y',
			    ykeys: ['item1', 'item2'],
			    labels: ['Item 1', 'Item 2'],
			    lineColors: ['#a0d0e0', '#3c8dbc'],
			    hideHover: 'auto'
			  });

			  // LINE CHART
			  var line = new Morris.Line({
			    element: 'line-chart',
			    resize: true,
			    data: [
			      {y: '2011 Q1', item1: 2666},
			      {y: '2011 Q2', item1: 2778},
			      {y: '2011 Q3', item1: 4912},
			      {y: '2011 Q4', item1: 3767},
			      {y: '2012 Q1', item1: 6810},
			      {y: '2012 Q2', item1: 5670},
			      {y: '2012 Q3', item1: 4820},
			      {y: '2012 Q4', item1: 15073},
			      {y: '2013 Q1', item1: 10687},
			      {y: '2013 Q2', item1: 8432}
			    ],
			    xkey: 'y',
			    ykeys: ['item1'],
			    labels: ['Item 1'],
			    lineColors: ['#3c8dbc'],
			    hideHover: 'auto'
			  });

			  //DONUT CHART
			  var donut = new Morris.Donut({
			    element: 'sales-chart',
			    resize: true,
			    colors: ["#3c8dbc", "#f56954", "#00a65a"],
			    data: [
			      {label: "UG", value: 12},
			      {label: "PG", value: 30},
			      {label: "Diploma", value: 20}
			    ],
			    hideHover: 'auto'
			  }); */
			//BAR CHART
			var isPrincipal = document.getElementById("isPrincipal").value;
			  var isIqac = document.getElementById("isIqac").value;
			//alert(isPrincipal);

			if (isPrincipal == 1 || isIqac==1) {

				$.getJSON('${getGraph}',

				{

					ajax : 'true'

				}, function(data) {

					google.charts.load('current', {
						'packages' : [ 'corechart', 'bar' ]
					});
					google.charts.setOnLoadCallback(drawStuff);
					//alert(data);
					function drawStuff() {

						var chartDiv = document.getElementById('intake_chart');
						//document.getElementById("bar-chart").style.border = "thin dotted red";
						var dataTable = new google.visualization.DataTable();

						dataTable.addColumn('string', 'academic year'); // Implicit domain column.

						dataTable.addColumn('number', 'Sanctioned Intake');
						dataTable.addColumn('number', 'Admitted Student');
						$.each(data, function(key, dt) {

							dataTable.addRows([

							[ dt.academicYear, dt.totalSancIntake,
									dt.noCurrentAdmitedStnt ]

							]);

						})

						var materialOptions = {
							legend : {
								position : 'top'
							},
							colors : [ 'orange', 'blue' ],
							hAxis : {
								title : 'YEAR',
								titleTextStyle : {
									color : 'black'
								},
								count : -1,
								viewWindowMode : 'pretty',
								slantedText : true
							},
							vAxis : {
								title : 'VALUE',
								titleTextStyle : {
									color : 'black'
								},
								count : -1,
								format : '#'
							},

						};
						var materialChart = new google.charts.Bar(chartDiv);

						function selectHandler() {
							var selectedItem = materialChart.getSelection()[0];
							if (selectedItem) {
								var topping = dataTable.getValue(
										selectedItem.row, 0);

								i = selectedItem.row, 0;
								itemSellBill(data[i].deptCode);

							}
						}

						function drawMaterialChart() {

							google.visualization.events.addListener(
									materialChart, 'select', selectHandler);
							materialChart.draw(dataTable, google.charts.Bar
									.convertOptions(materialOptions));

						}

						drawMaterialChart();

					}
					;
				});

				$.getJSON('${getTotSancIntakeProgramwiseGraph}',

				{

					ajax : 'true'

				}, function(data) {

					google.charts.load('current', {
						'packages' : [ 'corechart', 'bar' ]
					});
					google.charts.setOnLoadCallback(drawStuff);
					//alert(data);
					function drawStuff() {

						var chartDiv = document
								.getElementById('intake_chart_byprogram');
						//document.getElementById("bar-chart").style.border = "thin dotted red";
						var dataTable = new google.visualization.DataTable();

						dataTable.addColumn('string', 'academic year'); // Implicit domain column.

						dataTable.addColumn('number', 'Sanctioned Intake');
						dataTable.addColumn('number', 'Admitted Student');
						$.each(data,
								function(key, dt) {

									var pName = dt.programName + "-"
											+ dt.nameOfProgram;

									dataTable.addRows([

									[ pName, dt.sancIntake, dt.totalAdmitted ]

									]);

								})

						var materialOptions = {
							legend : {
								position : 'top'
							},
							colors : [ 'orange', 'blue' ],
							hAxis : {
								title : 'PROGRAM',
								titleTextStyle : {
									color : 'black'
								},
								count : -1,
								viewWindowMode : 'pretty',
								slantedText : true
							},
							vAxis : {
								title : 'VALUE',
								titleTextStyle : {
									color : 'black'
								},
								count : -1,
								format : '#'
							},

						};
						var materialChart = new google.charts.Bar(chartDiv);

						function selectHandler() {
							var selectedItem = materialChart.getSelection()[0];
							if (selectedItem) {
								var topping = dataTable.getValue(
										selectedItem.row, 0);

								i = selectedItem.row, 0;
								itemSellBill(data[i].deptCode);

							}
						}

						function drawMaterialChart() {

							google.visualization.events.addListener(
									materialChart, 'select', selectHandler);
							materialChart.draw(dataTable, google.charts.Bar
									.convertOptions(materialOptions));

						}

						drawMaterialChart();

					}
					;
				});
				
				if (isIqac==1) {
					
					$.getJSON('${getTotSancIntakeProgramwiseGraph}',

							{

								ajax : 'true'

							}, function(data) {

								google.charts.load('current', {
									'packages' : [ 'corechart', 'bar' ]
								});
								google.charts.setOnLoadCallback(drawStuff);
								//alert(data);
								function drawStuff() {

									var chartDiv = document
											.getElementById('student_support_scheme');
									//document.getElementById("bar-chart").style.border = "thin dotted red";
									var dataTable = new google.visualization.DataTable();

									dataTable.addColumn('string', 'academic year'); // Implicit domain column.

									dataTable.addColumn('number', 'Sanctioned Intake');
									dataTable.addColumn('number', 'Admitted Student');
									$.each(data,
											function(key, dt) {

												var pName = dt.programName + "-"
														+ dt.nameOfProgram;

												dataTable.addRows([

												[ pName, dt.sancIntake, dt.totalAdmitted ]

												]);

											})

									var materialOptions = {
										legend : {
											position : 'top'
										},
										colors : [ 'orange', 'blue' ],
										hAxis : {
											title : 'PROGRAM',
											titleTextStyle : {
												color : 'black'
											},
											count : -1,
											viewWindowMode : 'pretty',
											slantedText : true
										},
										vAxis : {
											title : 'VALUE',
											titleTextStyle : {
												color : 'black'
											},
											count : -1,
											format : '#'
										},

									};
									var materialChart = new google.charts.Bar(chartDiv);

									function selectHandler() {
										var selectedItem = materialChart.getSelection()[0];
										if (selectedItem) {
											var topping = dataTable.getValue(
													selectedItem.row, 0);

											i = selectedItem.row, 0;
											itemSellBill(data[i].deptCode);

										}
									}

									function drawMaterialChart() {

										google.visualization.events.addListener(
												materialChart, 'select', selectHandler);
										materialChart.draw(dataTable, google.charts.Bar
												.convertOptions(materialOptions));

									}

									drawMaterialChart();

								}
								;
							}); 
				}
			}
		});
	</script>
</body>
</html>



