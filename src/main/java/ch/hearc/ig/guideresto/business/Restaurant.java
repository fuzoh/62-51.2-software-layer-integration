package ch.hearc.ig.guideresto.business;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "RESTAURANTS")
public class Restaurant implements Serializable {

    @Id
    @Column(name = "NUMERO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RESTAURANTS")
    @SequenceGenerator(
            name = "SEQ_RESTAURANTS",
            sequenceName = "SEQ_RESTAURANTS",
            allocationSize = 1
    )
    private Integer id;

    @Column(name = "NOM")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SITE_WEB")
    private String website;

    @Embedded
    private Localisation address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "FK_TYPE", nullable = false)
    private RestaurantType type;

    @OneToMany(mappedBy = "restaurant")
    private Set<CompleteEvaluation> completeEvaluation = new LinkedHashSet<>();

    @OneToMany(mappedBy = "restaurant")
    private Set<BasicEvaluation> basicEvaluation = new LinkedHashSet<>();

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, String description, String website, String street, City city,
                      RestaurantType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.website = website;
        //this.evaluations = new HashSet<>();
        this.address = new Localisation(street, city);
        this.type = type;
    }

    public Restaurant(int id, String name, String description, String website, String street) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.website = website;
        // TODO : this.address = new Localisation(street);
    }

    public Set<BasicEvaluation> getBasicEvaluation() {
        return basicEvaluation;
    }

    public void setBasicEvaluation(Set<BasicEvaluation> basicEvaluation) {
        this.basicEvaluation = basicEvaluation;
    }

    public Set<CompleteEvaluation> getCompleteEvaluation() {
        return completeEvaluation;
    }

    public void setCompleteEvaluation(Set<CompleteEvaluation> completeEvaluation) {
        this.completeEvaluation = completeEvaluation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipCode() {
        return address.getCity().getZipCode();
    }

    public String getStreet() {
        return address.getStreet();
    }

    public String getCityName() {
        return address.getCity().getCityName();
    }

    public City getCity() {
        return address.getCity();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Set<Evaluation> getEvaluations() {
        return null;
    }

    //public void setEvaluations(Set<Evaluation> evaluations) {
    //    this.evaluations = evaluations;
    //}

    public Localisation getAddress() {
        return address;
    }

    public RestaurantType getType() {
        return type;
    }

    public void setType(RestaurantType type) {
        this.type = type;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer()
                                                                                     .getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Restaurant that = (Restaurant) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
                                                                       .getPersistentClass()
                                                                       .hashCode() : getClass().hashCode();
    }
}