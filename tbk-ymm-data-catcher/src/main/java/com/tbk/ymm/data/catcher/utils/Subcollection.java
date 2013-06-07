/**
 * 
 */
package com.tbk.ymm.data.catcher.utils;

import java.io.Serializable;


/**
 * @author Administrator
 */
public interface Subcollection<T> extends Serializable {

    public int getCount();

    public int getStart();

    public int getEnd();
    
    public int getPagesize();

    /**
     * 设置总数(count),本页开始数字(start),本页结束数字(end).
     * 使用了本方法就不用setCount,setStart,setEnd了.
     * 并且会自动判断一些东西,比如end<start,或start>count之类.
     * 注意参数,不是count,start,end,而是count,offset,pagesize
     * 
     * @param _count
     * @param offset
     * @param pagesize
     */
    public void setTheNumbers(Integer count, Integer offset, Integer pagesize);

}
