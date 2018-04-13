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
							<li>Utilities&nbsp;-&nbsp;Leave</li>
						</ul>
					</div>
				</div>
				<div class="row" style="padding:0px;margin:0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;margin:0px;">
						<h3 style="margin:10px 0 2px 0;">Apply for Leave</h3>
					</div>
				</div><hr style="border: 3px solid #8c8c8c;">
				<div class="row" style="margin-top:1%;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<p style="font-weight:bold;margin-bottom:2px;">Your Leave Balance</p>
						<p style="margin:0px;">Casual Leaves(CL): Only 8 are left.</p>
						<p style="margin:0px;">Earned Leaves(EL): Only 25.0 are left.</p>
					</div>
				</div>
				<div class="row" style="margin-top:1%;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<p class="subHeadingParaLeave">Complete the following details</p>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
					<p class="zeroBottomMarginPara" style="margin-top:0px;">Nature of Leave</p>
					</div>
					<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
						<select class="form-control" id="selectNatureLeave">
							<option value="select">Select</option>
							<option value="cl">CL</option>
							<option value="el">EL</option>
						</select>
					</div>
				</div>
				<!--hidden div--><div id="hiddenDiv"></div>
				<!--cl form-->
				<form id="clForm">
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">CL Required For</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input name='name' type="text" class="form-control" id="dateCLeave" placeholder="dd/mm/yyyy"/>
							<!--<div class='input-group add-on date datepicker' data-date-format="yyyy-mm-dd">
								<input name='name' value="" type="text" class="form-control date-picker" data-date-format="yyyy-mm-dd"/>
									<div class="input-group-btn">
										<button class="btn btn-default">
											<i class="fa fa-calendar"></i>
										</button>
									</div>
							</div>-->
						</div>
					</div>
					<div class="row">
						<div class="col-lg-offset-5 col-lg-6 col-md-offset-6 col-md-6 col-sm-offset-4 col-sm-8 col-xs-12 radioBtnLeave">
							<label class="radio-inline">
								<input type="radio" name="optradio">Full Day
							</label>
							<label class="radio-inline">
								<input type="radio" name="optradio">Forenoon
							</label>
							<label class="radio-inline">
								<input type="radio" name="optradio">Afternoon
							</label>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Station Leave Permission</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12 radioBtnStationLeave">
							<label class="radio-inline">
								<input type="radio" name="required">Required
							</label>
							<label class="radio-inline">
								<input type="radio" name="required">Not Required
							</label>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Reason For Leave</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12 marginTopZero">
							<textarea class="form-control" placeholder="Briefly mention your reason in minimum 10 characters and maximum 150 characters."></textarea>
						</div>
					</div>
					<div class="row" style="margin-top:.5%">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Address on Leave</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12 marginTopZero">
							<textarea class="form-control" placeholder="Your address during leave period."></textarea>
						</div>
					</div>
					<div class="row" style="margin-top:.5%">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Contact Number</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12 marginTopZero">
							<input class="form-control" type="text" placeholder="Your contact number.">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Additional Remarks, if any</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12 marginTopZero">
							<textarea class="form-control" placeholder="Any additional remarks that you would like to add, goes here."></textarea>
						</div>
					</div><br/>
					<div class="row" style="margin-top:1%;">
						<div class="col-lg-offset-5 col-lg-7 col-md-offset-6 col-md-6 col-sm-offset-4 col-sm-8 col-xs-12">
							<button type="button" class="btn btn-danger btnClform" id="resetFormCl">Reset</button>&nbsp;
							<button type="button" class="btn btn-success btnClform">Submit</button>
						</div>
					</div><br/><br/><br/><hr style="border: 3px solid #8c8c8c;"><br/>
				</form>
				<!--end of cl form-->
				<!--el form-->
				<form id="elForm">
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Duration For Leave</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 fromDurationDatePicker">
									<input name='name' type="text" class="form-control" id="dateELeaveFrom" placeholder="FROM"/>
									<!--<div class='input-group add-on date datepicker' data-date-format="yyyy-mm-dd">
										<input name='name' value="" type="text" class="form-control date-picker" data-date-format="yyyy-mm-dd" placeholder="FROM"/>
											<div class="input-group-btn">
												<button class="btn btn-default">
													<i class="fa fa-calendar"></i>
												</button>
											</div>
									</div>-->
								</div>
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 toDurationDatePicker">
									<input name='name' type="text" class="form-control" id="dateELeaveTo" placeholder="TO"/>
									<!--<div class='input-group add-on date datepicker' data-date-format="yyyy-mm-dd">
										<input name='name' value="" type="text" class="form-control date-picker" data-date-format="yyyy-mm-dd" placeholder="TO"/>
											<div class="input-group-btn">
												<button class="btn btn-default">
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
							<p class="zeroBottomMarginPara">Station Leave Permission</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12 radioBtnStationLeave">
							<label class="radio-inline">
								<input type="radio" name="required">Required
							</label>
							<label class="radio-inline">
								<input type="radio" name="required">Not Required
							</label>
						</div>
					</div>
					<div class="row" style="margin-top:.5%;">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Reason For Leave</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12 marginTopZero">
							<textarea class="form-control" placeholder="Briefly mention your reason in minimum 10 characters and maximum 150 characters."></textarea>
						</div>
					</div>
					<div class="row" style="margin-top:.5%;">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Address on Leave</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12 marginTopZero">
							<textarea class="form-control" placeholder="Your address during leave period."></textarea>
						</div>
					</div>
					<div class="row" style="margin-top:.5%;">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Contact Number</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12 marginTopZero">
							<input class="form-control" type="text" placeholder="Your contact number.">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Additional Remarks, if any</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12 marginTopZero">
							<textarea class="form-control" placeholder="Any additional remarks that you would like to add, goes here."></textarea>
						</div>
					</div><br/>
					<div class="row" style="margin-top:1%;">
						<div class="col-lg-offset-5 col-lg-7 col-md-offset-6 col-md-6 col-sm-offset-4 col-sm-8 col-xs-12">
							<button type="button" class="btn btn-danger btnElform" id="resetFormEl">Reset</button>&nbsp;
							<button type="button" class="btn btn-success btnElform">Submit</button>
						</div>
					</div><br/><br/><br/><hr style="border: 3px solid #8c8c8c;"><br/>
				</form>
				<!--end of el form-->
			</div>
				<?php include 'include/notificationBlock.php';?>
		</div>
			<!--footer start here-->
				<?php include 'include/footerPage.php';?>
			<!--footer end here-->
			<script>
			$(document).ready(function(){
				$( "#dateCLeave" ).datepicker({
					showOn: "button",
					buttonImage: "../images/calender.png",
					buttonImageOnly: true,
					buttonText: "Open calendar",
					dateFormat: 'dd/mm/yy'
					});
				$( "#dateELeaveFrom" ).datepicker({
					showOn: "button",
					buttonImage: "../images/calender.png",
					buttonImageOnly: true,
					buttonText: "Open calendar",
					dateFormat: 'dd/mm/yy'
					});
				$( "#dateELeaveTo" ).datepicker({
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