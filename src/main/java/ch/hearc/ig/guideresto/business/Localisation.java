package ch.hearc.ig.guideresto.business;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Localisation implements Serializable {

    @Column(name = "ADRESSE")
    String street;

    // I choose to load the type Eagerly, as it is displayed by CLI nearly systematically
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "FK_VILL", nullable = false)
    City city;

    public Localisation() {
    }
    public Localisation(String street, City city) {
        this.street = street;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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
        Localisation that = (Localisation) o;
        return getStreet() != null && Objects.equals(getStreet(), that.getStreet())
                && getCity() != null && Objects.equals(getCity(), that.getCity());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(street, city);
    }
}