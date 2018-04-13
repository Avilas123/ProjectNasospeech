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
							<li>Utilities&nbsp;-&nbsp;Add New User</li>
						</ul>
					</div>
				</div>
				<div class="row" style="padding:0px;margin:0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;margin:0px;">
						<h3 style="margin:10px 0 2px 0;">Enter New User Details</h3>
					</div>
				</div><hr style="border: 3px solid #8c8c8c;">
				<form id="addNewUser" style="margin-top:2%;">
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara" style="margin-top:0px;">User Type</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<select class="form-control" id="selectUserType">
								<option value="select">Select</option>
								<option value="cl">Student</option>
								<option value="el">Project Staff</option>
							</select>
						</div>
					</div>
					<div class="row" style="margin-top:1%;">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara" style="margin-top:0px;">Webmail Id(<span style="color:red;">*</span>)</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="email" placeholder="example@iitg.ernet.in"/>
						</div>
					</div><br/>
					<div class="row" style="margin-top:1%;">
						<div class="col-lg-offset-5 col-lg-7 col-md-offset-6 col-md-6 col-sm-offset-4 col-sm-8 col-xs-12">
							<button type="button" class="btn btn-danger addNUser" id="newUser">Reset</button>&nbsp;
							<button type="button" class="btn btn-success addNUser">Submit</button>
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
	</div>
</body>
</html>