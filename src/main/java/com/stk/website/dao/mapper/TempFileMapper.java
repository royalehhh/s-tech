package com.stk.website.dao.mapper;

import com.stk.website.dao.model.TempFile;
import com.stk.website.dao.model.TempFileExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TempFileMapper {
    long countByExample(TempFileExample example);

    int deleteByExample(TempFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TempFile record);

    int insertSelective(TempFile record);

    List<TempFile> selectByExample(TempFileExample example);

    TempFile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TempFile record, @Param("example") TempFileExample example);

    int updateByExample(@Param("record") TempFile record, @Param("example") TempFileExample example);

    int updateByPrimaryKeySelective(TempFile record);

    int updateByPrimaryKey(TempFile record);
}