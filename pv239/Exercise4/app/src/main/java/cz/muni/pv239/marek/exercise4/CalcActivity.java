package cz.muni.pv239.marek.exercise4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalcActivity extends AppCompatActivity {

    Button closeCalcButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        prepareUi();
    }

    private void prepareUi() {
        closeCalcButton = (Button) findViewById(R.id.closeCalcButton);

        closeCalcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
