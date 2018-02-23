package com.atelier.it.atelier;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.service.voice.VoiceInteractionSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.atelier.it.atelier.Volley.AppController;
import com.atelier.it.atelier.Volley.DefaultExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Locale;
//Response.ErrorListener, Response.Listener<String>
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button iniciar;
    private TextView invitado;

    // URL del objeto a analizar.
    String url = "http://atelierservices.azurewebsites.net/App/GenericLabels?lang=";

    String idioma = Locale.getDefault().getLanguage();
    //Definición de la cola de solicitud de Volley que maneja la solicitud de URL concurrentemente.
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciar = (Button)findViewById(R.id.iniciar);
        invitado = (TextView)findViewById(R.id.invitado);
        //llamada al metodo grabar().
        grabar();

    }

    public void grabar(){
        // Creación del Volley request queue.
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url+idioma, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = response.getJSONObject("response");
                            //Recupera la cadena etiquetada como "response" del objeto JSON de la petición.
                            //y los convertimos en objetos
                            String huesped = obj.getString("btnHuesped");
                            String invita = obj.getString("btnInvitado");

                            // Agreagación de las cadenas de datos a los Textview.
                            iniciar.setText(huesped);
                            invitado.setText(invita);
                            Gson gson = new Gson();
                            String jsonString = gson.toJson(obj);
                            try{
                                //Creamos un objeto de la clase OutputStreamWriter y le enviamos los datos que retornan (nombre y modo de apertura).
                                OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("etiquetas.txt", Activity.MODE_PRIVATE));
                                //Instanciamos el metodo write y le asignamos la variable donde se encuentra el Json
                                archivo.write(jsonString);
                                //hacemos que se vuelquen los datos del buffer y procedemos a cerrar el archivo.
                                archivo.flush();
                                archivo.close();
                            }catch (IOException e){
                            }
                            System.out.println("Se han guardado los datos");

                        }
                        // Try y catch están incluidos para manejar cualquier error debido a JSON.
                        catch (JSONException e) {
                            // Si ocurre un error, se imprimirá el error en el registro.
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error en la respuesta", error.getMessage());

                    }
                });

// Acceda a RequestQueue a través de su clase singleton.
        requestQueue.add(jsObjRequest);
        //MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iniciar:
                Intent intent = new Intent(MainActivity.this, log.class);
                startActivity(intent);
        }
    }
}
