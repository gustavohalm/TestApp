package com.xbrain.testapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xbrain.testapp.Models.Cliente;
import com.xbrain.testapp.R;

import java.util.List;

public class AdapterCliente extends RecyclerView.Adapter<AdapterCliente.MyViewHolder> {
    private List<Cliente> listClientes;
    private Context context;
    public AdapterCliente(List<Cliente> listClientes, Context context) {
        this.listClientes = listClientes;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_clientes, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtClienteName.setText(listClientes.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return listClientes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtClienteName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtClienteName = itemView.findViewById(R.id.txtProdutoName);
        }
    }
}
