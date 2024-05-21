$(document).ready(function(){
	$("#buttonCancel").on("click",function(){
		window.location.href = "[[@{/users}]]";
	});
	if($('#id').val() != ""){
		$('#emailInput').prop('readonly', true);
		$('#emailInput').focus(function(){
			$(this).css({
                'outline': 'none',
                'box-shadow': 'none',
                'border-color': '#dee2e6'

       		});
		});
	}
	//when choose avatar or change avatar
	$("#fileImage").change(function(){
		//get size of image file with byte unit
		fileSize = this.files[0].size;
		// check file size > 1.5MB = 1.5 * 1024 * 1024 * 1.5 = 1572864
		// Image that you choose large than 1,5 Mb
		if(fileSize > 1572864){
			this.setCustomValidity("You must choose an image less than 1,5 Mb");
			this.reportValidity();

		}else{
			this.setCustomValidity("");
			showImageThumnail(this);
		}
	});
});
//show thumnail when user choose a image file from web
function showImageThumnail(fileImageInput){
	// .files returns File list and file object at the index 0.
	let file = fileImageInput.files[0];
	//create a file reader object which can read content of the file
	let reader = new FileReader();
	// readAsDataURL() method begins to read url data of input file 
	
	// onload is an event activated when the file reading process is complete.
	// e.target.result contains readed data as a url data type. this url data type can be direct use like a thumnail   
	reader.onload = function(e){
		$('#thumnail').attr("src",e.target.result);
	};
	reader.readAsDataURL(file);
	
}

