package at.petshome.Fragments;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

import at.petshome.Activities.EditProfileActivity;
import at.petshome.Activities.RegisterActivity;
import at.petshome.Pet;
import at.petshome.R;
import at.petshome.Settings;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    ArrayList<Pet> mList = null;
    ArrayAdapter mAdapter = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void updateList() {
        mList.clear();
        File file = getContext().getDatabasePath("PetsHome");
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(file, null);
        Cursor cursor = database.rawQuery(String.format("SELECT * FROM pets WHERE user_id = %d", Settings.getInstance().getUid()), null);

        while (cursor.moveToNext()) {
            mList.add(new Pet(cursor.getInt(0), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
        }
    }

    private void handleItemClicked(int position) {
        Pet pet = mList.get(position);

        if (pet == null) {
            return;
        }

        Intent intent = new Intent(getContext(), EditProfileActivity.class);
        intent.putExtra("id", pet.getId());
        intent.putExtra("name", pet.getName());
        intent.putExtra("type", pet.getType());
        intent.putExtra("address", pet.getAddress());
        startActivity(intent);
        System.out.println(Settings.getInstance().getPet().getName());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView lv = getView().findViewById(R.id.lv_profile);
        mList = new ArrayList<>();
        updateList();
        mAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, mList);

        lv.setOnItemClickListener((parent, view1, position, id) -> handleItemClicked(position));
        lv.setAdapter(mAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onResume() {
        mAdapter.clear();
        updateList();
        mAdapter.notifyDataSetChanged();
        System.out.println("suscall");
        super.onResume();
    }
}