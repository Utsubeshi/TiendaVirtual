package com.mitienda.tiendavirtual.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mitienda.tiendavirtual.R;
import com.mitienda.tiendavirtual.fragments.CatalogoFragmentDirections;
import com.mitienda.tiendavirtual.model.Producto;

import java.util.ArrayList;
import java.util.List;

import static androidx.navigation.Navigation.findNavController;

public class CatalogoAdapter extends RecyclerView.Adapter<CatalogoAdapter.ViewHolder> {

    Context context;
    List<Producto> lista;

    public CatalogoAdapter(Context context) {
        this.context = context;
        lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogoAdapter.ViewHolder holder, int position) {
        Producto producto = lista.get(position);
        holder.imagenProducto.setImageResource(producto.getImagenTest());
        holder.tvNombreProduco.setText(producto.getNombre());
        holder.tvPrecioProducto.setText(String.format("S/ %s", producto.getPrecio()));
        holder.imagenProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatalogoFragmentDirections.ActionNavVerDetalleProducto action = CatalogoFragmentDirections.actionNavVerDetalleProducto(producto);
                //action.setNombreProducto(producto.getNombre());
                findNavController(v).navigate(action);
            }
        });
        holder.btnAgregarAlCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO agregar producto al carro
                Toast.makeText(context, "add item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void agregarElementos(ArrayList<Producto> data){
        lista.clear();
        lista.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        ImageView imagenProducto;
        TextView tvNombreProduco, tvPrecioProducto;
        ConstraintLayout contenedor;
        ImageButton btnAgregarAlCarro;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contenedor = itemView.findViewById(R.id.producto_contenedor);
            imagenProducto = itemView.findViewById(R.id.iv_producto_imagen);
            tvNombreProduco = itemView.findViewById(R.id.tv_producto_nombre);
            tvPrecioProducto = itemView.findViewById(R.id.tv_producto_precio);
            btnAgregarAlCarro = itemView.findViewById(R.id.btn_add_to_cart);
        }
    }
}
