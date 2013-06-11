<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
							<a class="tag<c:if test='${item.selected()}'> curr</c:if>" 
								href="${YMM_DOMAIN}/cate/${item.cid}">${item.name}</a>
						</c:forEach>
					</div>
				</div>
				<div class="list-filter-category list-filter-category-fixed cls" id="tagToolFixed" style="display:none;">
					<div class="list-filter-inner">
						<div class="category-wrapper cls">
							<c:forEach var="item" items="${subCateList}">
							<a class="tag<c:if test='${item.selected()}'> curr</c:if>" 
								href="${YMM_DOMAIN}/cate/${item.cid}">${item.name}</a>
						</c:forEach>
						</div>
						<div class="header-module-user-wrap">
							<p>回到首页</p>						
						</div>
					</div>
				</div> 
				<%@ include file="/views/inc/cate_filter_bar.inc"%>
			</div>
			<div>
				<div class="prlist">
					<div class="prlist-inner">
						<c:forEach var="item" items="${itemResultView.list}" varStatus="status">
							<div class="item lproot">
								<div>
									<a data-type="0" data-itemid="${item.trackIid}" data-rd="1" data-style="2" data-tmpl="230x312" target="_blank"></a>
								</div>
								<!--  
								<div class="photo">
									<a href="${item.itemUrl}" target="_blank">
										<img class="" src="${item.itemPicture}" alt="${item.itemName}">
									</a>
								</div>
								<p class="txt">
									<a href="${item.itemUrl}" target="_blank">${item.itemName}</a>
								</p>
								<div class="op" style="text-align: center;">
									<a class="btn-like" href="#"><i></i><span><em>36</em></span></a>
								</div>-->
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- pager -->
				<c:set var="resultView" value="${itemResultView}"/>
				<c:set var="url" value="${YMM_DOMAIN}/cate/${catePageParam.cateId}"/>
				<c:set var="extraParams" value=""/>
				<c:if test="${catePageParam.smallPrice > 0}">
					<c:set var="extraParams" value="${extraParams}&smallPrice=${catePageParam.smallPrice}"/>
				</c:if>
				<c:if test="${catePageParam.bigPrice > 0}">
					<c:set var="extraParams" value="${extraParams}&bigPrice=${catePageParam.bigPrice}"/>
				</c:if>
                <%@ include file="/common/pager.jsp" %>
                
				<!--  延迟加载的功能
				<div id="loading" class="loading loading-26" style="display: none;">
					<i></i>加载中……
				</div>
				-->
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
	<%@ include file="/common/tao_dian_jin.jsp" %>	
</body>
</html>