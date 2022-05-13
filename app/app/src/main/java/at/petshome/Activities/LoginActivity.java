package at.petshome.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        email_field.setError(null);
        password_field.setError(null);
        String password = password_field.getText().toString();



        if(!regexTesterEmail(email)){
            email_field.setError("Wrong format");
        }
        if(!regexPasswordTester(password)){
            if(password.length() < 8){
                password_field.setError("Password too short");
            }
            else {
                password_field.setError("Wrong format");
            }
        }

        boolean is_emailValid = regexTesterEmail(email);
        boolean is_passwordValid = regexPasswordTester(password);

        if(is_emailValid && is_passwordValid){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
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

    public boolean regexTesterEmail(String email){
        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean regexPasswordTester(String password){
        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
            Matcher matcher = pattern.matcher(password);
            return matcher.matches();
        }
}