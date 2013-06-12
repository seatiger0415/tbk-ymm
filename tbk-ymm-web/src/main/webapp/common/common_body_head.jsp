<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="head">
	<!-- start: header-module -->
	<noscript> 
		&lt;div class="nojs"&gt;
			&lt;p&gt;您的浏览器已经禁用了脚本，这会影响您正常使用本站的功能。&lt;/p&gt; 
		&lt;/div&gt;
	</noscript>
	<div class="header-module-hd cls">
		<div class="header-module-hd-main cls">
			<div class="header-module-logo-wrap">
				<div class="header-module-logo">
					<a href="${YMM_DOMAIN}">孕妈妈</a>
				</div>
				<div class="header-module-container">
					<a href="${YMM_DOMAIN}" class="header-module-sub-title">良品，让生活多一点不一样</a>
				</div>
			</div>
			<div class="header-module-other">
				<div class="header-module-other-main">
					<!-- <span class="header-module-follow-txt">姊妹站</span> -->
					<a class="life-service-nav" href="${YMM_DOMAIN}/article/cate/shopping" rel="nofollow">孕妈妈购物攻略</a>
					<!--
					<i class="login-txt-vertical"></i>
					<a href="${YMM_DOMAIN}/article/cate/experience" class="life-service-nav" rel="nofollow" >孕妈妈经验谈</a>
					-->  
					<i class="login-txt-vertical"></i>
					<a class="header-module-favorite" href="${YMM_DOMAIN}/siteMap" target="_blank">网站地图</a>
					<a class="header-module-favorite" href="javascript:;" onclick="goods.utils.addFavorite(&#39;http://www.yunmama123.com&#39;,&#39;收藏孕妈妈，随时发现精彩分享&#39;);">收藏孕妈妈</a>
					<!--  
					<div class="header-module-follow">
						<span class="header-module-follow-txt">关注我们：</span>
						<a href="" class="ico-head ico-follow weibo"></a>
						<a href="" class="ico-head ico-follow qqweibo"></a>
						<a href="" class="ico-head ico-follow weixin"></a>
						<div class="header-module-follow-more hidden">						
							<iframe width="136" height="26" frameborder="0" allowtransparency="true" marginwidth="0" marginheight="0" scrolling="no" border="0" src="/static/followbutton.htm"></iframe>
							<iframe src="/static/like.htm" allowtransparency="true" scrolling="no" border="0" frameborder="0" style="width:400px;height:25px;border:none;overflow:hidden;margin-top:2px;"></iframe>
							<iframe src="/static/index.htm" frameborder="0" scrolling="auto" width="178" height="24" marginwidth="0" marginheight="0" allowtransparency="true"></iframe>
						</div>
					</div>
					-->
				</div>
			</div>
		</div>
	</div>
	
	<!-- 类目例子：防辐射用品 -> 防辐射吊带 -> xxx -->

	<div class="header-module-bd cls">
		<div class="header-module-nav-main cls">
			<div class="header-module-nav">
				<ul class="cls">
					<li><a href="${YMM_DOMAIN}"<c:if test='${isHome}'> class="selected"</c:if>>首页</a></li>
					<c:forEach var="item" items="${navigationList}">
						<li class="tag">
							<i class="ico-head ico-vertical"></i>
							<a href="${YMM_DOMAIN}/cate/${item.id}"<c:if test='${item.selected()}'> class="selected"</c:if>>
								${item.name}
								<c:if test="${2 == item.status}">
									<i class="ico-head ico-hot"></i>
								</c:if>
							</a>
						</li>
						<!--  
						<li class="tag" data-cid="509"><i class="ico-head ico-vertical"></i><a href="#">孕妇装</a></li>
						<li class="tag" data-cid="509"><i class="ico-head ico-vertical"></i><a href="#">孕妇内衣</a></li>
						<li class="tag" data-cid="510"><i class="ico-head ico-vertical"></i><a href="#">孕妇洗护</a></li>
						<li class="tag" data-cid="510"><i class="ico-head ico-vertical"></i><a href="#">孕营养品</a></li>
						<li class="tag" data-cid="510"><i class="ico-head ico-vertical"></i><a href="#">其他</a></li>
						-->
					</c:forEach>
				</ul> 
			</div>
			<div class="header-module-user-wrap">
				<!-- begin 淘宝登陆组件 -->
				<div id="taobao-login"></div>
                <script>
                  TOP.ui("login-btn", {
                    container: "#taobao-login", 
                    type: "3,4", 
                    callback:{
                      login: function(){}, 
                      logout: function(){}
                    }
                  });
                </script>
                <!-- end 淘宝登陆组件 -->
                <!--
				<a href="#" data-type="third" class="third-login gm-login-sina ico-login ico-login-weibo" rel="nofollow">微博登录</a>
				<a href="#" data-type="third" class="third-login gm-login-qq ico-login ico-login-qq" rel="nofollow">QQ登录</a>
				<a class="login-txt" href="#" rel="nofollow" target="_blank" onclick="User.Power.login(); return false;">登录</a>
				<i class="login-txt-vertical"></i>
				<a href="#" target="_blank" class="login-txt" rel="nofollow">注册</a>
				-->
			</div>
			
		</div>	
	</div>
	<!-- end: header-module -->
</div>