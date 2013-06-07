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

	<script>
		var _hmt = _hmt || [];
	</script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
	<script type="text/javascript" src="/static/js/qwrap_leho.js"></script>
	<script>
		var G_CONF = {
				login_uid : '0',
				thirdInfo : [],
				man_head_img : 'd96a3fdeaf68d3e8db170ad5',
				woman_head_img : '43e2e6f41e3b3ebe22aa6560',
				subject_img : '726a17bd880cff1fb375718c',
				currentTime : '2013-05-24',
				time: '1369377029',
				img_url		: 'http://img.yunmama123.com',
				site_url	: 'http://www.yunmama123.com',
				res_site_url : '',
				psp_site_url: '',
				lp_post_url : ''};
		var broadcast_conf = {
				'goods' : ['add', 'delete'],
				'like' : ['add', 'remove'],
				'follow' : ['add', 'remove']};
		QW.namespace('goods.utils');
		QW.namespace('goods.widgets');
		QW.namespace('goods.page');
	</script>
	<script src="/static/js/h.js" type="text/javascript"></script>
</head>

<body>
	<style type="text/css">
		.banner-topbar{ width:100%; height:40px; background-color:#00ccff;display: none;}
		.banner-topbar-inner{ position:relative; width:980px; height:40px; text-align:center; margin:0 auto;}
		.banner-topbar-inner a{ display:block; height:40px; zoom:1;}
		.banner-topbar-inner  .close{ position:absolute; top:4px; right:4px; width:16px; height:16px; background:url(/static/img/ico-close.png) no-repeat;}
	</style>

	<div class="banner-topbar" style="display: block;">
		<div class="banner-topbar-inner">
			<a href="#" target="_blank">
				<img src="/static/img/980_40.jpg" alt="">
			</a>
			<a class="close" href="javascript:;"></a>
		</div>		
	</div>

	<script type="text/javascript">
		(function(){
			var noticed = Cookie.get('banner-topbar');
			if(!noticed){
				W('.banner-topbar').show();
		
				W('.banner-topbar-inner .close').click(function(){
					W('.banner-topbar').hide();
					var expiresTime = new Date();
					expiresTime.setDate( expiresTime.getDate() + 1);
					expiresTime.setHours(0);
					expiresTime.setMinutes(0);
					expiresTime.setSeconds(0);
					var delta = expiresTime - new Date();
					Cookie.set('banner-topbar', 1, {domain : 'leho.com', expires : delta});
				});
			}
		})();
	</script>
	
	<!-- begin wrapper 网站所有展示的内容 -->
	<div id="wrapper">
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
							<a href="#">孕妈妈</a>
						</div>
						<div class="header-module-container">
							<a href="#" class="header-module-sub-title">良品，让生活多一点不一样</a>
						</div>
					</div>
					<div class="header-module-other">
						<div class="header-module-other-main">
							<span class="header-module-follow-txt">姊妹站</span>
							<a href="http://mall.leho.com/" class="life-service-nav">孕妈妈交流</a>
							<i class="login-txt-vertical"></i>
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
							<li><a href="#" class="selected">首页</a><i class="ico-head ico-vertical"></i></li>
							<li class="tag" data-cid="508"><a href="#">防辐射<i class="ico-head ico-hot"></i></a></li>
							<li class="tag" data-cid="509"><i class="ico-head ico-vertical"></i><a href="#">孕妇装</a></li>
							<li class="tag" data-cid="510"><i class="ico-head ico-vertical"></i><a href="#">孕妇洗护</a></li>
							<li class="tag" data-cid="510"><i class="ico-head ico-vertical"></i><a href="#">孕营养品</a></li>
							<li class="tag" data-cid="510"><i class="ico-head ico-vertical"></i><a href="#">其他</a></li>
						</ul>
					</div>
					<!--  
					<div class="header-module-user-wrap">
						<a href="#" data-type="third" class="third-login gm-login-sina ico-login ico-login-weibo" rel="nofollow">微博登录</a>
						<a href="#" data-type="third" class="third-login gm-login-qq ico-login ico-login-qq" rel="nofollow">QQ登录</a>
						<a class="login-txt" href="#" rel="nofollow" target="_blank" onclick="User.Power.login(); return false;">登录</a>
						<i class="login-txt-vertical"></i>
						<a href="#" target="_blank" class="login-txt" rel="nofollow">注册</a>
					</div>
					-->
				</div>	
			</div>
			<!-- end: header-module -->
		</div>
		<div id="body">
		
			<!--begin: 商品推荐位-->
			<div class="tody-the-most-column">
				<ul class="cls">
					<li>
						<div class="item-grts xinxian"><h4>个性</h4></div>
						<a class="link-box" href="#" target="_blank"><img src="/static/img/280_280.jpg" alt="" width="280" height="280"><strong>先打手枪再起床</strong></a>						
					</li>
					<li>
						<div class="item-grts tebie"><h4>特别</h4></div>
						<a class="link-box" href="#" target="_blank"><img src="/static/img/280_280.jpg" alt="" width="280" height="280"><strong>你会用罐头做的掌上钢琴吗？</strong></a>						
					</li>
					<li>
						<div class="item-grts wenyi"><h4>舒适</h4></div>
						<a class="link-box" href="#" target="_blank"><img src="/static/img/280_280.jpg" alt="" width="280" height="280"><strong>手工懒人鞋 舒不舒服脚知道</strong></a>						
					</li>			
				</ul>
			</div>
			<!--end: 商品推荐位-->
			
			<!-- begin 主体部分——各个类目商品的展示 -->
			<c:forEach begin="0" end="5" varStatus="mainStatus">
				<div class="container cls">
					<a class="to-fix" data-category="jujia" name="jujia" style="position: relative; left: 0px; top: -65px; width">&nbsp;</a>
					<div class="category-moudles">
						<div class="category-wrap">
							<div class="category-title-wrap">
								<h2 class="category-title"><a href="#" target="_blank">Cate1[防辐射服]</a></h2>
								<div class="category-title-desc"><a href="#" target="_blank">每日精选</a></div>
							</div>
							<div class="category-tag-wrap">
								<a href="#" target="_blank" class="special">Cate2[0]</a>
								<c:forEach begin="0" end="5" varStatus="status">
									<a href="#" target="_blank">Cate2[${status.index}]</a>
								</c:forEach>
								<a href="#" target="_blank"><span class="category-tag-txt">更多</span><i class="ico-triangle"></i></a>
							</div>
						</div>
						<ul class="category-list">
							<c:forEach begin="0" end="5" varStatus="status">
								<li>
									<a href="#" target="_blank">
										<img class="lazy-load" src="/static/img/space.gif" data-src="/static/img/230_230.jpg">
										<div class="category-list-desc-wrap"></div>
										<dl class="category-list-desc"><dt>Cate2[${status.index}]</dt><dd>这里是一个商品</dd></dl>
									</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</c:forEach>	
			<!-- end 主体部分——各个类目商品的展示 -->
				
			<!-- begin body最下面的部分-->
			<div class="container cls">
				<!--start: 底图轮播-->
				<div class="picslide widget-slide bottom-switch-pic" data-jss="animType:&#39;scroll&#39;,autoPlayTime:4000">
					<div class="switch-pic-modules cls" style="overflow:hidden;width:980px;height:100px;">
						<ul class="pic slide-content cls">
							<li class="selected" style=""><a href="#" target="_blank"><img src="/static/img/980_100.jpg"></a></li>
							<li style=""><a href="#" target="_blank"><img src="/static/img/980_100.jpg"></a></li>
							<li style="display: none;"><a href="#" target="_blank"><img src="/static/img/980_100.jpg"></a></li>
							<li style="display: none;"><a href="#" target="_blank"><img src="/static/img/980_100.jpg"></a></li>
						</ul>
					</div>
					<ol class="switch-pic-nav slide-nav">
						<li class=""><span>六一</span></li>
						<li class="selected"><span>玩具</span></li>
						<li class=""><span>系带鞋</span></li>
						<li class=""><span>天天折扣</span></li>
					</ol>
				</div>
				<!--end: 底图轮播-->
			</div>
			<!-- end body最下面的部分-->
		</div>

		<!-- begin 页脚部分 -->
		<div id="footer">
			<div class="footer-inner">
			
				<!-- bgein 站内链接 页脚-->
				<div class="global-footer">
					<div class="global-footer-inner">
						<ul class="footer-nav cls">
							<li class="first"><a href="#" target="_blank" rel="nofollow">关于孕妈妈</a>|</li>
							<li><a href="#" target="_blank" rel="nofollow">在这儿工作</a>|</li>
							<li><a href="#" target="_blank" rel="nofollow">联系我们</a>|</li>
							<li><a href="#" target="_blank" rel="nofollow">xx协议</a>|</li>
							<li><a href="#" target="_blank" rel="nofollow">帮助中心</a>|</li>
							<li><a href="#" target="_blank" rel="nofollow">APP下载</a>|</li>
							<li><a href="#" target="_blank">网站地图</a>|</li>
						</ul>
					</div>
				</div>
				<!-- end 站内链接  页脚-->
				
				<!-- bgein 友情链接 页脚-->
				<div class="global-friend-links">
					<div class="friend-links-inner cls">
						<h4 class="friend-links-tit">友情链接：</h4>
						<div class="widget-slide friend-links-switch">
							
							<!-- 友链有两个轮播 -->
							<ul class="friend-links-item switch-content switch-nav slide-content">
								<li class="">
									<a href="http://hotel.qunar.com/" target="_blank">去哪儿酒店</a>
									<a href="http://www.kuxun.cn/" target="_blank">酷讯旅游网</a>
									<a href="http://life.self.com.cn/" target="_blank">悦己乐活</a>
									<a href="http://www.wed114.cn/" target="_blank">中国婚纱摄影网</a>
									<a href="http://www.zhiwo.com/gindex.html" target="_blank">知我药妆</a>
									<a href="http://www.leho.com/sale" target="_blank">什么值得买</a>
									<a href="http://life.caijing.com.cn/index.html" target="_blank">财经网生活</a>
									<a href="http://www.eastlady.cn/" target="_blank">东方女性网</a>
								</li>
								
								<li class="selected">
									<a href="http://www.mafengwo.cn/" target="_blank">蚂蜂窝旅游攻略</a>
									<a href="http://www.meilishuo.com/" target="_blank">美丽说</a>	
									<a href="http://www.ijie.com/" target="_blank">爱结网</a>
									<a href="http://www.chexun.com/" target="_blank">车讯网</a>
									<a href="http://www.jc258.cn/" target="_blank">竞彩网</a>
									<a href="http://bj.house.sina.com.cn/" target="_blank">北京房产</a>
									<a href="http://www.17k.com/" target="_blank">小说</a>
									<a href="http://www.zhcpic.com/" target="_blank">北京青年旅行社</a>
									<a href="http://www.leho.com/links" target="_blank">更多&gt;&gt;</a>
								</li>
							</ul>
							
							<!-- 这个应该是轮播时用的。。具体什么作用呢? -->
							<ul class="slide-nav" style="display:none">
								<li class=""><span>1</span></li>
								<li class="selected"><span>2</span></li>								
							</ul>	
						</div>				
					</div>
				</div>
				<!-- end 友情链接 页脚-->
				
				<div class="copyright">
					<div class="copyright-inner">
						<div class="copyright-info">
							<p>Copyright &#169; 2011-2013 xxx.com版权所有
								<a href="http://www.miibeian.gov.cn/" target="_blank" rel="nofollow">京ICP备xxx号</a>
								&nbsp;&nbsp;京公网安备xxx号
								<a href="#" target="_blank" rel="nofollow">xx协议</a>
							</p>
							<p>xxx有限公司&#169;</p>				
						</div>
					</div>
				</div>
				
			</div>
			
			<div class="fix-block-r" style="bottom: 10px; left: 1154px; visibility: visible;">
				<a class="fix-erweima" href="javascript:;">
					<img src="/static/img/fix-erweima-ico.jpg" alt="二维码">
				</a>
				<a class="feedback" href="javascript:;" style="">提建议</a>
				<a class="back-top" href="javascript:;" style="opacity: 1; display: none;">返回顶部</a>
			</div>
		</div>
		<!-- end 页脚部分 -->
	</div>
	<!-- end wrapper 网站所有展示的内容 -->	
	
	<script src="/static/js/common.js"></script>
		
</body>
</html>