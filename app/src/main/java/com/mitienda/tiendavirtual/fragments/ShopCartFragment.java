package com.mitienda.tiendavirtual.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mitienda.tiendavirtual.R;
import com.mitienda.tiendavirtual.adapters.ShopCartAdapter;
import com.mitienda.tiendavirtual.firebase.FirebaseDAO;
import com.mitienda.tiendavirtual.model.CarritoViewModel;
import com.mitienda.tiendavirtual.model.Producto;

import java.util.ArrayList;

public class ShopCartFragment extends Fragment implements ShopCartAdapter.OnItemClickListener {

    private RecyclerView rvCarrito;
    ArrayList<Producto> productos;
    private ShopCartAdapter adapter;
    private NavController navController;
    private CarritoViewModel carritoViewModel;
    private TextView tvSubTotal, tvCantidadProductos;
    private double subTotal;
    private Button btnCheckout;

    FirebaseDAO firebaseDAO;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_cart, container, false);
        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.hide();
        rvCarrito = view.findViewById(R.id.rv_carro_compras);
        rvCarrito.setLayoutManager(new LinearLayoutManager(getContext()));
        tvSubTotal = view.findViewById(R.id.tv_subtotal_pedido);
        tvCantidadProductos = view.findViewById(R.id.tv_cantidad_pedido);
        btnCheckout = view.findViewById(R.id.btn_checkout);
        firebaseDAO = new FirebaseDAO();
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                final NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.nav_checkout);
            }
        });

        productos = new ArrayList<Producto>();
        adapter = new ShopCartAdapter(getContext(), this);
        rvCarrito.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        carritoViewModel = new ViewModelProvider(requireActivity()).get(CarritoViewModel.class);
        updateUI();
    }

    @Override
    public void borrarProducto(int position) {
        Toast.makeText(getContext(), "Producto eliminado", Toast.LENGTH_SHORT).show();
        carritoViewModel.borrarProducto(position);
        adapter.agregarElementos(productos);
        calcularSubtotal();
    }

    @Override
    public void aumentarCantidad(int position) {
        carritoViewModel.aumentarCantidad(position);
        updateUI();
    }

    @Override
    public void reducirCantidad(int position) {
        carritoViewModel.reducirCantidad(position);
        updateUI();
    }

    private void updateUI() {
        productos = (ArrayList<Producto>) carritoViewModel.getProductosAgregados();
        adapter.agregarElementos(productos);
        calcularSubtotal();
    }

    public void calcularSubtotal () {
        subTotal = 0;
        int cantidadTotal = 0;
        for (Producto producto : productos){
            double precioPorProducto = producto.getCantidad() * producto.getPrecio();
            cantidadTotal += producto.getCantidad();
            subTotal += precioPorProducto;
        }
        String subtotal = "Subtotal: S/ " + String.valueOf(subTotal);
        tvSubTotal.setText(subtotal);
        String cantidadCarrito = "Productos en el carro: " + cantidadTotal;
        tvCantidadProductos.setText(cantidadCarrito);
    }
    }

