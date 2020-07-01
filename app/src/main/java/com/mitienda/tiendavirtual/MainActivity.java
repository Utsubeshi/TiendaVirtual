package com.mitienda.tiendavirtual;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.mitienda.tiendavirtual.fragments.CatalogoFragmentDirections;
import com.mitienda.tiendavirtual.model.Producto;
import com.mitienda.tiendavirtual.model.SharedViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private TextView tvEmailUsuario;
    FloatingActionButton fab;
    private NavController navController;
    private SharedViewModel sharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        NavigationView navigationView = findViewById(R.id.nav_view);
        View navView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
        TextView tv = (TextView)navView.findViewById(R.id.tv_user_email_main);
        //agregar datos del usuario al drawer
        tv.setText(firebaseAuth.getCurrentUser().getEmail());
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_mispedidos, R.id.nav_catalogo, R.id.shopCartFragment)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        fab.setOnClickListener(new View.OnClickListener() {
            //Abrir y validar el carrito
            @Override
            public void onClick(View view) {
                List<Producto> carrito = sharedViewModel.getProductosAgregados();
                if (carrito == null || carrito.size() == 0) {
                    Toast.makeText(MainActivity.this, "No hay productos en el carrito", Toast.LENGTH_SHORT).show();
                    return;
                }
                NavDirections action = CatalogoFragmentDirections.actionNavCatalogoToShopCartFragment();
                navController.navigate(action);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_logout){
            logOut();
            startActivity(new Intent(this, LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    //Cerrar sesion
    public void logOut() {
        FirebaseAuth.getInstance().signOut();
        GoogleSignIn.getClient(getApplicationContext(),new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build())
                .signOut().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,"Sign Out Fallido",Toast.LENGTH_SHORT);
            }
        });
        finish();
    }
}