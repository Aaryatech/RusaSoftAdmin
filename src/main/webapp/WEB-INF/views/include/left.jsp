<body>
	<div class="page-sidebar pagescroll">
		<!-- MAIN MENU - START -->
		<div class="page-sidebar-wrapper" id="main-menu-wrapper">
			<!-- USER INFO - START -->
			<div class="profile-info row">
				<div class="profile-image col-xs-4">
					<a href="ui-profile.html"> <img alt=""
						src="../data/profile/profile.jpg"
						class="img-responsive img-circle">
					</a>
				</div>
				<div class="profile-details col-xs-8">
					<h3>
						<a href="ui-profile.html">Shane Taylor</a>
						<!-- Available statuses: online, idle, busy, away and offline -->
						<span class="profile-status online"></span>
					</h3>
					<p class="profile-title">Web Developer</p>
				</div>
			</div>
			<!-- USER INFO - END -->
			<ul class='wraplist'>
				<li class='menusection'>Main</li>
				<li class="open"><a href="index.html"> <i
						class="fa fa-dashboard"></i> <span class="title">Dashboard</span>
				</a></li>

				<li class=""><a href="javascript:;"> <i
						class="fa fa-columns"></i> <span class="title">User
							Registration</span> <span class="arrow "></span>
				</a>
					<ul class="sub-menu">
						<li><a class=""
							href="${pageContext.request.contextPath}/showRegisterInstitute">
								Institute Registration</a> <a class=""
							href="${pageContext.request.contextPath}/iqacRegistration">IQAC
								Registration</a> <a class=""
							href="${pageContext.request.contextPath}/hodRegistration">HOD
								Registration</a> <a class=""
							href="${pageContext.request.contextPath}/showRegisterStaff">Faculty
								Registration</a> <a class=""
							href="${pageContext.request.contextPath}/showIqacAfterLogin">Fill
								Institute Information</a></li>

					</ul></li>

				<%-- 	<li class=""><a href="javascript:;"> <i
						class="fa fa-columns"></i> <span class="title">Master</span> <span
						class="arrow "></span>
				</a>
					<ul class="sub-menu">
						<li><a class=""
							href="${pageContext.request.contextPath}/addFaculty">Add
								Department</a></li></ul></li>
 --%>


				<li class=""><a href="javascript:;"> <i
						class="fa fa-columns"></i> <span class="title">Faulty
							Details</span> <span class="arrow "></span>
				</a>
					<ul class="sub-menu">
						<li><a class=""
							href="${pageContext.request.contextPath}/showPersonalDetails">Personal
								Details  </a></li>
								<li><a class=""
							href="${pageContext.request.contextPath}/showAcademicDetails">Academic
								Details </a></li>
								<li><a class=""
							href="${pageContext.request.contextPath}/showMphillDetails">M.Phill/Ph.D.
								Details </a></li>
								<li><a class=""
							href="${pageContext.request.contextPath}/showStudMentor">Student Mentoring
								Details </a></li>
								
								
								
						
								<li><a class=""
							href="${pageContext.request.contextPath}/showPublicationDetails">Publication/Presentation Details </a></li>		
								
								<li><a class=""
							href="${pageContext.request.contextPath}/showResearchDetails">Research Project
								Details </a></li>
								
								<li><a class=""
							href="${pageContext.request.contextPath}/showConsultancyDetails">Consultancy
								Details  </a></li>
								<li><a class=""
							href="${pageContext.request.contextPath}/showPatentDetails">Patent Details
								 </a></li>
								
								<li><a class=""
							href="${pageContext.request.contextPath}/showAwardDetails">Award Recognition Details

								 </a></li>
								 
								 
								<li><a class=""
							href="${pageContext.request.contextPath}/showOutReachDetails">Out Reach Activity

								 </a></li>
								
								
								<li><a class=""
							href="${pageContext.request.contextPath}/showOrganized">Organized Details



							</a></li>
								
								<li><a class=""
							href="${pageContext.request.contextPath}/showSubDetails">Subject Details 

								 </a></li>
								 	<li><a class=""
							href="${pageContext.request.contextPath}/showSWOC">SWOC Details 

								 </a></li>
								 	<li><a class=""
							href="${pageContext.request.contextPath}/showPhdGuide">Ph.D. Guidence Details 

								 </a></li>
									
								<%-- <li><a class=""
							href="${pageContext.request.contextPath}/showFacultyDetails/11">SWOC for HODs

								Details </a></li>
									
								<li><a class=""
							href="${pageContext.request.contextPath}/showFacultyDetails/12">Ph.D Guidance 

								Details </a></li> --%>
								
								

					</ul></li>

				<li class=""><a href="javascript:;"> <i
						class="fa fa-columns"></i> <span class="title">Research And
							Innovation </span> <span class="arrow "></span>
				</a>
					<ul class="sub-menu">
						<li><a class=""
							href="${pageContext.request.contextPath}/showResearchAndInnovationForm">Research
								And Innovation Form </a></li>

					</ul></li>

				<li class=""><a href="javascript:;"> <i
						class="fa fa-columns"></i> <span class="title">Program
							Details Module </span> <span class="arrow "></span>
				</a>
					<ul class="sub-menu">
						<li><a class=""
							href="${pageContext.request.contextPath}/showProgramDetails">Program
								Details Form </a></li>

					</ul></li>

				<li class=""><a href="javascript:;"> <i
						class="fa fa-columns"></i> <span class="title">Infrastructure
							Module </span> <span class="arrow "></span>
				</a>
					<ul class="sub-menu">
						<li><a class=""
							href="${pageContext.request.contextPath}/showInfrastructureForm">Infrastructure
								Form </a></li>

					</ul></li>




				<li><a href="#"> <i class="fa fa-lock"></i> <span
						class="title">Logout</span>
				</a></li>
			</ul>
			<div class="menustats"></div>
		</div>
		<!-- MAIN MENU - END -->
	</div>
</body>