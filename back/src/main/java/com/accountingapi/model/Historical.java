package com.accountingapi.model;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Historical {

    @EmbeddedId
    private HistoryKey id;

    private String message;

    @Size(min = 4, max = 255, message = "Minimum note length: 4 characters")
    private String comment;



    //************Getters & Setters************
    public HistoryKey getId() {
        return id;
    }

    public void setId(HistoryKey id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Historical that = (Historical) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(message, that.message) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, comment);
    }
}
