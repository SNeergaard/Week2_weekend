package com.example.week2_weekend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.week2_weekend.model.celebrity.Celebrity;
import com.example.week2_weekend.model.datasource.local.database.CelebDBhelper;

import java.util.ArrayList;

public class DatabaseActivity extends AppCompatActivity {
    RecyclerView rvCelebData;
    CelebRecyclerViewAdapter adapter;
    CelebDBhelper dbHelper;
    EditText etCelebAge;
    EditText etCelebName;
    EditText etCelebGender;
    EditText etCelebType;
    String selectedId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        dbHelper = new CelebDBhelper(this);
        bindViews();
        initRecyclerView();
    }

    private void bindViews() {
        rvCelebData = findViewById(R.id.rvCelebData);
        etCelebName = findViewById(R.id.etCelebName);
        etCelebAge = findViewById(R.id.etCelebAge);
        etCelebGender = findViewById(R.id.etCelebGender);
        etCelebType = findViewById(R.id.etCelebType);
    }

    private void populateView(Celebrity celebrity){
        etCelebName.setText(celebrity.getCelebrityName());
        etCelebAge.setText(celebrity.getCelebrityAge());
        etCelebGender.setText(celebrity.getCelebrityGender());
        etCelebType.setText(celebrity.getCelebrityType());
        selectedId = celebrity.getId();
    }

    private void initRecyclerView() {
        ArrayList<Celebrity> celebList = dbHelper.getAllCelebs();
        adapter = new CelebRecyclerViewAdapter(celebList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCelebData.setLayoutManager(layoutManager);
        rvCelebData.setAdapter(adapter);
    }

    public void onDatabasecrudClicked(View view){
        switch (view.getId()){
            case R.id.btnAdd:
                Celebrity celebrity = new Celebrity();
                final String name = etCelebName.getText().toString();
                final String age = etCelebAge.getText().toString();
                final String gender = etCelebGender.getText().toString();
                final String type = etCelebType.getText().toString();
                celebrity.setCelebrityName(name);
                celebrity.setCelebrityAge(age);
                celebrity.setCelebrityGender(gender);
                celebrity.setCelebrityType(type);
                dbHelper.insertCelebIntoBD(celebrity);
                adapter.onDatabaseChange(dbHelper.getAllCelebs());
                break;
            case R.id.btnDelete:
                dbHelper.deleteCelebInDB(selectedId);
                adapter.onDatabaseChange(dbHelper.getAllCelebs());
                break;
            case R.id.btnUpdate:
                Celebrity celebUpdate = new Celebrity();
                final String nameUpdate = etCelebName.getText().toString();
                final String ageUpdate = etCelebAge.getText().toString();
                final String genderUpdate = etCelebGender.getText().toString();
                final String typeUpdate = etCelebType.getText().toString();
                celebUpdate.setCelebrityName(nameUpdate);
                celebUpdate.setCelebrityAge(ageUpdate);
                celebUpdate.setCelebrityGender(genderUpdate);
                celebUpdate.setCelebrityType(typeUpdate);
                dbHelper.updateCelebinDB(selectedId, celebUpdate);
                adapter.onDatabaseChange(dbHelper.getAllCelebs());
                break;
        }
    }
}
