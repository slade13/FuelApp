package com.example.fuelapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fuelapp.Database.DatabaseHelper;
import com.example.fuelapp.R;

import java.util.Calendar;

public class AddFuelActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String COLUMN_VEHICLE_ID = "_id";
    final Calendar c = Calendar.getInstance();
    private int mYear, mMonth, mDay;
    private String choosenDate;
    private EditText newFuelTypeEt, newFuelAmountEt, newFuelCostEt, newMileageEt;
    private TextView newFuelDateTv;
    private Button addNewFuelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fuel);

        newFuelTypeEt = findViewById(R.id.newFuelTypeEt);
        newFuelAmountEt = findViewById(R.id.newFuelAmountEt);
        newFuelCostEt = findViewById(R.id.newFuelCostEt);
        newMileageEt = findViewById(R.id.newMileageEt);
        newFuelDateTv = findViewById(R.id.newFuelDateTv);
        addNewFuelBtn = findViewById(R.id.addNewFuelBtn);

        newFuelDateTv.setText(getCurrentDate());
        newFuelDateTv.setOnClickListener(this);
        addNewFuelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == newFuelDateTv) {
            chooseDate(newFuelDateTv);
        } else if (v == addNewFuelBtn) {
            DatabaseHelper myDB = new DatabaseHelper(AddFuelActivity.this);
            myDB.addFuel(
                newFuelTypeEt.getText().toString().trim(),
                Float.parseFloat(newFuelAmountEt.getText().toString().trim()),
                Float.parseFloat(newFuelCostEt.getText().toString().trim()),
                Integer.parseInt(newMileageEt.getText().toString().trim()),
                newFuelDateTv.getText().toString().trim(),
                getCarId()
            );

            Intent intent2 = new Intent(this, FuelActivity.class);
            intent2.putExtra(COLUMN_VEHICLE_ID, getCarId());
            startActivity(intent2);
        }
    }

    private String getCarId() {
        Intent intent = getIntent();
        return intent.getStringExtra(MainActivity.COLUMN_VEHICLE_ID);
    }

    public String getCurrentDate() {
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        if (mDay < 10 && mMonth < 10) {
            return "0" + mDay + ".0" + (mMonth + 1) + "." + mYear;
        } else if (mDay < 10) {
            return "0" + mDay + "." + (mMonth + 1) + "." + mYear;
        } else if (mMonth < 10) {
            return mDay + ".0" + (mMonth + 1) + "." + mYear;
        } else {
            return mDay + "." + (mMonth + 1) + "." + mYear;
        }
    }

    private void chooseDate(TextView option) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    if (dayOfMonth < 10 && monthOfYear < 9) {
                        choosenDate = "0" + dayOfMonth + ".0" + (monthOfYear + 1) + "." + year;
                    } else if (dayOfMonth < 10) {
                        choosenDate = "0" + dayOfMonth + "." + (monthOfYear + 1) + "." + year;
                    } else if (monthOfYear < 9) {
                        choosenDate = dayOfMonth + ".0" + (monthOfYear + 1) + "." + year;
                    } else {
                        choosenDate = dayOfMonth + "." + (monthOfYear + 1) + "." + year;
                    }
                    option.setText(choosenDate);
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}