package com.atelier.it.atelier;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.atelier.it.atelier.Volley.LongTimeoutAndTryRetryPolicy;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Created by Alexander Villegas on 15/02/2018.
 */

public class MainService {
    private String idioma;

    public MainService(String idioma){
        this.idioma = idioma;
    }
    public StringRequest getRequest(Response.Listener<String> responseListener, Response.ErrorListener errorListener){
        final HashMap<String, String> credenciales = new HashMap<>();
        credenciales.put("lang", idioma);

        String url = "http://atelierservices.azurewebsites.net/App/GenericLabels?lang="+idioma;
        StringRequest request = new StringRequest(Request.Method.GET, url, responseListener, errorListener){
            @Override
            public String getBodyContentType(){
                return "application/json charset="+getParamsEncoding();
            }
            @Override
            public byte[] getBody(){
                try {
                    return new JSONObject (credenciales).toString().getBytes(getParamsEncoding());
                }catch (UnsupportedEncodingException e){

                }
                return null;
            }
        };

        request.setRetryPolicy(new LongTimeoutAndTryRetryPolicy(LongTimeoutAndTryRetryPolicy.RETRIES_PHONE_ISP));
        return request;
    }
}
