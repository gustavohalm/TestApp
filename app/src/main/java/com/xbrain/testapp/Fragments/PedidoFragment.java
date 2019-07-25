package com.xbrain.testapp.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xbrain.testapp.API.Service;
import com.xbrain.testapp.Models.Cliente;
import com.xbrain.testapp.Models.Entrega;
import com.xbrain.testapp.Models.Pedido;
import com.xbrain.testapp.Models.Produto;
import com.xbrain.testapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class PedidoFragment extends Fragment {
    private Button btnSelectCliente, btnCreatePedido;
    private EditText editCidade, editEstado, editBairro, editRua, editNumero, editComplemento;
    private Pedido pedido;
    private List<Produto> listProdutos, listProdutosSelect;
    private Entrega entrega;
    private SharedPreferences prefsCliente;
    private Long id_cliente;
    private RecyclerView rcvProdutos, rcvSelected;
    public PedidoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pedido, container, false);
        btnSelectCliente = view.findViewById(R.id.btnSelectCliente);
        btnCreatePedido = view.findViewById(R.id.btnCreatePedido);
        editEstado = view.findViewById(R.id.editEnderecoEstado);
        editCidade = view.findViewById(R.id.editEnderecoCidade);
        editBairro = view.findViewById(R.id.editEnderecoBairro);
        editRua = view.findViewById(R.id.editEnderecoRua);
        editNumero = view.findViewById(R.id.editEnderecoNumero);
        editComplemento = view.findViewById(R.id.editEnderecoComplemento);

        prefsCliente = getContext().getSharedPreferences("cliente_selected", getContext().MODE_PRIVATE);
        String nameSelected = prefsCliente.getString("cliente", "0");
        id_cliente = prefsCliente.getLong("cliente_id", 0);
        if (nameSelected != "0")
            btnSelectCliente.setText(nameSelected);

        

        return view;
    }

    public void makePedido()
    {
        pedido = new Pedido();
        Cliente cliente = new Cliente();
        cliente.setId(id_cliente);

        pedido.setCliente(cliente);
        pedido.setProdutos(listProdutosSelect);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);
        Call<Pedido>  callPedido = service.createPedido(pedido);

        callPedido.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                if(response.isSuccessful())
                {
                    pedido = response.body();
                    makeEntrega();

                }
                else
                {
                    Toast.makeText(getContext(), "Erro ao realizar pedido: " + response.code(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {

                Toast.makeText(getContext(), "Falha ao realizar pedido: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public void makeEntrega()
    {
        entrega = new Entrega();
        entrega.setPedido(pedido);
        entrega.setEstado(editEstado.getText().toString());
        entrega.setCidade(editCidade.getText().toString());
        entrega.setBairro(editBairro.getText().toString());
        entrega.setNumero(editNumero.getText().toString());
        entrega.setEndereco(editRua.getText().toString());
        entrega.setComplemento(editComplemento.getText().toString());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);
        Call<Entrega> callEntrega = service.createEntrega(entrega);

        callEntrega.enqueue(new Callback<Entrega>() {
            @Override
            public void onResponse(Call<Entrega> call, Response<Entrega> response) {
                if(response.isSuccessful())
                {
                    entrega = response.body();
                    makeEntrega();

                }
                else
                {
                    Toast.makeText(getContext(), "Erro ao realizar pedido: " + response.code(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Entrega> call, Throwable t) {
                Toast.makeText(getContext(), "Erro ao realizar pedido: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}
