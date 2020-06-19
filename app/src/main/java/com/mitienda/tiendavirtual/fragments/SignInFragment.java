package com.mitienda.tiendavirtual.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mitienda.tiendavirtual.MainActivity;
import com.mitienda.tiendavirtual.R;

import java.util.HashMap;
import java.util.Map;

public class SignInFragment extends Fragment {

    private TextInputEditText etEmail, etNombre, etPassword, etPassword2;
    private Button btnRegistrar;
    String userID;

    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        etEmail = view.findViewById(R.id.et_email_registro);
        etNombre = view.findViewById(R.id.et_nombre_registro);
        etPassword = view.findViewById(R.id.et_password_registro);
        etPassword2 = view.findViewById(R.id.et_password2_registro);
        btnRegistrar = view.findViewById(R.id.btn_registrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarNuevoUsuario();
            }
        });
        return view;
    }

    public void registrarNuevoUsuario(){
        final String nombre = etNombre.getText().toString().trim();
        final String correo = etEmail.getText().toString().trim();
        final String password  = etPassword.getText().toString().trim();
        final String password2  = etPassword2.getText().toString().trim();

        if(TextUtils.isEmpty(correo)){
            etEmail.setError("Ingrese un correo valido");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            etEmail.setError("Ingrese un correo valido");
            return;
        }
        if(TextUtils.isEmpty(nombre)){
            etNombre.setError("Nombre requerido");
            return;
        }
        if(TextUtils.isEmpty(password)){
            etPassword.setError("Contraseña requerida");
            return;
        }
        if(password.length() < 6){
            etPassword.setError("Contraseña debe tener al menos 6 caracteres");
            return;
        }
        if(!password.equals(password2)){
            etPassword.setText("");
            etPassword2.setText("");
            etPassword.requestFocus();
            etPassword.setError("La contraseña no coincide, intentelo nuevamente");
            return;
        }

        fAuth.createUserWithEmailAndPassword(correo,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getActivity(),"Usuario creado",Toast.LENGTH_SHORT).show();
                    userID = fAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = fStore.collection("Usuarios").document(userID);
                    Map<String,Object> user = new HashMap<>();
                    user.put("Nombre", nombre);
                    user.put("Email", correo);
                    user.put("Password",password);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            //TODO
                        }
                    });
                    startActivity(new Intent(getActivity(), MainActivity.class));
                } else {
                    Toast.makeText(getActivity(), "Hubo un error - intentelo despues", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}