package mx.uach.ocorona.login.models;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Clase usuario.
 *
 * Created by ocorona on 11/07/16.
 */
public class User {

    /**
     * Correo electronico del usuario.
     */
    public String email;

    /**
     * Clave para la contraseña del usuario.
     */
    public String peanut;

    /**
     * Constructor vacío.
     */
    public User(){}

    /**
     * Constructor de un nuevo usuario.
     *
     * @param email Correo electronico del usuario
     * @param peanut Clave para la contraseña del usuario
     */
    public User(String email, String peanut) {
        this.email = email;
        this.peanut = peanut;
    }

    /**
     * Regresa el correo electrónico.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Asigna el correo electrónico.
     *
     * @param email {@code String}
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Regresa la contraseña.
     *
     * @return penaut
     */
    public String getPeanut() {
        return peanut;
    }

    /**
     * Asigna la contraseña.
     *
     * @param peanut {@code String}
     */
    public void setPeanut(String peanut) {
        this.peanut = peanut;
    }

    /**
     * Regresa una concatenación de los campos de usuario.
     *
     * @return {@code String}
     */
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", peanut='" + peanut + '\'' +
                '}';
    }
}
