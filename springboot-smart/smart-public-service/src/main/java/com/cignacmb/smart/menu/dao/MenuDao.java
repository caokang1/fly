package com.cignacmb.smart.menu.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author flm
 * @Create 2020-04-29 11:24
 */

@Mapper
public interface MenuDao {

    List<Object> selectMenuList(String UserCode);
}
