<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page isELIgnored="false" %> 在web.xml中的web-app版本设置不是2.4的情况下，默认不能使用EL表达式，需要这一句设置一下--%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="UTF-8">	
	<meta name="keywords" content="婧麒防辐射服,孕妇防辐射服,孕妇装,十月妈咪,添香,防辐射服哪个牌子好,孕妇内衣">
	<meta name="description" content="孕妈妈良品导购,有品质又实用的孕妈妈导购网站,为孕妈妈们提供靠谱和精致的物品推荐。">
	<title>孕妈妈  网站地图</title>
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
	<style>
	    .prlist-inner table tbody tr td a {
	            color:#0000FF;
		}
    </style>
</head>

<body>
	<!-- begin wrapper 网站所有展示的内容 -->
	<div id="wrapper">		
		<!-- 网站的统一的头 -->
		<%@ include file="/common/common_body_head.jsp"%>
		
		<div id="body">				
			<div>
				<div class="prlist">
					<div class="prlist-inner">
						<table cellpadding="0" cellspacing="20" class="article-tilte-outline">
							<tr>
								<td>
									<a href="${YMM_DOMAIN}" target="_blank" style="color:#FF4500;">首页</a>
							    </td>
							    <td>
							    	<c:forEach var="item" items="${navigationList}">
							    		<a href="${YMM_DOMAIN}/cate/${item.id}" target="_blank">${item.name}</a>&nbsp;&nbsp;&nbsp;&nbsp;
							    	</c:forEach>					
							    </td>
							</tr>
							<tr></tr>
							
							<c:forEach var="item" items="${allCateDTOList}">
								<tr>
									<td>
							    		<a href="${YMM_DOMAIN}/cate/${item.curNavCate.id}" target="_blank" style="color:#FF4500;">${item.curNavCate.name}</a>
								    </td>
								    <td>
								    	<c:forEach var="innerItem" items="${item.lv2CateList}">
											<a href="${YMM_DOMAIN}/cate/${innerItem.cid}" target="_blank">${innerItem.name}</a>
											&nbsp;&nbsp;&nbsp;&nbsp;
										</c:forEach>
								    	<a href="${YMM_DOMAIN}/cate/${item.curNavCate.id}/article" target="_blank">${item.curNavCate.name}精品攻略</a>
								    </td>
							    </tr>
							</c:forEach>
							<tr></tr>
							
							<tr>
								<td>
									<a href="${YMM_DOMAIN}/article/cate/shopping" target="_blank" style="color:#FF4500;">孕妈妈购物攻略</a>
							    </td>
							    <td>
								    <c:forEach var="item" items="${shoppingArticleCateList}">
								    	<a href="${YMM_DOMAIN}/article/cate/${item.id}" target="_blank">${item.name}</a>&nbsp;&nbsp;&nbsp;&nbsp;
								    </c:forEach>
							    </td>
							</tr>
							<tr></tr>
							<c:forEach var="item" items="${shoppingArticleCateList}">
								<tr>
									<td>
							    		<a href="${YMM_DOMAIN}/article/cate/${item.id}" target="_blank" style="color:#FF4500;">${item.name}</a>
								    </td>
								    <td>
								    	<c:forEach var="innerItem" items="${item.articleList()}">
											<a href="${YMM_DOMAIN}/article/${innerItem.id}" target="_blank">${innerItem.title}</a>
											&nbsp;&nbsp;&nbsp;&nbsp;
										</c:forEach>
								    </td>
							    </tr>
							</c:forEach>
							<tr></tr>
						</table>
					</div>
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
	<%@ include file="/common/tao_dian_jin.jsp" %>	
</body>
</html>