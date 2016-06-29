package org.asion.boot.mvc.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Asion.
 * @since 16/6/28.
 */
public class Sample implements Serializable {
    private Long id;

    private String text;

    private String summary;

    private Calendar createdAt = Calendar.getInstance();

    private Calendar updatedAt = Calendar.getInstance();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public Calendar getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Calendar updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static Sample empty() {
        return new Sample();
    }
}
