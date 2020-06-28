package com.mitienda.tiendavirtual.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Producto implements Parcelable {

    private int id;
    private String nombre;
    private String descripcion;
    private String marca;
    private double precio;
    private double precioCosto;
    private int stock;
    private int stockMinimo;
    private String categoria;
    private String urlImagen;
    private String urlImagenCatalogo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //
    private int imagenTest;

    public Producto() {
    }

    public Producto(String nombre, double precio, int imagenTest) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagenTest = imagenTest;
    }

    public Producto(int id, String nombre, String descripcion, String marca, double precio, double precioCosto, int stock, int stockMinimo, String categoria, String urlImagen, String urlImagenCatalogo, int imagenTest) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.marca = marca;
        this.precio = precio;
        this.precioCosto = precioCosto;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.categoria = categoria;
        this.urlImagen = urlImagen;
        this.urlImagenCatalogo = urlImagenCatalogo;
        this.imagenTest = imagenTest;
    }

    protected Producto(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        descripcion = in.readString();
        marca = in.readString();
        precio = in.readDouble();
        precioCosto = in.readDouble();
        stock = in.readInt();
        stockMinimo = in.readInt();
        categoria = in.readString();
        urlImagen = in.readString();
        urlImagenCatalogo = in.readString();
        imagenTest = in.readInt();
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

    public int getImagenTest() {
        return imagenTest;
    }

    public void setImagenTest(int imagenTest) {
        this.imagenTest = imagenTest;
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

    public double getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(double precioCosto) {
        this.precioCosto = precioCosto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
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

    public String getUrlImagenCatalogo() {
        return urlImagenCatalogo;
    }

    public void setUrlImagenCatalogo(String urlImagenCatalogo) {
        this.urlImagenCatalogo = urlImagenCatalogo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeString(marca);
        dest.writeDouble(precio);
        dest.writeDouble(precioCosto);
        dest.writeInt(stock);
        dest.writeInt(stockMinimo);
        dest.writeString(categoria);
        dest.writeString(urlImagen);
        dest.writeString(urlImagenCatalogo);
        dest.writeInt(imagenTest);
    }
}
