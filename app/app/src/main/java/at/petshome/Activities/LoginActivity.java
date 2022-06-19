package at.petshome.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.petshome.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void onLogin(View view) {
        EditText email_field = (EditText)findViewById(R.id.login_email);
        EditText password_field = (EditText)findViewById(R.id.login_password);

        email_field.setError(null);
        password_field.setError(null);

        String email = email_field.getText().toString();
        String password = password_field.getText().toString();

        SQLiteDatabase database = openOrCreateDatabase("PetsHome", MODE_PRIVATE, null);

        Cursor cursor = database.rawQuery(String.format("SELECT * FROM users"), null);

        while (cursor.moveToNext()) {
            String currentEmail = cursor.getString(0);
            String currentPassword = cursor.getString(2);

            if (currentEmail.equals(email) && currentPassword.equals(password)) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return;
            }
            else if (currentEmail.equals(email) && !currentPassword.equals(password)) {
                password_field.setError("Wrong password");
                return;
            }
        }
        email_field.setError("Wrong email");
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