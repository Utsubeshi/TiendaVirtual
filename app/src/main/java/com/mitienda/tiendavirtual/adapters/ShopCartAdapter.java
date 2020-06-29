package com.mitienda.tiendavirtual.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mitienda.tiendavirtual.R;
import com.mitienda.tiendavirtual.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ShopCartAdapter extends RecyclerView.Adapter<ShopCartAdapter.ViewHolder> {

    Context context;
    List<Producto> carritoList;

    public ShopCartAdapter(Context context) {
        this.context = context;
        carritoList = new ArrayList<>();
    }

    public void agregarElementos(ArrayList<Producto> data){
        carritoList.clear();
        carritoList.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShopCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopCartAdapter.ViewHolder holder, int position) {
        Producto producto = carritoList.get(position);
        holder.tvNombre.setText(producto.getNombre());
        holder.tvPrecio.setText(String.valueOf(producto.getPrecio()));
        holder.tvMarca.setText(producto.getMarca());
    }

    @Override
    public int getItemCount() {
        return carritoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProducto, ivMasCantidad, ivMenosCantidad;
        TextView tvNombre, tvMarca, tvPrecio, tvCantidad;
        ImageButton btnEliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProducto = itemView.findViewById(R.id.iv_carrito_imagen_producto);
            ivMasCantidad = itemView.findViewById(R.id.iv_carrito_mas_producto);
            ivMenosCantidad = itemView.findViewById(R.id.iv_carrito_menos_producto);
            tvNombre = itemView.findViewById(R.id.tv_carrito_nombre_producto);
            tvMarca = itemView.findViewById(R.id.tv_carrito_marca_producto);
            tvPrecio = itemView.findViewById(R.id.tv_carrito_precio_producto);
            tvCantidad = itemView.findViewById(R.id.tv_carrito_cantidad_producto);
            btnEliminar = itemView.findViewById(R.id.btn_carrito_quitar_producto);
        }
    }
}







