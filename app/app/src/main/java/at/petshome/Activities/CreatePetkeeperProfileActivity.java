package at.petshome.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import at.petshome.R;
import at.petshome.Miscellaneous.Settings;

public class CreatePetkeeperProfileActivity extends AppCompatActivity {
    private ArrayList<String> mList = null;
    private ArrayAdapter mAdapter = null;
    private Spinner mSpinner = null;

    private EditText mEmailField = null;
    private EditText mNameField = null;
    private EditText mAboutField = null;
    private EditText mCityField = null;
    private EditText mZIPField = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_petkeeper_profile);

        mSpinner = findViewById(R.id.petkeeper_type);
        mList = new ArrayList<>();
        mList.add("Dog");
        mList.add("Cat");
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, mList);
        mSpinner.setAdapter(mAdapter);

        mEmailField = findViewById(R.id.petkeeper_email);
        mNameField = findViewById(R.id.petkeeper_full_name);
        mAboutField = findViewById(R.id.petkeeper_aboutyou);
        mCityField = findViewById(R.id.petkeeper_city);
        mZIPField = findViewById(R.id.petkeeper_zip);
    }

    public void createProfile(View view) {
        String petType = mList.get(mSpinner.getSelectedItemPosition());

        boolean wrong = false;

        if (mEmailField.getText() == null || mEmailField.getText().toString().equals("")) {
            mEmailField.setError("Wrong format");
            wrong = true;
        }

        if (mNameField.getText() == null || mNameField.getText().toString().equals("")) {
            mNameField.setError("Wrong format");
            wrong = true;
        }

        if (mAboutField.getText() == null || mAboutField.getText().toString().equals("")) {
            mAboutField.setError("Wrong format");
            wrong = true;
        }

        if (mCityField.getText() == null || mCityField.getText().toString().equals("")) {
            mCityField.setError("Wrong format");
            wrong = true;
        }

        if (mZIPField.getText() == null || mZIPField.getText().toString().equals("")) {
            mZIPField.setError("Wrong format");
            wrong = true;
        }

        int zipCode = 0;

        try {
            zipCode = Integer.parseInt(mZIPField.getText().toString());
        }
        catch (NumberFormatException ex) {
            wrong = true;
        }

        if (wrong) {
            return;
        }

        SQLiteDatabase database = openOrCreateDatabase("PetsHome", MODE_PRIVATE, null);
        database.execSQL(String.format("INSERT INTO petkeepers(user_id, email, name, about , type, city, zip) VALUES(%d, '%s', '%s', '%s', '%s', '%s', %d)", Settings.getInstance().getUid(), mEmailField.getText().toString(), mNameField.getText().toString(), mAboutField.getText().toString(), petType, mCityField.getText().toString(), zipCode));
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}