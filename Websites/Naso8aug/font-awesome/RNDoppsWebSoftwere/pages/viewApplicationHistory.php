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
							<li>Utilities&nbsp;-&nbsp;History</li>
						</ul>
					</div>
				</div>
				<div class="row" style="padding:0px;margin:0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;margin:0px;">
						<h3 style="margin:10px 0 2px 0;">History about Travel and Temporary Advance</h3>
					</div>
				</div><hr style="border: 3px solid #8c8c8c;">
				<div class="row" style="margin-top:2%;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<button type="button" class="btn btn-info" id="btnViewTravelHist" style="font-family: Verdana, Arial, Helvetica;">Travel History</button>
						<button type="button" class="btn btn-info" id="btnViewTempAdvHist" style="font-family: Verdana, Arial, Helvetica;">Advance History</button>
					</div>
				</div>
				<span id="historyTempAdv"><div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<p class="subHeadingRateContract">Temporary Advance Applications</p>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><div class="panel panel-default table-responsive">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>Reference Number</th>
									<th>Date and Time of Application</th>
									<th>Settlement due in</th>
									<th>Project Number</th>
									<th>Account Head</th>
									<th>Amount</th>
									<th>Justification</th>
									<th>Settlement</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input class="form-control" type="text"/></td>
									<td><input class="form-control" type="text"/></td>
									<td><input class="form-control" type="text"/></td>
									<td><input class="form-control" type="text"/></td>
									<td><input class="form-control" type="text"/></td>
									<td><input class="form-control" type="text"/></td>
									<td><input class="form-control" type="text"/></td>
									<td><input class="form-control" type="text"/></td>
								</tr>
							</tbody>
						</table></div>
					</div>
				</div></span>
				<span id="historyTravel"><div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<p class="subHeadingRateContract">Travel History</p>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><div class="panel panel-default table-responsive">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>Reference Number</th>
									<th>Date and Time of Application</th>
									<th>Settlement due in</th>
									<th>Project Number</th>
									<th>Account Head</th>
									<th>Amount</th>
									<th>Justification</th>
									<th>Settlement</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input class="form-control" type="text"/></td>
									<td><input class="form-control" type="text"/></td>
									<td><input class="form-control" type="text"/></td>
									<td><input class="form-control" type="text"/></td>
									<td><input class="form-control" type="text"/></td>
									<td><input class="form-control" type="text"/></td>
									<td><input class="form-control" type="text"/></td>
									<td><input class="form-control" type="text"/></td>
								</tr>
							</tbody>
						</table></div>
					</div>
				</div></span>
				<!--hidden div--><div id="hiddenDiv"></div>
				
			</div>
				<?php include 'include/notificationBlock.php';?>
		</div>
			<!--footer start here-->
				<?php include 'include/footerPage.php';?>
			<!--footer end here-->
	</div>
	<script>
		$(document).ready(function(){
			$('#historyTempAdv').hide();$('#historyTravel').hide();
			$('#btnViewTravelHist').on('click',function(){
				$('#historyTempAdv').hide();$('#historyTravel').show();
			});
			$('#btnViewTempAdvHist').on('click',function(){
				$('#historyTempAdv').show();$('#historyTravel').hide();
			});
		});
	</script>
</body>
</html>