$(document).ready(function(){
			/*common part*/
			var i = 0; var j = 0; var k = 0;
			$('.firstHeading>a').click(function(){
				i++;
				if(i % 2 == 0){
					$('.firstHeading>a').removeClass('active');
				} else {
					$('.firstHeading>a').addClass('active');
					$('.secondHeading>a').removeClass('active');j=0;
					$('.thirdHeading>a').removeClass('active');k=0;
				}
			});
			$('.secondHeading>a').click(function(){
				j++;
				if(j % 2 == 0){
					$('.secondHeading>a').removeClass('active');
				} else {
					$('.firstHeading>a').removeClass('active');i=0;
					$('.secondHeading>a').addClass('active');
					$('.thirdHeading>a').removeClass('active');k=0;
				}
			});
			$('.thirdHeading>a').click(function(){
				k++;
				if(k % 2 == 0){
					$('.thirdHeading>a').removeClass('active');
				} else {
					$('.firstHeading>a').removeClass('active');i=0;
					$('.secondHeading>a').removeClass('active');j=0;
					$('.thirdHeading>a').addClass('active');
				}	
			});
			/*end of common part*/
			/*start of applyTravel*/
			$('#resetFormTravel').on('click',function(){
				var result = confirm("Want to Reset the fields?");
				if (result) {
					$('#travelForm').trigger("reset");
				}
			});
			/*end of applyTravel*/
			/*start of leave*/
			$('#clForm').hide();$('#elForm').hide();$('#hiddenDiv').show();
			$('select#selectNatureLeave').on('change', function() {
				var opt = $(this).val();
				if (opt == 'cl') {
					$('#clForm').show();$('#elForm').hide();$('#hiddenDiv').hide();
				}
				if (opt == 'el') {
					$('#elForm').show();$('#clForm').hide();$('#hiddenDiv').hide();
				}
				if (opt == 'select') {
					$('#clForm').hide();$('#elForm').hide();$('#hiddenDiv').show();
				}
			});
			$('#resetFormCl').on('click',function(){
				var result = confirm("Want to Reset the fields?");
				if (result) {
					$('#clForm').trigger("reset");
				}
			});
			$('#resetFormEl').on('click',function(){
				var result = confirm("Want to Reset the fields?");
				if (result) {
					$('#elForm').trigger("reset");
				}
			});
			/*end of leave*/
			/*start of temporaryAdvance*/
			$('#resetFormTA').on('click',function(){
				var result = confirm("Want to Reset the fields?");
				if (result) {
					$('#temporaryAdvanceForm').trigger("reset");
				}
			});
			/*end of temporaryAdvance*/
			/*start of addUser*/
			$('#newUser').on('click',function(){
				var result = confirm("Want to Reset the fields?");
				if (result) {
					$('#addNewUser').trigger("reset");
				}
			});
			/*end of addUser*/
			/*start of inCharge*/
			$('#btnResetInCharge').on('click',function(){
				var result = confirm("Want to Reset the fields?");
				if (result) {
					$('#formInCharge').trigger("reset");
				}
			});
			/*end of inCharge*/
			/*start of applyAdhocRecruitment*/
			$('#btnAdhocReq').on('click',function(){
				var result = confirm("Want to Reset the fields?");
				if (result) {
					$('#formApplyAdhocRec').trigger("reset");
				}
			});
			/*end of applyAdhocRecruitment*/
			/*on focus placeholders are hide*/
			$('input,textarea').focus(function(){
				$(this).data('placeholder',$(this).attr('placeholder'))
					.attr('placeholder','');
				}).blur(function(){
				$(this).attr('placeholder',$(this).data('placeholder'));
			});
			/*end on focus placeholders are hide*/
			/*start of viewAttendance*/
			$(".responsive-calendar").responsiveCalendar({
				time: '2016-12',
				events: {
				}
			});
			/*end of viewAttendance*/
			/*start of header page Logo Icon*/
				$('#logoImg11').hide();
				$('#logoImg21').hide();
				$('#logoImg31').hide();
					$('#logoHover1').hover(
						function(){
							$('#logoImg1').hide();
							$('#logoImg11').show();
						},
						function(){
							$('#logoImg1').show();
							$('#logoImg11').hide();
						}
					);
					$('#logoHover2').hover(
						function(){
							$('#logoImg2').hide();
							$('#logoImg21').show();
						},
						function(){
							$('#logoImg2').show();
							$('#logoImg21').hide();
						}
					);
					$('#logoHover3').hover(
						function(){
							$('#logoImg3').hide();
							$('#logoImg31').show();
						},
						function(){
							$('#logoImg3').show();
							$('#logoImg31').hide();
						}
					);
			/*end of header page Logo Icon*/
		});