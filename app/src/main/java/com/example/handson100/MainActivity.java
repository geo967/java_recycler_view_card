package com.example.handson100;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    TextView textBelowImage;
    List<Model> list = new ArrayList<>();
    ArrayList<Integer> movieList=new ArrayList<>();
    Items items=new Items();
    OkHttpClient okHttpClient=new OkHttpClient();
    String urlOfSite="https://jsonplaceholder.typicode.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBelowImage=findViewById(R.id.text_below_image);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(urlOfSite)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.getUnsafeOkHttpClient().build())
                .build();


        MyApi api=retrofit.create(MyApi.class);
        Call<List<ModelOfApi>> call=api.getModelsOfApi();




        call.enqueue(new Callback<List<ModelOfApi>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<ModelOfApi>> call, Response<List<ModelOfApi>> response) {

                if(response.code() !=200){
                    String t="Check connection";
                    textBelowImage.setText(t);
                    return;
                }

                List<ModelOfApi> data=response.body();
               for (int i = 0; i <data.size() ; i++) {
                    HttpsTrustManager.allowAllSSL();
                    movieList.add(data.get(i).getId());

                }



            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<ModelOfApi>> call, Throwable t) {
                textBelowImage.setText(t.getMessage());
            }
        });



        list=items.getList();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new MyAdapter(this, list,movieList);
        recyclerView.setAdapter(myAdapter);

    }





/*

    public static okhttp3.OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @SuppressLint("TrustAllX509TrustManager")
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @SuppressLint("TrustAllX509TrustManager")
                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
*/

}