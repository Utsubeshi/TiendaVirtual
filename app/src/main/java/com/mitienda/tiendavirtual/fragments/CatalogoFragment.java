package com.mitienda.tiendavirtual.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;
import android.widget.TextView;

import com.mitienda.tiendavirtual.R;
import com.mitienda.tiendavirtual.adapters.CatalogoAdapter;
import com.mitienda.tiendavirtual.model.Producto;

import java.util.ArrayList;

public class CatalogoFragment extends Fragment {

    private RecyclerView rvCatalogo;
    ArrayList<Producto> listaProductos;
    private CatalogoAdapter adapter;

    public CatalogoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalogo, container, false);
        rvCatalogo = view.findViewById(R.id.rv_contenedor_productos);
        rvCatalogo.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //return inflater.inflate(R.layout.fragment_catalogo, container, false);
        listaProductos = new ArrayList<Producto>();
        listaProductos.add(new Producto("Zapatilla 1", 200, R.drawable.producto01));
        listaProductos.add(new Producto("Zapatilla 1", 200, R.drawable.producto02));
        listaProductos.add(new Producto("Zapatilla 1", 200, R.drawable.producto03));
        listaProductos.add(new Producto("Zapatilla 1", 200, R.drawable.producto04));
        listaProductos.add(new Producto("Zapatilla 1", 200, R.drawable.producto05));
        listaProductos.add(new Producto("Zapatilla 1", 200, R.drawable.producto06));
        listaProductos.add(new Producto("Zapatilla 1", 200, R.drawable.producto07));
        rvCatalogo.setAdapter(new CatalogoAdapter(getContext(),listaProductos));

        return view;
    }
}