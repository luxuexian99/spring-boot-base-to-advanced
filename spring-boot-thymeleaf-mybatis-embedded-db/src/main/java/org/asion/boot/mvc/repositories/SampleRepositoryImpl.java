package org.asion.boot.mvc.repositories;

import org.asion.boot.mvc.mapper.SampleMapper;
import org.asion.boot.mvc.model.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Asion.
 * @since 16/7/12.
 */
@Repository
public class SampleRepositoryImpl implements SampleRepository {
    @Autowired
    private SampleMapper sampleMapper;

    @Override
    public Iterable<Sample> findAll() {
        return sampleMapper.selectAll();
    }

    @Override
    public Sample save(Sample sample) {
        int rows;
        if (null == sample.getId()) {
            rows = sampleMapper.insert(sample);
        } else {
            rows = sampleMapper.update(sample);
        }
        assert rows == 1;
        return sample;
    }

    @Override
    public Sample findOne(Long id) {
        return sampleMapper.select(id);
    }

    @Override
    public void delete(Long id) {
        int rows = sampleMapper.delete(id);
        assert rows == 1;
    }

}
