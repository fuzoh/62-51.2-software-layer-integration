package ch.hearc.ig.guideresto.business;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
public abstract class Evaluation implements Serializable {

    @Id
    @Column(name = "NUMERO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_EVAL"
    )
    @SequenceGenerator(
            name = "SEQ_EVAL",
            sequenceName = "SEQ_EVAL",
            allocationSize = 1
    )
    private Integer id;

    @Column(name = "DATE_EVAL")
    private LocalDate visitDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "FK_REST")
    private Restaurant restaurant;

    public Evaluation() {
    }

    public Evaluation(Integer id, LocalDate visitDate, Restaurant restaurant) {
        this.id = id;
        this.visitDate = visitDate;
        this.restaurant = restaurant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}