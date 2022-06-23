package at.petshome.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import at.petshome.R;

public class ShowPetkeeperActivity extends AppCompatActivity {
    private TextView mEmailField = null;
    private TextView mNameField = null;
    private TextView mAboutField = null;
    private TextView mPetTypeField = null;
    private TextView mCityField = null;
    private TextView mZipField = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_petkeeper);

        mEmailField = findViewById(R.id.information_email);
        mNameField = findViewById(R.id.information_name);
        mAboutField = findViewById(R.id.information_about);
        mPetTypeField = findViewById(R.id.information_pettype);
        mCityField = findViewById(R.id.information_city);
        mZipField = findViewById(R.id.information_zip);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Bundle bundle = getIntent().getExtras();

        mEmailField.setText("E-mail: " + bundle.getString("email"));
        mNameField.setText("Name: " + bundle.getString("name"));
        mAboutField.setText("About: " + bundle.getString("about"));
        mPetTypeField.setText("Pet type: " + bundle.getString("pettype"));
        mCityField.setText("City: " + bundle.getString("city"));
        mZipField.setText("ZIP: " + bundle.getInt("zip"));
    }
}