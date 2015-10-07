/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.Student;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ITWannaBe
 */
@Stateless
public class StudentBean {

    @PersistenceContext
    EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void createStudent(String userName, String password, String name, String email) {
        try {
            Student st = new Student(userName, password, name, email);
            em.persist(st);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<Student> getAll() {
        return em.createNamedQuery("findAllStudents").getResultList();
    }
    
    public void update(String userName, String password, String name, String email) {
        Student st = em.merge(em.find(Student.class, userName));
        st.setEmail(email);
        st.setName(name);
        st.setPassword(password);
        em.persist(st);
    }
    
    public void remove (String userName) {
        em.remove(em.find(Student.class, userName));
    }
}
