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
import com.mitienda.tiendavirtual.fragments.ButtonHightLighterOnTouch;
import com.mitienda.tiendavirtual.model.Producto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShopCartAdapter extends RecyclerView.Adapter<ShopCartAdapter.ViewHolder> {

    Context context;
    List<Producto> carritoList;
    OnItemClickListener onItemClickListener;

    public ShopCartAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
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
        Picasso.get().load(producto.getUrlImagen()).into(holder.ivProducto);
        String nombre = producto.getMarca() + " " + producto.getNombre();
        holder.tvNombre.setText(nombre);
        String precio = "S/ " + (producto.getPrecio() * producto.getCantidad());
        holder.tvPrecio.setText(precio);
        holder.tvMarca.setText(producto.getCategoria());
        holder.tvCantidad.setText(String.valueOf(producto.getCantidad()));
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.borrarProducto(position);
            }
        });
        holder.ivMasCantidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //maximo 3 productos
                if (producto.getCantidad() < 3) onItemClickListener.aumentarCantidad(position);
            }
        });
        holder.ivMenosCantidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //minimo 1 producto
                if (producto.getCantidad() > 1) onItemClickListener.reducirCantidad(position);
            }
        });
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
            btnEliminar.setOnTouchListener(new ButtonHightLighterOnTouch(btnEliminar));
            ivMenosCantidad.setOnTouchListener(new ButtonHightLighterOnTouch(ivMenosCantidad));
            ivMasCantidad.setOnTouchListener(new ButtonHightLighterOnTouch(ivMasCantidad));
        }
    }
    //para acceder al SharedViewModel
    public interface OnItemClickListener {
        //quitar producto del carro
        public void borrarProducto(int position);
        //aumentar la cantidad de un producto en el carro
        public void aumentarCantidad(int position);
        //reducir la cantidad de un producto en el carro
        public void reducirCantidad(int position);
    }
}







