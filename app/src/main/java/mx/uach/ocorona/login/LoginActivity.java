package mx.uach.ocorona.login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import mx.uach.ocorona.login.utils.WebService;

/**
 * Clase para la actividad de Logueo.
 */
//public class LoginActivity extends ActionBarActivity {
public class LoginActivity extends AppCompatActivity {

    //Variables de referencia para los campos de la actividad de logueo.
    private EditText txtUser;
    private EditText txtPassword;
    private Button buttLogin;
    private TextView labelErrorMessage;

    /**
     * Método por defecto para inicializar la actividad de logueo.
     *
     * @param savedInstanceState {@code Bundle}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.txtUser = (EditText) findViewById(R.id.txtUser);
        this.txtPassword = (EditText) findViewById(R.id.txtPassword);
        this.buttLogin = (Button) findViewById(R.id.buttLogin);
        this.labelErrorMessage = (TextView) findViewById(R.id.labelErrorMessage);
    }

    /**
     * Envía los datos capturados del usuario e intenta loguear al mismo.
     *
     * @param view {@code View}
     */
    public void submit(View view) {
        labelErrorMessage.setTextColor(Color.RED);
        labelErrorMessage.setText("");
        String errorFields = "Por favor complete todos los campos.";
        String errorEmail = "El correo no es valido.";
        String error = "Los datos son incorrectos.";
        String errorServ = "Ocurrió un error inesperado...";
        final String usrEmail = txtUser.getText().toString();
        final String pass = txtPassword.getText().toString();
        if (!usrEmail.isEmpty() && !pass.isEmpty()) {
            if (!isValidEmailAddress(usrEmail)) {
                Toast.makeText(this, "Acceso denegado", Toast.LENGTH_LONG).show();
                labelErrorMessage.setText(errorEmail);
            }else if (WebService.loginIntent(usrEmail, pass)) {
                Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(mainIntent);
            } else if (WebService.read(usrEmail, pass) == null) {
                Toast.makeText(this, "Acceso denegado", Toast.LENGTH_LONG).show();
                labelErrorMessage.setText(errorServ);
            } else {
                Toast.makeText(this, "Acceso denegado", Toast.LENGTH_LONG).show();
                labelErrorMessage.setText(error);
            }
        } else {
            Toast.makeText(this, "Acceso denegado", Toast.LENGTH_LONG).show();
            labelErrorMessage.setText(errorFields);
        }



//        if (!user.isEmpty() && !pass.isEmpty()) {
//            if(WebService.userExists(user)) {
//                if (WebService.correctPassword(user, pass)) {
//                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
//                    LoginActivity.this.startActivity(mainIntent);
//                } else {
//                    labelErrorMessage.setText(errorPass);
//                }
//            } else {
//                labelErrorMessage.setText(errorUser);
//            }
//        } else {
//            labelErrorMessage.setText(errorFields);
//        }
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "([a-zA-Z0-9ñÑ]+(?:[._+-][a-zA-Z0-9ñÑ]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

}
