<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="UTF-8">	
	<meta name="keywords" content="孕妈妈,防辐射服,孕妇,孕妇装,孕妇服装,孕妇内衣">
	<meta name="description" content="孕妈妈良品导购,有品质又实用的准妈妈导购网站,为孕妈妈们提供靠谱和精致的物品推荐。">
	<title>孕妈妈  最实用的孕妈妈导购网站</title>
	<!--  <link rel="shortcut icon" href="http://www.leho.com/favicon.ico" type="image/x-icon"> -->

	<link rel="stylesheet" type="text/css" href="/static_example/goods_bc.css">
	<link rel="stylesheet" type="text/css" href="/static_example/goods_common.css">		
	<link rel="stylesheet" type="text/css" href="/static_example/home.css">

	<script>
		var _hmt = _hmt || [];
	</script>
	<script type="text/javascript" src="/static_example/qwrap_leho.js"></script>			
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
	
	<script type="text/javascript">
		var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
		document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F01fc154214f914949f06945dfd0e5d2d' type='text/javascript'%3E%3C/script%3E"));
	</script>
	<script src="/static_example/h.js" type="text/javascript"></script>
</head>

<body>
	<style type="text/css">
		.banner-topbar{ width:100%; height:40px; background-color:#00ccff;display: none;}
		.banner-topbar-inner{ position:relative; width:980px; height:40px; text-align:center; margin:0 auto;}
		.banner-topbar-inner a{ display:block; height:40px; zoom:1;}
		.banner-topbar-inner  .close{ position:absolute; top:4px; right:4px; width:16px; height:16px; background:url(http://co.youa.com/picture/services/leho/maibaobao/ico-close.png) no-repeat;}
	</style>

	<div class="banner-topbar" style="display: block;">
		<div class="banner-topbar-inner">
			<a href="http://www.leho.com/sale?from=headertop" target="_blank">
				<img src="/static_example/20e916ef598ba7ee80ea8e3b3321cec8.jpg" alt="">
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
							<a href="http://mall.leho.com/" class="life-service-nav">生活服务导航</a>
							<i class="login-txt-vertical"></i>
							<a class="header-module-favorite" href="javascript:;" onclick="goods.utils.addFavorite(&#39;http://www.leho.com&#39;,&#39;收藏孕妈妈，随时发现精彩分享&#39;);">收藏孕妈妈</a>
							<div class="header-module-follow">
								<span class="header-module-follow-txt">关注我们：</span><a href="" class="ico-head ico-follow weibo"></a><a href="" class="ico-head ico-follow qqweibo"></a><a href="" class="ico-head ico-follow weixin"></a>
								<div class="header-module-follow-more hidden">						<iframe width="136" height="26" frameborder="0" allowtransparency="true" marginwidth="0" marginheight="0" scrolling="no" border="0" src="/static_example/followbutton.htm"></iframe>
									<iframe src="/static_example/like.htm" allowtransparency="true" scrolling="no" border="0" frameborder="0" style="width:400px;height:25px;border:none;overflow:hidden;margin-top:2px;"></iframe>
									<iframe src="/static_example/index.htm" frameborder="0" scrolling="auto" width="178" height="24" marginwidth="0" marginheight="0" allowtransparency="true"></iframe>
									
									<!-- 这个出现在页面最下方 
									<div class="header-follow-weixin">
										<span class="ico-header-erweima"></span>
										<p>扫微信二维码</p>
										<p>每天有奖问答</p>
									</div>
									-->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	
			<div class="header-module-bd cls">
				<div class="header-module-nav-main cls">
					<div class="header-module-nav">
						<ul class="cls">
							<li><a href="#" class="selected">首页</a><i class="ico-head ico-vertical"></i></li>
							<li class="tag" data-cid="508"><a href="#">居家</a></li>
							<li class="tag" data-cid="509"><a href="#">服饰</a></li>
							<li class="tag" data-cid="510"><a href="#">鞋包</a></li>
							<li class="tag" data-cid="511"><a href="#">母婴</a></li>
							<li class="tag" data-cid="505"><i class="ico-head ico-vertical"></i><a href="#">着迷</a></li>
							<li><i class="ico-head ico-vertical"></i><a href="#">今天值得买<i class="ico-head ico-hot"></i></a></li>
							<li><i class="ico-head ico-vertical"></i><a rel="nofollow" target="_blank" href="#">手机版</a></li>
						</ul>
					</div>
					<!--  
					<div class="header-module-user-wrap">
						<a href="http://i.leho.com/psp/third/login/sina/in?ref=http%3A%2F%2Fwww.leho.com" data-type="third" class="third-login gm-login-sina ico-login ico-login-weibo" rel="nofollow">微博登录</a>
						<a href="http://i.leho.com/psp/third/login/qq/in?ref=http%3A%2F%2Fwww.leho.com" data-type="third" class="third-login gm-login-qq ico-login ico-login-qq" rel="nofollow">QQ登录</a>
						<a class="login-txt" href="http://i.leho.com/psp/login" rel="nofollow" target="_blank" onclick="User.Power.login(); return false;">登录</a>
						<i class="login-txt-vertical"></i>
						<a href="http://i.leho.com/psp/reg" target="_blank" class="login-txt" rel="nofollow">注册</a>
					</div>
					-->
				</div>	
			</div>
			<!-- end: header-module -->
		</div>
		<div id="body">
		
			<!--start: 第一行的轮播+列表推荐位-->
			<div class="first-column cls">
				<!--start: 头图轮播-->
				<div class="picslide widget-slide top-switch-pic" data-jss="animType:&#39;scroll&#39;,autoPlayTime:4000">
					<div class="switch-pic-modules cls" style="overflow:hidden;width:690px;height:300px;">
						<ul class="pic slide-content cls">
							<li class="selected" style="display: none;"><a href="#" target="_blank"><img src="/static_example/c12b712651be1461022dd442f930941b.jpg"></a></li>
				            <li style="display: none;"> <a href="#" target="_blank"><img src="/static_example/72034c7c3ae1f34db3732896336875ee.jpg"></a></li>
		                    <li style="display: none;"><a href="#" target="_blank"><img src="/static_example/892944e84789d6dae82cdbf7e99d727e.jpg"></a></li> 
		                    <li style="display: none;"><a href="#" target="_blank"><img src="/static_example/c233d36f4b707fb804a89f97512fc5b1.jpg"></a></li> 
		                    <li style=""><a href="#" target="_blank"><img src="/static_example/367967758ebee79facec89abe9a69a80.jpg"></a></li> 
		                    <li style=""> <a href="#" target="_blank"><img src="/static_example/4a3e6b8ff740949cedb053192bf25159.jpg"></a></li>
		                    <li style="display: none;"> <a href="#" target="_blank"><img src="/static_example/3a7d5edb901eed756f336acd6de62377.jpg"></a></li>		
						</ul>
					</div>
					<ol class="switch-pic-nav slide-nav">
						<li class=""><span>六一算数</span></li>
				        <li class=""><span>毕业旅行</span></li>
						<li class=""><span>收纳</span></li>
						<li class=""><span>母婴折扣送礼</span></li>
						<li class=""><span>翻滚吧快乐</span></li>
				        <li class="selected"><span>小赘肉</span></li>
						<li><span>系带鞋</span></li>
					</ol>
				</div>
				<!--end: 头图轮播-->
				
				<!--start: 轮播右侧列表-->
				<div class="top-recommended-wrap">
					<div class="top-recommended">
						<ul>
							<li><a target="_blank" href="#"><span>儿童节专享：猫和老鼠全集双语经典... </span><strong>￥21.50</strong></a></li>
							<li><a target="_blank" href="#"><span>儿童节专享：奥迪Q7遥控充电汽车正... </span><strong>￥49.00</strong></a></li>
							<li><a target="_blank" href="#"><span>儿童节专享：喜羊羊与灰太狼我的青... </span><strong>￥38.89</strong></a></li>
							<li><a target="_blank" href="#"><span>儿童节专享：儿童木质平面拼图益智... </span><strong>￥8.80</strong></a></li>
							<li><a target="_blank" href="#"><span>儿童节专享：喜羊羊与灰太狼专柜正... </span><strong>￥19.90</strong></a></li>
						</ul>
						<p class="more"><a href="#" target="_blank"><span>更多精挑细选的最值宝贝，查看更多</span></a></p>
					</div>
					<div class="top-recommended wap-download"><a target="_blank" href="#"><span>最帮你省钱的手机购物软件，立即下载</span></a></div>
				</div>
				<!--end: 轮播右侧列表-->
				
			</div>
			<!--end: 第一行的轮播+列表推荐位-->
			
			<!--begin: 第二行的商品推荐位-->
			<div class="tody-the-most-column">
				<ul class="cls">
					<li>
						<div class="item-grts xinxian"><h4>个性</h4></div>
						<a class="link-box" href="#" target="_blank"><img src="/static_example/72e7849038164f25714b8b6db2960fa9.jpg" alt="" width="280" height="280"><strong>先打手枪再起床</strong></a>						
					</li>
					<li>
						<div class="item-grts tebie"><h4>特别</h4></div>
						<a class="link-box" href="#" target="_blank"><img src="/static_example/321d88fdeada6a9a7c03fd01c7315b6f.jpg" alt="" width="280" height="280"><strong>你会用罐头做的掌上钢琴吗？</strong></a>						
					</li>
					<li>
						<div class="item-grts wenyi"><h4>舒适</h4></div>
						<a class="link-box" href="#" target="_blank"><img src="/static_example/75ba0a326b8d38e9e34bb757551a2068.jpg" alt="" width="280" height="280"><strong>手工懒人鞋 舒不舒服脚知道</strong></a>						
					</li>
			
				</ul>
			</div>
			<!--end: 第二行的商品推荐位-->
			
			<!-- begin 主体部分——各个类目商品的展示 -->
			<c:forEach var="oneCate" items="" begin="0" end="5" varStatus="stats">
				<div class="container cls">
					<a class="to-fix" data-category="jujia" name="jujia" style="position: relative; left: 0px; top: -65px; width">&nbsp;</a>
					<div class="category-moudles">
						<div class="category-wrap">
							<div class="category-title-wrap">
								<h2 class="category-title"><a href="#" target="_blank">居家</a></h2>
								<div class="category-title-desc"><a href="#" target="_blank">每日精选</a></div>
							</div>
							<div class="category-tag-wrap">
								<a href="#" target="_blank" class="special">创意设计</a>
								<a href="#" target="_blank">客厅</a>
								<a href="#" target="_blank">卧室</a>
								<a href="#" target="_blank">厨房</a>
								<a href="#" target="_blank">卫浴</a>
								<a href="#" target="_blank" class="special">花园</a>
								<a href="#" target="_blank">杂货zakka</a>
								<a href="#" target="_blank"><span class="category-tag-txt">更多</span><i class="ico-triangle"></i></a>
							</div>
						</div>
						<ul class="category-list">
							<li>
								<a href="#" target="_blank">
									<img class="lazy-load" src="/static_example/space.gif" data-src="http://img.leho.com/img_new/5317eac0a54418c28624867c--220-220-12.jpg">
									<div class="category-list-desc-wrap"></div>
									<dl class="category-list-desc"><dt>客厅</dt><dd>丹麦设计师Finn Juhl设计的一款沙发，Fi...</dd></dl>
								</a>
							</li>
							<li>
								<a href="#" target="_blank">
									<img class="lazy-load" src="/static_example/space.gif" data-src="http://img.leho.com/img_new/b6adb7bcaac00638387d8a6f--220-220-12.jpg">
									<div class="category-list-desc-wrap"></div>
									<dl class="category-list-desc"><dt>卫浴</dt><dd>现代的生活节奏越来越快，有时人们只能...</dd></dl>
								</a>
							</li>
							<li>
								<a href="#" target="_blank">
									<img class="lazy-load" src="/static_example/space.gif" data-src="http://img.leho.com/img_new/62dded30c95378c6a0d04f6d--220-220-12.jpg">
									<div class="category-list-desc-wrap"></div>
									<dl class="category-list-desc"><dt>家饰</dt><dd>炎热的夏季，怎能少了绿叶的陪伴~大型绿...</dd></dl>
								</a>
							</li>
							<li><a href="#" target="_blank"><img class="lazy-load" src="/static_example/space.gif" data-src="http://img.leho.com/img_new/75dff2e04ae542a70019b372--220-220-12.jpg"><div class="category-list-desc-wrap"></div><dl class="category-list-desc"><dt>厨房</dt><dd>宜家代购斯比塔水果工具套装，可以把水...</dd></dl></a></li><li><a href="#" target="_blank"><img class="lazy-load" src="/static_example/space.gif" data-src="http://img.leho.com/img_new/c0d4fe59d798ff978d1f6c4e--220-220-12.jpg"><div class="category-list-desc-wrap"></div><dl class="category-list-desc"><dt>花园</dt><dd>这货让你紧张了吗？它只是单纯的花瓶，...</dd></dl></a></li><li><a href="http://www.leho.com/faxian/jujia/2574?from=meirijingxuan" target="_blank"><img class="lazy-load" src="/static_example/space.gif" data-src="http://img.leho.com/img_new/571bbe272c28d33fa0d04f15--220-220-12.jpg"><div class="category-list-desc-wrap"></div><dl class="category-list-desc"><dt>收纳</dt><dd>你有强迫症吗？这么多的空格有没有冲动...</dd></dl></a></li><li><a href="http://www.leho.com/faxian/jujia/2560?from=meirijingxuan" target="_blank"><img class="lazy-load" src="/static_example/space.gif" data-src="http://img.leho.com/img_new/87fc75cc34899872913f7a92--220-220-12.jpg"><div class="category-list-desc-wrap"></div><dl class="category-list-desc"><dt>杂货</dt><dd>青釉陶瓷杯纯手工玫瑰雕花茶杯，漂亮的...</dd></dl></a></li>
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
							<li class="selected" style=""><a href="http://www.leho.com/sale?from=bottomswitch" target="_blank"><img src="/static_example/cc5a795c2dc17f50e333bf65b68c5e8f.jpg"></a></li>
							<li style=""><a href="http://www.leho.com/faxian/muying/2570?from=bottomswitch" target="_blank"><img src="/static_example/c7021e92155ef56dc5ce1b8f24c63fc9.jpg"></a></li>
							<li style="display: none;"><a href="http://www.leho.com/faxian/xiebao/2550?from=bottomswitch" target="_blank"><img src="/static_example/1fa5c6e975d3b76a9962852cdf8c1885.jpg"></a></li>
							<li style="display: none;"><a href="http://www.leho.com/sale?from=bottomswitch" target="_blank"><img src="/static_example/95353cc6910175ef38fff2aa05e0a7ae.jpg"></a></li>
						</ul>
					</div>
					<ol class="switch-pic-nav slide-nav">
						<li class=""><span>六一</span></li>
						<li class="selected"><span>玩具</span></li>
						<li class=""><span>系带鞋</span></li>
						<li class=""><span>乐活良品 天天折扣</span></li>
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
							<li class="first"><a href="http://www.leho.com/aboutus" target="_blank" rel="nofollow">关于孕妈妈</a>|</li>
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
					<img src="/static_example/fix-erweima-ico.jpg" alt="二维码">
				</a>
				<a class="feedback" href="javascript:;" style="">提建议</a>
				<a class="back-top" href="javascript:;" style="opacity: 1; display: none;">返回顶部</a>
			</div>
		</div>
		<!-- end 页脚部分 -->
	</div>
	<!-- end wrapper 网站所有展示的内容 -->	
	
	<script src="/static_example/common.js"></script>
	<script src="/static_example/wb.js" type="text/javascript" charset="utf-8"></script>	
		
</body>
</html>