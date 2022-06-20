package at.petshome.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import at.petshome.R;

public class CreateProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
    }

    public void createProfile(View view) {
        EditText name = findViewById(R.id.profile_name);
        EditText type = findViewById(R.id.profile_type);
        EditText address = findViewById(R.id.profile_address);


    }
}