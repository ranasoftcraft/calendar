/**
*@author sandeep.rana
*/
//let milestone = 0;
$(document).ready(function() {
    try{
            milestone = new URLSearchParams(window.location.search).get('milestone');
            getIssues(milestone);
    } catch(e){}
});

function getIssues(milestone) {

    $.ajax({
    		method: "GET",
    		url: `/calendar/user/milestone/issues?milestone=${milestone}`,
    		cache : false,
            contentType : "application/json",
            dataType : "json",
    		beforeSend: function(xhr) {
    			console.log('Loading ....');
    		},
    		success: function(response) {
    			console.log(response);
    			if(response?.totalElements > 0) {
    			    response?.content?.forEach((item, index) => {
                            $('#issues').append(createAnItem(item,index));
                    });
    			} else {
    			    $('#issues').append(emptyState());
    			}
    		},
    		error: function(err) {
    			console.error(err);
    		},
    	});
}

function createAnItem(item, index) {
    return `<li class="list-group-item d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold">${item?.title}</div>
                                <div id="comments_${index}" style="width:400px">
                                    ${markdownToHtml(item?.comments, index)}
                                </div>

                            </div>
                            ${badgeStatus(item?.done)}
                </li>`;
}

function markdownToHtml(markdown, index) {
    return marked(markdown, { breaks: true });
}

function badgeStatus(done) {
    if(done) {
        return `<span class="badge bg-success rounded-pill">Done</span>`;
    }
    return `<span class="badge bg-info rounded-pill">In Progress</span>`;
}

function emptyState() {
    return `<div class="alert alert-warning" role="alert"> Nothing yet finalized </div>`;
}