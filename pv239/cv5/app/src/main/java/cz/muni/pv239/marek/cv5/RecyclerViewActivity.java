package cz.muni.pv239.marek.cv5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cz.muni.pv239.marek.cv5.api.GitHubService;
import cz.muni.pv239.marek.cv5.model.User;
import cz.muni.pv239.marek.cv5.recyclerview.DividerItemDecoration;
import cz.muni.pv239.marek.cv5.recyclerview.RecyclerTouchListener;
import cz.muni.pv239.marek.cv5.recyclerview.WatchersAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class RecyclerViewActivity extends AppCompatActivity {

    private final List<User> watcherList = new ArrayList<>();

    @Inject
    WatchersAdapter mAdapter;

    @Inject
    GitHubService mGitHubService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        ((Cv5App) getApplication()).getAppComponent().inject(this);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setWatcherList(watcherList);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                User watcher = watcherList.get(position);
                Toast.makeText(getApplicationContext(), "You clicked + " + watcher.getLogin(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        loadWatchers("openwrt", "openwrt");
    }


    private void loadWatchers(String username, String repositoryName) {
        Call<List<User>> userCall = mGitHubService.getWatcherList(username, repositoryName);
        userCall.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                for (User user : response.body()) {
                    watcherList.add(user);
                }

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Timber.d(t, "failed getWatcherList call");
            }
        });
    }
}
