package org.asion.boot.mvc.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.asion.boot.mvc.model.Sample;

import java.util.List;

/**
 * @author Asion.
 * @since 16/7/12.
 */
@Mapper
public interface SampleMapper {

    String SELECT_COLUMNS = " id, text, summary, created_at as createdAt, updated_at as updatedAt";

    String SELECT_ALL = "select " + SELECT_COLUMNS + " from asion_sample_sample where 1=1";
    String INSERT = "insert into asion_sample_sample(text, summary, created_at, updated_at) values(#{sample.text}, #{sample.summary}, #{sample.createdAt, jdbcType=DATE}, #{sample.updatedAt, jdbcType=DATE})";
    String UPDATE = "update asion_sample_sample set text=#{sample.text}, summary=#{sample.summary}, updated_at=#{sample.updatedAt} where id=#{sample.id}";
    String SELECT_BY_ID = "select " + SELECT_COLUMNS + " from asion_sample_sample where id = #{id}";
    String DELETE_BY_ID = "delete from asion_sample_sample where id = #{id}";

    @Select(SELECT_ALL)
    List<Sample> selectAll();

    @Insert(INSERT)
    @ResultType(Sample.class)
    @SelectKey(before = false, keyProperty = "sample.id", resultType = Long.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID() AS id")
    int insert(@Param("sample") Sample sample);

    @Update(UPDATE)
    int update(@Param("sample") Sample sample);

    @Select(SELECT_BY_ID)
    Sample select(@Param("id") Long id);

    @Delete(DELETE_BY_ID)
    int delete(@Param("id") Long id);
}
