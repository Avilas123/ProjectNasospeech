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
							<li>Utilities&nbsp;-&nbsp;Rate Contract</li>
						</ul>
					</div>
				</div><!--<hr style="border: 1px solid #d9d9d9;"/>-->
				<div class="row" style="padding:0px;margin:0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin:0px;padding:0px;">
						<h3 style="margin:10px 0 2px 0;">Rate Contract</h3>
					</div>
				</div><hr style="border: 3px solid #8c8c8c;">
				<div class="row" style="margin-top:2%;">
					<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
					<p class="zeroBottomMarginPara">Select Form Type</p>
					</div>
					<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
						<select class="form-control" id="selectFormType">
							<option value="select">Select Form Type</option>
							<option value="p3">P3(CHEMICALS/GLASSWARE/ PLASTIC WARE/GAS UNDER RC)</option>
							<option value="p4">P4(UPS, UPS BATTERY, HP PRINTER CARTRIDGES, ETC. UNDER RC)</option>
						</select><br/>
					</div>
				</div>
				<div id="hideDivRateContract" style="height:600px;"></div>
				<form id="Form_p3" style="margin-top:1%;">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p class="subHeadingRateContract">Basic details of the Applicant</p>
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
							<p class="zeroBottomMarginPara">Indentor</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Email ID</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="email"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Designation</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<select class="form-control" id="designationContract">
								<option value="select">Select</option>
								<option value="prof">Professor</option>
								<option value="asso_prof">Associate Professor</option>
								<option value="assis_prof">Assistant Professor</option>
								<option value="tech_off">Technical Officer</option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Department / Centre / Section</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div><br/>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p style="font-weight:bold;margin-bottom:-8px;">Indenter/ARC firm's details & undertaking by the indenter</p>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p>The items as per the details, are required for my teaching/ research (or as the case may be) purpose and these items are available with: 
							(Please mention here the name of the firm as per ARC circular)</p>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Item Type</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<select class="form-control" id="itemTypeContract">
								<option value="select">Select Item Type</option>
								<option value="chemical">Chemicals</option>
								<option value="glassware">Glassware</option>
								<option value="plasticware">Plasticware</option>
								<option value="filtration">Filtration</option>
								<option value="mixed_catalogue">Mixed Catalogue</option>
								<option value="medical">Medical Lab Reagents & Kits</option>
							</select>
						</div>
					</div>
					<div class="row" style="margin-top:20px;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="checkbox">
								<label><input type="checkbox" value="">Certified that the items mentioned overleaf are for my experiments which are to be procured from the above 
								mentioned firm/its authorized dealer only.</label>
							</div>
							<div class="checkbox">
								<label><input type="checkbox" value="">Certified that the (a) Items, (b) Catalogue Numbers, (c) Page Numbers and (d) Prices of the above mentioned 
								items are furnished as per the CURRENT APPLICABLE PRICE LIST only and we shall be held responsible in case the information is found to be incorrect, at a later stage.</label>
							</div>
							<div class="checkbox">
								<label><input type="checkbox" value="">The materials are to be delivered within two weeks from the date of issue of purchase order.</label>
							</div>
						</div>
					</div><br/>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p class="subHeadingRateContract">Particulars of Items</p>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><div class="panel panel-default table-responsive" style="margin-bottom:5px;">
							<table class="table table-bordered" id="p3TableDynamic_field">
								<thead>
									<tr>
										<th>S.No.</th>
										<th>Item Description</th>
										<th>Cat No.</th>
										<th>Page No.</th>
										<th>Unit Rate</th>
										<th>Quantity</th>
										<th>Total</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<tr id="p3row1">
										<td>1</td>
										<td><input class="form-control" type="text" name="itemDes[]" id="itemDes"/></td>
										<td><input class="form-control" type="text" name="catNo[]" id="catNo"/></td>
										<td><input class="form-control" type="text" name="pageNo[]" id="pageNo"/></td>
										<td><input class="form-control" type="text" name="unitRate[]" id="unitRate"/></td>
										<td><input class="form-control" type="text" name="quantity[]" id="quantity"/></td>
										<td><input class="form-control" type="text" name="total[]" id="total"/></td>
										<td><button type="button" class="btn btn-danger btn-removeP3" id="1"><i class="fa fa-minus" aria-hidden="true"></i></button></td>
									</tr>
								</tbody>
							</table></div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<button type="button" class="btn btn-info" id="addRowP3"><i class="fa fa-plus" aria-hidden="true"></i></button>
						</div>
					</div><br/>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Total</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Less : Discount (If Any in %)</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Total After Discount</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Add : Packing/Freight Etc. (If Any in %)</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Total After Packing Etc.</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Add : VAT/CST (in %)</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Total After Tax</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Grand Total</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div><br/>
					<div class="row" style="margin-top:1%;">
						<div class="col-lg-offset-5 col-lg-7 col-md-offset-6 col-md-6 col-sm-offset-4 col-sm-8 col-xs-12">
							<button type="button" class="btn btn-danger p3submit" id="resetFormP3">Reset</button>&nbsp;
							<button type="button" class="btn btn-success p3submit">Submit</button>
						</div>
					</div><br/><br/><br/><hr style="border: 3px solid #8c8c8c;"><br/>
				</form>	
				<form id="Form_p4" style="margin-top:1%;">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p class="subHeadingRateContract">Basic details of the Applicant</p>
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
							<p class="zeroBottomMarginPara">Indentor</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Email ID</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="email"/>
						</div>
					</div>
					<div class="row" style="margin-top:5px;">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Designation</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<select class="form-control" id="form4DesignationContract">
								<option value="select">Select</option>
								<option value="prof">Professor</option>
								<option value="asso_prof">Associate Professor</option>
								<option value="assis_prof">Assistant Professor</option>
								<option value="tech_off">Technical Officer</option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Department / Centre / Section</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text" style="margin:0px;"/>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Justification</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<textarea class="form-control" placeholder="Required for"></textarea>
						</div>
					</div>
					<div class="row" style="margin-top:5px;">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Select Item Type</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<select class="form-control" id="form4SelectItemType">
								<option value="select">Select</option>
								<option value="ups_battery">UPS Batterys</option>
								<option value="hp_printer_cartridges">HP Printer Cartridges</option>
								<option value="gas_refilling">Gas Refilling</option>
								<option value="copier_papers">Copier Papers</option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Select Vendor</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<select class="form-control" id="form4SelectItemType">
								<option value="select">Select</option>
								<option value="prof">1</option>
								<option value="asso_prof">2</option>
								<option value="assis_prof">3</option>
								<option value="tech_off">4</option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Vendor Address</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<textarea class="form-control"></textarea>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Vendor Email</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="email">
						</div>
					</div>
					<div class="row" style="margin-top:5px;">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Agreement Number</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<textarea class="form-control"></textarea>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p class="subHeadingRateContract">Particulars of Items</p>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><div class="panel panel-default table-responsive" style="margin-bottom:5px;">
							<table class="table table-bordered" id="p4TableDynamic_field">
								<thead>
									<tr>
										<th>S.No.</th>
										<th>Item Description</th>
										<th>Quantity</th>
										<th>Rate (Rs)</th>
										<th>Amount (Rs)</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<tr id="p4row1">
										<td>1</td>
										<td><input class="form-control" type="text" name="p4itemDes[]"/></td>
										<td><input class="form-control" type="text" name="p4quantity[]"/></td>
										<td><input class="form-control" type="text" name="p4rate[]"/></td>
										<td><input class="form-control" type="text" name="p4amount[]"/></td>
										<td><button type="button" class="btn btn-danger btn-removeP4" id="1"><i class="fa fa-minus" aria-hidden="true"></i></button></td>
									</tr>
								</tbody>
							</table></div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<button type="button" class="btn btn-info" id="addRowP4"><i class="fa fa-plus" aria-hidden="true"></i></button>
						</div>
					</div><br/>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Total Basic Value</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Less: Discount (if any)</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Sub Total</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Add:VAT(to be deducted at source in %)</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Grand Total for IIT Guwahati(Round off)</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<input class="form-control" type="text">
						</div>
					</div>
					<div class="row" style="margin-top:5px;">
						<div class="col-lg-5 col-md-6 col-sm-4 col-xs-12">
							<p class="zeroBottomMarginPara">Grand Total In Words</p>
						</div>
						<div class="col-lg-7 col-md-6 col-sm-8 col-xs-12">
							<textarea class="form-control"></textarea>
						</div>
					</div><br/>
					<div class="row" style="margin-top:1%;">
						<div class="col-lg-offset-5 col-lg-7 col-md-offset-6 col-md-6 col-sm-offset-4 col-sm-8 col-xs-12">
							<button type="button" class="btn btn-danger p4submit" id="resetFormP4">Reset</button>&nbsp;
							<button type="button" class="btn btn-success p4submit">Submit</button>
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
					var i = 1; 
					$('#addRowP3').click(function(){
						i++;
						$('#p3TableDynamic_field').append('<tr id="p3row'+i+'"><td>'+i+'</td><td><input class="form-control" type="text" name="itemDes[]" id="itemDes"/></td><td><input class="form-control" type="text" name="catNo[]" id="catNo"/></td><td><input class="form-control" type="text" name="pageNo[]" id="pageNo"/></td><td><input class="form-control" type="text" name="unitRate[]" id="unitRate"/></td><td><input class="form-control" type="text" name="quantity[]" id="quantity"/></td><td><input class="form-control" type="text" name="total[]" id="total"/></td><td><button type="button" class="btn btn-danger btn-removeP3" id="'+i+'"><i class="fa fa-minus" aria-hidden="true"></i></button></td></tr>');
					});
					$(document).on('click','.btn-removeP3',function(){
						var button_id = $(this).attr("id");
						if (button_id == i){
							var result = confirm("Are you want to delete this row ?");
							if (result) {
								$('#p3row'+button_id+'').remove();
								i--;
							}
						} else {
							alert('You should delete rows starting from last !');
						}
					});
					var j = 1;
					$('#addRowP4').click(function(){
						j++;
						$('#p4TableDynamic_field').append('<tr id="p4row'+j+'"><td>'+j+'</td><td><input class="form-control" type="text" name="p4itemDes[]"/></td><td><input class="form-control" type="text" name="p4quantity[]"/></td><td><input class="form-control" type="text" name="p4rate[]"/></td><td><input class="form-control" type="text" name="p4amount[]"/></td><td><button type="button" class="btn btn-danger btn-removeP4" id="'+j+'"><i class="fa fa-minus" aria-hidden="true"></i></button></td></tr>');
					});
					$(document).on('click','.btn-removeP4',function(){
						var button_id = $(this).attr("id");
						if (button_id == j){
							var result = confirm("Are you want to delete this row ?");
							if (result) {
								$('#p4row'+button_id+'').remove();
								j--;
							} 
						} else {
							alert('You should delete rows starting from last !');
						}
					});
				});
			</script>
	</div>
	
</body>
</html>