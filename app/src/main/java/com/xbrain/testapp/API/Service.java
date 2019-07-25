package com.xbrain.testapp.API;

import com.xbrain.testapp.Models.Cliente;
import com.xbrain.testapp.Models.Entrega;
import com.xbrain.testapp.Models.Pedido;
import com.xbrain.testapp.Models.Produto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Service {

    //region Clientes endpoint's
    @POST("clientes/")
    Call<Cliente> createCliente(@Body Cliente cliente);

    @GET("clientes/")
    Call<List<Cliente>> getClientes();

    @GET("clientes/{id}")
    Call<Cliente> getCliente(@Path("id") Long id);
    //endregion

    //region Produtos endpoint's
    @POST("produtos/")
    Call<Produto> createProduto(@Body Produto produto);

    @GET("produtos/")
    Call<List<Produto>> getProdutos();

    @GET("produtos/{id}")
    Call<Pedido> getProduto(@Path("id") Long id);
    //endregion

    //region Pedidos endpoint's
    @POST("pedidos/")
    Call<Pedido> createPedido(@Body Pedido pedido);

    @GET("pedidos/")
    Call<List<Produto>> getPedidos();

    @GET("pedidos/{id}")
    Call<Pedido> getPedido(@Path("id") Long id);
    //endregion

    //region Entrega endpoint's
    @POST("entregas/")
    Call<Entrega> createEntrega(@Body Entrega entrega);

    @GET("entregas/")
    Call<List<Entrega>> getEntregas();

    @GET("entregas/{id}")
    Call<Entrega> getEntrega(@Path("id") Long id);
    //endregion

}
