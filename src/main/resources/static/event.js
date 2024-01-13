/**
*@author sandeep.rana
*/
let millis = 0;
$(document).ready(function() {
    try{
            const date = new URLSearchParams(window.location.search).get('date');
            millis = Date.parse(date);
    } catch(e){}
});

function addEvent() {

    const request = {
        title: $('#title').val(),
        description: $('#description').val(),
        mode: $('#mode').val() == 'true' ? 'PRIVATE' : 'PUBLIC',
        colorCode: $('#colorCode').val()
    }
    if(millis !==0) {
        request['calendarTimestamp']=millis;
    }

    $.ajax({
    		method: "POST",
    		url: `/admin/event/create`,
    		cache : false,
            contentType : "application/json",
            dataType : "json",
            data : JSON.stringify(request),
    		beforeSend: function(xhr) {
    			console.log('Loading ....');
    		},
    		success: function(response) {
    			console.log(response);
    		    alert('Saved...');
    		},
    		error: function(err) {
    			console.error(err);
    		},
    	});
}
