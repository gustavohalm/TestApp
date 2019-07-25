package com.xbrain.testapp.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xbrain.testapp.API.Service;
import com.xbrain.testapp.Models.Cliente;
import com.xbrain.testapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClienteFragment extends Fragment {
    private EditText editNome, editEmail, editCelphone;
    private Button btnCreate;
    private Cliente cliente;
    public ClienteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cliente, container, false);
        editNome = view.findViewById(R.id.editClienteName);
        editEmail = view.findViewById(R.id.editClienteEmail);
        editCelphone = view.findViewById(R.id.editClienteCelular);
        btnCreate = view.findViewById(R.id.btnCreateCliente);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequest();
            }
        });

        return view;
    }

    public void makeRequest()
    {

        String nome = editNome.getText().toString();
        String email = editEmail.getText().toString();
        String celphone =  editCelphone.getText().toString();

        cliente  = new Cliente();
        cliente.setName(nome);
        cliente.setCelphone(celphone);
        cliente.setEmail(email);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);

        Call<Cliente> call = service.createCliente(cliente);

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {

                if( response.isSuccessful())
                {
                    cliente = response.body();
                    Toast.makeText(getContext(), "Cliente criado com sucesso", Toast.LENGTH_LONG).show();
                }
                else
                {

                    Toast.makeText(getContext(), "Erro ao criar cliente: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Toast.makeText(getContext(), "Erro ao criar cliente: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

}
