package com.mitienda.tiendavirtual.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mitienda.tiendavirtual.R;
import com.mitienda.tiendavirtual.model.Producto;

public class DetalleProductoFragment extends Fragment implements View.OnClickListener {

    private ImageView ivAumentar, ivReducir;
    private TextView tvCantidad, tvNombre;
    private MaterialButton btnAddToCart;
    int contador = 1;
    private Producto producto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_producto, container, false);
        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.hide();
        ivAumentar = view.findViewById(R.id.iv_mas_producto);
        ivReducir = view.findViewById(R.id.iv_menos_producto);
        ivAumentar.setOnClickListener(this);
        ivReducir.setOnClickListener(this);
        tvCantidad = view.findViewById(R.id.tv_cantidad_producto);
        tvNombre = view.findViewById(R.id.tv_titulo_detalle_prducto);
        btnAddToCart = view.findViewById(R.id.btn_detalle_add_cart);
        btnAddToCart.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null){
            DetalleProductoFragmentArgs args = DetalleProductoFragmentArgs.fromBundle(getArguments());
            producto = args.getProducto();
            tvNombre.setText(producto.getNombre());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.show();
    }

    @Override
    public void onClick(View v) {
        contador = Integer.parseInt(tvCantidad.getText().toString());
        switch (v.getId()){
            case (R.id.iv_mas_producto):
                if (contador < 3) {
                    contador++;
                    tvCantidad.setText(String.valueOf(contador));
                }
                break;
            case (R.id.iv_menos_producto):
                if (contador > 1) {
                    contador--;
                    tvCantidad.setText(String.valueOf(contador));
                }
                break;
            case (R.id.btn_detalle_add_cart):
                //TODO agregar producto al carro y su cantidad
                Toast.makeText(getContext(), "Producto: " + producto.getNombre() + " Cantidad: " + contador, Toast.LENGTH_SHORT).show();
            default:
                break;
        }

    }
}