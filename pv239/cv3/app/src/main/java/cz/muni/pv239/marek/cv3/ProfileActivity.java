package cz.muni.pv239.marek.cv3;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cz.muni.pv239.marek.cv3.model.UserProfile;
import io.realm.Realm;
import io.realm.RealmResults;

public class ProfileActivity extends AppCompatActivity {

    private Button mSaveButton;
    private EditText mNameEditText;
    private EditText mSurnameEditText;

    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // realm init
        Realm.init(this);
        mRealm = Realm.getDefaultInstance();

        // ui refs
        prepareUi();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    private void prepareUi() {
        mNameEditText = (EditText) findViewById(R.id.nameEditText);
        mSurnameEditText = (EditText) findViewById(R.id.surnameEditText);

        mSaveButton = (Button) findViewById(R.id.saveButton);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeProfile();
            }
        });

        // display profile from realm db now
        displayProfile();
    }

    private void storeProfile() {
        mRealm.beginTransaction();

        UserProfile userProfile = mRealm.where(UserProfile.class).findFirst();

        if (userProfile == null) {
            userProfile = mRealm.createObject(UserProfile.class);
        }

        // set the property values
        // notice, we can use .trim() on String instance to eliminate white spaces
        userProfile.setName(mNameEditText.getText().toString().trim());
        userProfile.setSurname(mSurnameEditText.getText().toString().trim());

        // sync with db
        mRealm.commitTransaction();
    }

    private void displayProfile() {
        mRealm.beginTransaction();


        UserProfile userProfile = mRealm.where(UserProfile.class).findFirst();

        if (userProfile != null) {
            mNameEditText.setText(userProfile.getName());
            mSurnameEditText.setText(userProfile.getSurname());
        }
        mRealm.commitTransaction();
    }

}
