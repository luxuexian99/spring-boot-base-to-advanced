package org.asion.boot.mvc.web;

import org.asion.boot.mvc.SpringBootMvcMybatisTestApplication;
import org.asion.boot.mvc.model.Sample;
import org.asion.boot.mvc.repositories.SampleRepository;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Spring Boot Test
 *
 * @author Asion.
 * @since 16/7/12.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringBootMvcMybatisTestApplication.class)
@WebMvcTest(value = SampleController.class)
public class SampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SampleRepository sampleRepository;

    private Long sampleIdA = 1L;
    private Long sampleIdB = 2L;

    private List<Sample> samples = new ArrayList<>(10);
    Date _now = new Date();
    private Sample sampleA = new Sample(sampleIdA, "SummaryA", "TextA", _now, _now);
    private Sample sampleB = new Sample(sampleIdB, "SummaryB", "TextB", _now, _now);

    @Before
    public void setup() {

        samples.add(sampleA);
        samples.add(sampleB);
        given(sampleRepository.findAll()).willReturn(samples);
        given(sampleRepository.findOne(sampleIdA)).willReturn(sampleA);
        given(sampleRepository.findOne(sampleIdB)).willReturn(sampleB);
//        given(sampleRepository.save(sampleA)).willReturn(sampleA);
    }

    @Test
    public void testList() throws Exception {
        this.mockMvc.perform(get("/sample/list").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(view().name("sample/list"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("samples"))
                .andExpect(model().attribute("samples", new IsInstanceOf(Collection.class)))
                .andExpect(content().string(new StringContains("<h1>Samples : View all</h1>")));
    }

    @Test
    public void testOne() throws Exception {
        this.mockMvc.perform(get("/sample/{id}", sampleIdB)
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(view().name("sample/view"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("sample"))
                .andExpect(content().string(new StringContains("<title>Samples : View</title>")));
    }

    @Test
    public void testCreateForm() throws Exception {

        // MockMvc 不支持此种类型的参数: /sample/?form
        this.mockMvc.perform(get("/sample/?form=form")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(view().name("sample/form"))
                .andExpect(model().size(1))
                .andExpect(content().string(new StringContains("<title>Samples : Create</title>")));
    }

    @Test
    public void testCreate() throws Exception {
        Date now = new Date();
        Sample sample = // new Sample();
                 new Sample(null, "Summary Create Test!", "This is test Create text!", now, now);
        Sample sample1 = new Sample(sampleIdA, "Summary Create Test!", "This is test Create text!", now, now);
        given(sampleRepository.save(sample)).willReturn(sample1);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowStr = dateFormat.format(now);

        this.mockMvc.perform(
                post("/sample/create")
                        .param("summary", "Summary Create Test!")
                        .param("text", "This is test Create text!")
                        .param("createdAt", nowStr)
                        .param("updatedAt", nowStr)
                        .accept(MediaType.TEXT_PLAIN)
            )
                .andExpect(status().isOk())
                .andExpect(view().name("sample/form"))
                .andExpect(model().size(2))
                .andExpect(model().attributeExists("formErrors"))
                .andExpect(content().string(new StringContains("<a href=\"/sample/list\"> Samples </a>")));
//                .andExpect(status().is3xxRedirection())
//            .andExpect(view().name("redirect:/sample/{sample.id}"))
//            .andExpect(model().size(1))
//            .andExpect(model().attributeExists("sample.id"));
    }

    @Test
    public void testDelete() throws Exception {
        this.mockMvc.perform(get("/sample/delete/2")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(view().name("sample/list"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("samples"))
                .andExpect(model().attribute("samples", new IsInstanceOf(Collection.class)))
                .andExpect(content().string(new StringContains("<h1>Samples : View all</h1>")));
    }

    @Test
    public void testModifyForm() throws Exception {
        this.mockMvc.perform(get("/sample/modify/{id}", 2)
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(view().name("sample/form"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("sample"))
                .andExpect(content().string(new StringContains("<h1>Samples : Create</h1>")));
    }

    @Test
    public void testModify() throws Exception {
        Sample sample = new Sample(2L, "Summary Update Test", "This is test Update text!", new Date(), new Date());
        given(sampleRepository.save(sample)).willReturn(sample);

        this.mockMvc.perform(post("/sample/create")
                .param("id", "2")
                .param("summary", "Summary Update Test")
                .param("text", "This is test Update text!")
                .accept(MediaType.TEXT_PLAIN)
        )
                .andExpect(status().isOk())
                .andExpect(view().name("sample/form"))
                .andExpect(model().size(2))
                .andExpect(model().attributeExists("formErrors"))
                .andExpect(content().string(new StringContains("<a href=\"/sample/list\"> Samples </a>")));
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/sample/{sample.id}"))
//                .andExpect(model().size(1))
//                .andExpect(model().attributeExists("sample.id"));
    }

    @Test
    public void testFoo() {
        //Expected exception in controller
        try {
            mockMvc.perform(get("/sample/foo"));
        } catch (Exception e) {
            assertEquals("Request processing failed; nested exception is java.lang.RuntimeException: Expected exception in controller", e.getMessage());
        }
    }

}
