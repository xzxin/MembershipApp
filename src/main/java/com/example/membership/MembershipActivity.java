package com.example.membership;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.membership.database.DBHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MembershipActivity extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership);

        DBHelper dbHelper = new DBHelper(this, "membership.db", null, 1);
        db = dbHelper.getReadableDatabase();

        Button createMemberBtn = (Button) findViewById(R.id.createMemberBtn);
        createMemberBtn.setOnClickListener(view -> {
            Intent i = new Intent(MembershipActivity.this, CreateMemberActivity.class);
            startActivity(i);
        });

        TableLayout table = (TableLayout)findViewById(R.id.tabel);
        List<List<String>>testData = getTestData();
        for (List<String> testDatum : testData) {
            Context applicationContext = getApplicationContext();

            // 卡号
            TableRow tableRow = new TableRow(applicationContext);
            TextView textView = new TextView(applicationContext);
            textView.setText(testDatum.get(0));
            textView.setWidth(50);
            tableRow.addView(textView);

            textView = new TextView(applicationContext);
            textView.setText(testDatum.get(1));
            tableRow.addView(textView);


            textView = new TextView(applicationContext);
            textView.setText(testDatum.get(2));
            tableRow.addView(textView);

            textView = new TextView(applicationContext);
            textView.setText(testDatum.get(3));
            tableRow.addView(textView);

            table.addView(tableRow);
        }
    }

    List<List<String>> getTestData() {
//        int dataCount = 10;
//        int columnCount = 5;
//        int cardIndex = 1000;
//        String[][] data = new String[dataCount][columnCount];
//        for (int i = 0; i < dataCount; i++) {
//            data[i][0] = String.valueOf(cardIndex++);
//            data[i][1] = "Test" + i;
//            data[i][2] = "13333333333";
//            data[i][3] = "2000.01.01";
//            data[i][4] = String.valueOf(2000 + i);
//        }
//        return data;
        List<List<String>> lists = new ArrayList<>();
        Cursor cursor = db.query("membership", new String[]{"id", "name", "phone", "points", "create_time", "detail"}, null, null, null, null, null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String points = cursor.getString(3);
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(id));
            list.add(String.valueOf(name));
            list.add(String.valueOf(phone));
            list.add(String.valueOf(points));
            lists.add(list);
        }
        return lists;
    }
}