$(function() {
	getProductsList(1, 10);
})
//获取产品信息轮播
function getProductsList(currPage, row) {
//	var page = {
//		"row": row,
//		"page": currPage
//	};
	//	page = $.toJSON(page);
//	page = JSON.stringify(page);
//	alert(page);
	$.ajax({
		//		type:"get",
		//		url:"../json/product.json",
		type: "post",
		url: basePath + "/product/list",
		async: false,
		data: {
			"row": row,
			"page": currPage
		},
		dataType: "json",
		success: function(data) {
			console.log(data);
			//分页
			var tempStr = '';
			var totalPage = data.totalPage;//总页数
			var totalCount = data.totalCount;//总记录数
			var pageSize = row;
			if(currPage>1){
				tempStr += '<div class="lyb_pages_button" onclick="getProductsList(1,'+pageSize+')">首页</div>';
				tempStr += '<div class="lyb_pages_button" onclick="getProductsList('+(currPage-1)+','+pageSize+')">上一页</div>';
			}else{
				tempStr += '<div class="lyb_pages_button" >首页</div>';
				tempStr += '<div class="lyb_pages_button" >上一页</div>';
			}
			for(var i=0;i<totalPage;i++){
				tempStr += '<div class="lyb_pages_button1" onclick="getProductsList('+(i+1)+','+pageSize+')">'+(i+1)+'</div>'
			}
			if(currPage<totalPage){
				tempStr += '<div class="lyb_pages_button" onclick="getProductsList('+(currPage+1)+','+pageSize+')">下一页</div>';
				tempStr += '<div class="lyb_pages_button" onclick="getProductsList('+totalPage+','+pageSize+')">尾页</div>';
			}else{
				tempStr += '<div class="lyb_pages_button" >下一页</div>';
				tempStr += '<div class="lyb_pages_button" >尾页</div>';
			}
			$(".product_pages").html(tempStr);
			
			//加载数据
			var productList = '';
			$("#productShow").html('');
			$.each(data.pageList, function(i, item) {
				if(i % 2 == 0) {
					productList += '<div style="height:405px;cursor:pointer;background:#f2f2f2;" onClick="openProductDetail(' + item.id + ')"><div class="body_1002"><div class="FL pd_gkl"><div class="pd_rt_cn">' + item.name + '</div><div class="pd_rt_cn2">' + item.desc.substr(0,10) + '……</div><div class="pd_rt_cn3"><img src="../img/product/jszc_gd.jpg" /></div></div><div style="float: right;"><img src="'+item.img+'" style="height:300px;width: 300px;margin: 50px 0px;"/></div></div></div>';
				} else {
					productList += '<div style="height:405px;cursor:pointer;background:#ededed;" onClick="openProductDetail(' + item.id + ')"><div class="body_1002"><div style="float: left;"><img src="'+item.img+'" style="height:300px;width: 300px;margin: 50px 0px;"/></div><div class="FL pd_gk"><div class="pd_rt_cn">' + item.name + '</div><div class="pd_rt_cn2">' + item.desc.substr(0,10) + '……</div><div class="pd_rt_cn3"><img src="../img/product/jszc_gd.jpg" /></div></div></div></div>';
				}
			})
			$("#productShow").append(productList);
		}
	});
}

//打开产品详情页面
function openProductDetail(id) {
	sessionStorage.setItem("productId", id);
	window.location.href = "productDetail.html";
}