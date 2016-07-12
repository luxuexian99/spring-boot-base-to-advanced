package org.asion.boot.mvc.repositories;

import org.asion.boot.mvc.BaseApplicationTests;
import org.asion.boot.mvc.model.Sample;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Asion.
 * @since 16/7/12.
 */
public class SampleRepositoryTest extends BaseApplicationTests {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private SampleRepository sampleRepository;

    @Test
    public void testSave() {
        Sample sample = sampleRepository.findOne(1L);
        assertThat(sample).isNotNull()
                .hasFieldOrPropertyWithValue("summary", "Summary1");

        // insert
        sample.setId(null); // reset id
        sample.setSummary("Test Insert Summary!");
        sample.setText("Test Insert Text!");
        Sample created = sampleRepository.save(sample);
        assertThat(created).isNotNull().hasFieldOrPropertyWithValue("summary", "Test Insert Summary!");

        // update
        created.setSummary("Test Update Summary!");
        created.setText("Test Update Text!");
        Sample updated = sampleRepository.save(sample);
        assertThat(updated).isNotNull().hasFieldOrPropertyWithValue("summary", "Test Update Summary!");
    }

    @Test
    public void testFindOne() {
        Sample sample = sampleRepository.findOne(1L);
        assertThat(sample)
                .isNotNull()
                .hasFieldOrPropertyWithValue("summary", "Summary1");
        System.out.println(sample);
    }

    @Test
    public void testFindAll() {
        Iterable<Sample> samples = sampleRepository.findAll();
        assertThat(samples).hasSize(12);
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        Sample sampleSrc = sampleRepository.findOne(id);
        assertThat(sampleSrc).isNotNull();

        sampleRepository.delete(id);

        Sample sample = sampleRepository.findOne(id);
        assertThat(sample).isNull();
    }
}
