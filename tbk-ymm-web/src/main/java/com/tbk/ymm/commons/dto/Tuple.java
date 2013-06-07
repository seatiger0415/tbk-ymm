package com.tbk.ymm.commons.dto;

/**
 * @author David (qidawei@xiaomi.com)
 */
public class Tuple<T1, T2> {

	public final T1 first;
	public final T2 second;

	private Tuple(T1 first, T2 second) {
		this.first = first;
		this.second = second;
	}

	public static <T1, T2> Tuple<T1, T2> newTuple(T1 first, T2 second) {
		return new Tuple<T1, T2>(first, second);
	}

}
