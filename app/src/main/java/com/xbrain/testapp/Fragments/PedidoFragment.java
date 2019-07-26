package com.xbrain.testapp.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.xbrain.testapp.API.Service;
import com.xbrain.testapp.Adapters.AdapterPedido;
import com.xbrain.testapp.Adapters.AdpaterProduto;
import com.xbrain.testapp.MainActivity;
import com.xbrain.testapp.Models.Cliente;
import com.xbrain.testapp.Models.Entrega;
import com.xbrain.testapp.Models.Pedido;
import com.xbrain.testapp.Models.Produto;
import com.xbrain.testapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
    private Button btnSelectCliente, btnProduto,btnCreatePedido;
    private EditText editCidade, editEstado, editBairro, editRua, editNumero, editComplemento;
    private Pedido pedido;
    private List<Produto> listProdutos, listProdutosSelect;
    private Entrega entrega;
    private SharedPreferences prefsCliente;
    private Long id_cliente;
    private Spinner spingProdutos;
    private RecyclerView rcvSelected;
    public PedidoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pedido, container, false);
        btnSelectCliente = view.findViewById(R.id.btnSelectCliente);
        btnProduto = view.findViewById(R.id.btnAddProduto);
        btnCreatePedido = view.findViewById(R.id.btnCreatePedido);
        editEstado = view.findViewById(R.id.editEnderecoEstado);
        editCidade = view.findViewById(R.id.editEnderecoCidade);
        editBairro = view.findViewById(R.id.editEnderecoBairro);
        editRua = view.findViewById(R.id.editEnderecoRua);
        editNumero = view.findViewById(R.id.editEnderecoNumero);
        editComplemento = view.findViewById(R.id.editEnderecoComplemento);
        spingProdutos = view.findViewById(R.id.spinProdutos);
        rcvSelected = view.findViewById(R.id.rcvSelected);
        listProdutosSelect = new ArrayList<Produto>();
        prefsCliente = getContext().getSharedPreferences("cliente_selected", getContext().MODE_PRIVATE);
        String nameSelected = prefsCliente.getString("cliente", "0");
        id_cliente = prefsCliente.getLong("cliente_id", 0);
        if (nameSelected != "0")
            btnSelectCliente.setText(nameSelected);

        btnSelectCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).openDialogCliente();
            }
        });


        makeProdutos();

        btnProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Produto selectProduto = (Produto) spingProdutos.getSelectedItem();
                listProdutosSelect.add(selectProduto);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
                AdpaterProduto adapterProduto = new AdpaterProduto(listProdutosSelect, getContext());

                rcvSelected.setHasFixedSize(true);
                rcvSelected.setLayoutManager(manager);
                rcvSelected.setAdapter(adapterProduto);
            }
        });

        btnCreatePedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePedido();
            }
        });

        return view;
    }

    public void makeProdutos()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.15.111:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);
        Call<List<Produto>> call = service.getProdutos();
        call.enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                if( response.isSuccessful())
                {
                    listProdutos = response.body();
                    ArrayAdapter<Produto> adapterProduto = new ArrayAdapter<Produto>(getContext(),android.R.layout.simple_spinner_item);
                    adapterProduto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    adapterProduto.addAll(listProdutos);
                    spingProdutos.setAdapter(adapterProduto);
                }
                else
                {
                    Log.v("debugMode", "Erro ao recuperar produtos: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {

                Log.v("debugMode", "Falha ao recuperar produtos: " + t.getMessage());
            }
        });

    }

    public void makePedido()
    {
        pedido = new Pedido();
        Cliente cliente = new Cliente();
        cliente.setId(id_cliente);

        pedido.setCliente(cliente);
        pedido.setProdutos(listProdutosSelect);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.15.111:8081/")
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
                .baseUrl("http://192.168.15.111:8081/")
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
