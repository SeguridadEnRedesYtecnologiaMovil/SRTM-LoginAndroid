package mx.uach.ocorona.login.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import mx.uach.ocorona.login.models.User;

/**
 * Maneja la comunicación con el servicio web de play.
 *
 * Created by ocorona on 11/07/16.
 */
public class WebService {

    /**
     * Cadena con la URL del dominio.
     */
    private static final String SERVICE_DOMAIN = "TODO write your IP here";

    /**
     * Cadena con la URL del servicio de logueo.
     */
    public static final String URL_LOGIN = String.format("%s/login/users/", SERVICE_DOMAIN);

    private static String requestURL = "";

    /**
     * Verifica que el usuario exista en la base de datos.
     *
     * @param userEmail {@code String} email
     * @return True/False
     */
    public static Boolean userExists (String userEmail) {
        Boolean exists = Boolean.FALSE;
        return exists;
    }

    /**
     * Verifica que la contraseña sea valida para el correo del usuario.
     *
     * @param userEmail {@code String} email
     * @param peanut {@code String} contraseña
     * @return True/False
     */
    public static Boolean correctPassword (String userEmail, String peanut) {
        Boolean exists = Boolean.FALSE;
        return exists;
    }

    public static Boolean loginIntent(final String userEmail, final String peanut) {
        Boolean success;
        try {
            User user = getUser(userEmail, peanut);
            if(user != null && user.getEmail() != null){
                success = Boolean.TRUE;
            } else {
                success = Boolean.FALSE;
            }
        } catch (Exception e) {
            Log.w("Error", "Fallo al intentar leer el servicio");
            success = Boolean.FALSE;
        }

        return success;
    }


    /**
     * Recibe
     * @param userEmail
     * @param peanut
     * @return
     */
    public static String read(String userEmail, String peanut){
        String json="{}";
        try {
            requestURL = String.format("%s%s/%s", URL_LOGIN,userEmail,peanut);
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("requestURL = " + requestURL);
            System.out.println("---------------------------------------------------------------------------");
            URL route = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) route.openConnection();
            System.out.println("connection = " + connection);
//            json = transformBuffer(connection.getInputStream()).toString();
            json = transformBuffer(connection.getInputStream()).toString();
            System.out.println("json = " + json);
            System.out.println("Yei");
        }catch (Exception e) {
            System.out.println("e = " + e);
            Log.w("Error", "Fallo al intentar leer el servicio");
            return null;
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

    public static byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            return res;

        } catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }

    public static User getUser(String email, String penut){
        ConnectServer server = new ConnectServer();
        server.execute(email, penut);
        User user = new User();
        try {
            String json = server.get();
            if(json.equals("null")){
                return null;
            } else{
                Gson gson = new Gson();
                Type listType = new TypeToken<User>(){}.getType();
                user = gson.fromJson(json, listType);
            }

        } catch (Exception e){
            Log.e("Error", "No se puede leer el JSON.");
        }

        return user;
    }

    private static class ConnectServer extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... parameters) {
            return read(parameters[0], parameters[1]);
        }
    }

}
