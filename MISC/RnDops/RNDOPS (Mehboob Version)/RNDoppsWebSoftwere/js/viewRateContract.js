$(document).ready(function(){
			$('#Form_p3').hide();$('#Form_p4').hide();
			$('#resetFormP3').on('click',function(){
				var result = confirm("Want to Reset the fields?");
				if (result) {
					$('#Form_p3').trigger("reset");
				}
			});
			$('#resetFormP4').on('click',function(){
				var result = confirm("Want to Reset the fields?");
				if (result) {
					$('#Form_p4').trigger("reset");
				}
			});
			$( 'select#selectFormType' ).on('change',function(){
				var opt = $(this).val();
				if (opt == 'p3'){
					$('#Form_p3').show();$('#Form_p4').hide();$('#hideDivRateContract').hide();
				}
				if (opt == 'p4'){
					$('#Form_p3').hide();$('#Form_p4').show();$('#hideDivRateContract').hide();
				}
				if (opt == 'select'){
					$('#Form_p3').hide();$('#Form_p4').hide();$('#hideDivRateContract').show();
				}
			});
			
		});