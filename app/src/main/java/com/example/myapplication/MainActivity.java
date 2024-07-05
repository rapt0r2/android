package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextRollNumber;
    private EditText editTextSemester;
    private TextView textViewResult;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextRollNumber = findViewById(R.id.editTextRollNumber);
        editTextSemester = findViewById(R.id.editTextSemester);
        textViewResult = findViewById(R.id.textViewResult);

        // Initialize the database helper
        databaseHelper = new DatabaseHelper(this);
    }

    public void fetchResults(View view) {
        String name = editTextName.getText().toString();
        int rollNumber = Integer.parseInt(editTextRollNumber.getText().toString());
        int semester = Integer.parseInt(editTextSemester.getText().toString());

        // Query the database and fetch results based on user input
        String result = databaseHelper.fetchResult(name, rollNumber, semester);

        if (result != null) {
            textViewResult.setText("Result: " + result);
        } else {
            textViewResult.setText("No result found.");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database helper when the activity is destroyed
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}
