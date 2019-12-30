package com.example.ienovo.list_ho;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import List_of_news.Element_News;
import List_of_news.ProductsAdapter_News;

public class MainActivity extends Activity {
    List<Element_News> productList;
    SwipeRefreshLayout mSwipeRefreshLayout;

    //API_url
  String url="http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/1.json?api-key=BPBMGAWRLKQlpZ5hZSx57YGnCaEbtqTm";
    RecyclerView recyclerView;

    ProgressBar Pbar;
    private Button eroorbutton;
   String  v_unit_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container1);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadProducts();
            }

        });
        recyclerView = (RecyclerView) findViewById(R.id.recylcerView21);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setVisibility(View.GONE);

        Pbar = (ProgressBar)findViewById(R.id.progressBar1);
        Pbar.setVisibility(View.GONE);
        eroorbutton = (Button) findViewById(R.id.erorrp);
        eroorbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vvv) {
                loadProducts();

            }
        });

        productList = new ArrayList<>();

        loadProducts();

    }

    public void loadProducts() {
        productList.clear();
        Pbar.setVisibility(View.VISIBLE);
        eroorbutton.setVisibility(View.GONE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {


                            //unit_test_value
                            v_unit_test="loaded";



                            recyclerView.setVisibility(View.VISIBLE);
                            mSwipeRefreshLayout.setRefreshing(false);
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray array_2 = jsonObject.getJSONArray("results");

                          //  Toast.makeText(MainActivity.this, "jj"+array_2, Toast.LENGTH_LONG).show();

                          //  Log.e("loooooooooooooooool",array_2.toString());

                            for (int i =array_2.length()-1 ; i >=0; i--) {


                                JSONObject product = array_2.getJSONObject(i);

                                productList.add(new Element_News(
                                        product.getString("title"),
                                        product.getString("abstract")

                                ));


                            }

                            ProductsAdapter_News adapter = new ProductsAdapter_News(MainActivity.this, productList);
                            recyclerView.setAdapter(adapter);
                            Pbar.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            v_unit_test="cash expetin";
                            e.printStackTrace();
                              //  getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            mSwipeRefreshLayout.setRefreshing(false);

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        v_unit_test="error conection";
                         //   getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Pbar.setVisibility(View.GONE);
                        eroorbutton.setVisibility(View.VISIBLE);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
        Volley.newRequestQueue(MainActivity.this).add(stringRequest);
    }



}

