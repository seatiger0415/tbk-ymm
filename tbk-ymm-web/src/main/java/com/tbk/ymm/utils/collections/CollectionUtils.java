package com.tbk.ymm.utils.collections;

import java.util.Collection;

/**
 * @author David (qidawei@xiaomi.com)
 */
public class CollectionUtils {

	private CollectionUtils() {
	}

	/**
	 * @param c
	 * @return
	 */
	public static <T> boolean isCollectionEmpty(Collection<T> c) {
		if (c == null || c.isEmpty()) {
			return true;
		}
		return false;
	}

}
