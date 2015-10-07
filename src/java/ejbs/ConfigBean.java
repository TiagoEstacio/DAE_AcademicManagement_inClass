/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Singleton;
import javax.ejb.Startup;


@Singleton
@Startup
public class ConfigBean {
    
    @EJB
    StudentBean stb;
    
    @PostConstruct
    public void populateDB() {
        try {
             stb.createStudent("1","1pass","1nome","1mail");
             stb.createStudent("2","2pass","2nome","2mail");
             stb.createStudent("3","3pass","3nome","3mail");
             stb.createStudent("4","4pass","4nome","4mail");
        } catch (Exception e) {
             throw new EJBException(e.getMessage());
        }
       
        
    } 
    
}
