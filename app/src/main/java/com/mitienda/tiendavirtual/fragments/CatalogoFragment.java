package com.mitienda.tiendavirtual.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mitienda.tiendavirtual.R;
import com.mitienda.tiendavirtual.adapters.CatalogoAdapter;
import com.mitienda.tiendavirtual.model.CarritoViewModel;
import com.mitienda.tiendavirtual.model.Producto;

import java.util.ArrayList;

public class CatalogoFragment extends Fragment implements CatalogoAdapter.OnItemClickListener ,SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {

    private RecyclerView rvCatalogo;
    ArrayList<Producto> listaProductos;
    private CatalogoAdapter adapter;

    //Firebase
    private FirebaseFirestore dbFireStore = FirebaseFirestore.getInstance();
    private CollectionReference coleccion = dbFireStore.collection("Productos");

    //NavController
    private NavController navController;
    private CarritoViewModel carritoViewModel;

    public CatalogoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalogo, container, false);
        setHasOptionsMenu(true);
        rvCatalogo = view.findViewById(R.id.rv_contenedor_productos);
        rvCatalogo.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        listaProductos = new ArrayList<Producto>();
        getProductosFromFirestore();
        adapter = new CatalogoAdapter(getContext(), this);
        rvCatalogo.setAdapter(adapter);
        return view;
    }
    //consumiendo FireStore
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        carritoViewModel = new ViewModelProvider(requireActivity()).get(CarritoViewModel.class);
    }

    //agregar producto al carrito
    @Override
    public void onItemClick(Producto producto) {
        Toast.makeText(requireContext(), producto.getNombre() + " Agreado al carro", Toast.LENGTH_SHORT).show();
        //Toast.makeText(requireContext(), producto.getId(), Toast.LENGTH_SHORT).show();
        carritoViewModel.agregarProducto(producto);
    }

    //Busqueda
    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText == null || newText.trim().isEmpty()) {
            adapter.agregarElementos(listaProductos);
            return false;
        }
        newText = newText.toLowerCase();
        final ArrayList<Producto> listaFiltrada = new ArrayList<>();
        for (Producto producto : listaProductos) {
            final String nombre = producto.getNombre().toLowerCase();
            final String marca = producto.getMarca().toLowerCase();
            if (nombre.contains(newText) || marca.contains(newText)) {
                listaFiltrada.add(producto);
            }
        }
        adapter.agregarElementos(listaFiltrada);
        return true;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        adapter.agregarElementos(listaProductos);
        return true;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        final  MenuItem searchItem = menu.findItem(R.id.action_seach);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            FloatingActionButton fab = getActivity().findViewById(R.id.fab);
            fab.show();
        } catch (Exception e){  }
    }
}

