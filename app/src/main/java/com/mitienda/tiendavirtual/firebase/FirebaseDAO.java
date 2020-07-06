package com.mitienda.tiendavirtual.firebase;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mitienda.tiendavirtual.model.UserViewModel;
import com.mitienda.tiendavirtual.model.Usuario;

import java.util.HashMap;
import java.util.Map;

public class FirebaseDAO {

    //firebase
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private String idUsuario;
    CollectionReference usuarios = fStore.collection("Usuarios");
    CollectionReference productos = fStore.collection("Productos");
    CollectionReference pedidos = fStore.collection("Pedidos");


    //Enum
    private static final String KEY_NOMBRE = "nombres";
    private static final String KEY_PATERNO = "paterno";
    private static final String KEY_MATERNO = "materno";
    private static final String KEY_DNI = "dni";
    private static final String KEY_TELEFONO = "telefono";
    private static final String KEY_IDUSER ="idUser";
    private static final String KEY_DIRECION ="direccion";


    public FirebaseDAO() {
        idUsuario = firebaseAuth.getCurrentUser().getUid();
    }


    public void guardarUsuario(Usuario user) {
        Map<String, Object> usuario = new HashMap<>();
        usuario.put(KEY_NOMBRE, user.getNombres());
        usuario.put(KEY_PATERNO, user.getPaterno());
        usuario.put(KEY_MATERNO, user.getMaterno());
        usuario.put(KEY_DNI, user.getDNI());
        usuario.put(KEY_TELEFONO, user.getTelefono());
        usuario.put(KEY_DIRECION, user.getDireccion());
        usuarios.document(idUsuario).set(usuario);
    }

    public void actualizarUsuario(Usuario usuario) {
        final DocumentReference documentReference = usuarios.document(idUsuario);
        documentReference.update(KEY_NOMBRE, usuario.getNombres());
        documentReference.update(KEY_PATERNO, usuario.getPaterno());
        documentReference.update(KEY_MATERNO, usuario.getMaterno());
        documentReference.update(KEY_DNI, usuario.getDNI());
        documentReference.update(KEY_TELEFONO, usuario.getTelefono());
        documentReference.update(KEY_DIRECION, usuario.getDireccion()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                UserViewModel.usuarioEstado.setValue(UserViewModel.UsuarioEstado.DATOS_COMPLETOS);
            }
        });
    }

    public DocumentReference getUserFromDatabase(){
        return usuarios.document(idUsuario);
    }
}
