package com.atelier.it.atelier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Registro extends AppCompatActivity {
    private MenuItem mostrar;
    private TextView titleToolbar, txtingresamail, txthoradellegada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        titleToolbar = (TextView)findViewById(R.id.toolbar_title);
        titleToolbar.setText("Datos de Registro");

        txtingresamail = (TextView)findViewById(R.id.txtingresamail);
        txthoradellegada = (TextView)findViewById(R.id.txthoradellegada);

        setTitle("");

        imprimirEtiquetas();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        mostrar = menu.findItem(R.id.option);
        mostrar.setTitle("Guardar");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(Registro.this, log.class);
                startActivity(intent);
                return true;
            case R.id.option:
                Intent intent2 = new Intent(Registro.this, Home.class);
                startActivity(intent2);
        }
        return true;
    }

    public void imprimirEtiquetas(){
        String[] archivos = fileList();

        if(existe(archivos, "etiquetas.txt")){
            //Gson gson = new Gson();
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
                String correo = subObj.getString("txtingresamail");
                String llegada = subObj.getString("txthoradellegada");

                txtingresamail.setHint(correo);
                txthoradellegada.setHint(llegada);


            }catch (IOException e){

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private boolean existe(String[] archivos, String archbusca) {
        for(int f = 0; f<archivos.length;f++)
            if(archbusca.equals(archivos[f]))
                return true;
        return false;
    }
}

/*
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿ Acepta la ejecución de este programa en modo prueba ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                aceptar();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelar();
            }
        });
        dialogo1.show();




        public void aceptar() {
        Toast t=Toast.makeText(this,"Bienvenido a probar el programa.", Toast.LENGTH_SHORT);
        t.show();
    }

    public void cancelar() {
        finish();
    }


 */
