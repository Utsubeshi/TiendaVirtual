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
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mitienda.tiendavirtual.R;
import com.mitienda.tiendavirtual.adapters.CatalogoAdapter;
import com.mitienda.tiendavirtual.model.Producto;
import com.mitienda.tiendavirtual.model.SharedViewModel;
import com.squareup.picasso.Picasso;

public class DetalleProductoFragment extends Fragment implements View.OnClickListener , CatalogoAdapter.OnItemClickListener {

    private ImageView ivAumentar, ivReducir, ivProducto;
    private TextView tvCantidad, tvNombre, tvCategoria, tvDetalle, tvColor, tvPrecio ;
    private MaterialButton btnAddToCart;
    int contador = 1;
    private Producto producto;

    SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_producto, container, false);
        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.hide();
        ivAumentar = view.findViewById(R.id.iv_carrito_mas_producto);
        ivReducir = view.findViewById(R.id.iv_carrito_menos_producto);
        ivProducto = view.findViewById(R.id.iv_imagen_producto);
        ivAumentar.setOnClickListener(this);
        ivReducir.setOnClickListener(this);
        tvCantidad = view.findViewById(R.id.tv_carrito_cantidad_producto);
        tvNombre = view.findViewById(R.id.tv_titulo_detalle_prducto);
        tvCategoria = view.findViewById(R.id.tv_categoria_detalle_producto);
        tvDetalle = view.findViewById(R.id.tv_detalle_prducto);
        tvColor = view.findViewById(R.id.tv_color_prducto);
        tvPrecio = view.findViewById(R.id.tv_precio_producto);
        btnAddToCart = view.findViewById(R.id.btn_add);
        btnAddToCart.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null){
            //recuperar producto del bundle
            DetalleProductoFragmentArgs args = DetalleProductoFragmentArgs.fromBundle(getArguments());
            producto = args.getProducto();
            //llenar las vistas
            updateUI();
            sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        }
    }

    private void updateUI() {
        String titulo = producto.getMarca() + " " + producto.getNombre();
        Picasso.get().load(producto.getUrlImagen()).into(ivProducto);
        tvNombre.setText(titulo);
        tvColor.setText(producto.getColor());
        String detalle = producto.getDetalle();
        String detalleModificado = detalle.replace("; ", "\n");
        tvDetalle.setText(detalleModificado);
        tvCategoria.setText(producto.getCategoria());
        String precio = "S/ " + producto.getPrecio();
        tvPrecio.setText(precio);
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
            //aumentar valor al contador
            case (R.id.iv_carrito_mas_producto):
                if (contador < 3) {
                    contador++;
                    tvCantidad.setText(String.valueOf(contador));
                }
                break;
            case (R.id.iv_carrito_menos_producto):
                //reducir valor al contador
                if (contador > 1) {
                    contador--;
                    tvCantidad.setText(String.valueOf(contador));
                }
                break;
            case (R.id.btn_add):
               // Mandar producto al sharedmodel
                producto.setCantidad(contador);
                onItemClick(producto);
            default:
                break;
        }

    }

    //mandar producto al sharedModel
    @Override
    public void onItemClick(Producto producto) {
        Toast.makeText(requireContext(), producto.getNombre() + " Agreado al carro", Toast.LENGTH_SHORT).show();
        sharedViewModel.agregarProducto(producto);
    }
}