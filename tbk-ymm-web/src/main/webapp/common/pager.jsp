<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="curUrl" value="${url}"/>
<c:set var="curExtraParams" value="${extraParams}"/>

<c:if test="${not empty resultView}">
	<c:set var="curPage" value="${resultView.curPage}"/>
	<c:set var="countAll" value="${resultView.countAll}"/>
	<div id="pager">
		<div class="global-page-module global-page-big">
			<c:if test="${curPage > 0}">
				<a class="global-page-next" href="${curUrl}?curPage=${curPage-1}${curExtraParams}">
					<em>上一页</em><i class="ico-global-page"></i>
				</a>
			</c:if>
			
			<c:forEach var="item" items="${resultView.prePageNoList}">
				<c:if test="${curPage == item-1}">
					<span class="select">${item}</span>
				</c:if>
				<c:if test="${curPage != item-1}">
					<a href="${curUrl}?curPage=${item-1}${curExtraParams}">${item}</a>
				</c:if>
			</c:forEach>
			
			<c:if test="${not empty resultView.midPageNoList}">
				<span class="global-page-break">...</span>
			</c:if>
			
			<c:forEach var="item" items="${resultView.midPageNoList}">
				<c:if test="${curPage == item-1}">
					<span class="select">${item}</span>
				</c:if>
				<c:if test="${curPage != item-1}">
					<a href="${curUrl}?curPage=${item-1}${curExtraParams}">${item}</a>
				</c:if>
			</c:forEach>
			<c:if test="${not empty resultView.suffixPageNoList}">
				<span class="global-page-break">...</span>
			</c:if>
			<c:forEach var="item" items="${resultView.suffixPageNoList}">
				<c:if test="${curPage == item-1}">
					<span class="select">${item}</span>
				</c:if>
				<c:if test="${curPage != item-1}">
					<a href="${curUrl}?curPage=${item-1}${curExtraParams}">${item}</a>
				</c:if>
			</c:forEach>
			<c:if test="${curPage < resultView.allPageNo - 1}">
				<a class="global-page-next" href="${curUrl}?curPage=${curPage+1}${curExtraParams}"><em>下一页</em><i class="ico-global-page"></i></a>
			</c:if>
		</div>
	</div>
</c:if>