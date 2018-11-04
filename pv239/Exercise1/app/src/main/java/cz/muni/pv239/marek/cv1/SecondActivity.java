package cz.muni.pv239.marek.cv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    Button mOkButton = null;
    TextView mMessageBodyTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle extras = getIntent().getExtras();
        final String message = extras.getString("EXTRA_MESSAGE", "N/A");

        mMessageBodyTextView = (TextView) findViewById(R.id.messageBodyTextView);
        mMessageBodyTextView.setText(message);

        mOkButton = (Button) findViewById(R.id.closeButton);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // this finishes the activity
                finish();
            }
        });


    }
}
