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
							<li>Utilities&nbsp;-&nbsp;Adhoc Recruitment</li>
						</ul>
					</div>
				</div>
				<div class="row" style="padding:0px;margin:0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;margin:0px;">
						<h3 style="margin:10px 0 2px 0;">Performa for Approval of filling up Adhoc Project positions</h3>
					</div>
				</div><hr style="border: 3px solid #8c8c8c;">
				<form id="formApplyAdhocRec">
					<div class="row" style="margin-top:2%;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<p>IMPORTANT:Adhoc appointment through Selection Committee is resorted only to cater to the urgent requirement of the project. Therefore, adhoc selection is not to be proposed matter of 
						routine and it should confine to project research positions only, however it will be considered with proper justification within the frame of rule.</p>
						</div>
					</div><br/>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Project Title</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input type="text" class="form-control"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Project Code</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<select class="form-control" id="project_code">
								<option value="select">Select</option>
								<option value="a1">A1</option>
								<option value="a2">A2</option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Department</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input type="text" class="form-control"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Project Duration</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 projectFromDate">
									
										<input name='name' type="text" class="form-control" id="dateFromAdhoc" placeholder="FROM"/>
											<!--<div class="input-group-btn">
												<button class="btn btn-default">
													<i class="fa fa-calendar"></i>
												</button>
											</div>-->
							
								</div>
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 projectToDate">
									<input name='name' type="text" class="form-control" id="dateToAdhoc" placeholder="TO"/>
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
					</div><br/>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p class="subHeadingRateContract">Details of Posts</p>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><div class="panel panel-default table-responsive" style="margin-bottom:5px;">
							<table class="table table-bordered" id="adhocRecruit">
								<thead>
									<tr>
										<th>S.No.</th>
										<th>Project Staff Designation</th>
										<th>No. of Vacancies</th>
										<th>Basic Pay Recommended</th>
										<th>HRA Required</th>
										<th>Medical Required</th>
										<th>Total Amount</th>
										<th>Duration of Appointment in Days (max 89)</th>
										<th>Qualification</th>
										<th>Justification</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<tr id="rowAdhocRecruit1">
										<td>1</td>
										<td><input class="form-control" type="text" name="staffDesg[]"/></td>
										<td><input class="form-control" type="text" name="vacancies[]"/></td>
										<td><input class="form-control" type="text" name="basicPay[]"/></td>
										<td><input class="form-control" type="text" name="hra[]"/></td>
										<td><input class="form-control" type="text" name="ma[]"/></td>
										<td><input class="form-control" type="text" name="totalAmt[]"/></td>
										<td><input class="form-control" type="text" name="duration[]"/></td>
										<td><input class="form-control" type="text" name="qualification[]"/></td>
										<td><input class="form-control" type="text" name="justification[]"/></td>
										<td><button type="button" class="btn btn-danger btn-removeAdhocRecruit" id="1"><i class="fa fa-minus" aria-hidden="true"></i></button></td>
									</tr>
								</tbody>
							</table></div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<button type="button" class="btn btn-info" id="addRowAdhocRecruit"><i class="fa fa-plus" aria-hidden="true"></i></button>
						</div>
					</div><br/>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p class="subHeadingRateContract">Declaration for Selection Committee</p>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="checkbox">
								<label><input type="checkbox" value="">Chairman of selection committee will be HOD/HOC.</label>
							</div>
							<div class="checkbox">
								<label><input type="checkbox" value="">PI will be the convener of the selection committee.</label>
							</div>
							<div class="checkbox">
								<label><input type="checkbox" value="">If PI is HOD/HOC, any other faculty member should be selected as chairman of selection committee.</label>
							</div>
							<div class="checkbox">
								<label><input type="checkbox" value="">Any two faculty members of the institute will be expert members.</label>
							</div>
							<div class="checkbox">
								<label><input type="checkbox" value="">If required, as per the funding agency, external members should be part of selection committee.</label>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p>Placed for approval of Dean of Research & Development</p>
						</div>
					</div><br/>
					<div class="row" style="margin-top:1%;">
						<div class="col-lg-offset-5 col-lg-7 col-md-offset-6 col-md-6 col-sm-offset-4 col-sm-8 col-xs-12">
							<button type="button" class="btn btn-danger adhocReq" id="btnAdhocReq">Reset</button>&nbsp;
							<button type="button" class="btn btn-success adhocReq">Submit</button>
						</div>
					</div><br/><br/><br/><hr style="border: 3px solid #8c8c8c;"><br/>
				</form>
				<!--hidden div--><div id="hiddenDiv"></div>
				
			</div>
				<?php include 'include/notificationBlock.php';?>
		</div>
			<!--footer start here-->
				<?php include 'include/footerPage.php';?>
			<!--footer end here-->
		<script>
			$(document).ready(function(){
				var i = 1; 
				$('#addRowAdhocRecruit').click(function(){
					i++;
					$('#adhocRecruit').append('<tr id="rowAdhocRecruit'+i+'"><td>'+i+'</td><td><input class="form-control" type="text" name="staffDesg[]"/></td><td><input class="form-control" type="text" name="vacancies[]"/></td><td><input class="form-control" type="text" name="basicPay[]"/></td><td><input class="form-control" type="text" name="hra[]"/></td><td><input class="form-control" type="text" name="ma[]"/></td><td><input class="form-control" type="text" name="totalAmt[]"/></td><td><input class="form-control" type="text" name="duration[]"/></td><td><input class="form-control" type="text" name="qualification[]"/></td><td><input class="form-control" type="text" name="justification[]"/></td><td><button type="button" class="btn btn-danger btn-removeAdhocRecruit" id="'+i+'"><i class="fa fa-minus" aria-hidden="true"></i></button></td></tr>');
				});
				$(document).on('click','.btn-removeAdhocRecruit',function(){
					var button_id = $(this).attr("id");
					if (button_id == i){
						var result = confirm("Are you want to delete this row ?");
						if (result) {
							$('#rowAdhocRecruit'+button_id+'').remove();
							i--;
						}
					} else {
						alert('You should delete rows starting from last !');
					}
				});
				$( "#dateFromAdhoc" ).datepicker({
					showOn: "button",
					buttonImage: "../images/calender.png",
					buttonImageOnly: true,
					buttonText: "Open calendar",
					dateFormat: 'dd/mm/yy'
					});
				$( "#dateToAdhoc" ).datepicker({
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