package com.lenovo.ailab.smartedge.utils;

import com.github.pagehelper.PageInfo;
import com.lenovo.ailab.smartedge.domain.PageRequest;
import com.lenovo.ailab.smartedge.domain.PageResult;

/**
 * Title: PageUtils.java
 * @Autohr "chenpeng"
 * @data 2019年12月5日
 * @Email chenpeng10@lenovo.com
 * @description: 
**/
public class PageUtils {
	/**
     * 将分页信息封装到统一的接口
     * @param pageRequest 
     * @param pageInfo
     * @return
     */
    public static <T> PageResult<T> getPageResult(PageRequest pageRequest, PageInfo<T> pageInfo) {
        PageResult<T> pageResult = new PageResult<T>();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setList(pageInfo.getList());
        return pageResult;
    }
}
