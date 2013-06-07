<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page isELIgnored="false" %> 在web.xml中的web-app版本设置不是2.4的情况下，默认不能使用EL表达式，需要这一句设置一下--%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="UTF-8">	
	<meta name="keywords" content="怀孕,防辐射服,孕妇,孕妇装,孕妇服装,孕妇内衣,导购">
	<meta name="description" content="孕妈妈良品导购,有品质又实用的孕妈妈导购网站,为孕妈妈们提供靠谱和精致的物品推荐。">
	<title>孕妈妈  最实用的孕妈妈导购网站</title>
	<!--  <link rel="shortcut icon" href="http://www.leho.com/favicon.ico" type="image/x-icon"> -->

	<link rel="stylesheet" type="text/css" href="/static/css/goods_bc.css">
	<link rel="stylesheet" type="text/css" href="/static/css/goods_common.css">		
	<link rel="stylesheet" type="text/css" href="/static/css/home.css">
	
	<%@ include file="/common/common_head_js.jsp" %>
</head>

<body>
	<!-- 网站最上面的广告 -->
	<%@ include file="/common/common_top_ad.jsp"%>
	
	<!-- begin wrapper 网站所有展示的内容 -->
	<div id="wrapper">	
		<!-- 网站的统一的头 -->
		<%@ include file="/common/common_body_head.jsp"%>
		
		<div id="body">
			<!--begin: 商品推荐位-->
			<%-- <%@ include file="inc/home_recommend.inc" %> --%>
			<!--end: 商品推荐位-->
			
			<!-- begin 主体部分——各个类目商品的展示 -->
			<c:forEach var="item" items="${homeDataList}" varStatus="mainStatus">
				<div class="container cls">
					<a class="to-fix" style="position: relative; left: 0px; top: -65px; width">&nbsp;</a>
					<div class="category-moudles">
						<div class="category-wrap" style="height:300px;">
							<div class="category-title-wrap">
								<h2 class="category-title"><a href="${YMM_DOMAIN}/cate/${item.navigationCate.id}" target="_blank">${item.navigationCate.name}</a></h2>
								<div class="category-title-desc"><a href="${YMM_DOMAIN}/cate/${item.navigationCate.id}" target="_blank">每日精选</a></div>
							</div>
							<div class="category-tag-wrap" style="height:190px;">
								<!--  <a href="#" target="_blank" class="special">Cate2[0]</a>-->
								<c:forEach var="innerItem" items="${item.itemCateList}" varStatus="status">
									<a href="${YMM_DOMAIN}/cate/${innerItem.cid}" target="_blank">${innerItem.name}</a>
								</c:forEach>
								<a href="${YMM_DOMAIN}/cate/${item.navigationCate.id}" target="_blank"><span class="category-tag-txt">更多</span><i class="ico-triangle"></i></a>
							</div>
						</div>
						<ul class="category-list">
							<c:forEach var="innerItem" items="${item.itemList}" varStatus="status">
								<li style="height:312px;">
									<!--  淘点金组件 -->
									<div>
										<a data-type="0" data-itemid="${innerItem.trackIid}" data-rd="1" data-style="2" data-tmpl="230x312" target="_blank"></a>
									</div>
									<!--  
									<a data-itemid="${innerItem.trackIid}" target="_blank" href="${innerItem.itemUrl}" target="_blank">
										<img class="lazy-load" src="/static/img/space.gif" data-src="${innerItem.itemPicture}">
										<div class="category-list-desc-wrap"></div>
										<dl class="category-list-desc">
											<dt style="font-size: 12px">${innerItem.itemCate().name}</dt>
											<dd>${innerItem.itemNameWithCut()}</dd>
										</dl>
									</a>
									-->
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</c:forEach>	
			<!-- end 主体部分——各个类目商品的展示 -->
				
			<!-- body最下面的轮播广告部分-->
			<%@ include file="/common/common_bottom_ad.jsp"%>			
		</div>
		
		<!-- 页脚部分 -->
		<%@ include file="/common/common_footer.jsp" %>		
	</div>
	<!-- end wrapper 网站所有展示的内容 -->	
	
	<script src="/static/js/common.js"></script>
	<%@ include file="/common/tao_dian_jin.jsp" %>	
</body>
</html>