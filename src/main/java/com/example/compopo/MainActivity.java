package com.example.compopo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnContextClickListener {
EditText nombre,apellido;
Button insertar;
RequestQueue requestQueue;
private static final String URL="http://10.0.2.2/popo/conect.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        nombre=findViewById(R.id.nombre);
        apellido=findViewById(R.id.apellido);
        insertar=(Button) findViewById(R.id.insertar);
    insertar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        int id=view.getId();
        if(id==R.id.insertar) {

            String name = nombre.getText().toString().trim();
            String appl = apellido.getText().toString().trim();
           insertarprueba(name,appl);
            StringRequest stringRequest=new StringRequest(
                Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this,"insertaste ok",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> parametro = new HashMap<>();
                parametro.put("nombre", String.valueOf(nombre));  // Cambia "name" a "nombre"
                parametro.put("apellido", appl);
                return parametro;
            }
        };
        requestQueue.add(stringRequest);
        }
    }
});
        requestQueue= Volley.newRequestQueue(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    public void onClick (View v){



    }@Override
    public boolean onContextClick(View view) {

        return false;
    }
    private void insertarprueba(String nom,String ape){
        StringRequest stringRequest=new StringRequest(
                Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this,"insertaste ok",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> parametro = new HashMap<>();
                parametro.put("nombre", nom);  // Cambia "name" a "nombre"
                parametro.put("apellido", ape);
                return parametro;
            }
        };
        requestQueue.add(stringRequest);
    }
}