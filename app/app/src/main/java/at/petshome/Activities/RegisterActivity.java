package at.petshome.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.petshome.R;
import at.petshome.Miscellaneous.Hash;

public class RegisterActivity extends AppCompatActivity {
    private EditText mNameField;
    private EditText mEmailField;
    private EditText mPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mNameField = findViewById(R.id.register_name);
        mEmailField = findViewById(R.id.register_email);
        mPasswordField = findViewById(R.id.register_password);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void doRegister(View view) {
        mNameField.setError(null);
        mEmailField.setError(null);
        mPasswordField.setError(null);

        boolean wrong = false;

        if (!regexTesterEmail(mEmailField.getText().toString())) {
            mEmailField.setError("Wrong format");
            wrong = true;
        }

        if (!regexTesterPassword(mPasswordField.getText().toString())) {
            mPasswordField.setError("Wrong format");
            wrong = true;
        }

        if (mNameField.getText() == null || mNameField.getText().toString().equals("")) {
            mNameField.setError("Wrong format");
            wrong = true;
        }

        if (wrong) {
            return;
        }

        SQLiteDatabase database = openOrCreateDatabase("PetsHome", MODE_PRIVATE, null);

        try {
            database.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, email TEXT NOT NULL, name TEXT NOT NULL, password NOT NULL)");
            database.execSQL("CREATE TABLE pets(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, user_id INTEGER NOT NULL, name TEXT NOT NULL, type TEXT NOT NULL, city TEXT NOT NULL, zip INTEGER NOT NULL, FOREIGN KEY(user_id) REFERENCES users(id))");
            database.execSQL("CREATE TABLE petkeepers(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, user_id INTEGER NOT NULL, email TEXT NOT NULL, name TEXT NOT NULL, about TEXT NOT NULL, type TEXT NOT NULL, city TEXT NOT NULL, zip INTEGER NOT NULL, FOREIGN KEY(user_id) REFERENCES users(id))");
        }
        catch (SQLException ex) {

        }
        //database.execSQL("CREATE TABLE pettypes(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, user_id INTEGER NOT NULL, type TEXT NOT NULL, FOREIGN KEY(user_id) REFERENCES users(id))");

        Cursor users = database.rawQuery("SELECT * FROM users", null);

        while (users.moveToNext()) {
            if (users.getString(1).equals(mEmailField.getText().toString())) {
                mEmailField.setError("Email already in use");
                return;
            }
        }

        database.execSQL(String.format("INSERT INTO users(email, name, password) VALUES('%s', '%s', '%s')", mEmailField.getText(), mNameField.getText(), Hash.getInstance().hash(mPasswordField.getText().toString())));

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
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

    public void deleteDatabase(View view) {
        deleteDatabase("PetsHome");
    }
}