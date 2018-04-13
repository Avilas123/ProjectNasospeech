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
							<li>Utilities&nbsp;-&nbsp;Set Incharge</li>
						</ul>
					</div>
				</div>
				<div class="row" style="padding:0px;margin:0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;margin:0px;">
						<h3 style="margin:10px 0 2px 0;">Enter Details for Account delegation</h3>
					</div>
				</div><hr style="border: 3px solid #8c8c8c;">
				<form id="formInCharge" style="margin-top:2%;">
					<div class="row" style="margin-top:1%;">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara" style="margin-top:0px;">Webmail-ID Incharge</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="email" placeholder="example@iitg.ernet.in"/>
						</div>
					</div>
					<div class="row" style="margin-top:1%;">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara" style="margin-top:0px;">Name of Incharge</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row" style="margin-top:1%;">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara" style="margin-top:0px;">Duration</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input name='name' type="text" class="form-control" id="dateFromInCharge" placeholder="FROM"/>
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
						<div class="col-lg-offset-5 col-lg-6 col-md-offset-6 col-md-6 col-sm-offset-4 col-sm-8 col-xs-12">
							<label class="radio-inline" style="font-family: Verdana, Arial, Helvetica;color:#595959;">
								<input type="radio" name="FromdayDuration">Forenoon
							</label>
							<label class="radio-inline" style="font-family: Verdana, Arial, Helvetica;color:#595959;">
								<input type="radio" name="FromdayDuration">Afternoon
							</label>
						</div>
					</div>
					<div class="row" style="margin-top:3%;">
						<div class="col-lg-offset-5 col-lg-7 col-md-offset-6 col-md-6 col-sm-offset-4 col-sm-8 col-xs-12">
							<input name='name' type="text" class="form-control" id="dateToInCharge" placeholder="TO"/>
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
						<div class="col-lg-offset-5 col-lg-6 col-md-offset-6 col-md-6 col-sm-offset-4 col-sm-8 col-xs-12">
							<label class="radio-inline" style="font-family: Verdana, Arial, Helvetica;color:#595959;">
								<input type="radio" name="toDayDuration">Forenoon
							</label>
							<label class="radio-inline" style="font-family: Verdana, Arial, Helvetica;color:#595959;">
								<input type="radio" name="toDayDuration">Afternoon
							</label>
						</div>
					</div><br/>
					<div class="row" style="margin-top:1%;">
						<div class="col-lg-offset-5 col-lg-7 col-md-offset-6 col-md-6 col-sm-offset-4 col-sm-8 col-xs-12">
							<button type="button" class="btn btn-danger btnInCharge" id="btnResetInCharge">Reset</button>&nbsp;
							<button type="button" class="btn btn-success btnInCharge">Submit</button>
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
				$( "#dateFromInCharge" ).datepicker({
					showOn: "button",
					buttonImage: "../images/calender.png",
					buttonImageOnly: true,
					buttonText: "Open calendar",
					dateFormat: 'dd/mm/yy'
					});
				$( "#dateToInCharge" ).datepicker({
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