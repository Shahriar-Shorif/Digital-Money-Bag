package com.shorifulislam.digitalmoneybag;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ShowAllData extends AppCompatActivity {

    ListView listView;
    DataBaseHelper dataBaseHelper;
    TextView cat;

    public static boolean CATEGORY = true;

    public static ArrayList<HashMap<String,String>> arrayList;
    public static HashMap<String, String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_data);

        listView = findViewById(R.id.listview);
        cat = findViewById(R.id.cat);
        dataBaseHelper = new DataBaseHelper(this);

        if (CATEGORY == true) cat.setText("খরচের হিসাব");
        else cat.setText("আয়ের হিসাব");
        LoadData();




    }

    private void LoadData() {
        Cursor cursor = null;
        if(CATEGORY == true) cursor = dataBaseHelper.getDataExpense();
            else cursor = dataBaseHelper.getDataIncome();

        if(cursor != null && cursor.getCount()>=0){
            arrayList = new ArrayList<>();
            for(;cursor.moveToNext();){
                int id = cursor.getInt(0);
                double amount = cursor.getDouble(1);
                String reason = cursor.getString(2);
                String date = cursor.getString(3);


                hashMap = new HashMap<>();
                hashMap.put("id", String.valueOf(id));
                hashMap.put("amount", String.valueOf(amount));
                hashMap.put("reason",reason);
                hashMap.put("date", date);
                arrayList.add(hashMap);
            }

            MyAdapter myAdapter = new MyAdapter();
            listView.setAdapter(myAdapter);
        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }


    //=======================================================//
        public class MyAdapter extends BaseAdapter{
            @Override
            public int getCount() {
                return arrayList.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = getLayoutInflater().inflate(R.layout.list_item_sample,null);
                TextView Reason = view.findViewById(R.id.Reason);
                TextView date = view.findViewById(R.id.date);
                TextView amount = view.findViewById(R.id.amount);
                TextView delete = view.findViewById(R.id.delete);

                HashMap<String,String>hashMap1 = arrayList.get(position);
                String id = hashMap1.get("id");
                String rea = hashMap1.get("reason");
                String DateTime = hashMap1.get("date");
                String Amount = hashMap1.get("amount");

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (CATEGORY == true) dataBaseHelper.DeleteDataByIdFromExpense(id);
                        else dataBaseHelper.DeleteDataByIdFromIncome(id);
                        LoadData();

                    }
                });




                Reason.setText("কারন: "+rea);
                date.setText("তারিখ: "+DateTime);
                amount.setText("টাকার পরিমাণ: "+Amount);

                return view;

        }
    }

    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }


}