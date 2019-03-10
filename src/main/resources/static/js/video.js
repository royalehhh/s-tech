$(function(){
	getVideoList(1,10);
})
//获取视频列表
function getVideoList(currPage,row){
	$.ajax({
//		type:"get",
//		url:"../json/video.json",
		type:"post",
		url:basePath+"/video/list",
		async:false,
		data: {
			"row": row,
			"page": currPage
		},
		dataType: "json",
		success:function(data){
			console.log(data)
			
			//分页
			var tempStr = '';
			var totalPage = data.totalPage;//总页数
			var totalCount = data.totalCount;//总记录数
			var pageSize = row;
			if(currPage>1){
				tempStr += '<div class="lyb_pages_button" onclick="getVideoList(1,'+pageSize+')">首页</div>';
				tempStr += '<div class="lyb_pages_button" onclick="getVideoList('+(currPage-1)+','+pageSize+')">上一页</div>';
			}else{
				tempStr += '<div class="lyb_pages_button" >首页</div>';
				tempStr += '<div class="lyb_pages_button" >上一页</div>';
			}
			for(var i=0;i<totalPage;i++){
				tempStr += '<div class="lyb_pages_button1" onclick="getVideoList('+(i+1)+','+pageSize+')">'+(i+1)+'</div>'
			}
			if(currPage<totalPage){
				tempStr += '<div class="lyb_pages_button" onclick="getVideoList('+(currPage+1)+','+pageSize+')">下一页</div>';
				tempStr += '<div class="lyb_pages_button" onclick="getVideoList('+totalPage+','+pageSize+')">尾页</div>';
			}else{
				tempStr += '<div class="lyb_pages_button" >下一页</div>';
				tempStr += '<div class="lyb_pages_button" >尾页</div>';
			}
			$(".product_pages").html(tempStr);
			
			$("#totalPage").html(data.totalPage);
			$("#totalCount").html(data.totalCount);
			
			$("#videoShow").html('');
			var videoList = '<ul>';
			$.each(data.pageList, function(i,item) {
//				videoList += '<li><a data-fancybox data-type="iframe" data-src="'+item.url+'" href="javascript:;"><video src="'+item.url+'" style="height: 140px;width: 100%;object-fit: fill;" /></video><span>'+item.title+'</span></a></li>'
				videoList += '<li><a href="#" onclick="openVideo('+item.id+');"><video src="'+item.url+'" style="height: 140px;width: 100%;object-fit: fill;"/></video><span>'+item.title+'</span></a></li>';
//				videoList += '<li><a href="#" onclick="openVideo('+item.id+');"><video src="'+item.url+'" style="height:100%;width:100%;" /></video><span>'+item.title+'</span></a></li>';
			});
			videoList += '</ul>';
			$("#videoShow").append(videoList);
		}
	});
}
//打开视频观看
function openVideo(id){
	$.ajax({
//		type:"get",
//		url:"../json/videoDetail.json",
		type:"post",
		url:basePath+"/video/detail",
		async:true,
		data:{
			"id":id
		},
		dataType:"json",
		success:function(data){
			console.log(data);
			$("#videoId").attr("src",data.video.url);
			$("#videoDetail").css("display","block");
			document.getElementById("videoId").play();
		}
	});
}
//关闭视频
function closeVideo(){
	document.getElementById("videoId").pause();
	document.getElementById("videoId").currentTime=0;
//	$("#videoId").pause();
//	$("#videoId").currentTime=0;
	$("#videoDetail").css("display","none");
}
