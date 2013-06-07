<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- 
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
--%>