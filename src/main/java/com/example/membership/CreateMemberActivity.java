package com.example.membership;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.membership.database.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateMemberActivity extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_member);

        DBHelper dbHelper = new DBHelper(this, "membership.db", null, 1);
        db = dbHelper.getWritableDatabase();

        Button addMemberBtn = (Button)findViewById(R.id.addMemberBtn);
        addMemberBtn.setOnClickListener(view -> {
            TextView memberNameInput = (TextView)findViewById(R.id.memberNameInput);
            String memberName = memberNameInput.getText().toString();

            TextView phoneNumberInput = (TextView)findViewById(R.id.phoneNumberInput);
            String phoneNumber = phoneNumberInput.getText().toString();

            ContentValues contentValues = new ContentValues();
            if ("".equals(memberName)) {
                return;
            }

            if (phoneNumber == null || "".equals(phoneNumber)) {
                return;
            }
            contentValues.put("name", memberName);

            TextView initPointInput = (TextView)findViewById(R.id.initPointInput);
            String initPoints = initPointInput.getText().toString();

            String dateTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            // todo 校验手机号码唯一性
            contentValues.put("phone", phoneNumber);
            contentValues.put("points", initPoints);
            contentValues.put("create_time", dateTime);
            contentValues.put("detail", "新建会员 " + memberName + ", 手机号码 " + phoneNumber + ", 创建时间 " + dateTime);
            db.insert("membership", null, contentValues);

            Intent i = new Intent(CreateMemberActivity.this, MembershipActivity.class);
            startActivity(i);
        });

        Button cancelAddMemberBtn = (Button)findViewById(R.id.cancelAddMemberBtn);
        cancelAddMemberBtn.setOnClickListener(view -> {
            Intent i = new Intent(CreateMemberActivity.this, MembershipActivity.class);
            startActivity(i);
        });
    }
}