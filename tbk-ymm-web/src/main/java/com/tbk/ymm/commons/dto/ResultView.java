package com.tbk.ymm.commons.dto;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import com.tbk.ymm.data.catcher.utils.CommonUtil;

/**
 * 分页的视图；可以包含两种意义：1.视图内的list是全集；2.视图内的list只包含当前页的数据
 * 
 * @author David (qidawei@xiaomi.com)
 * @param <T>
 */
public class ResultView<T> {

	@SuppressWarnings("rawtypes")
	private static final ResultView EMPTY = new ResultView();

	@SuppressWarnings("unchecked")
	public static <T> ResultView<T> emptyResultView() {
		return EMPTY;
	}

	public static <T> ResultView<T> newResultView() {
		return new ResultView<T>();
	}

	private Collection<T> list = Collections.emptyList();
	private int curPage;
	private int pageSize;
	private int countAll;
	// 冗余分页结果，例如： 1 2 3 4 5 6 ... 51 52 53
	private List<Integer> prePageNoList = Lists.newArrayList();
	private List<Integer> midPageNoList = Lists.newArrayList();
	private List<Integer> suffixPageNoList = Lists.newArrayList();
	private int allPageNo = 0;

	/**
	 * 构建分页结果：对prePageNoList midPageNoList suffixPageNoList赋值
	 */
	public void buildPageResult() {
		if (pageSize <= 0 || countAll <= 0) {
			return;
		}
		int tmp = countAll / pageSize;
		allPageNo = countAll % pageSize == 0 ? tmp : tmp + 1;
		if (allPageNo <= 10) {
			prePageNoList = getBiggerList(1, allPageNo);
			return;
		}
		//
		int curPageNo = curPage + 1;
		if (curPageNo > allPageNo) {
			curPageNo = 1;
		}
		//
		if (curPageNo <= 8) { // 1 2 3 4 5 6 7 8 ... 100
			prePageNoList = getBiggerList(1, 9);
			suffixPageNoList = Lists.newArrayList(allPageNo);
		} else if (allPageNo - curPageNo < 7) { // 1.. 94 95 96 97 98 99 100
			prePageNoList = Lists.newArrayList(1);
			suffixPageNoList = getSmallerList(allPageNo, 9);
		} else { // 1... 2 3 4 5 6 7 8 9 10 ... 100
			prePageNoList = Lists.newArrayList(1);
			midPageNoList = getSmallerList(curPageNo, 5);
			midPageNoList.addAll(getBiggerList(curPageNo + 1, 4));
			suffixPageNoList = Lists.newArrayList(allPageNo);
		}
	}

	private List<Integer> getBiggerList(int base, int num) {
		List<Integer> list = Lists.newArrayListWithExpectedSize(num);
		for (int i = 0; i < num; i++) {
			list.add(base++);
		}
		return list;
	}

	private List<Integer> getSmallerList(int base, int num) {
		List<Integer> list = Lists.newArrayListWithExpectedSize(num);
		for (int i = 0; i < num; i++) {
			list.add(base--);
		}
		Collections.reverse(list);
		return list;
	}

	@Override
	public String toString() {
		return list.toString() + " countAll=" + countAll + ",curPage=" + curPage + ",pageSize=" + pageSize;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + curPage;
		result = prime * result + pageSize;
		result = prime * result + countAll;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ResultView<?> other = (ResultView<?>) obj;
		if (curPage != other.curPage) {
			return false;
		}
		if (pageSize != other.pageSize) {
			return false;
		}
		if (countAll != other.countAll) {
			return false;
		}
		if (list == null) {
			if (other.list != null) {
				return false;
			}
		} else if (!list.equals(other.list)) {
			return false;
		}
		return true;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCountAll() {
		return countAll;
	}

	public void setCountAll(int countAll) {
		this.countAll = countAll;
	}

	public Collection<T> getList() {
		return list;
	}

	public void setList(final Collection<T> list) {
		this.list = CommonUtil.checkToDefaultValue(list);
	}

	public List<Integer> getPrePageNoList() {
		return prePageNoList;
	}

	public List<Integer> getMidPageNoList() {
		return midPageNoList;
	}

	public List<Integer> getSuffixPageNoList() {
		return suffixPageNoList;
	}

	public int getAllPageNo() {
		return allPageNo;
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		/*
		 * final List<Integer> list = new ArrayList<Integer>();
		 * list.add(5);
		 * list.add(1);
		 * list.add(45);
		 * list.add(562);
		 * list.add(2487);
		 * list.add(6);
		 * int count = list.size();
		 * final int curpage = 0;
		 * final int pagesize = 3;
		 * final int offset = curpage * pagesize;
		 * count = -1;
		 */
	}
}
