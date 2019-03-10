$(function(){
	getProductDetail();
})

//填充产品详情页面内容
function getProductDetail(){
	var id = sessionStorage.getItem("productId");
//	alert(id);
	$.ajax({
//		type:"get",
//		url:"../json/productDetail.json",
		type:"post",
		url:basePath+"/product/detail",
		async:false,
		data:{
			"id":id
		},
		dataType:"json",
//		contentType:"application/json",
		success:function(data){
			console.log(data);
			var productDetail = "";
			var product = data.product;
			productDetail += '<div style="background:url('+product.img+') top center no-repeat;background-size: auto 400px;"><div class="proh"></div><div class="pro_tit"><img src="../img/product/dot_pro1.jpg" />&nbsp;'+product.name+'</div><div class="pro_not"><p>'+product.desc+'</p></div><div class="clear pro_borb">&nbsp;</div></div>';
			$.each(product.details, function(i,item) {
				productDetail += '<div class="pro_tit"><img src="../img/product/dot_pro1.jpg" />&nbsp;'+item.functionName+'</div><div class="pro_not">'+item.functionDesc+'</div><div class="clear pro_borb">&nbsp;</div>';
			});
			$("#productDetail").append(productDetail);
		}
	});
}