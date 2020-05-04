package com.cignacmb.smart.base.common;

import java.util.Map;

public class PageInfo<E> {
    /**
     * 总数
     */
    private int count = 0;
    /**
     * 每页行数
     */
    private int limit = 10;
    /**
     * 当前页数
     */
    private int page = 1;
    /**
     * Oracle分页，最大行数
     */
    private int maxOffset = 0;
    /**
     * Oracel分页,最小行数
     */
    private int minOffset = 0;

    /**
     * Oracel分页 当前页第一条数据所在行数
     */
    private int offset = 0;
    /**
     * 参数为entity本身
     */
    private E record;
    /**
     * 其它参数
     */
    private Map<String, Object> params;

    public PageInfo() {

    }

    public PageInfo(PageInfo<?> p) {
        this.count = p.count;
        this.limit = p.limit;
        this.page = p.page;
        this.minOffset = p.minOffset;
        this.maxOffset = p.maxOffset;
    }

    public PageInfo<E> calculateOffset(int count){
        if(0 == count) {
            return this;
        }

        this.count = count;
        this.minOffset = this.limit*(this.page-1) + 1;
        this.maxOffset = this.limit*this.page;

        return this;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public E getRecord() {
        return record;
    }

    public void setRecord(E record) {
        this.record = record;
    }

    public int getMaxOffset() {
        return maxOffset;
    }

    public void setMaxOffset(int maxOffset) {
        this.maxOffset = maxOffset;
    }

    public int getMinOffset() {
        return minOffset;
    }

    public void setMinOffset(int minOffset) {
        this.minOffset = minOffset;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
    public int getOffset() {
        return this.limit*(this.page-1);
    }
}
