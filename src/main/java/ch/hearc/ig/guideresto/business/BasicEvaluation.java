package ch.hearc.ig.guideresto.business;

import ch.hearc.ig.guideresto.persistence.cache.CacheAble;

import java.time.LocalDate;

public class BasicEvaluation extends Evaluation implements CacheAble {

  private boolean likeRestaurant;
  private String ipAddress;

  public BasicEvaluation(Integer id, LocalDate visitDate, Restaurant restaurant, boolean likeRestaurant,
      String ipAddress) {
    super(id, visitDate, restaurant);
    this.likeRestaurant = likeRestaurant;
    this.ipAddress = ipAddress;
  }

  public Boolean isLikeRestaurant() {
    return likeRestaurant;
  }

}