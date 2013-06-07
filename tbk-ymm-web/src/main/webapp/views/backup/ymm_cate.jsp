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
	<link rel="stylesheet" type="text/css" href="/static/css/list.css">

	<%@ include file="/common/common_head_js.jsp" %>
	<script>
		_hmt.push(['_setAutoPageview', false]);
		_hmt.push(['_trackPageview', '/faxian/jujia?&abtest=2']);
	</script>
	<script src="/static/js/logger.js"></script>
	<link href="/static/css/bdsstyle.css" rel="stylesheet" type="text/css">
</head>

<body>
	<!-- 网站最上面的广告 -->
	<%@ include file="/common/common_top_ad.jsp"%>
	
	<!-- begin wrapper 网站所有展示的内容 -->
	<div id="wrapper">		
		<!-- 网站的统一的头 -->
		<%@ include file="/common/common_body_head.jsp"%>
		
		<div id="body">	
			<div class="list-filter">
				<div class="list-filter-category" id="tagTool">
					<div class="list-filter-inner">
						<c:forEach var="item" items="${subCateList}">
							<a class="tag<c:if test='${item.isSelected()}'> curr</c:if>" 
								href="${YMM_DOMAIN}/cate/${item.cid}">${item.name}</a>
						</c:forEach>
						<!--  
						<a class="tag" href="#">客厅</a>
						<a class="tag" href="#">家饰</a>
						<a class="tag" href="#">卧室</a>
						<a class="tag" href="#">卫浴</a>
						<a class="tag" href="#">厨房</a>
						<a class="tag" href="#">小家电</a>
						<a class="tag" href="#">灯具</a>
						<a class="tag" href="#">花园</a>
						<a class="tag" href="#">创意设计</a>
						<a class="tag" href="#">杂货</a>
						<a class="tag" href="#">收纳</a>
						<a class="tag" href="#">清凉一夏</a>
						-->
					</div>
				</div>
				<div class="list-filter-category list-filter-category-fixed cls" id="tagToolFixed" style="display:none;">
					<div class="list-filter-inner">
						<div class="category-wrapper cls">
							<c:forEach var="item" items="${subCateList}">
							<a class="tag<c:if test='${item.isSelected()}'> curr</c:if>" 
								href="${YMM_DOMAIN}/cate/${item.cid}">${item.name}</a>
						</c:forEach>
						</div>
						<div class="header-module-user-wrap">
							<p>回到首页</p>
							<p>xxx知识</p>						
						</div>
					</div>
				</div>
				<div class="list-filter-inner">
					<ul class="list-filter-status">
						<li class="curr"><a href="#" target="_self">小编精选</a></li>
						<li class="middle"><a href="#" target="_self">7天最热</a></li>
						<li class=""><a href="#" target="_self">最新推荐</a></li>
					</ul>
					<div class="list-filter-price">
						<span class="tt">价格：</span>
						<ul>
							<li class="curr">
								<a href="#" target="_self">全部</a>
							</li>
							<li class="">
								<a href="#" target="_self">0-50</a>
							</li>
							<li class="">
								<a href="#" target="_self">51-200</a>
							</li>
							<li class="">
								<a href="#" target="_self">201-500</a>
							</li>
							<li class="">
								<a href="#" target="_self">500以上</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div>
				<div class="prlist">
					<div class="prlist-inner">
						<c:forEach begin="0" end="11" varStatus="status">
							<div class="item lproot" data-lpid="53679">
								<div class="photo">
									<a href="#" target="_blank">
										<img class="" src="/static/img/230_230.jpg" alt="这是一个商品">
									</a>
								</div>
								<p class="txt">
									<a href="#" target="_blank">这里是商品说明</a>
								</p>
								<div class="op" style="text-align: center;">
									<a class="btn-like" href="#"><i></i><span><em>36</em></span></a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div id="pager" style="display: none;">
					<div class="global-page-module global-page-big">
						<span class="select">1</span>
						<a href="#">2</a>
						<a href="#">3</a>
						<a href="#">4</a>
						<a href="#">5</a>
						<a href="#">6</a>
						<a href="#">7</a>
						<a href="#">8</a>
						<a href="#">9</a>
						<span class="global-page-break">...</span>
						<a href="#">62</a>
						<a class="global-page-next" href="#"><em>下一页</em><i class="ico-global-page"></i></a>
					</div>
				</div>
				<div id="loading" class="loading loading-26" style="display: none;">
					<i></i>加载中……
				</div>
			</div>
				
			<!-- body最下面的轮播广告部分-->
			<%--@ include file="/common/common_bottom_ad.jsp"--%>
		</div>
		
		<!-- 下面应该是右面分享的组件 -->
		<script type="text/javascript" id="bdshare_js" data="uid=599962&amp;type=slide&amp;img=0&amp;pos=right" src="/static/js/bds_s_v2.js"></script>
		<script type="text/javascript" id="bdshell_js" src="/static/js/shell_v2.js"></script>
		<script type="text/javascript">
			var bds_config = {
				snsKey:{ bdText : "怀孕了？不知道需要准备什么？快来孕妈妈专业良品导购站看一下吧~",
						 searchPic : 1,
						 bdComment : "孕妈妈良品导购,有品质又实用的孕妈妈导购网站,为孕妈妈们提供靠谱和精致的物品推荐。",
						 bdDesc : "",
						 bdPic : ""
					}};
			document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000);
		</script>
		
		<!-- 页脚部分 -->
		<%@ include file="/common/common_footer.jsp" %>
	</div>
	<!-- end wrapper 网站所有展示的内容 -->	
	
	<script src="/static/js/common.js"></script>
		
</body>
</html>