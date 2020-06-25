package com.mitienda.tiendavirtual.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mitienda.tiendavirtual.R;

public class DetalleProductoFragment extends Fragment implements View.OnClickListener {

    private ImageView ivAumentar, ivReducir;
    private TextView tvCantidad, tvNombre;
    int contador = 1;

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
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        //fab.hide();
    }

    @Override
    public void onClick(View v) {
        contador = Integer.parseInt(tvCantidad.getText().toString());
        switch (v.getId()){
            case (R.id.iv_mas_producto):
                if (contador < 3) {
                    contador++;
                    tvCantidad.setText(String.valueOf(contador));
                    //Toast.makeText(getActivity(), "" + contador, Toast.LENGTH_SHORT).show();
                }
                break;
            case (R.id.iv_menos_producto):
                if (contador > 1) {
                    contador--;
                    tvCantidad.setText(String.valueOf(contador));
                    //Toast.makeText(getActivity(), "" + contador, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }
}