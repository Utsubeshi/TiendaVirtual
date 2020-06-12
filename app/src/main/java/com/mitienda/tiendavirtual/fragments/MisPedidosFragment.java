package com.mitienda.tiendavirtual.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mitienda.tiendavirtual.R;

public class MisPedidosFragment extends Fragment {

  /*  public MisPedidosFragment() {
        // Required empty public constructor
    }

    public static MisPedidosFragment newInstance(String param1, String param2) {
        MisPedidosFragment fragment = new MisPedidosFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mis_pedidos, container, false);
    }
}