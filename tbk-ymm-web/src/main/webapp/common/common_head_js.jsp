<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- begin 塞在这里吧 -->
<link rel="shortcut icon" href="/static/img/favicon/favicon-ymm.ico" type="image/x-icon">
<!-- end -->
<!-- taobao jssdk -->
<script src="http://l.tbcdn.cn/apps/top/x/sdk.js?appkey=21522531"></script>
<script>
	var _hmt = _hmt || [];
</script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="/static/js/qwrap_leho.js"></script>
<script>
	var G_CONF = {
			login_uid : '0',
			thirdInfo : [],
			man_head_img : '',
			woman_head_img : '',
			subject_img : '',
			currentTime : '',
			time: '',
			img_url		: 'http://yunmama123.com/static/img',
			site_url	: 'http://yunmama123.com',
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