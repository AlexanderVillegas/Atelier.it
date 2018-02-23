package com.atelier.it.atelier;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Home extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private TextView lblsaludo, lblhabitacion, btn_llave, btn_cuenta, btn_tienda, btn_restaurantes, btn_tv, btn_mapa, btn_resortservice, btn_roomservice;
    private CardView uno, dos, tres;
    private MenuItem cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        uno = (CardView)findViewById(R.id.uno);
        dos = (CardView)findViewById(R.id.dos);
        tres = (CardView)findViewById(R.id.tres);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");

        lblsaludo = (TextView)findViewById(R.id.lblsaludo);
        lblhabitacion = (TextView)findViewById(R.id.lblhabitacion);
        btn_llave = (TextView)findViewById(R.id.btn_llave);
        btn_cuenta = (TextView)findViewById(R.id.btn_cuenta);
        btn_tienda = (TextView)findViewById(R.id.btn_tienda);
        btn_restaurantes = (TextView)findViewById(R.id.btn_restaurantes);
        btn_tv = (TextView)findViewById(R.id.btn_tv);
        btn_mapa = (TextView)findViewById(R.id.btn_mapa);
        btn_resortservice = (TextView)findViewById(R.id.btn_resortservice);
        btn_roomservice = (TextView)findViewById(R.id.btn_roomservice);

        imprimirEtiquetas();

        uno.setOnClickListener(this);
        dos.setOnClickListener(this);
        tres.setOnClickListener(this);
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar, menu);
        cerrar = menu.findItem(R.id.option);
        cerrar.setTitle("Cerrar Sesión");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.option:
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
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
                String saludo = subObj.getString("lblsaludo");
                String habitacion = subObj.getString("lblhabitacion");

                String llave = subObj.getString("btn_llave");
                String cuenta = subObj.getString("btn_cuenta");
                String tienda = subObj.getString("btn_tienda");
                String restaurant = subObj.getString("btn_restaurantes");
                String guia = subObj.getString("btn_tv");
                String mapa = subObj.getString("btn_mapa");
                String servicios = subObj.getString("btn_resortservice");
                String roomservice = subObj.getString("btn_roomservice");

                lblsaludo.setText(saludo);
                lblhabitacion.setText(habitacion);

                btn_llave.setText(llave);
                btn_cuenta.setText(cuenta);
                btn_tienda.setText(tienda);
                btn_restaurantes.setText(restaurant);
                btn_tv.setText(guia);
                btn_mapa.setText(mapa);
                btn_resortservice.setText(servicios);
                btn_roomservice.setText(roomservice);



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


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.uno:
                Intent intent = new Intent(Home.this, Key.class);
                startActivity(intent);
                break;
            case R.id.dos:
                Intent intent2 = new Intent(Home.this, MapsActivity.class);
                startActivity(intent2);
                break;
            case R.id.tres:
                Intent intent3 = new Intent(Home.this, MainRestaurant.class);
                startActivity(intent3);
        }
    }

    @Override
    public void onBackPressed(){
    }
}
