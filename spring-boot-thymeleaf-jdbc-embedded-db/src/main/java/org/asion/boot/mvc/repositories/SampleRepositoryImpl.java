package org.asion.boot.mvc.repositories;

import org.asion.boot.mvc.model.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Calendar;

/**
 * @author Asion.
 * @since 16/7/4.
 */
@Repository
public class SampleRepositoryImpl implements SampleRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Iterable<Sample> findAll() {
        return jdbcTemplate.query(SampleSql.SELECT_ALL, userMapper);
    }

    @Override
    public Sample save(Sample sample) {
        int rows;
        if (sample.getId() == null) {
            // insert return id
            KeyHolder keyHolder = new GeneratedKeyHolder();
            rows = jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(SampleSql.INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, sample.getText());
                ps.setString(2, sample.getSummary());
                ps.setDate(3, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
                ps.setDate(4, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
                return ps;
            }, keyHolder);

            sample.setId(keyHolder.getKey().longValue());
        } else {
            rows = jdbcTemplate.update(SampleSql.UPDATE, sample.getText(), sample.getSummary(), new java.sql.Date(Calendar.getInstance().getTimeInMillis()), sample.getId());
        }
        assert rows == 1;
        return sample;
    }

    @Override
    public Sample findOne(Long id) {
        return jdbcTemplate.queryForObject(SampleSql.SELECT_BY_ID, userMapper, id);
    }

    @Override
    public void delete(Long id) {
        int rows = jdbcTemplate.update(SampleSql.DELETE_BY_ID, id);
        assert rows == 1;
    }

    private static final RowMapper<Sample> userMapper = (resultSet, i) -> {
        Sample sample = new Sample();
        sample.setId(resultSet.getLong("id"));
        sample.setText(resultSet.getString("text"));
        sample.setSummary(resultSet.getString("summary"));
        Calendar calendar = Calendar.getInstance();
        if (resultSet.getDate("created_at") != null) {
            calendar.setTime(resultSet.getDate("created_at"));
        }
        sample.setCreatedAt(calendar);
        if (resultSet.getDate("updated_at") != null) {
            calendar.setTime(resultSet.getDate("updated_at"));
        }
        sample.setUpdatedAt(calendar);
        return sample;
    };
}
