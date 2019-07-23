package com.xbrain.testapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xbrain.testapp.Models.Produto;
import com.xbrain.testapp.R;

import java.util.List;

public class AdpaterProduto extends RecyclerView.Adapter<AdpaterProduto.MyViewHolder> {
    private List<Produto> listProdutos;
    private Context context;

    public AdpaterProduto(List<Produto> listProdutos, Context context) {
        this.listProdutos = listProdutos;
        this.context = context;
    }

    @NonNull
    @Override
    public AdpaterProduto.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_produtos, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.txtName.setText( listProdutos.get(position).getName() );
            holder.txtValue.setText( listProdutos.get(position).getName() );
    }


    @Override
    public int getItemCount() {
        return listProdutos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtValue;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtProdutoName);
            txtValue = itemView.findViewById(R.id.txtProdutoValue);
        }
    }

}
