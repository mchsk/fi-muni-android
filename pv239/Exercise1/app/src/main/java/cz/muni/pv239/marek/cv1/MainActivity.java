package cz.muni.pv239.marek.cv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mSendButton = null;
    EditText mMessageBodyEditText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMessageBodyEditText = (EditText) findViewById(R.id.messageBodyEditText);

        mSendButton = (Button) findViewById(R.id.sendButton);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = mMessageBodyEditText.getText().toString();

                // in case user does not fill anything
                if (message.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fill the message ;)", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }

                Intent intent = new Intent(getBaseContext(), SecondActivity.class);
                intent.putExtra("EXTRA_MESSAGE", message);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Sending " + message, Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}