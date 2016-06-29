package org.asion.boot.mvc.web;

import org.asion.boot.mvc.model.Sample;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * Sample controller.
 * @author Asion.
 * @since 16/5/29.
 */
@Controller
@RequestMapping("/sample/")
public class SampleController {

    private static LongAdder counter = new LongAdder();

    // 充当数据存储
    private final ConcurrentMap<Long, Sample> samples = new ConcurrentHashMap<>();

    public SampleController() {
        // 默认初始化10个数据
        for (int i = 0; i < 10; i++) {
            counter.increment();
            Long id = counter.longValue();
            Sample sample = Sample.empty();
            sample.setId(id);
            sample.setSummary("Summary" + id);
            sample.setText("This is text"+ id);
            samples.put(id, sample);
        }
    }

    @RequestMapping("list")
    public ModelAndView list() {
        Iterable<Sample> samples = this.samples.values();
        return new ModelAndView("sample/list", "samples", samples);
    }

    @RequestMapping("{id}")
    public ModelAndView view(@PathVariable("id") Long id) {
        Sample sample = this.samples.get(id);
        return new ModelAndView("sample/view", "sample", sample);
    }

    @GetMapping(params = "form=form")
    public String createForm(@ModelAttribute Sample sample) {
        return "sample/form";
    }

    @PostMapping(value = "create")
    public ModelAndView create(@Valid Sample sample, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("sample/form", "formErrors", result.getAllErrors());
        }
        Long id = sample.getId();
        if (id == null) {
            counter.increment();
            id = counter.longValue();
            sample.setId(id);
        }
        this.samples.put(id, sample);
        redirect.addFlashAttribute("globalSample", "Successfully save a new sample");
        return new ModelAndView("redirect:/sample/{sample.id}", "sample.id", sample.getId());
    }

    @RequestMapping("foo")
    public String foo() {
        throw new RuntimeException("Expected exception in controller");
    }

    @RequestMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        this.samples.remove(id);
        Iterable<Sample> samples = this.samples.values();
        return new ModelAndView("sample/list", "samples", samples);
    }

    @GetMapping(value = "modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Long id) {
        Sample sample = this.samples.get(id);
        return new ModelAndView("sample/form", "sample", sample);
    }

}
