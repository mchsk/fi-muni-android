package cz.muni.pv239.marek.cv5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

    @OnClick(R.id.multipaneActivityButton)
    public void openMultiPaneFragmentActivity() {
        Intent intent = new Intent(getBaseContext(), MultiPaneActivity.class);
        startActivity(intent);
    }

}
