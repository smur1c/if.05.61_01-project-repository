package at.petshome.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import at.petshome.R;
import at.petshome.database.Connector;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLogin(View view) {
        EditText email_field = (EditText)findViewById(R.id.ĺogin_email);
        EditText password_field = (EditText)findViewById(R.id.login_password);

        String email = email_field.getText().toString();
        String password = password_field.getText().toString();

        if (email == null || password == null || email.equals("") || password.equals("")) {
            System.out.println("Email or password cant be empty!");
        }
        else {

        }
    }

    public void onRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void onResetPassword(View view) {
        Intent intent = new Intent(this, ResetPasswordActivity.class);
        startActivity(intent);
    }
}