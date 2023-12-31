package ch.hearc.ig.guideresto.business;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TYPES_GASTRONOMIQUES")
public class RestaurantType implements Serializable {

    @Id
    @Column(name = "NUMERO", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TYPES_GASTRONOMIQUES")
    @SequenceGenerator(
            name = "SEQ_TYPES_GASTRONOMIQUES", sequenceName = "SEQ_TYPES_GASTRONOMIQUES",
            allocationSize = 1
    )
    private Integer id;

    @Column(name = "LIBELLE", nullable = false, length = 100)
    private String label;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @OneToMany(mappedBy = "type", cascade = CascadeType.MERGE)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Set<Restaurant> restaurants = new LinkedHashSet<>();

    public RestaurantType() {
    }

    public RestaurantType(Integer id, String label, String description) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.restaurants = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantType that = (RestaurantType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}