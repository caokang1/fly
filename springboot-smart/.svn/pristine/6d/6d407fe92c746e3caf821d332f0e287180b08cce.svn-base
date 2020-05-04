package com.cignacmb.smart.base.service.impl;

import com.cignacmb.smart.base.common.PageInfo;
import com.cignacmb.smart.base.mapper.BaseMapper;
import com.cignacmb.smart.base.service.BaseService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseServiceImpl<E> implements BaseService<E> {

    protected abstract BaseMapper<E> getMapper();

    /**
     * 根据主键删除记录
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByPrimaryKey(E record) throws Exception {
        return getMapper().deleteByPrimaryKey(record) >= 0;
    }

    /**
     * 插入一条记录，可选字段
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertSelective(E record) throws Exception {
        return getMapper().insertSelective(record) > 0;
    }

    /**
     * 根据主键查找记录
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public E selectByPrimaryKey(E record) throws Exception {
        return getMapper().selectByPrimaryKey(record);
    }

    /**
     * 根据主键更新可选字段
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateByPrimaryKeySelective(E record) throws Exception {
        return getMapper().updateByPrimaryKeySelective(record) > 0;
    }

    /**
     * 以表字段为条件查询
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public List<E> selectByCondition(E record) throws Exception {
        return getMapper().selectByCondition(record);
    }

    /**
     * 查询全部
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<E> selectAll() throws Exception {
        return getMapper().selectAll();
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public List<E> selectByPage(PageInfo<E> page) throws Exception {
        page.setCount(getMapper().countByPage(page));

        if(page.getCount() == 0) {
            return new ArrayList<E>();
        }

        return getMapper().selectByPage(page, new RowBounds(page.getOffset(), page.getLimit()));
    }

    /**
     * 分页计数
     *
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public Integer countByPage(PageInfo<E> page) throws Exception {
        return getMapper().countByPage(page);
    }

    /**
     * 计数
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public Integer count(E record) throws Exception {
        return getMapper().count(record);
    }

    /**
     * 以表字段为条件删除记录
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByCondition(E record) throws Exception {
        return getMapper().deleteByCondition(record) >= 0;
    }

    /**
     * 批量插入，1000行
     *
     * @param list
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertBatch(List<E> list) throws Exception {
        int size = list.size();
        int limit = 1000;
        int times = (size - 1)/limit + 1;
        int re = 0;

        //每次批量插入1000行
        if(times == 1) {
            return getMapper().insertBatch(list) > 0;
        }
        else {
            for(int i = 0; i < times; i++) {
                List<E> subList = list.subList(0, limit*(i+1)>size?size%limit:limit);
                re += getMapper().insertBatch(subList);
                list.subList(0, limit*(i+1)>size?size%limit:limit).clear();
            }

            return re > 0;
        }
    }

    /**
     * 判断是否存在
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean ifExistsByCondition(E record) throws Exception {
        return getMapper().count(record) > 0;
    }
}
