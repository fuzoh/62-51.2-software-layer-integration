package ch.hearc.ig.guideresto.business;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "COMMENTAIRES")
public class CompleteEvaluation extends Evaluation implements Serializable {

    @Lob
    @Column(name = "COMMENTAIRE", nullable = false)
    private String comment;

    @Column(name = "NOM_UTILISATEUR", nullable = false, length = 100)
    private String username;

    @OneToMany(mappedBy = "evaluation")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Grade> grades = new LinkedHashSet<>();

    public CompleteEvaluation() {
    }

    public CompleteEvaluation(
            Integer id, LocalDate visitDate, Restaurant restaurant, String comment, String username
    ) {
        super(id, visitDate, restaurant);
        this.comment = comment;
        this.username = username;
        this.grades = new HashSet<>();
    }

    public String getComment() {
        return comment;
    }

    public String getUsername() {
        return username;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o)
                .getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        CompleteEvaluation that = (CompleteEvaluation) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer().getPersistentClass()
                .hashCode() : getClass().hashCode();
    }

}