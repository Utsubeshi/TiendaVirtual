package com.mitienda.tiendavirtual.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.mitienda.tiendavirtual.firebase.FirebaseDAO;

public class UserViewModel extends ViewModel {

    final public static MutableLiveData<UsuarioEstado> usuarioEstado = new MutableLiveData<>();
    private FirebaseDAO firebaseDAO;

    public enum UsuarioEstado{
        DATOS_COMPLETOS,
        FALTAN_DATOS
    }

    public UserViewModel() {
        firebaseDAO = new FirebaseDAO();
        DocumentReference usuario = firebaseDAO.getUserFromDatabase();
        usuario.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Usuario user = documentSnapshot.toObject(Usuario.class);
                if (user.getNombres() == null || user.getNombres().isEmpty()) {
                    usuarioEstado.setValue(UsuarioEstado.FALTAN_DATOS);
                    return;
                }
                if ( user.getPaterno() == null || user.getPaterno().isEmpty()){
                    usuarioEstado.setValue(UsuarioEstado.FALTAN_DATOS);
                    return;
                }
                if (user.getMaterno() == null || user.getMaterno().isEmpty() ){
                    usuarioEstado.setValue(UsuarioEstado.FALTAN_DATOS);
                    return;
                }
                if (user.getDNI() == null || user.getDNI().isEmpty()){
                    usuarioEstado.setValue(UsuarioEstado.FALTAN_DATOS);
                    return;
                }
                if (user.getTelefono() == null || user.getTelefono().isEmpty()){
                    usuarioEstado.setValue(UsuarioEstado.FALTAN_DATOS);
                    return;
                }
                if (user.getDireccion() == null || user.getDireccion().isEmpty()){
                    usuarioEstado.setValue(UsuarioEstado.FALTAN_DATOS);
                    return;
                }
                usuarioEstado.setValue(UsuarioEstado.DATOS_COMPLETOS);
            }
        });
    }


}
