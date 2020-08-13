package com.example.assignment3.Activities;

import Models.StudentDetails;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.assignment3.R;

public class StudentAddingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_adding);
        Button button = findViewById(R.id.student_submit);
        getSupportActionBar().setTitle("ADD STUDENT");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = findViewById(R.id.student_name);
                EditText studentclass = findViewById(R.id.student_class);
                EditText studentrollno= findViewById(R.id.student_rollno);
                StudentDetails studentDetails = new StudentDetails(name.getText().toString(),
                                                                    new Integer(studentclass.getText().toString()),
                                                                    studentrollno.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("studentdetail" , studentDetails);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}