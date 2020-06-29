package com.mitienda.tiendavirtual.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mitienda.tiendavirtual.R;
import com.mitienda.tiendavirtual.adapters.ShopCartAdapter;
import com.mitienda.tiendavirtual.model.Producto;

import java.util.ArrayList;

public class ShopCartFragment extends Fragment {

    private RecyclerView rvCarrito;
    ArrayList<Producto> productos;
    private ShopCartAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_cart, container, false);
        rvCarrito = view.findViewById(R.id.rv_carro_compras);
        rvCarrito.setLayoutManager(new LinearLayoutManager(getContext()));
        productos = new ArrayList<Producto>();
        adapter = new ShopCartAdapter(getContext());

        productos.add(new Producto("asdf","producto1", "asdf", "asdfqwer", 100, 20, "cosasraras", null, 1,"rojo"));
        productos.add(new Producto("asdf","producto2", "asdfqwer", "ljhyt", 200, 20, "cosasmasraras", null, 1,"rojo"));
        productos.add(new Producto("asdf","producto3", "as09idfqwer", "ljhyt", 300, 20, "cosasmasraras", null, 1,"negro"));
        adapter.agregarElementos(productos);
        rvCarrito.setAdapter(adapter );
        return view;
    }
}