package com.mitienda.tiendavirtual.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.mitienda.tiendavirtual.R;
import com.mitienda.tiendavirtual.firebase.FirebaseDAO;
import com.mitienda.tiendavirtual.model.UserViewModel;
import com.mitienda.tiendavirtual.model.Usuario;

import java.util.regex.Pattern;

public class UserUpdateCheckoutFragment extends Fragment {

    private TextInputLayout etNombres, etPaterno, etMaterno, etDNI, etCelular, etDireccion;
    private Button btnActualizarDatos;
    private FirebaseDAO firebaseDAO;
    private Context context = getContext();
    private Usuario usuario;
    private UserViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_update_checkout, container, false);
        etNombres = view.findViewById(R.id.ti_chk_nombres);
        etPaterno = view.findViewById(R.id.ti_chk_paterno);
        etMaterno = view.findViewById(R.id.ti_chk_materno);
        etDNI = view.findViewById(R.id.ti_chk_dni);
        etCelular = view.findViewById(R.id.ti_chk_celular);
        firebaseDAO = new FirebaseDAO();
        etDireccion = view.findViewById(R.id.ti_chk_direccion);
        btnActualizarDatos = view.findViewById(R.id.btn_update_user_chk);
        mostrarDatosDelUsuario();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        final NavController navController = Navigation.findNavController(view);
        btnActualizarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCampos(navController);
            }
        });
    }

     public void mostrarDatosDelUsuario () {
         final DocumentReference usuario = firebaseDAO.getUserFromDatabase();
         usuario.addSnapshotListener(new EventListener<DocumentSnapshot>() {
             @Override
             public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                 etNombres.getEditText().setText(documentSnapshot.getString("nombres"));
                 etPaterno.getEditText().setText(documentSnapshot.getString("paterno"));
                 etMaterno.getEditText().setText(documentSnapshot.getString("materno"));
                 etCelular.getEditText().setText(documentSnapshot.getString("telefono"));
                 etDNI.getEditText().setText(documentSnapshot.getString("dni"));
                 etDireccion.getEditText().setText(documentSnapshot.getString("direccion"));
             }
         });
     }

     public void validarCampos(NavController navController){
        usuario = new Usuario();
         String nombre = etNombres.getEditText().getText().toString().trim();
         String paterno = etPaterno.getEditText().getText().toString().trim();
         String materno = etMaterno.getEditText().getText().toString().trim();
         String dni = etDNI.getEditText().getText().toString().trim();
         String telefono = etCelular.getEditText().getText().toString().trim();
         String direccion = etDireccion.getEditText().getText().toString().trim();

         boolean a = esNombreValido(nombre);
         boolean b = esNombreValido(paterno);
         boolean c = esNombreValido(materno);
         boolean d = esDNIValido(dni);
         boolean e = esTelefonoValido(telefono);
         boolean f = esDireccionValida(direccion);

         if (a && b && c && d && e && f){
             usuario = new Usuario(nombre, paterno, materno, dni, telefono,direccion);
             firebaseDAO.actualizarUsuario(usuario);
             Handler handler;
             Runnable runnable;
             runnable = new Runnable() {
                 @Override
                 public void run() {
                     navController.navigate(R.id.nav_checkout);
                 }
             };
             handler = new Handler();
             handler.postDelayed(runnable, 2000);
         }
     }

    private boolean esNombreValido(String nombre) {
        //Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        Pattern patron = Pattern.compile("^[0-9A-Za-zñÑáéíóúÁÉÍÓÚ ]+$");
        if (!patron.matcher(nombre).matches() || nombre.length() > 30) {
            etNombres.setError("Nombre inválido");
            return false;
        } else {
            etNombres.setError(null);
        }
        return true;
    }

    private boolean esTelefonoValido(String telefono) {
        if (!Patterns.PHONE.matcher(telefono).matches()) {
            etCelular.setError("Teléfono inválido");
            return false;
        } else {
            etCelular.setError(null);
        }
        return true;
    }

    private boolean esDNIValido(String dni) {
        if (!Patterns.PHONE.matcher(dni).matches() || dni.length() != 8 ) {
            etDNI.setError("DNI inválido");
            return false;
        } else {
            etDNI.setError(null);
        }
        return true;
    }

    private boolean esDireccionValida(String direccion) {
        if (direccion.isEmpty()) {
            etDireccion.setError("DNI inválido");
            return false;
        } else {
            etDireccion.setError(null);
        }
        return true;
    }
}