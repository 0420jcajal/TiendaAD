package com.example.demo.models;
import jakarta.persistence.*;



@Entity
@Table(name="user")
public class User {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name="nombre")
    private String nombre;
    @Column(name="contrasena")
    private String contrasena;
    @Column(name="edad")
    private int edad;
    @Column(name="administrador")
    private boolean adminstrador;
    public User(long id, String nombre, String contrasena, int edad, boolean adminstrador) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.edad = edad;
        this.adminstrador = adminstrador;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public boolean isAdminstrador() {
        return adminstrador;
    }
    public void setAdminstrador(boolean adminstrador) {
        this.adminstrador = adminstrador;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((contrasena == null) ? 0 : contrasena.hashCode());
        result = prime * result + edad;
        result = prime * result + (adminstrador ? 1231 : 1237);
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (contrasena == null) {
            if (other.contrasena != null)
                return false;
        } else if (!contrasena.equals(other.contrasena))
            return false;
        if (edad != other.edad)
            return false;
        if (adminstrador != other.adminstrador)
            return false;
        return true;
    }
    public User() {
    } 

    

}
