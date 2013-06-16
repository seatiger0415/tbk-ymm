<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page isELIgnored="false" %> 在web.xml中的web-app版本设置不是2.4的情况下，默认不能使用EL表达式，需要这一句设置一下--%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="UTF-8">	
	<meta name="keywords" content="怀孕,防辐射服,孕妇,孕妇装,孕妇服装,孕妇内衣,导购">
	<meta name="description" content="怀孕 购物攻略">
	<title>孕妈妈  购物攻略</title>
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
	<link href="/static/css/article_cate.css" rel="stylesheet" type="text/css">
	<style>
		.list-filter{border-bottom:1px solid #d9d9d9;padding-bottom: 0px;background-color: #F3F3F3;}
		.list-filter-status li{width:160px; height:32px;font-size:12px;}
	</style>	
</head>

<body>
	<!-- begin wrapper 网站所有展示的内容 -->
	<div id="wrapper">		
		<!-- 网站的统一的头 -->
		<%@ include file="/common/common_body_head.jsp"%>
		
		<div id="body" class="body-body">	
			<div class="list-filter">				
				<div class="list-filter-inner">
					<ul class="list-filter-status">
						<c:forEach var="item" items="${articleCateList}">
							<li <c:if test="${curArticleCateId == item.id}">class="curr"</c:if>>
								<a href="${YMM_DOMAIN}/admin/article/cate/${item.id}" target="_self">${item.name}</a>
							</li>
						</c:forEach>
					</ul>				
				</div>
			</div>
			<div class="article-body">
				<div class="article-list">
					<form action="${YMM_DOMAIN}/admin/article/update" method="POST">
						<table>
							<tr>
								<td>id:</td>
								<td><input type="text" name="article.id" value="${article.id}" size="20"/></td>
							</tr>
							<tr>
								<td>类目id:</td>
								<td><input type="text" name="article.articleCateId" value="${article.articleCateId}" size="20"/></td>
							</tr>
							<tr>
								<td>文章系列id:</td>
								<td><input type="text" name="article.articleSeriesId" value="${article.articleSeriesId}" size="20"/></td>
							</tr>
							<tr>
								<td>status:</td>
								<td><input type="text" name="article.status" value="${article.status}" size="20"/></td>
							</tr>
							<tr>
								<td>rank:</td>
								<td><input type="text" name="article.rank" value="${article.rank}" size="20"/></td>
							</tr>
							<tr>
								<td>创建时间:</td>
								<td><input type="text" name="article.createTime" value="${article.createTime}" size="20"/></td>
							</tr>
							<tr>
								<td>更新时间:</td>
								<td><input type="text" name="article.updateTime" value="${article.updateTime}" size="20" disabled="true" /></td>
							</tr>
							<tr>
								<td>title:</td>
								<td><textarea name="article.title" cols= "100" rows="1">${article.title}</textarea></td>
							</tr>
							<tr>
								<td>keywords(逗号分隔):</td>
								<td><textarea name="article.keywords" cols= "100" rows="10">${article.keywords}</textarea></td>
							</tr>
							<tr>
								<td>content:</td>
								<td><textarea name="article.content" cols= "150" rows="20">${article.content}</textarea></td>
							</tr>
							<tr>
								<td>brief:</td>
								<td><textarea name="article.brief" cols= "100" rows="10">${article.brief}</textarea></td>
							</tr>
						</table>
						<input type="submit" value="提交" />
						<input type="reset" value="清除" />
					</form>
				</div>
			</div>
			<div class="article-bottom"></div>
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