package br.com.fwtj.mdd.util.jpa;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import org.hibernate.Session;

@ApplicationScoped
public class EntityManagerProducer implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory pu1Factory;
    private EntityManagerFactory pu2Factory;

    public EntityManagerProducer() {
        pu1Factory = Persistence.createEntityManagerFactory("PU1");
        pu2Factory = Persistence.createEntityManagerFactory("PU2");
    }

    @RequestScoped
    @Produces
    @PU1
    public Session createAppEntityManager() {
        return (Session) pu1Factory.createEntityManager();
    }

    @RequestScoped
    @Produces
    @PU2
    public Session createCorporativoEntityManager() {
        return (Session) pu2Factory.createEntityManager();
    }

    public void closeEntityManagerPU1(@Disposes @PU1 EntityManager manager) {
        if (manager.isOpen()) {
            manager.close();
        }
    }

    public void closeEntityManagerPU2(@Disposes @PU2 EntityManager manager) {
        if (manager.isOpen()) {
            manager.close();
        }
    }

}
