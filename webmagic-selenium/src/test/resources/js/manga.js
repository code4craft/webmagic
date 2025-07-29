var videoArray = new Array();

var omain=document.getElementsByClassName('result_title');
for (var i = 0; i < omain.length; i++) {
	        var titletext =  omain[i].innerText.trim();
	        var playword = {"title":"","playUrl":"","time":""};
	        playword.title=titletext;
	        videoArray.push(playword);
	        if(i>=10){
	        	break;
	        }
}      

return JSON.stringify(videoArray).toString();


