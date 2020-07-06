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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.mitienda.tiendavirtual.R;
import com.mitienda.tiendavirtual.firebase.FirebaseDAO;
import com.mitienda.tiendavirtual.model.CarritoViewModel;
import com.mitienda.tiendavirtual.model.Producto;
import com.mitienda.tiendavirtual.model.UserViewModel;
import com.mitienda.tiendavirtual.model.Usuario;

import java.util.ArrayList;

public class CheckoutFragment extends Fragment implements View.OnClickListener {

    UserViewModel viewModel;
    FirebaseDAO firebaseDAO;
    private CarritoViewModel carritoViewModel;
    private TextView tvNombre, tvDireccion, tvCelular, tvDni, tvSubtotal, tvTotal;
    private Button btnEditarDatos, btnContinuar;
    ArrayList<Producto> productos;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);
        tvNombre = view.findViewById(R.id.tv_cliente_nombre);
        tvDireccion = view.findViewById(R.id.tv_cliente_direccion);
        tvCelular = view.findViewById(R.id.tv_cliente_celular);
        tvDni = view.findViewById(R.id.tv_cliente_dni);
        tvSubtotal = view.findViewById(R.id.tv_subtotal_pedido_chk);
        tvTotal = view.findViewById(R.id.tv_total_a_pagar);
        btnEditarDatos = view.findViewById(R.id.btn_editar_user);
        btnContinuar = view.findViewById(R.id.btn_confirmar);
        btnEditarDatos.setOnClickListener(this);
        btnContinuar.setOnClickListener(this);
        firebaseDAO = new FirebaseDAO();
        carritoViewModel = new ViewModelProvider(requireActivity()).get(CarritoViewModel.class);
        productos = new ArrayList<Producto>();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        //final NavController navController = Navigation.findNavController(view);
        navController = Navigation.findNavController(view);
        //validar datos del cliente
        viewModel.usuarioEstado.observe(getViewLifecycleOwner(),
                new Observer<UserViewModel.UsuarioEstado>() {
                    @Override
                    public void onChanged(UserViewModel.UsuarioEstado usuarioEstado) {
                        switch (usuarioEstado) {
                            case DATOS_COMPLETOS:
                                Toast.makeText(getContext(), "Datos del usuario validados", Toast.LENGTH_SHORT).show();
                                getClienteData();
                                calcularTotal();
                                break;
                            case FALTAN_DATOS:
                                navController.navigate(R.id.nav_userupdate_checkout);
                                break;
                        }
                    }
                }
        );
    }

    public void getClienteData() {
        DocumentReference usuario = firebaseDAO.getUserFromDatabase();
        usuario.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                assert documentSnapshot != null;
                Usuario cliente = documentSnapshot.toObject(Usuario.class);
                StringBuilder nombreCompleto = new StringBuilder();
                nombreCompleto.append(cliente.getNombres()).append(" ").append(cliente.getPaterno()).append(" ").append(cliente.getMaterno());
                tvNombre.setText(nombreCompleto);
                tvDireccion.setText(cliente.getDireccion());
                tvCelular.setText(cliente.getTelefono());
                tvDni.setText(cliente.getDNI());
            }
        });
    }

    public void calcularTotal () {
        int subTotal = 0;
        productos = (ArrayList<Producto>) carritoViewModel.getProductosAgregados();
        for (Producto producto : productos){
            double precioPorProducto = producto.getCantidad() * producto.getPrecio();
            subTotal += precioPorProducto;
        }
        String subtotal = "S/ " + String.valueOf(subTotal);
        tvSubtotal.setText(subtotal);
        String total = "Total a pagar: S/ " + (subTotal + 15) ;
        tvTotal.setText(total);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case (R.id.btn_editar_user) :
                navController.navigate(R.id.nav_userupdate_checkout);
                break;
            default:
                break;
        }

    }
}