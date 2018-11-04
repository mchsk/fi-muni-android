package cz.muni.pv239.marek.exercise5;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cz.muni.pv239.marek.exercise5.fragments.OnStepChangedListener;
import cz.muni.pv239.marek.exercise5.fragments.StepDetailFragment;
import cz.muni.pv239.marek.exercise5.fragments.StepTitleFragment;

/**
 * Created by marek on 04.04.17.
 */

public class MultiPaneActivity extends AppCompatActivity implements OnStepChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_pane);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            StepTitleFragment stepTitleFragment = new StepTitleFragment();
            stepTitleFragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, stepTitleFragment)
                    .commit();
        }
    }

    @Override
    public void OnSelectionChanged(int stepIndex) {
        StepDetailFragment stepDetailFragment = (StepDetailFragment) getFragmentManager()
                .findFragmentById(R.id.step_detail_fragment);

        if (stepDetailFragment != null) {
            stepDetailFragment.setDescription(stepIndex);
        } else {
            StepDetailFragment newDesriptionFragment = new StepDetailFragment();
            Bundle args = new Bundle();

            args.putInt(StepDetailFragment.KEY_POSITION, stepIndex);
            newDesriptionFragment.setArguments(args);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.fragment_container, newDesriptionFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

}
