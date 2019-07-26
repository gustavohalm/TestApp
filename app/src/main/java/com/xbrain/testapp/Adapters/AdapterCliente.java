package com.xbrain.testapp.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xbrain.testapp.Fragments.PedidoFragment;
import com.xbrain.testapp.MainActivity;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.btnName.setText(listClientes.get(position).getName());

        holder.btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = context.getSharedPreferences("cliente_selected", context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("cliente", listClientes.get(position).getName());
                editor.putLong("cliente_id", listClientes.get(position).getId());
                editor.apply();
                MainActivity  activity = (MainActivity) view.getContext();
                PedidoFragment pedidoFragment = new PedidoFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, pedidoFragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listClientes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Button btnName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnName = itemView.findViewById(R.id.btnClienteName);
        }
    }
}
