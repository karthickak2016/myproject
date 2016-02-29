$(document).ready(function() {   
	$("#createnewuser").click(function() {
		$.get('pages/commons/createnewuser.html', function(data){
			$('#includeContent').html(data);
		}).done(function(){
			loadOptions();
		})
	});	
	function loadOptions() {
		$('ageselect option').remove();
		$("#ageselect").append("<option value=''>"+''+"</option>");
		for (var i = 18; i < 57; i++) {
			 $("#ageselect").append("<option value='"+i+"'>"+i+"</option>");
		}
		$("#clearbtn").click(function() {
			$('#empid').val('');
			$('#name').val('');
			$('#ageselect').val('');
			$('#address').val('');
			$('#salary').val('');
		});
		$("#submitbtn").click(function() {
			alert('New user added');
		});
	}
});