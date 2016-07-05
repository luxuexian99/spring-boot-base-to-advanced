package org.asion.boot.mvc.repositories;

import org.asion.boot.mvc.model.Sample;

/**
 * @author Asion.
 * @since 16/7/4.
 */
public interface SampleRepository {
    Iterable<Sample> findAll();

    Sample save(Sample sample);

    Sample findOne(Long id);

    void delete(Long id);
}
