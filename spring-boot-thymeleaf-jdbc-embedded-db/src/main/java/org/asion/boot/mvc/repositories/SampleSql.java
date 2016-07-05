package org.asion.boot.mvc.repositories;

/**
 * @author Asion.
 * @since 16/7/4.
 */
public interface SampleSql {

    String SELECT_COLUMNS = " id, text, summary, created_at, updated_at ";

    String SELECT_ALL = "select " + SELECT_COLUMNS + " from asion_sample_sample";
    String INSERT = "insert into asion_sample_sample(text, summary, created_at, updated_at) values(?, ?, ?, ?)";
    String UPDATE = "update asion_sample_sample set text=?, summary=?, updated_at=? where id=?";
    String SELECT_BY_ID = "select " + SELECT_COLUMNS + " from asion_sample_sample where id = ?";
    String DELETE_BY_ID = "delete from asion_sample_sample where id = ?";

}
