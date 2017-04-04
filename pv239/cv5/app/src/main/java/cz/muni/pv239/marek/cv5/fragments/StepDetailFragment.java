package cz.muni.pv239.marek.cv5.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cz.muni.pv239.marek.cv5.R;

public class StepDetailFragment extends Fragment {

    public final static String KEY_POSITION = "position";
    int mCurrentPosition = -1;

    String[] mStepDetails;
    TextView mStepDetailTextView;

    public StepDetailFragment(){
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mStepDetails = getResources().getStringArray(R.array.step_details);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (savedInstanceState != null){
            mCurrentPosition = savedInstanceState.getInt(KEY_POSITION);
        }

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        mStepDetailTextView = (TextView) view.findViewById(R.id.step_detail);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null){
            setDescription(args.getInt(KEY_POSITION));
        } else if(mCurrentPosition != -1){
            setDescription(mCurrentPosition);
        }
    }

    public void setDescription(int descriptionIndex){
        mStepDetailTextView.setText(mStepDetails[descriptionIndex]);
        mCurrentPosition = descriptionIndex;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_POSITION,mCurrentPosition);
    }
}
