package com.atelier.it.atelier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.atelier.it.atelier.Volley.DefaultExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class log extends AppCompatActivity {
    //Declaracion de variables
    private Button iniciar;
    private TextView txtapellido, txtfecha, txtreservacion, titleToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        //Instancia del toolbar para su visualización
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("");
        //Asignación del valor de la variables por medio del id del elemento
        iniciar = (Button)findViewById(R.id.iniciar);
        txtapellido = (TextView)findViewById(R.id.txtapellido);
        txtfecha = (TextView)findViewById(R.id.txtfecha);
        txtreservacion= (TextView)findViewById(R.id.txtreservacion);
        titleToolbar = (TextView)findViewById(R.id.toolbar_title);
        titleToolbar.setText("Iniciar Sesión");

        String[] archivos = fileList();

        if(existe(archivos, "etiquetas.txt")){

            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("etiquetas.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                while(linea != null){
                    todo = todo + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();

                JSONObject jObj = new JSONObject(todo);
                JSONObject subObj = jObj.getJSONObject("nameValuePairs");
                String login = subObj.getString("btnLogin");
                String apellido = subObj.getString("txtapellido");
                String fecha = subObj.getString("txtfecha");
                String reservacion = subObj.getString("txtreservacion");
                System.out.println(todo);

                iniciar.setText(login);
                txtapellido.setHint(apellido);
                txtfecha.setHint(fecha);
                txtreservacion.setHint(reservacion);


            }catch (IOException e){

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(log.this, Registro.class);
                startActivity(intent);
            }
        });
    }

    private boolean existe(String[] archivos, String archbusca) {
        for(int f = 0; f<archivos.length;f++)
            if(archbusca.equals(archivos[f]))
                return true;
        return false;
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(log.this, MainActivity.class);
                startActivity(intent);
                return true;
        }
        return true;
    }
}
