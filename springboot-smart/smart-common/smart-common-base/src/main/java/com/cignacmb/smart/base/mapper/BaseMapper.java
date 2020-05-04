package com.cignacmb.smart.base.mapper;

import com.cignacmb.smart.base.common.PageInfo;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.List;

public interface BaseMapper<E> {
    int deleteByPrimaryKey(E record) throws SQLException;

    int insertSelective(E record) throws SQLException;

    E selectByPrimaryKey(E record) throws SQLException;

    int updateByPrimaryKeySelective(E record) throws SQLException;

    List<E> selectByCondition(E record) throws SQLException;

    List<E> selectAll() throws SQLException;

    int count(E record) throws SQLException;

    int deleteByCondition(E record) throws SQLException;

    int insertBatch(List<E> list) throws SQLException;

    int countByPage(PageInfo<E> page) throws SQLException;

    List<E> selectByPage(PageInfo<E> page, RowBounds rowBounds) throws SQLException;
}
