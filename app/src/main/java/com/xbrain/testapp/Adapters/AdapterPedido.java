package com.xbrain.testapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xbrain.testapp.Models.Pedido;
import com.xbrain.testapp.R;

import java.util.List;

public class AdapterPedido extends RecyclerView.Adapter<AdapterPedido.MyViewHolder> {
    private List<Pedido> listPedidos;
    private Context context;

    public AdapterPedido(List<Pedido> listPedidos, Context context) {
        this.listPedidos = listPedidos;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_pedidos, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.txtPedidoCliente.setText("Cliente: " + listPedidos.get(position).getCliente().getName());
            holder.txtPedidoId.setText("Pedido: #" + listPedidos.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return listPedidos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtPedidoId, txtPedidoCliente;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPedidoId = itemView.findViewById(R.id.txtPedidoId);
            txtPedidoCliente = itemView.findViewById(R.id.txtPedidoCliente);
        }

    }

}
