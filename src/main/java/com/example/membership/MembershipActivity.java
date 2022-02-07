package com.example.membership;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MembershipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership);

        TableLayout table = (TableLayout)findViewById(R.id.tabel);
        String[][] testData = getTestData();
        for (String[] testDatum : testData) {
            Context applicationContext = getApplicationContext();

            // 卡号
            TableRow tableRow = new TableRow(applicationContext);
            TextView textView = new TextView(applicationContext);
            textView.setText(testDatum[0]);
            textView.setWidth(50);
            tableRow.addView(textView);

            textView = new TextView(applicationContext);
            textView.setText(testDatum[1]);
            tableRow.addView(textView);


            textView = new TextView(applicationContext);
            textView.setText(testDatum[2]);
            tableRow.addView(textView);

            textView = new TextView(applicationContext);
            textView.setText(testDatum[4]);
            tableRow.addView(textView);

            table.addView(tableRow);
        }
    }

    String[][] getTestData() {
        int dataCount = 10;
        int columnCount = 5;
        int cardIndex = 1000;
        String[][] data = new String[dataCount][columnCount];
        for (int i = 0; i < dataCount; i++) {
            data[i][0] = String.valueOf(cardIndex++);
            data[i][1] = "Test" + i;
            data[i][2] = "13333333333";
            data[i][3] = "2000.01.01";
            data[i][4] = String.valueOf(2000 + i);
        }
        return data;
    }
}