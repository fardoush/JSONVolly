package com.example.jsonvolly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private Button button;
    private RequestQueue mqueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textId);
        button=findViewById(R.id.btnId);
        mqueue= Volley.newRequestQueue(this);
        button.setOnClickListener(this);


    }
    //Button click

    @Override
    public void onClick(View v) {
        //json add function create
        jsonParse();

    }

    //json function
    private void jsonParse() {
        // json url
        String url="https://api.myjson.com/bins/1g4b3y";
        //json  Object Request use to get method
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

          //JSON Object Response ----
          @Override
            public void onResponse(JSONObject response) {

                try {
                    //json array declear to  array name
                    JSONArray jsonArray=response.getJSONArray("employees");
                    for (int i=0;i<jsonArray.length();i++){

                                        JSONObject employee=jsonArray.getJSONObject(i);
                                        //declear to json object
                                        String name=employee.getString("name");
                                        int id=employee.getInt("id");
                                        String mail=employee.getString("mail");
                                        textView.append(name+","+String.valueOf(id)+","+mail +"\n\n");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                // find to error
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });

        mqueue.add(request);
    }
}
