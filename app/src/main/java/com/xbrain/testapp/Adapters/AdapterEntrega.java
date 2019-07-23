package com.xbrain.testapp.Adapters;

import android.content.Context;
import android.icu.util.ValueIterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xbrain.testapp.Models.Entrega;
import com.xbrain.testapp.R;

import java.util.List;

public class AdapterEntrega extends RecyclerView.Adapter<AdapterEntrega.MyViewHolder> {
    private List<Entrega> listEntregas;
    private Context context;

    public AdapterEntrega(List<Entrega> listEntregas, Context context) {
        this.listEntregas = listEntregas;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_entregas, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtEntregaId.setText("Entrega : #" + listEntregas.get(position).getId());
        holder.txtCliente.setText("Cliente: " + listEntregas.get(position).getPedido().getCliente().getName());
    }

    @Override
    public int getItemCount() {
        return listEntregas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtEntregaId, txtCliente;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCliente = itemView.findViewById(R.id.txtEntregaCliente);
            txtEntregaId = itemView.findViewById(R.id.txtEntregaId);
        }
    }

}
