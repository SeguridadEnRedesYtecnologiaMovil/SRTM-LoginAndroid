package mx.uach.ocorona.login.models;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ocorona on 11/07/16.
 */
public class User {

    private static final String DOMAIN = "http://jsonplaceholder.typicode.com";

    public static final String URL_USUARIO = String.format("%s/users", DOMAIN);

    public static String read(String url){
        String json="{}";
        try {
            URL ruta = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) ruta.openConnection();
            json = transformBuffer(connection.getInputStream()).toString();
        }catch (Exception e) {
            Log.w("Error", "No se puede leer el servicio");
            json = "null";
        }
        return json;
    }

    public static String transformBuffer(InputStream inputStream){
        String linea = "";
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader((inputStream)));
            int value = -1;
            while ((value = reader.read()) != -1) {
                char c = (char) value;
                linea = String.format("%s%s", linea, c);
            }
        }catch (Exception e) {
            Log.e("Error", "No se puede leer el JSON");
        }finally {
            try{
                if (reader != null){
                    reader.close();
                }
            }catch (Exception e){
                Log.e("Error","No se puede cerrar el JSON");
            }
        }
        return linea;
    }

}
