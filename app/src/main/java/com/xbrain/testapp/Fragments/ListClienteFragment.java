package com.xbrain.testapp.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xbrain.testapp.API.Service;
import com.xbrain.testapp.Adapters.AdapterCliente;
import com.xbrain.testapp.Models.Cliente;
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
public class ListClienteFragment extends Fragment {
    private RecyclerView rcvClientes;
    private List<Cliente> listCliente;
    public ListClienteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_list_cliente, container, false);
        rcvClientes = view.findViewById(R.id.rcvListCliente);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.15.111:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);
        Call<List<Cliente>> callCliente =  service.getClientes();

        callCliente.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if (response.isSuccessful())
                {
                    listCliente = response.body();
                    populateRecycler();
                }
                else
                {
                    Log.v("debugMode", "Erro ao recuperar clientes " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {

                Log.v("debugMode", "Erro ao recuperar clientes " +t.getMessage());
            }
        });

        return view;
    }

    public  void populateRecycler()
    {
        Log.v("debugMode", "Metodo is entering" );
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        AdapterCliente adpCliente = new AdapterCliente(listCliente, getContext());
        rcvClientes.setHasFixedSize(true);
        rcvClientes.setLayoutManager(manager);
        rcvClientes.setAdapter(adpCliente);
        Log.v("debugMode", "Metodo is closing" );
    }
}
