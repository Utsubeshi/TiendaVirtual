package com.mitienda.tiendavirtual.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.Exclude;

public class Producto implements Parcelable {

    private String id;
    private String nombre;
    private String detalle;
    private String marca;
    private double precio;
    private int stock;
    private String categoria;
    private String urlImagen;
    private int cantidad;
    private String color;

    public Producto() {
    }

    public Producto(String id, String nombre, String detalle, String marca, double precio, int stock, String categoria, String urlImagen, int cantidad, String color) {
        this.id = id;
        this.nombre = nombre;
        this.detalle = detalle;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.urlImagen = urlImagen;
        this.cantidad = cantidad;
        this.color = color;
    }

    protected Producto(Parcel in) {
        id = in.readString();
        nombre = in.readString();
        detalle = in.readString();
        marca = in.readString();
        precio = in.readDouble();
        stock = in.readInt();
        categoria = in.readString();
        urlImagen = in.readString();
        cantidad = in.readInt();
        color = in.readString();
    }

    public static final Creator<Producto> CREATOR = new Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    @Exclude
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nombre);
        dest.writeString(detalle);
        dest.writeString(marca);
        dest.writeDouble(precio);
        dest.writeInt(stock);
        dest.writeString(categoria);
        dest.writeString(urlImagen);
        dest.writeInt(cantidad);
        dest.writeString(color);

    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", marca='" + marca + '\'' +
                ", precio=" + precio +
                '}';
    }
}
