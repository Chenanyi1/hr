package com.huarui.hr.lp.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MyCountMapper {
	public Integer getTableCount(@Param("rowsName")String rowsName,@Param("tableName")String tableName);
}
