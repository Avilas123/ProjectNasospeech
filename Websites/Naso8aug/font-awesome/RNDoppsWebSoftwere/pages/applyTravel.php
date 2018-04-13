<!DOCTYPE html>
<html lang="en">
<head>
  <title>RndOps</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
   <?php include 'include/linkScript.php'?>
</head>
<body>
	<?php include 'include/headerPage.php'?>
	<div class="container-fluid" id="bodyPart">
		<div class="row">
			<?php include 'include/accordianPage.php'?>
			<div class="col-lg-offset-3 col-lg-6 col-md-offset-3 col-md-6 col-sm-12 col-xs-12">
				<div class="row" style="padding:0px;margin:0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xd-12" style="padding:0px;margin:0px;">
						<ul class="breadcrumb" style="padding:0px;margin:0px;">
							<li><a href="welcome.php">Home</a></li>
							<li>Utilities&nbsp;-&nbsp;Travel</li>
						</ul>
					</div>
				</div>
				<div class="row" style="padding:0px;margin:0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;margin:0px;">
						<h3 style="margin:10px 0 2px 0;">Apply for Travel</h3>
					</div>
				</div><hr style="border: 3px solid #8c8c8c;">
				<form id="travelForm">
					<div class="row" style="margin-top:1%;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p class="subHeadingPara">Basic details of the Applicant</p>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Name of the Applicant</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input type="text" class="form-control"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Designation of the Applicant</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input type="text" class="form-control"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Department</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<select class="form-control" id="dept">
								<option value="select">Select</option>
								<option value="design">Design</option>
								<option value="computer">Computer Science</option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p class="subHeadingPara">Details of the conference for which financial assistant is sought</p>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-4 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Type of Visit</p>
						</div>
						<div class="col-lg-7 col-md-8 col-sm-8 col-xs-12 radioBtnVisit">
							<label class="radio-inline">
								<input type="radio" name="visitradio">Conference
							</label>
							<label class="radio-inline">
								<input type="radio" name="visitradio">Field Visit
							</label>
							<label class="radio-inline">
								<input type="radio" name="visitradio">Other
							</label>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-4 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Nature</p>
						</div>
						<div class="col-lg-7 col-md-8 col-sm-8 col-xs-12 radioBtnVisit">
							<label class="radio-inline">
								<input type="radio" name="natureradio">National
							</label>
							<label class="radio-inline">
								<input type="radio" name="natureradio">International
							</label>
							<label class="radio-inline">
								<input type="radio" name="natureradio">Local
							</label>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Venue and Address</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<textarea class="form-control" placeholder="Please specify the venue or address of the conference."></textarea>
						</div>
					</div>
					<!--<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Date</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<div class='input-group add-on date datepicker' data-date-format="yyyy-mm-dd">
								<input name='name' value="" type="text" class="form-control date-picker" data-date-format="yyyy-mm-dd" placeholder="FROM"/>
									<div class="input-group-btn">
										<button class="btn btn-default btnDate">
											<i class="fa fa-calendar"></i>
										</button>
									</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-offset-5 col-lg-7 col-md-offset-6 col-md-6 col-sm-offset-4 col-sm-8 col-xs-12">
							<div class='input-group add-on date datepicker' data-date-format="yyyy-mm-dd">
								<input name='name' value="" type="text" class="form-control date-picker" data-date-format="yyyy-mm-dd" placeholder="TO"/>
									<div class="input-group-btn">
										<button class="btn btn-default btnDate">
											<i class="fa fa-calendar"></i>
										</button>
									</div>
							</div>
						</div>
					</div>-->
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Date</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 fromDatePicker">
									<input name='name' type="text" class="form-control" id="dateFromConf" placeholder="FROM"/>
									<!--<div class='input-group add-on date datepicker' data-date-format="yyyy-mm-dd">
										<input name='name' value="" type="text" class="form-control date-picker" data-date-format="yyyy-mm-dd" placeholder="FROM"/>
											<div class="input-group-btn">
												<button class="btn btn-default btnDate">
													<i class="fa fa-calendar"></i>
												</button>
											</div>
									</div>-->
								</div>
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 toDatePicker">
									<input name='name' type="text" class="form-control" id="dateToConf" placeholder="TO"/>
									<!--<div class='input-group add-on date datepicker' data-date-format="yyyy-mm-dd">
										<input name='name' value="" type="text" class="form-control date-picker" data-date-format="yyyy-mm-dd" placeholder="TO"/>
											<div class="input-group-btn">
												<button class="btn btn-default btnDate">
													<i class="fa fa-calendar"></i>
												</button>
											</div>
									</div>-->
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Organizer(s)</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row" style="margin-top:.5%;">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Purpose of the visit</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<textarea class="form-control" placeholder="Please specify the purpose of the visit."></textarea>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p class="subHeadingPara">Estimate of the financial assistance requested for the conference</p>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Mode of Travel</p>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-8 col-xs-12 radioBtnVisit">
							<label class="radio-inline">
								<input type="radio" name="traveModeRadio">Train
							</label>
							<label class="radio-inline">
								<input type="radio" name="traveModeRadio">Indian Airlines
							</label>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-offset-5 col-lg-5 col-md-offset-6 col-md-6 col-sm-offset-4 col-sm-8 col-xs-12 travelRadioBtnVisit">
							<label class="radio-inline">
								<input type="radio" name="traveModeRadio">Non Indian Airlines
							</label>
							<label class="radio-inline">
								<input type="radio" name="traveModeRadio">Road
							</label>
						</div>
					</div>
					<div class="row" style="margin-top:1%;">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Travel Estimate</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-offset-5 col-lg-2 col-md-offset-6 col-md-2 col-sm-offset-4 col-sm-3 col-xs-12">
							<input type="file">
						</div>
					</div>
					<div class="row" style="margin-top:2%;">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Registration fee</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-offset-5 col-lg-2 col-md-offset-6 col-md-2 col-sm-offset-4 col-sm-3 col-xs-12">
							<input type="file">
						</div>
					</div>
					<div class="row" style="margin-top:2%;">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Accommodation + Food</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-offset-5 col-lg-2 col-md-offset-6 col-md-2 col-sm-offset-4 col-sm-3 col-xs-12">
							<input type="file">
						</div>
					</div>
					<div class="row" style="margin-top:2%;">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Any other</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Total Estimate</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p class="subHeadingPara">Period of leave</p>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Station leave required</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input name='name' type="text" class="form-control" id="dateFromStationLeave" placeholder="FROM"/>
							<!--<div class='input-group add-on date datepicker' data-date-format="yyyy-mm-dd">
								<input name='name' value="" type="text" class="form-control date-picker" data-date-format="yyyy-mm-dd" placeholder="FROM"/>
									<div class="input-group-btn">
										<button class="btn btn-default btnDate">
											<i class="fa fa-calendar"></i>
										</button>
									</div>
							</div>-->	
						</div>
					</div>
					<div class="row">
						<div class="col-lg-offset-5 col-lg-6 col-md-offset-6 col-md-6 col-sm-offset-4 col-sm-8 col-xs-12 stationLeaveReqdRadio">
							<label class="radio-inline">
								<input type="radio" name="FromdayRadio">Full Day
							</label>
							<label class="radio-inline">
								<input type="radio" name="FromdayRadio">Forenoon
							</label>
							<label class="radio-inline">
								<input type="radio" name="FromdayRadio">Afternoon
							</label>
						</div>
					</div>
					<div class="row" style="margin-top:1%;">
						<div class="col-lg-offset-5 col-lg-7 col-md-offset-6 col-md-6 col-sm-offset-4 col-sm-8 col-xs-12">
							<input name='name' type="text" class="form-control" id="dateToStationLeave" placeholder="TO"/>
							<!--<div class='input-group add-on date datepicker' data-date-format="yyyy-mm-dd">
								<input name='name' value="" type="text" class="form-control date-picker" data-date-format="yyyy-mm-dd" placeholder="TO"/>
									<div class="input-group-btn">
										<button class="btn btn-default btnDate">
											<i class="fa fa-calendar"></i>
										</button>
									</div>
							</div>-->
						</div>
					</div>
					<div class="row">
						<div class="col-lg-offset-5 col-lg-6 col-md-offset-6 col-md-6 col-sm-offset-4 col-sm-8 col-xs-12 stationLeaveReqdRadio">
							<label class="radio-inline">
								<input type="radio" name="toDayRadio">Full Day
							</label>
							<label class="radio-inline">
								<input type="radio" name="toDayRadio">Forenoon
							</label>
							<label class="radio-inline">
								<input type="radio" name="toDayRadio">Afternoon
							</label>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p class="subHeadingPara">Declaration</p>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p>It is certified that my participation in the above Conference will be in the interest of teaching and research at this Institute and all arrangements will be made to 
							take care of my academic commitments including teaching and research during the period of my leave/absence. This has direct bearing to the Project from which fund is 
							sought. It is requested that financial assistance, permission for attending the conference/ workshop/ seminar etc.and leave may please be granted to me. It is certified 
							that the claim for journey mentioned has not been preferred or will not be claimed from any other source.</p>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<input type="checkbox"/> I Accept
						</div>
					</div><br/>
					<div class="row" style="margin-top:1%;">
						<div class="col-lg-offset-5 col-lg-7 col-md-offset-6 col-md-6 col-sm-offset-4 col-sm-8 col-xs-12">
							<button type="button" class="btn btn-danger btnTravel" id="resetFormTravel">Reset</button>&nbsp;
							<button type="button" class="btn btn-success btnTravel">Submit</button>
						</div>
					</div><br/><br/><br/><hr style="border: 3px solid #8c8c8c;"><br/>
				</form>
			</div>
			<?php include 'include/notificationBlock.php'?>
		</div>
			<!--footer start here-->
				<?php include 'include/footerPage.php';?>
			<!--footer end here-->
			<script>
			$(document).ready(function(){
				$( "#dateFromConf" ).datepicker({
					showOn: "button",
					buttonImage: "../images/calender.png",
					buttonImageOnly: true,
					buttonText: "Open calendar",
					dateFormat: 'dd/mm/yy'
					});
				$( "#dateToConf" ).datepicker({
					showOn: "button",
					buttonImage: "../images/calender.png",
					buttonImageOnly: true,
					buttonText: "Open calendar",
					dateFormat: 'dd/mm/yy'
					});
				$( "#dateFromStationLeave" ).datepicker({
					showOn: "button",
					buttonImage: "../images/calender.png",
					buttonImageOnly: true,
					buttonText: "Open calendar",
					dateFormat: 'dd/mm/yy'
					});
				$( "#dateToStationLeave" ).datepicker({
					showOn: "button",
					buttonImage: "../images/calender.png",
					buttonImageOnly: true,
					buttonText: "Open calendar",
					dateFormat: 'dd/mm/yy'
					});
			});
			</script>
	</div>
</body>
</html>