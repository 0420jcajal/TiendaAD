package com.example.demo.models;
import java.util.List;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="pedido")
public class Pedido {

    @Id
    @Column(name="numeroPedido")
    private long numeroPedido;

    @Column(name="descripcion")
    private String descripcion;
    @Column(name="precio")
    private float precio;
    @Column(name="estado")
    private String estado;
    @Column(name="comprador")
    private String comprador;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Pedido_Producto> pedidoProductos;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Pedido_Producto> getPedidoProductos() {
        return pedidoProductos;
    }

    public void setPedidoProductos(List<Pedido_Producto> pedidoProductos) {
        this.pedidoProductos = pedidoProductos;
    }

    public Pedido() {
    }

    public long getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(long numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (numeroPedido ^ (numeroPedido >>> 32));
        result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result + Float.floatToIntBits(precio);
        result = prime * result + ((estado == null) ? 0 : estado.hashCode());
        result = prime * result + ((comprador == null) ? 0 : comprador.hashCode());
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
        Pedido other = (Pedido) obj;
        if (numeroPedido != other.numeroPedido)
            return false;
        if (descripcion == null) {
            if (other.descripcion != null)
                return false;
        } else if (!descripcion.equals(other.descripcion))
            return false;
        if (Float.floatToIntBits(precio) != Float.floatToIntBits(other.precio))
            return false;
        if (estado == null) {
            if (other.estado != null)
                return false;
        } else if (!estado.equals(other.estado))
            return false;
        if (comprador == null) {
            if (other.comprador != null)
                return false;
        } else if (!comprador.equals(other.comprador))
            return false;
        return true;
    }

    public float calcularPrecioTotal() {
        float total = 0;
        for (Pedido_Producto pp : pedidoProductos) {
            total += pp.getProducto().getPrecio() * pp.getCantidad();
        }
        return total;
    }
    
}
