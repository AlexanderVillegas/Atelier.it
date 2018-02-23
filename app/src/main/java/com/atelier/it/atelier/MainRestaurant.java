package com.atelier.it.atelier;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Locale;

public class MainRestaurant extends AppCompatActivity {
    private TextView lista;

    // URL del objeto a analizar.
    String url = "http://atelierservices.azurewebsites.net/App/GenericLabels?lang=";

    String idioma = Locale.getDefault().getLanguage();
    //Definición de la cola de solicitud de Volley que maneja la solicitud de URL concurrentemente.
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_restaurant);
        lista = (TextView)findViewById(R.id.lista);
    }

    public void grabar(){
        // Creación del Volley request queue.
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url+idioma, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Recupera la cadena etiquetada como "response" del array JSON de la petición.
                            //y los convertimos en objetos
                            JSONArray objArray = response.getJSONArray("response");

                            //Creación de JSONObject para la asignación los objetos que tiene el array.
                            JSONObject obj = new JSONObject();

                            // Recorrido del array para extraer los objetos del Json array
                            for (int i=0; i<objArray.length();i++) {
                                obj = objArray.getJSONObject(i);
                                // Agreagación de las cadenas de datos a variables.
                                String nombre = obj.getString("nombre");

                                lista.append(nombre);
                            }

                            Gson gson = new Gson();
                            String jsonString = gson.toJson(obj);
                            try{
                                //Creamos un objeto de la clase OutputStreamWriter y le enviamos los datos que retornan (nombre y modo de apertura).
                                OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("etiquetas.txt", Activity.MODE_PRIVATE));
                                //Instanciamos el metodo write y le asignamos la variable donde se encuentra el Json
                                archivo.write(jsonString);
                                //Se vuelcan los datos del buffer y se procede a cerrar el archivo.
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

    }
