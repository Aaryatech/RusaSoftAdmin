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
<body class="" >
	<c:url value="/checkUniqueField" var="checkUniqueField"></c:url>
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

							<div class="actions panel_actions pull-right">
								<a
									href="${pageContext.request.contextPath}/showStudAttendActivityaa"><button
										type="button" class="btn btn-info">Back</button></a>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<!-- 	<div class="alert alert-danger" id="regerror"></div> -->
									 <form class="form-horizontal" action="#" method="post"
                                            enctype="multipart/form-data" novalidate="novalidate" id="formidhere" >
                                            <div class="form-group">
                                                <label class="control-label col-sm-2"
                                                    for="config_smtp_host">Textfields *:</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control has-error" name="formfield1" id="formfield1">
                                                     <span class="error_form text-danger" id="error_formfield1" style="display:none;" >Please enter mobile</span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2"
                                                    for="mobile">Mobile * :</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" name="mobile" id="mobile">
                                                     <span class="error_form text-danger" id="error_mobile" style="display:none;" >Please enter mobile</span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2"
                                                    for="email">email:</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" name="email" id="email">
                                                      <span class="error_form text-danger" id="error_email" style="display:none;" >Please enter email</span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="config_mail_protocol">
                                                    Simple
                                                    Select:
                                                    <!--<span    data-original-title="Only choose 'Mail' unless your host has disabled the php mail function." rel="tooltip" data-color-class = "primary" data-animate=" animated fadeIn" data-toggle="tooltip"   data-placement="top">Help</span>-->
                                                </label>
                                                <div class="col-sm-10">
                                                    <select name="config_mail_protocol" id="config_mail_protocol"
                                                        class="form-control">
                                                        <option value="mail" selected="">Mail</option>
                                                        <option value="smtp">SMTP</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="config_smtp_username">Simple
                                                    Group
                                                    Select:</label>
                                                <div class="col-sm-10">
                                                    <select class="form-control" id="config_smtp_username">
                                                        <option></option>
                                                        <optgroup label="North America">
                                                            <option>Alabama</option>
                                                            <option>Alaska</option>
                                                            <option>Arizona</option>
                                                            <option>Arkansas</option>
                                                            <option>California</option>

                                                        </optgroup>
                                                        <optgroup label="Europe">
                                                            <option>Albania</option>
                                                            <option>Andorra</option>
                                                            <option>Armenia</option>
                                                            <option>Austria</option>first_name
                                                            <option>Azerbaijan</option>
                                                            <option>Belarus</option>
                                                            <option>Belgium</option>
                                                            <option>Bosnia & Herzegovina</option>
                                                            <option>Bulgaria</option>
                                                        </optgroup>
                                                        <optgroup label="Asia">
                                                            <option>Afghanistan</option>
                                                            <option>Bahrain</option>
                                                            <option>Bangladesh</option>
                                                            <option>Bhutan</option>
                                                            <option>Brunei</option>
                                                            <option>Cambodia</option>
                                                            <option>China</option>
                                                        </optgroup>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="aaaaa">Search
                                                    with
                                                    Select:</label>
                                                <div class="col-sm-10">
                                                    <select class="" id="countryid-1">
                                                        <option></option>
                                                        <optgroup label="North America">
                                                            <option>Alabama</option>
                                                           register_user <option>Alaska</option>
                                                            <option>Arizona</option>
                                                            <option>Arkansas</option>
                                                            <option>California</option>

                                                        </optgroup>
                                                        <optgroup label="Europe">
                                                            <option>Albania</option>
                                                            <option>Andorra</option>
                                                            <option>Armenia</option>
                                                            <option>Austria</option>
                                                            <option>Azerbaijan</option>
                                                            <option>Belarus</option>
                                                            <option>Belgium</option>
                                                            <option>Bosnia & Herzegovina</option>
                                                            <option>Bulgaria</option>
                                                        </optgroup>
                                                        <optgroup label="Asia">
                                                            <option>Afghanistan</option>
                                                            <option>Bahrain</option>
                                                            <option>Bangladesh</option>
                                                            <option>Bhutan</option>
                                                            <option>Brunei</option>
                                                            <option>Cambodia</option>
                                                            <option>China</option>
                                                        </optgroup>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2"
                                                    for="bbbbb">Multiple select:</label>
                                                <div class="col-sm-10">
                                                    <select class="" id="countryid-2" multiple>
                                                        <option></option>
                                                        <optgroup label="United States">
                                                            <option>Alabama</option>
                                                            <option>Alaska</option>
                                                            <option>Arizona</option>
                                                            <option>Arkansas</option>
                                                            <option selected>California</option>
                                                            <option>Colorado</option>
                                                            <option>Connecticut</option>
                                                            <option>Delaware</option>
                                                            <option selected>Florida</option>
                                                            <option>Georgia</option>
                                                            <option>Hawaii</option>
                                                            <option>Idaho</option>
                                                            <option>Illinois</option>
                                                            <option>Indiana</option>
                                                            <option>Iowa</option>
                                                            <option>Kansas</option>
                                                            <option>Kentucky[C]</option>
                                                            <option>Louisiana</option>
                                                            <option>Maine</option>
                                                            <option>Maryland</option>
                                                            <option>Massachusetts[D]</option>
                                                            <option>Michigan</option>
                                                            <option>Minnesota</option>
                                                            <option>Mississippi</option>
                                                            <option>Missouri</option>
                                                            <option>Montana</option>
                                                            <option>Nebraska</option>
                                                            <option>Nevada</option>
                                                            <option>New Hampshire</option>
                                                            <option>New Jersey</option>
                                                            <option>New Mexico</option>
                                                            <option>New York</option>
                                                            <option>North Carolina</option>
                                                            <option>North Dakota</option>
                                                            <option>Ohio</option>
                                                            <option>Oklahoma</option>
                                                            <option>Oregon</option>
                                                            <option>Pennsylvania[E]</option>
                                                            <option>Rhode Island[F]</option>
                                                            <option>South Carolina</option>
                                                            <option>South Dakota</option>
                                                            <option>Tennessee</option>
                                                            <option>Texas</option>
                                                            <option>Utah</option>
                                                            <option>Vermont</option>
                                                            <option>Virginia[G]</option>
                                                            <option selected>Washington</option>
                                                            <option>West Virginia</option>
                                                            <option>Wisconsin</option>
                                                            <option>Wyoming</option>
                                                        </optgroup>
                                                    </select>

                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-sm-2"
                                                    for="config_alert_emails">Tags:(&lt;input&gt;)
                                                </label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" id="tagsinput-1"
                                                        data-role="tagsinput"
                                                        value="Sample tag, Another great tag, Awesome!" />

                                                    <small>Comma sperated</small>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2"
                                                    for="config_alert_emails">Tags:(&lt;select&gt;)
                                                </label>
                                                <div class="col-sm-10">
                                                    <select multiple data-role="tagsinput">
                                                        <option value="Amsterdam">Amsterdam</option>
                                                        <option value="Washington">Washington</option>
                                                        <option value="Sydney">Sydney</option>
                                                        <option value="Beijing">Beijing</option>
                                                        <option value="Cairo">Cairo</option>
                                                        <option value="Paris">Paris</option>
                                                    </select>

                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="config_alert_emails">Date

                                                </label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control datepicker"
                                                        data-format="dd-mm-yyyy">


                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="config_alert_emails">Only
                                                    year

                                                </label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control datepicker"
                                                        data-min-view-mode="years" data-start-view="2"
                                                        data-format="yyyy">


                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="config_alert_emails">Only
                                                    month & year

                                                </label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control datepicker"
                                                        data-min-view-mode="months" data-start-view="2"
                                                        data-format="mm-yyyy">


                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2">Start with month</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control datepicker"
                                                        data-start-view="1">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-sm-2">Start with year</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control datepicker"
                                                        data-start-view="2">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-sm-2">From Date</label>
                                                <div class="col-sm-10">
                                                    <input type="text" id="fromDate" class="form-control datepicker1"
                                                         >
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2">To Date</label>
                                                <div class="col-sm-10">
                                                    <input type="text" id="toDate" class="form-control datepicker1"
                                                         >
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <div class="col-sm-offset-2 col-sm-10">
                                                    <button type="submit" class="btn btn-primary">Submit</button>
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
	 


	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

<script>

  $( function() {
	  
	  $("#countryid-1").select2({
          placeholder: 'Select your country...',
          allowClear: true
      }).on('select2-open', function() {
          // Adding Custom Scrollbar
          $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
      });


      $("#countryid-2").select2({
          placeholder: 'Choose your favorite US Countries',
          allowClear: true
      }).on('select2-open', function() {
          // Adding Custom Scrollbar
          $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
      });

      
    var dateFormat = "dd-mm-yyyy",
      from = $( "#fromDate" )
        .datepicker({
          defaultDate: "+1w",
          changeMonth: true,
          numberOfMonths: 3
        })
        .on( "change", function() {
         
          to.datepicker( "option", "minDate", getDate( this ) );
        }),
      to = $( "#toDate" ).datepicker({
        defaultDate: "+1w",
        changeMonth: true,
        numberOfMonths: 3
      })
      .on( "change", function() {
        from.datepicker( "option", "maxDate", getDate( this ) );
      });
 
    function getDate( element ) {
      var date;
      try {
        date = $.datepicker.parseDate( dateFormat, element.value );regerror
      } catch( error ) {
        date = null;
      }
 
      return date;
    }
  } );
  </script>
  
  
 <script>
            //
            function validateEmail(email) {
            
            	var eml = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
            
            	if (eml.test($.trim(email)) == false) {
            
            
            	return false;
            
            	}
            
            	return true;
            
            }
             function validateMobile(mobile) {
            		var mob = /^[1-9]{1}[0-9]{9}$/;
            
            
            		if (mob.test($.trim(mobile)) == false) {
            
            		//alert("Please enter a valid email address .");
            		return false;
            
            		}
            		return true;
            
             }
            	$(document).ready(function($){
            	
            		$("#formidhere").submit(function(e) {
            			 var isError=false;
            			 var errMsg="";
            				
           
            				if(!$("#formfield1").val()){
            					 
            				isError=true;
            				errMsg += '<li>Please enter a valid name.</li>';
            				
            				$("#formfield1").addClass("has-error")
            				$("#error_formfield1").show()
            					//return false;
            				} else {
            					$("#error_formfield1").hide()
            				}
            				 
            				if(!$("#mobile").val() || !validateMobile($("#mobile").val())){
            
            				isError=true;
            				errMsg += '<li>Please enter a valid email address.</li>';
            				errMsg_alert = 'Please enter a valid mobile number.';
            				$("#error_mobile").html(errMsg_alert);
            				$("#error_mobile").show();
            				//alert();
            					//return false;
            				} else {
            					$("#error_mobile").html("Please enter mobile")
            					$("#error_mobile").hide()
            				}
            				if(!$("#email").val() || !validateEmail($("#email").val())){
            
            				isError=true;
            				errMsg += '<li>Please enter a valid email address.</li>';
            				errMsg_alert += 'Please enter a valid email address. \n';
            				$("#error_email").show()
            					//return fregister_useralse;
            				} else {
            					$("#error_email").hide()
            				}
            
            		 
            
            
            			  /* if ($('#termcondition').is(':checked')) {
            				} else {
            				isError=true;
            					errMsg += '<li>You must agree to our Term & Conditions.</li>';
            					errMsg_alert = 'You must agree to our Term & Conditions. \n';
            					alert(errMsg_alert);
            					return false;
            				}
             */
            
            
            				if(!isError) {
            					//dataString = $("#regform").serialize();
            					// alert(weregister_userb_site_url);
            						$("#regerror").html("");
            					    $("#regerror").hide();
            
            								 //ajax send this to php page
            						$("#formidhere").submit();
            								//end ajax send this to php page
            					   } else {
            					   $("#regerror").html(errMsg);
            					    $("#regerror").show();
            					   }
            					   return false;
            				} );
            	});
			//
			
			    
          
        </script>
</body>
</html>