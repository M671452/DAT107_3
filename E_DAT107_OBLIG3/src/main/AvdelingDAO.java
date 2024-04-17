package main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AvdelingDAO {
    private EntityManagerFactory emf;

    public AvdelingDAO() {
        this.emf = Persistence.createEntityManagerFactory("my_persistence_unit");
    }

    public Avdeling finnAvdelingMedId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Avdeling.class, id);
        } finally {
            em.close();
        }
    }

    public void leggInnNyAvdeling(Avdeling nyAvdeling) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(nyAvdeling);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }
}