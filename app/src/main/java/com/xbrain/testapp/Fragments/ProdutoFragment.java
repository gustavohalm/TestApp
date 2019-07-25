package com.xbrain.testapp.Fragments;


import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xbrain.testapp.API.Service;
import com.xbrain.testapp.Models.Produto;
import com.xbrain.testapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdutoFragment extends Fragment {
    private EditText editName, editValue, editQuantity;
    private Button btnAdd;
    private Produto produto;

    public ProdutoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_produto, container, false);

        editName = view.findViewById(R.id.editProdutoName);
        editQuantity = view.findViewById(R.id.editProdutoQuantity);
        editValue = view.findViewById(R.id.editProdutoValue);
        btnAdd = view.findViewById(R.id.btnCreateProduto);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequest();
            }
        });

        return view;
    }

    public void makeRequest()
    {

        String name = editName.getText().toString();
        Double value = Double.valueOf(  editValue.getText().toString() );
        Integer quantity =  Integer.valueOf( editQuantity.getText().toString() );
        produto = new Produto();
        produto.setName(name);
        produto.setValue(value);
        produto.setQuantity(quantity);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);

        Call<Produto> call = service.createProduto(produto);

        call.enqueue(new Callback<Produto>() {
            @Override
            public void onResponse(Call<Produto> call, Response<Produto> response) {
               if (response.isSuccessful())
               {
                   produto = response.body();
                   Toast.makeText(getContext(), "Produto adicionado com sucesso", Toast.LENGTH_LONG).show();
               }
               else
               {

                   Toast.makeText(getContext(), "Não foi possivel adicioanr o produto" + response.code(), Toast.LENGTH_LONG).show();
               }
            }

            @Override
            public void onFailure(Call<Produto> call, Throwable t) {
                Toast.makeText(getContext(), "Não foi possivel adicioanr o produto" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

}
