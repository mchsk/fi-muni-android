package cz.muni.pv239.marek.cv5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // use Timber for better logging :)
        Timber.plant(new Timber.DebugTree());

        // this is how the ButterKnife is bound
        ButterKnife.bind(this);
    }

    @OnClick(R.id.recyclerActivityButton)
    public void openRecyclerViewActivity() {
        Intent intent = new Intent(getBaseContext(), RecyclerViewActivity.class);
        startActivity(intent);
    }

}
