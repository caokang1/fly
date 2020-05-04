package com.cignacmb.smart.base.service;

import com.cignacmb.smart.base.common.PageInfo;

import java.util.List;

public interface BaseService<E> {
    /**
     * 根据主键删除记录
     * @param record
     * @return
     * @throws Exception
     */
    public boolean deleteByPrimaryKey(E record) throws Exception;

    /**
     * 插入一条记录，可选字段
     * @param record
     * @return
     * @throws Exception
     */
    public boolean insertSelective(E record) throws Exception;

    /**
     * 根据主键查找记录
     * @param record
     * @return
     * @throws Exception
     */
    public E selectByPrimaryKey(E record) throws Exception;

    /**
     * 根据主键更新可选字段
     * @param record
     * @return
     * @throws Exception
     */
    public boolean updateByPrimaryKeySelective(E record) throws Exception;

    /**
     * 以表字段为条件查询
     * @param record
     * @return
     * @throws Exception
     */
    public List<E> selectByCondition(E record) throws Exception;

    /**
     * 查询全部
     * @return
     * @throws Exception
     */
    public List<E> selectAll() throws Exception;

    /**
     * 分页查询
     * @param page
     * @return
     * @throws Exception
     */
    public List<E> selectByPage(PageInfo<E> page) throws Exception;

    /**
     * 分页计数
     * @param page
     * @return
     * @throws Exception
     */
    public Integer countByPage(PageInfo<E> page) throws Exception;

    /**
     * 计数
     * @param record
     * @return
     * @throws Exception
     */
    public Integer count(E record) throws Exception;

    /**
     * 以表字段为条件删除记录
     * @param record
     * @return
     * @throws Exception
     */
    public boolean deleteByCondition(E record) throws Exception;

    /**
     * 批量插入，1000行
     * @param list
     * @return
     * @throws Exception
     */
    public boolean insertBatch(List<E> list) throws Exception;

    /**
     * 判断是否存在
     * @param record
     * @return
     * @throws Exception
     */
    public boolean ifExistsByCondition(E record) throws Exception;
}
