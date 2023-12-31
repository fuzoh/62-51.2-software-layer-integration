package ch.hearc.ig.guideresto.business;

import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "VILLES")
public class City implements Serializable {

    @Id
    @Column(name = "NUMERO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VILLES")
    @SequenceGenerator(name = "SEQ_VILLES", sequenceName = "SEQ_VILLES", allocationSize = 1)
    private Integer id;

    @Column(name = "CODE_POSTAL")
    private String zipCode;

    @Column(name = "NOM_VILLE")
    private String cityName;

    @OneToMany(
            mappedBy = "address.city", cascade = CascadeType.REMOVE, orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<Restaurant> restaurants;

    public City(Integer id, String zipCode, String cityName) {
        this.id = id;
        this.zipCode = zipCode;
        this.cityName = cityName;
        this.restaurants = new HashSet<>();
    }

    public City(String zipCode, String cityName) {
        this.zipCode = zipCode;
        this.cityName = cityName;
        this.restaurants = new HashSet<>();
    }

    public City() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCityName() {
        return cityName;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
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
        City city = (City) o;
        return getId() != null && Objects.equals(getId(), city.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer().getPersistentClass()
                .hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return "City{" + "id=" + id + ", zipCode='" + zipCode + '\'' + ", cityName='" + cityName + '\'' + ", restaurants=" + restaurants + '}';
    }

}