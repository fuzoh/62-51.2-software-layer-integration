package ch.hearc.ig.guideresto.business;

import ch.hearc.ig.guideresto.persistence.cache.CacheAble;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Restaurant implements CacheAble {

    private Integer id;
    private String name;
    private String description;
    private String website;
    private Set<Evaluation> evaluations;
    private Localisation address;
    private RestaurantType type;
    public Restaurant(Integer id, String name, String description, String website, String street, City city,
                      RestaurantType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.website = website;
        this.evaluations = new HashSet<>();
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
        return evaluations;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}