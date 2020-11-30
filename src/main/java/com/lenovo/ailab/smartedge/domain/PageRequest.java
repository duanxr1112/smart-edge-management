package com.lenovo.ailab.smartedge.domain;
/**
 * Title: PageRequest.java
 * @Autohr "chenpeng"
 * @data 2019年12月5日
 * @Email chenpeng10@lenovo.com
 * @description: 
**/
public class PageRequest {
	/**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;
    
     
    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
