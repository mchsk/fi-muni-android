package cz.muni.pv239.marek.cv5.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cz.muni.pv239.marek.cv5.R;

/**
 * Created by Andy on 04-Jan-15.
 */
public class StepTitleFragment extends ListFragment {

    public StepTitleFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] stepTitles = getResources().getStringArray(R.array.step_titles);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, stepTitles);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        OnVersionNameSelectionChangeListener listener = (OnVersionNameSelectionChangeListener) getActivity();
        listener.OnSelectionChanged(position);
    }
}
