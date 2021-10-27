package com.example.calculate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String SAVED = "SAVED";

    TextView textView;
    CalculateLogic calculateLogic = null;

    int[] numbers = {
            R.id.btn_one,
            R.id.btn_two,
            R.id.btn_three,
            R.id.btn_four,
            R.id.btn_five,
            R.id.btn_six,
            R.id.btn_seven,
            R.id.btn_eight,
            R.id.btn_nine,
            R.id.btn_zero
    };

    int[] operation = {
            R.id.btn_plus,
            R.id.btn_minus,
            R.id.btn_multiplication,
            R.id.btn_division,
            R.id.btn_equals,
            R.id.btn_discharge
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (calculateLogic == null){
            calculateLogic = new CalculateLogic();
        }

        textView = findViewById(R.id.input_and_result);
        for (int num : numbers) {
            findViewById(num).setOnClickListener(numberClickListener);
        }

        for (int oper : operation) {
            findViewById(oper).setOnClickListener(operationClickListener);
        }

    }

    View.OnClickListener numberClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calculateLogic.numberClick(v.getId());
            textView.setText(calculateLogic.getText());
        }
    };

    View.OnClickListener operationClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calculateLogic.operationClick(v.getId());
            textView.setText(calculateLogic.getText());
        }
    };

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED, calculateLogic);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculateLogic = (CalculateLogic) savedInstanceState.getParcelable(SAVED);
        textView.setText(calculateLogic.getText());
    }
}
