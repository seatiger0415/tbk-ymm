<div class="list-filter-inner">
	<ul class="list-filter-status">
		<li <c:if test="${isItemPage}">class="curr"</c:if>>
			<a href="${YMM_DOMAIN}/cate/${curCid}" target="_self">商品精选</a>
		</li>
		<li <c:if test="${!isItemPage}">class="curr"</c:if>>
			<a href="${YMM_DOMAIN}/cate/${curCid}/article" target="_self">精品攻略</a>
		</li>
	</ul>
	<div class="list-filter-price">
		<span class="tt">价格：</span>
		<ul>
			<li <c:if test="${catePageParam.smallPrice <= 0 && catePageParam.bigPrice <= 0}">class="curr"</c:if>>
				<a href="${YMM_DOMAIN}/cate/${curCid}" target="_self">全部</a>
			</li>
			<li <c:if test="${catePageParam.smallPrice == 0 && catePageParam.bigPrice == 50}">class="curr"</c:if>>
				<a href="${YMM_DOMAIN}/cate/${curCid}?smallPrice=0&bigPrice=50" target="_self">0-50</a>
			</li>
			<li <c:if test="${catePageParam.smallPrice == 51 && catePageParam.bigPrice == 200}">class="curr"</c:if>>
				<a href="${YMM_DOMAIN}/cate/${curCid}?smallPrice=51&bigPrice=200" target="_self">51-200</a>
			</li>
			<li <c:if test="${catePageParam.smallPrice == 201 && catePageParam.bigPrice == 500}">class="curr"</c:if>>
				<a href="${YMM_DOMAIN}/cate/${curCid}?smallPrice=201&bigPrice=500" target="_self">201-500</a>
			</li>
			<li <c:if test="${catePageParam.smallPrice == 501}">class="curr"</c:if>>
				<a href="${YMM_DOMAIN}/cate/${curCid}?smallPrice=501" target="_self">500以上</a>
			</li>
		</ul>
	</div>
</div>