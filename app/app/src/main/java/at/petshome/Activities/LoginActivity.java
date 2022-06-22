package at.petshome.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import at.petshome.R;
import at.petshome.Miscellaneous.Hash;
import at.petshome.Miscellaneous.Settings;

public class LoginActivity extends AppCompatActivity {
    private EditText mEmailField;
    private EditText mPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        mEmailField = findViewById(R.id.login_email);
        mPasswordField = findViewById(R.id.login_password);
        super.onStart();
    }

    public void onLogin(View view) {
        mEmailField.setError(null);
        mPasswordField.setError(null);

        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        SQLiteDatabase database = openOrCreateDatabase("PetsHome", MODE_PRIVATE, null);
        Cursor cursor;
        try {
            cursor = database.rawQuery(String.format("SELECT * FROM users"), null);
        }
        catch (SQLException ex) {
            mEmailField.setError("Email not found");
            return;
        }

        while (cursor.moveToNext()) {
            String currentEmail = cursor.getString(1);
            String currentPassword = cursor.getString(3);

            if (currentEmail.equals(email) && Hash.getInstance().hash(password).equals(currentPassword)) {
                Settings.getInstance().setEmail(currentEmail);
                Settings.getInstance().setUid(cursor.getInt(0));
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return;
            }
            else if (currentEmail.equals(email) && !currentPassword.equals(password)) {
                mPasswordField.setError("Wrong password");
                return;
            }
        }
        mEmailField.setError("Wrong email");
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