
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biblio.modelos;

/**
 *
 * @author sergi
 */
public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String password;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{nombre=").append(nombre);
        sb.append(", email=").append(email);
        sb.append('}');
        return sb.toString();
    }

    public Usuario(int id, String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.id = id;
    }

    public Usuario() {
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public boolean checkNombre(String nombre){
        return nombre.length() >= 6;
    }
    
    public boolean checkEmail(String email){
        //return email.matches("@+");
        boolean ok = false;
        if (email.matches("[-\\w\\.]+@\\w+\\.\\w+")) 
            ok = true;
        return ok;
    }
    
    public boolean checkPassword(String pwd){
     boolean ok = false;
        if (pwd.matches(".*[A-Z].*") && pwd.length() >=8) 
            ok = true;
        return ok;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}