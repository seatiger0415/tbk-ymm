<%@ page contentType="text/html;charset=UTF-8" %>

<script type="text/javascript">
	(function(win,doc){
	var s = doc.createElement("script"), h = doc.getElementsByTagName("head")[0];
		if (!win.alimamatk_show) {
			s.charset = "gbk";
			s.async = true;
			s.src = "http://a.alimama.cn/tkapi.js";
			h.insertBefore(s, h.firstChild);
		};
		var o = {
			pid: "mm_34998088_4052366_13188367",
			appkey: "${APP_KEY}",
			unid: ""
		};
		win.alimamatk_onload = win.alimamatk_onload || [];
		win.alimamatk_onload.push(o);
	})(window,document);
</script>
