package com.shorifulislam.digitalmoneybag;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    TextView addExpense,addIncome,TotalExpense,totalIncome,tvCalculate,showExpense,showIncome;
    DataBaseHelper dataBaseHelper;

    DrawerLayout drawerLayout;
    ImageView openMenu, liked;
    NavigationView navigationView;
    MaterialToolbar materialToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseHelper = new DataBaseHelper(this);

        addExpense = findViewById(R.id.addExpense);
        TotalExpense = findViewById(R.id.totalExpense);
        totalIncome = findViewById(R.id.totalIncome);
        addIncome = findViewById(R.id.addIncome);
        tvCalculate = findViewById(R.id.tvCalculate);
        showExpense = findViewById(R.id.showExpense);
        showIncome = findViewById(R.id.showIncome);

        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
        openMenu = findViewById(R.id.openMenu);
        materialToolbar = findViewById(R.id.materialToolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                MainActivity.this,drawerLayout,materialToolbar,R.string.close,R.string.open
        );
        drawerLayout.addDrawerListener(toggle);

        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData.TITILE = true;
                startActivity(new Intent(MainActivity.this, AddData.class));
            }
        });

        addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData.TITILE = false;
                startActivity(new Intent(MainActivity.this,AddData.class));
            }
        });


        showExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAllData.CATEGORY = true;
                startActivity(new Intent(MainActivity.this,ShowAllData.class));
            }
        });


        showIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAllData.CATEGORY = false;
                startActivity(new Intent(MainActivity.this,ShowAllData.class));
            }
        });

        UpdateUI();

    }


    //===================================================================
    public void UpdateUI(){
        TotalExpense.setText("৳ "+dataBaseHelper.CalculateTotalExpense());
        totalIncome.setText("৳ "+dataBaseHelper.CalculateTotalIncome());
        tvCalculate.setText("৳ "+dataBaseHelper.CalculateWholeIncome());
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        UpdateUI();
    }


}