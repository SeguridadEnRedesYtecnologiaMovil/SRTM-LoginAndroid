package mx.uach.ocorona.login;

import android.app.Activity;
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

import mx.uach.ocorona.login.utils.WebService;

//public class LoginActivity extends ActionBarActivity {
public class LoginActivity extends AppCompatActivity {

    private EditText txtUser;
    private EditText txtPassword;
    private Button buttLogin;
    private TextView labelErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.txtUser = (EditText) findViewById(R.id.txtUser);
        this.txtPassword = (EditText) findViewById(R.id.txtPassword);
        this.buttLogin = (Button) findViewById(R.id.buttLogin);
        this.labelErrorMessage = (TextView) findViewById(R.id.labelErrorMessage);
    }

    public void submit(View view) {
        labelErrorMessage.setTextColor(Color.RED);
        labelErrorMessage.setText("");
        String errorFields = "Por favor complete todos los campos...";
        String errorUser = "El usuario no existe...";
        String errorPass = "La contraseña no es correcta...";
        String user = txtUser.getText().toString();
        String pass = txtPassword.getText().toString();
        if (!user.isEmpty() && !pass.isEmpty()) {
            if(WebService.userExists(user)) {
                if (WebService.correctPassword(user, pass)) {



                }
            } else {
                labelErrorMessage.setText(errorUser);
            }
        } else {
            labelErrorMessage.setText(errorFields);
        }
    }

}
