/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejbs.StudentBean;
import entities.Student;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

/**
 *
 * @author ITWannaBe
 */
@ManagedBean
@SessionScoped
public class AdministratorManager {

    @EJB
    StudentBean stb;
    private String userName;
    private String password;
    private String name;
    private String email;

    private Student currentStudent;

    /**
     * Creates a new instance of AdministratorManager
     */
    public AdministratorManager() {
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String createStudent() {
        try {
            stb.createStudent(userName, password, name, email);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        clear();
        return "index?faces-redirect=true";
    }

    private void clear() {
        setUserName(null);
        setPassword(null);
        setName(null);
        setEmail(null);
    }

    public List<Student> getAllStudents() {
        return stb.getAll();
    }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }

    public Student getCurrentStudent() {
        return currentStudent;
    }

    public String updateStudent() {
        try {
            stb.update(userName, password, name, email);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        clear();
        return "index?faces-redirect=true";
    }

    public void removeStudent(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("deleteStudentId");
            String id = param.getValue().toString();
            System.out.println(id);
            stb.remove(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
