package com.example.volley_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
      JSONArray jsonArray;
      ArrayList<Model>list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        RequestQueue queue= Volley.newRequestQueue(this);
        String url= "https://jsonplaceholder.typicode.com/users";

        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject mainobj = null;

                        try {
                            jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {


                                mainobj = jsonArray.getJSONObject(i);
                                int id = mainobj.getInt("id");
                                String name = mainobj.getString("name");
                                String username = mainobj.getString("username");
                                String email = mainobj.getString("email");

                                JSONObject address=mainobj.getJSONObject("address");

                                String city=address.getString("city");

                                JSONObject geo=address.getJSONObject("geo");

                                String lat=geo.getString("lat");

                                Model model = new Model(id, name, username, email,city,lat);
                                list.add(model);

                                Log.d("AAA", "onResponse: Object id=" +id);
                                Log.d("AAA", "onResponse: Object name=" +name);
                                Log.d("AAA", "onResponse: Object username=" +username);
                                Log.d("AAA", "onResponse: Object= email=" +email);
                                Log.d("AAA", "onResponse: Object= city=" +city);
                                Log.d("AAA", "onResponse: Object= lat=" +lat);

                            }
                            Recyclerview_Adapter adapter=new Recyclerview_Adapter(MainActivity.this,list);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TTT", "onErrorResponse: Error=",error );
            }
        });
        queue.add(request);

    }
}