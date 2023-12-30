package ch.hearc.ig.guideresto.business;

import ch.hearc.ig.guideresto.business.converters.BoolConverter;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "LIKES")
public class BasicEvaluation extends Evaluation implements Serializable {

    @Column(name = "ADRESSE_IP", nullable = false, length = 100)
    private String ipAddress;

    @Column(name = "APPRECIATION", nullable = false)
    @Convert(converter = BoolConverter.class)
    private Boolean likeRestaurant = false;

    public BasicEvaluation() {
    }

    public BasicEvaluation(Integer id, LocalDate visitDate, Restaurant restaurant, boolean likeRestaurant,
                           String ipAddress) {
        super(id, visitDate, restaurant);
        this.likeRestaurant = likeRestaurant;
        this.ipAddress = ipAddress;
    }

    public void setLikeRestaurant(Boolean likeRestaurant) {
        this.likeRestaurant = likeRestaurant;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Boolean isLikeRestaurant() {
        return likeRestaurant;
    }

}