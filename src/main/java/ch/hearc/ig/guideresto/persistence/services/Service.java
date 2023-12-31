package ch.hearc.ig.guideresto.persistence.services;

import jakarta.persistence.EntityManagerFactory;

public abstract class Service {

    final EntityManagerFactory emf;


    public Service(EntityManagerFactory emf) {
        this.emf = emf;
    }

}
