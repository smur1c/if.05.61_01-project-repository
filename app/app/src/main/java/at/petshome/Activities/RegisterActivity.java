package at.petshome.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.petshome.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void doRegister(View view) {
        EditText name_field = (EditText)findViewById(R.id.register_name);
        EditText email_field = (EditText)findViewById(R.id.register_email);
        EditText password_field = (EditText)findViewById(R.id.register_password);

        if (!regexTesterEmail(email_field.getText().toString()) || !regexTesterPassword(password_field.getText().toString())) {
            email_field.setError("Wrong format");
            password_field.setError("Wrong format");
            return;
        }

        SQLiteDatabase database = openOrCreateDatabase("PetsHome", MODE_PRIVATE, null);

        database.execSQL("CREATE TABLE users(email TEXT NOT NULL PRIMARY KEY, name TEXT NOT NULL, password NOT NULL)");

        Cursor users = database.rawQuery("SELECT * FROM users", null);

        while (users.moveToNext()) {
            if (users.getString(0).equals(email_field.getText().toString())) {
                email_field.setError("Email already in use");
                return;
            }
        }

        database.execSQL(String.format("INSERT INTO users(email, name, password) VALUES('%s', '%s', '%s')", email_field.getText(), name_field.getText(), password_field.getText()));
    }

    public boolean regexTesterEmail(String email){
        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean regexTesterPassword(String password){
        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}