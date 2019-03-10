$(function(){
	getNewsDetail();
})

//获取新闻详情并展示
function getNewsDetail(){
	var newsId = sessionStorage.getItem("newsId");
	$.ajax({
//		type:"get",
//		url:"../json/newsDetail.json",
		type:"post",
		url:basePath+"/news/detail",
		async:false,
		dataType:"json",
		data:{
			"id":newsId
		},
		success:function(data){
			console.log(data)
			var newsDetail = data.news;
			$("#newsTitle").text(newsDetail.title);//新闻标题
			$("#noteView").html(newsDetail.content);//新闻内容
			$("#newsImg").attr("src",newsDetail.img);//新闻图片
			$("#newsImg").attr("src",newsDetail.img);//新闻图片
			$("#newsTime").html(newsDetail.createTime);//新闻时间
		}
	});
	
}
