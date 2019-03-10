$(function(){
//	alert("asfaf");
	getNewsList(1,10);
})
//查询所有的新闻信息
function getNewsList(currPage,row){
//	alert("asda");
	$.ajax({
//		type:"get",
//		url:"../json/news.json",
		type:"post",
		url:basePath+"/news/list",
		async:false,
		data: {
			"row": row,
			"page": currPage
		},
		dataType:"json",
		success:function(data){
			//分页
			var tempStr = '';
			var totalPage = data.totalPage;//总页数
			var totalCount = data.totalCount;//总记录数
			var pageSize = row;
			if(currPage>1){
				tempStr += '<div class="lyb_pages_button" onclick="getNewsList(1,'+pageSize+')">首页</div>';
				tempStr += '<div class="lyb_pages_button" onclick="getNewsList('+(currPage-1)+','+pageSize+')">上一页</div>';
			}else{
				tempStr += '<div class="lyb_pages_button" >首页</div>';
				tempStr += '<div class="lyb_pages_button" >上一页</div>';
			}
			for(var i=0;i<totalPage;i++){
				tempStr += '<div class="lyb_pages_button1" onclick="getNewsList('+(i+1)+','+pageSize+')">'+(i+1)+'</div>'
			}
			if(currPage<totalPage){
				tempStr += '<div class="lyb_pages_button" onclick="getNewsList('+(currPage+1)+','+pageSize+')">下一页</div>';
				tempStr += '<div class="lyb_pages_button" onclick="getNewsList('+totalPage+','+pageSize+')">尾页</div>';
			}else{
				tempStr += '<div class="lyb_pages_button" >下一页</div>';
				tempStr += '<div class="lyb_pages_button" >尾页</div>';
			}
			$(".product_pages").html(tempStr);
			
			//加载数据
			$(".list_lb").html("");
			$.each(data.pageList, function(i,item) {
//				$(".list_lb").append('<div class="list_lb_box"><div class="list_lb_box_bt"><span class="FL"><a href="#" onclick="openNewsDetail('+item.id+');">'+item.title+'</a></span><span class="FR" style="color:#000;font-family:'微软雅黑';font-size:16px;">发表时间：<span style="color:#db251a;">'+item.createTime+'</span></span></div><div class="list_pic"><img src="'+item.img+'" width="193" height="136" /></div><div class="list_lb_box_nr">&nbsp; &nbsp; &nbsp; &nbsp; '+item.introduction+' &nbsp…<br /><a href="#" onclick="openNewsDetail('+item.id+');">阅读全文&nbsp; <img src="../img/news/dot_li_moe.gif" /></a></div></div><div class="clear" style="height:25px;border-top:1px #eee dashed; margin-top:10px;"></div>');
				$(".list_lb").append('<div class="list_lb_box"><div class="list_lb_box_bt"><span class="FL"><a href="#" onclick="openNewsDetail('+item.id+');">'+item.title+'</a></span><span class="FR" style="color:#000;font-family:"微软雅黑";font-size:16px;">发表时间：<span style="color:#db251a;">'+item.createTime+'</span></span></div><div class="list_pic"><img src="'+item.img+'" width="193" height="136" /></div><div class="list_lb_box_nr">&nbsp; &nbsp; &nbsp; &nbsp; '+item.introduction+' &nbsp…<br /><a href="#" onclick="openNewsDetail('+item.id+');">阅读全文&nbsp; <img src="../img/news/dot_li_moe.gif" /></a></div></div><div class="clear" style="height:25px;border-top:1px #eee dashed; margin-top:10px;"></div>');	
			});
			
		}
	});
}
function openNewsDetail(id){
	sessionStorage.setItem("newsId",id);
	window.location.href = "newsDetail.html";
}
