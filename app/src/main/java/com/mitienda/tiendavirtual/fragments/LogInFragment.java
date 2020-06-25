package com.mitienda.tiendavirtual.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mitienda.tiendavirtual.MainActivity;
import com.mitienda.tiendavirtual.R;

import static androidx.navigation.Navigation.findNavController;

public class LogInFragment extends Fragment {

    public static final int GOOGLE_SIGN_IN_CODE = 10005;
    private TextInputEditText etEmail, etPassword;
    private Button btnLogin;
    TextView tvRegistar, tvRecuperarPass;

    //Firebase
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    String userID;

    //Google Sign In
    SignInButton btnsignInGoogle;
    GoogleSignInOptions gso;
    GoogleSignInClient signInClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);

        etEmail = view.findViewById(R.id.et_username_login);
        etPassword = view.findViewById(R.id.et_login_password);
        btnLogin = view.findViewById(R.id.btn_add_cart);
        tvRegistar = view.findViewById(R.id.btn_registrarse);
        tvRecuperarPass = view.findViewById(R.id.tv_recuperar_pass);
        btnsignInGoogle = view.findViewById(R.id.btn_google_login);
        fAuth = FirebaseAuth.getInstance();

        //Construyendo datos de Google Sign In Options
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("611705661195-ru9r934ca7di21h2elkkgftnakh9g5e3.apps.googleusercontent.com")
                .requestEmail()
                .build();

        signInClient = GoogleSignIn.getClient(getContext(),gso);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(getContext());
        if(signInAccount != null || fAuth.getCurrentUser() != null){
            startActivity(new Intent(getContext(), MainActivity.class));
        }

        btnsignInGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign = signInClient.getSignInIntent();
                startActivityForResult(sign, GOOGLE_SIGN_IN_CODE);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciarSesion();
            }
        });

        tvRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNavController(v).navigate(R.id.action_nav_login_fragment_to_signInFragment);
            }
        });

        tvRecuperarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNavController((v)).navigate(R.id.action_nav_login_fragment_to_passwordRecoveryFragment);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //iniciar session con google
        if(requestCode == GOOGLE_SIGN_IN_CODE){
            Task<GoogleSignInAccount> signInTask = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount signInAcc = signInTask.getResult(ApiException.class);
                AuthCredential authCredential = GoogleAuthProvider.getCredential(signInAcc.getIdToken(), null);
                fAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(getActivity(), "Tu cuenta de Google ha sido conectada", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Fallo la wea", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    public void IniciarSesion(){
        final String correo = etEmail.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        //Validando campos
        if(TextUtils.isEmpty(correo)){
            etEmail.setError("Correo requerido");
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
        //Autenticación de Usuarios con pass y correo
        fAuth.signInWithEmailAndPassword(correo, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getContext(), "Bienvenido!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(),MainActivity.class));
                } else {
                    Toast.makeText(getContext(),"Error: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}