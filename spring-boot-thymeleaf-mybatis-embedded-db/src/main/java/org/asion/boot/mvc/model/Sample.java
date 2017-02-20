package org.asion.boot.mvc.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Asion.
 * @since 16/6/28.
 */
public class Sample implements Serializable {
    private Long id;

    private String text;

    private String summary;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Sample() {}

    public Sample(Long id, String summary, String text) {
        this.id = id;
        this.summary = summary;
        this.text = text;
    }

    public Sample(Long id, String summary, String text, Date createdAt, Date updatedAt) {
        this.id = id;
        this.text = text;
        this.summary = summary;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Sample empty() {
        return new Sample();
    }

    @Override
    public String toString() {
        return "Sample{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", summary='" + summary + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
