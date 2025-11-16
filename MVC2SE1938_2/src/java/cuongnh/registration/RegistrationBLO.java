/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cuongnh.registration;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Admin
 */
public class RegistrationBLO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MVC2SE1938_2PU");

    public RegistrationBLO() {
    }

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public Registration checkLogin(String username, String password) {
        EntityManager em = this.emf.createEntityManager();
        Registration result = null;
        String jpql = "SELECT r "
                + "FROM Registration r "
                + "WHERE r.username = :username "
                + "AND r.password = :password";
        try {
            Query query = em.createQuery(jpql);
            query.setParameter("username", username);
            query.setParameter("password", password);
            result = (Registration) query.getSingleResult();
        } catch (NoResultException ex) {
            result = null;
        } finally {
            this.close();
        }
        
        return result;
    }
    
    public List<Registration> searchLastname(String searchValue) {
        EntityManager em = this.emf.createEntityManager();
        List<Registration> result = null;
        String jpql = "Registration.findByLikeLastname";
        try {
            Query query = em.createNamedQuery(jpql);
            query.setParameter("lastname", "%" + searchValue + "%");
        
            result = (List<Registration>) query.getResultList();
        } catch (NoResultException ex) {
            result = null;
        } finally {
            this.close();
        }
        return result;
    }
    
    public boolean deleteAccount(String username) {
        EntityManager em = this.emf.createEntityManager();
        boolean result = false;
        
        try {
            Registration tmp = em.find(Registration.class, username);
            if (tmp != null) {
                em.getTransaction().begin();
                em.remove(tmp);
                em.getTransaction().commit();
                result = true;
            }
        } finally {
            this.close();
        }
        
        return result;
    }
    
    public boolean updateAccount(String username, 
            String password, boolean role) {
        EntityManager em = this.emf.createEntityManager();
        boolean result = false;
        
        try {
            Registration tmp = em.find(Registration.class, username);
            if (tmp != null) {
                tmp.setPassword(password);
                tmp.setIsAdmin(role);
                em.getTransaction().begin();
                em.merge(tmp);
                em.getTransaction().commit();
                result = true;
            }
        } finally {
            this.close();
        }
        
        return  result;
    } 
    
    public boolean createAccount(Registration r) {
        EntityManager em = this.emf.createEntityManager();
        boolean result = false;
        
        try {
            Registration tmp = em.find(Registration.class, r.getUsername());
            if (tmp == null) {
                this.persist(r);
                result = true;
            }
        } finally {
            this.close();
        }
        
        return result;
    }
    
    public void close() {
        if (this.emf != null) {
            if (this.emf.isOpen()) {
                this.emf.close();
            }
        }
    }
}
