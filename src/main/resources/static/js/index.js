(function($) {
	getProducts();
	getNews(1, 10);
})(jQuery)
//获取产品信息轮播
function getProducts() {
	$.ajax({
		//		type:"get",
		//		url:"../json/product.json",
		type: "post",
		url: basePath + "/product/home",
		async: false,
		success: function(data) {
			if(data.code == 0) {
				$(".bd ul").html();
				var productList = '';
				$.each(data.products, function(i, item) {
					productList += '<li class="new_nr" onclick="openProductDetail(' + item.id + ');" style="float: left; width: 300px;"><div class="new_nr2"><img src="' + item.img + '" width="294" height="201" /></div><div class="new_nr1" style="min-height:23px;clear:both;" align="center">' + item.name + '</div></li>';
				})
				$(".bd ul").append(productList);
			}

		}
	});
}
//打开产品详情页面
function openProductDetail(id) {
	sessionStorage.setItem("productId", id);
	window.location.href = "productDetail.html";
}
//获取首页新闻列表
function getNews(currPage, row) {
	$.ajax({
		//		type:"get",
		//		url:"../json/news.json",
		type: "post",
		url: basePath + "/news/list",
		async: false,
		data: {
			"row": row,
			"page": currPage
		},
		dataType: "json",
		success: function(data) {
			console.log(data)
			//加载数据
			$("#picBox .cf").html("");
			$("#listBox .cf").html('');
			$.each(data.pageList, function(i, item) {
				if(i==0){
					$("#listBox .cf").append('<li class="on"><img src="' + item.img + '" width="126px" height="78px" alt="" /></li>');
				}else{
					$("#listBox .cf").append('<li ><img src="' + item.img + '" width="126px" height="78px" alt="" /></li>');
				}
				$("#picBox .cf").append('<li class="pic"><a href="#" onclick="openNewsDetail(' + item.id + ')"><img src="' + item.img + '" width="530px" height="311px" alt="" /></a><span>' + item.title + '</span><div style="position:absolute; bottom:0px; left:0px; color:#000;"><div class="mid2_ttxw_wz" style="position:absolute;background:#ebebeb; z-index:99; bottom:-4px; left:0px;"><div style="width:431px; padding-top:10px; padding-left:13px;">&nbsp; ' + item.introduction + '<a href="#" onclick="openNewsDetail(' + item.id + ')" style="color:#e98c00;">【详细】</a></div></div><div class="wrap"></div></li>');
			});

		}
	});
}
//打开新闻详情
function openNewsDetail(id) {
	sessionStorage.setItem("newsId", id);
	window.location.href = "newsDetail.html";
}