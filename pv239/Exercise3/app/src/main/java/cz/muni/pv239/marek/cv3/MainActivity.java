package cz.muni.pv239.marek.cv3;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_WRITE_PERMISSION = 1001;
    private static final String TAG = "MainActivity";
    private Button mSaveButton;
    private Button mRealmSampleButton;
    private EditText mContentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareUi();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //
            } else {
                savingPermissionCheck();
            }
        }
    }


    private void prepareUi() {
        mContentEditText = (EditText) findViewById(R.id.contentEditText);
        mSaveButton = (Button) findViewById(R.id.saveButton);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savingPermissionCheck();
            }
        });
        mRealmSampleButton = (Button) findViewById(R.id.realmSampleButton);
        mRealmSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void savingPermissionCheck() {

        // runtime permissions start with the Marshmallow ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // let's check whethere we already have the permission
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // android helper method to tell us if it's useful to show a hint
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // show some alert explaining why it is important to grant the permission
                    showSavingPermissionRationale(this);
                } else {
                    // just straight to the point
                    requestPermissions(
                            new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_WRITE_PERMISSION);
                }
            } else {
                // Marshmallow or higher but the permissions are granted -> save the data
                saveData();
            }

        } else {
            // lower than Marshmallow -> permissions were granted during the install process
            saveData();
        }
    }

    private void showSavingPermissionRationale(final Context context) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Granting the permission needed");
        alertBuilder.setMessage("Hi there, the app needs to write to the external storage to " +
                "work properly. The content from the screen is stored there. " +
                "Thank you for the understanding.");
        alertBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // this way you can get to the screen to set the permissions manually
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("package", getPackageName(), null));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    private void saveData() {
        try {
            // the content
            String content = mContentEditText.getText().toString();

            // getting the root sd card directory
            File rootDir = ContextCompat.getExternalFilesDirs(this, null)[0];

            // it may happen we do not have external memory available now
            if (rootDir == null) {
                Toast.makeText(this, "SD card not found.", Toast.LENGTH_SHORT).show();
                return;
            }

            File file = new File(rootDir, "cv3.txt");

            // this way you can log
            Log.i(TAG, file.getAbsolutePath());

            // make sure the file exists
            if (!file.exists()) {
                Boolean created = file.createNewFile();
                if (!created) {
                    Toast.makeText(this, "Could not create the file.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            // writing the data
            FileWriter fileWritter = new FileWriter(file.getName(), true);
            BufferedWriter bufferedWritter = new BufferedWriter(fileWritter);
            bufferedWritter.write(content);
            bufferedWritter.close();

            // looks okay!
            Toast.makeText(this, "Saved.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Not saved: " +
                    e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
