package main;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class AnsattDAO {
	private EntityManagerFactory emf;

    public AnsattDAO() {
        this.emf = Persistence.createEntityManagerFactory("my_persistence_unit");
    }

    public Ansatt finnAnsattMedId(long id) {
    	EntityManager em = emf.createEntityManager();
        try {
            return em.find(Ansatt.class, id);
        } finally {
            em.close();
        }
    }

 // Søk etter ansatt på brukernavn (initialer)
    public Ansatt finnAnsattMedBrukernavn(String brukernavn) {
    	EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Ansatt> query = em.createQuery(
                "SELECT a FROM Ansatt a WHERE a.brukernavn = :brukernavn", Ansatt.class);
            query.setParameter("brukernavn", brukernavn);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Ansatt> finnAlleAnsatte() {
    	EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a", Ansatt.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void oppdaterAnsatt(long id, String nyStilling, int nyLonn) {
    	EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Ansatt ansatt = em.find(Ansatt.class, id);
            if (ansatt != null) {
                ansatt.setStilling(nyStilling);
                ansatt.setLonn(nyLonn);
                em.merge(ansatt);
            }
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }
 // Legg inn en ny ansatt og angi hvilken avdeling vedkommende skal jobbe på
    public void leggInnNyAnsatt(Ansatt nyAnsatt, int avdelingId) {
    	 EntityManager em = emf.createEntityManager();
         em.getTransaction().begin();
         try {
             Avdeling avdeling = em.find(Avdeling.class, avdelingId);
             if (avdeling != null) {
                 nyAnsatt.setAvdeling(avdeling);
                 em.persist(nyAnsatt);
                 em.getTransaction().commit();
             } else {
                 System.out.println("Kunne ikke legge til ansatt. Avdeling med ID " + avdelingId + " eksisterer ikke.");
             }
         } finally {
             if (em.getTransaction().isActive()) {
                 em.getTransaction().rollback();
             }
             em.close();
         }
    } 
}
