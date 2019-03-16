package ru.af.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "message",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    private int id;

    @Column(name = "body", length = 20, nullable = true)
    private String body;

    @Column(name = "time", length = 20, nullable = true)
    private long time;

    @Column(name = "user_id", length = 20, nullable = true)
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id &&
                time == message.time &&
                userId == message.userId &&
                Objects.equals(body, message.body);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, body, time, userId);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", time=" + time +
                ", userId=" + userId +
                '}';
    }
}
