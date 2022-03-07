package entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "visitations")
public class Visitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public Visitation() {
    }

    public Visitation(LocalDate date, Comment comment) {
        this.date = date;
        this.comment = comment;
    }

    public Comment getComment() {
        return comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Visitation{" +
                "date=" + date +
                ", comment=" + comment +
                '}';
    }
}
