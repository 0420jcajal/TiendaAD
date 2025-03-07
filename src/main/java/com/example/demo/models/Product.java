package com.example.demo.models;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="nombre")
    private String nombre;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="imagenPath")
    private String imagenPath;
    @Column(name="stock")
    private int stock;
    @Column(name="precio")
    private float precio;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Pedido_Producto> pedidoProductos;
    
    public Product() {
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
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getImagenPath() {
        return imagenPath;
    }
    public void setImagenPath(String imagenPath) {
        this.imagenPath = imagenPath;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result + ((imagenPath == null) ? 0 : imagenPath.hashCode());
        result = prime * result + stock;
        result = prime * result + Float.floatToIntBits(precio);
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
        Product other = (Product) obj;
        if (id != other.id)
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (descripcion == null) {
            if (other.descripcion != null)
                return false;
        } else if (!descripcion.equals(other.descripcion))
            return false;
        if (imagenPath == null) {
            if (other.imagenPath != null)
                return false;
        } else if (!imagenPath.equals(other.imagenPath))
            return false;
        if (stock != other.stock)
            return false;
        if (Float.floatToIntBits(precio) != Float.floatToIntBits(other.precio))
            return false;
        return true;
    }
    public List<Pedido_Producto> getPedidoProductos() {
        return pedidoProductos;
    }
    public void setPedidoProductos(List<Pedido_Producto> pedidoProductos) {
        this.pedidoProductos = pedidoProductos;
    }

}
