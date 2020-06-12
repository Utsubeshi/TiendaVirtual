package com.mitienda.tiendavirtual.model;

public class Producto {

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

    //
    private int imagenTest;

    public Producto() {
    }

    public Producto(String nombre, double precio, int imagenTest) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagenTest = imagenTest;
    }

    public Producto(String nombre, String descripcion, String marca, double precio, double precioCosto, int stock, int stockMinimo, String categoria, String urlImagen, String urlImagenCatalogo, int imagenTest) {
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
}
