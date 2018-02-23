package com.atelier.it.atelier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Alexander Villegas on 19/02/2018.
 */

public class IOHelper {
    public static String stringFromStream(InputStream is){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String linea = null;

            while((linea = br.readLine()) != null)
                sb.append(linea).append("\n");
            br.close();
            return sb.toString();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
