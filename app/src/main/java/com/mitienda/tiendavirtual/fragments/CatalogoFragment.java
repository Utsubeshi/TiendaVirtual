package com.mitienda.tiendavirtual.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mitienda.tiendavirtual.R;
import com.mitienda.tiendavirtual.adapters.CatalogoAdapter;
import com.mitienda.tiendavirtual.model.Producto;

import java.util.ArrayList;

public class CatalogoFragment extends Fragment  {

    private RecyclerView rvCatalogo;
    ArrayList<Producto> listaProductos;
    private CatalogoAdapter adapter;

    //Firebase
    private FirebaseFirestore dbFireStore = FirebaseFirestore.getInstance();
    private CollectionReference coleccion = dbFireStore.collection("Productos");

    public CatalogoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalogo, container, false);
        rvCatalogo = view.findViewById(R.id.rv_contenedor_productos);
        rvCatalogo.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        listaProductos = new ArrayList<Producto>();
        /*listaProductos.add(new Producto("Zapatilla 1", 200, R.drawable.producto01));
        listaProductos.add(new Producto("Zapatilla 2   200, R.drawable.producto03));
        listaProductos.add(new Producto("Zapatilla 4", 200, R.drawable.producto04));
        listaProductos.add(new Producto("Zapatilla 5", 200, R.drawable.producto05));
        listaProductos.add(new Producto("Zapatilla 6", 200, R.drawable.producto06));
        listaProductos.add(new Producto("Zapatilla 7", 200, R.drawable.producto07));*/
        getProductosFromFirestore();
        adapter = new CatalogoAdapter(getContext());
        rvCatalogo.setAdapter(adapter);
        return view;
    }

    public void getProductosFromFirestore(){
        coleccion.addSnapshotListener( new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e!=null){
                    return;
                }
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    Producto producto = documentSnapshot.toObject(Producto.class);
                    listaProductos.add(producto);
                }
                adapter.agregarElementos(listaProductos);
            }
        });

    }

}

