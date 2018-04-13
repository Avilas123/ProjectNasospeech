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
							<li>Utilities&nbsp;-&nbsp;Temporary Advance</li>
						</ul>
					</div>
				</div>
				<div class="row" style="padding:0px;margin:0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;margin:0px;">
						<h3 style="margin:10px 0 2px 0;">Apply for Temporary Advance</h3>
					</div>
				</div><hr style="border: 3px solid #8c8c8c;">
				<form id="temporaryAdvanceForm">
					<div class="row" style="margin-top:1%;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p class="subHeadingTemAdv">Basic details of the Applicant</p>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Application Initiated By</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Name of the Applicant</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Designation</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Department/Center/Section</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Webmail ID</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Employee Code</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row" style="margin-top:3%;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p class="subHeadingTemAdv">Details of Temporary Advance to be drawn</p>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-7 col-md-6 col-sm-12 col-xs-12">
							<p class="zeroBottomMarginPara">Is this Advance related to Travel?(With Leave From Rnd)</p>
						</div>
						<div class="col-lg-3 col-md-4 col-sm-12 col-xs-12 radioBtnVisit">
							<label class="radio-inline">
								<input type="radio" name="advanceRelatedwith">Yes
							</label>
							<label class="radio-inline">
								<input type="radio" name="advanceRelatedwith">No
							</label>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-7 col-md-6 col-sm-12 col-xs-12">
							<p class="zeroBottomMarginPara">Is this Advance related to Travel? (Without Leave From RnD)</p>
						</div>
						<div class="col-lg-3 col-md-4 col-sm-12 col-xs-12 radioBtnVisit">
							<label class="radio-inline">
								<input type="radio" name="advanceRelatedwithOut">Yes
							</label>
							<label class="radio-inline">
								<input type="radio" name="advanceRelatedwithOut">No
							</label>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-12 col-xs-12">
							<button type="button" class="btn btn-default btnViewInfo">View Info</button>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Amount (In Rupees)</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Amount (In Words)</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Purpose / Justification</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<textarea class="form-control" placeholder="Briefly mention the purpose"></textarea>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-offset-5 col-lg-2 col-md-offset-6 col-md-2 col-sm-offset-4 col-sm-2 col-xs-12">
							<input type="file">
							    <!--<label class="btn btn-default btn-file">
									Browse <input type="file" style="display: none;">
								</label>-->
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p class="subHeadingTemAdv">Declaration</p>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p>It is certified that settlement against this advance will be submitted within a period of 45 days from the date of the advance drawn.</p>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<input type="checkbox"/> I Accept
						</div>
					</div><br/>
					<div class="row" style="margin-top:1%;">
						<div class="col-lg-offset-5 col-lg-7 col-md-offset-6 col-md-6 col-sm-offset-4 col-sm-8 col-xs-12">
							<button type="button" class="btn btn-danger btnTemporaryAdvance" id="resetFormTA">Reset</button>&nbsp;
							<button type="button" class="btn btn-success btnTemporaryAdvance">Submit</button>
						</div>
					</div><br/><br/><br/><hr style="border: 3px solid #8c8c8c;"><br/>
				</form>
			</div>
			<?php include 'include/notificationBlock.php'?>
		</div>
			<!--footer start here-->
				<?php include 'include/footerPage.php';?>
			<!--footer end here-->
	</div>
</body>
</html>