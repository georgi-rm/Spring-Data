package entities;

import javax.persistence.*;

@Entity
@Table(name = "diagnoses")
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public Diagnose() {
    }

    public Diagnose(String name, Comment comment) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Diagnose{" +
                "name='" + name + '\'' +
                ", comment=" + comment +
                '}';
    }
}
