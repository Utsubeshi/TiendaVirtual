package com.mitienda.tiendavirtual.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {

    public static final String TAG = "SharedViewModel";
    //lista del carrito
    private MutableLiveData<List<Producto>> mutableCarritoList = new MutableLiveData<>();
    private MutableLiveData<Producto> mutableProducto = new MutableLiveData<>();

    public LiveData<List<Producto>> getCarritoList() {
        return  mutableCarritoList;
    }

    public LiveData<Producto> getProducto() {
        return mutableProducto;
    }

    public void setMovie(int position) {
        Producto producto = mutableCarritoList.getValue().get(position);
        mutableProducto.setValue(producto);
    }

    public List<Producto> getProductosAgregados () {
        List<Producto> list = mutableCarritoList.getValue();
        return list;
    }

    public void agregarProducto (Producto producto) {
        if (mutableCarritoList.getValue() != null) {
            List<Producto> list = mutableCarritoList.getValue();
            list.add(producto);
            mutableCarritoList.setValue(list);
            return;
        }
        List<Producto> list = new ArrayList<>();
        list.add(producto);
        mutableCarritoList.setValue(list);
    }

    public void borrarProducto(int position) {
        if (mutableCarritoList.getValue() != null) {
            List<Producto> list = mutableCarritoList.getValue();
            list.remove(position);
            mutableCarritoList.setValue(list);
            return;
        }
        List<Producto> list = new ArrayList<>();
        list.remove(position);
        mutableCarritoList.setValue(list);
    }

    public void aumentarCantidad(int position) {
        List<Producto> list = mutableCarritoList.getValue();
        Producto producto = list.get(position);
        producto.setCantidad(producto.getCantidad() + 1);
        list.set(position, producto);
        mutableCarritoList.setValue(list);
    }

    public void reducirCantidad(int position) {
        List<Producto> list = mutableCarritoList.getValue();
        Producto producto = list.get(position);
        producto.setCantidad(producto.getCantidad() - 1);
        list.set(position, producto);
        mutableCarritoList.setValue(list);

    }
}
