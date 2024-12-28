package com.shorifulislam.digitalmoneybag;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddData extends AppCompatActivity {

    EditText amount,reason;
    Button insertData;
    TextView title;
    DataBaseHelper dataBaseHelper;
    public static boolean TITILE = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        dataBaseHelper = new DataBaseHelper(AddData.this);

        amount = findViewById(R.id.amount);
        reason = findViewById(R.id.reason);
        insertData = findViewById(R.id.insertData);
        title = findViewById(R.id.title);

        if(TITILE == true){
            title.setText("ADD EXPENSE");
            insertData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double samount = Double.parseDouble(amount.getText().toString());
                    String Reson = reason.getText().toString();
                    dataBaseHelper.addExpense(samount,Reson);
                    Toast.makeText(AddData.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddData.this,MainActivity.class));
                    finish();
                }
            });
        }else{

            title.setText("ADD INCOME");
            insertData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double samount = Double.parseDouble(amount.getText().toString());
                    String Reson = reason.getText().toString();
                    dataBaseHelper.addIncome(samount,Reson);
                    Toast.makeText(AddData.this, "Inserted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddData.this,MainActivity.class));
                    finish();
                }
            });

        }




    }
}