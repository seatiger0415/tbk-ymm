package com.tbk.ymm.web.interceptors;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletResponse;

import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Interceptor;

import org.springframework.stereotype.Service;

import com.tbk.ymm.web.utils.CookieManager;

/**
 *
 */
@Service
@Interceptor(oncePerRequest = true)
public class YmmCommonInterceptor extends ControllerInterceptorAdapter {

	public YmmCommonInterceptor() {
		//
	}

	@Override
	protected Object before(final Invocation inv) throws Exception {
		final HttpServletResponse response = inv.getResponse();
		// 这里很重要，因为IE下必须设置这个P3P隐私策略，才能设置cookie到父域下
		response.addHeader("P3P", "CP=CAO PSA OUR");

		// 设置cookie是为了防盗链需求
		CookieManager.getInstance().saveCookie(response, "vip", "1");
		return true;
	}

	@Override
	protected boolean isForAction(final Method actionMethod, final Class<?> controllerClazz) {
		// if ((controllerClazz == MusicboxController.class)
		// || (controllerClazz == FmController.class)) {
		// return true;
		// }
		// // 管理员也是要听歌的，是吧？
		// if (controllerClazz.getName().contains("controllers.admin")) {
		// return true;
		// }
		return true;

	}
}
