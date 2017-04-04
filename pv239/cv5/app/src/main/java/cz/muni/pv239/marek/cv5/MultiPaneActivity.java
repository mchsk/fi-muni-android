package cz.muni.pv239.marek.cv5;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import cz.muni.pv239.marek.cv5.fragments.StepDetailFragment;
import cz.muni.pv239.marek.cv5.fragments.OnVersionNameSelectionChangeListener;
import cz.muni.pv239.marek.cv5.fragments.StepTitleFragment;

/**
 * Created by marek on 04.04.17.
 */

public class MultiPaneActivity extends AppCompatActivity implements OnVersionNameSelectionChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_pane);

        ButterKnife.bind(this);

        // Check whether the Activity is using the layout verison with the fragment_container
        // FrameLayout and if so we must add the first fragment
        if (findViewById(R.id.fragment_container) != null){

            // However if we are being restored from a previous state, then we don't
            // need to do anything and should return or we could end up with overlapping Fragments
            if (savedInstanceState != null){
                return;
            }

            // Create an Instance of Fragment
            StepTitleFragment stepTitleFragment = new StepTitleFragment();
            stepTitleFragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, stepTitleFragment)
                    .commit();
        }
    }

    @Override
    public void OnSelectionChanged(int versionNameIndex) {
        StepDetailFragment stepDetailFragment = (StepDetailFragment) getFragmentManager()
                .findFragmentById(R.id.step_detail_fragment);

        if (stepDetailFragment != null){
            //
            stepDetailFragment.setDescription(versionNameIndex);
        } else {
            StepDetailFragment newDesriptionFragment = new StepDetailFragment();
            Bundle args = new Bundle();

            args.putInt(StepDetailFragment.KEY_POSITION,versionNameIndex);
            newDesriptionFragment.setArguments(args);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the backStack so the User can navigate back
            fragmentTransaction.replace(R.id.fragment_container,newDesriptionFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
    
}
