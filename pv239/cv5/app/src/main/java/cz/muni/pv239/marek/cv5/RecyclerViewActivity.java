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


import cz.muni.pv239.marek.cv5.api.GitHubApi;
import cz.muni.pv239.marek.cv5.model.User;
import cz.muni.pv239.marek.cv5.recyclerview.DividerItemDecoration;
import cz.muni.pv239.marek.cv5.recyclerview.WatchersAdapter;
import cz.muni.pv239.marek.cv5.recyclerview.RecyclerTouchListener;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

public class RecyclerViewActivity extends AppCompatActivity {
    private GitHubApi mGitHubApi = new GitHubApi();
    private List<User> watcherList = new ArrayList<>();
    private RecyclerView recyclerView;
    private WatchersAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new WatchersAdapter(watcherList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
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
        Observable<Response<List<User>>> watchersObservalbe = mGitHubApi.getService().getWatcherList(username, repositoryName);
        watchersObservalbe
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(createWatchersObserver());
    }

    private Observer createWatchersObserver() {
        return new DisposableObserver<Response<List<User>>>() {

            @Override
            public void onNext(Response<List<User>> listResponse) {
                for (User watcher : listResponse.body()) {
                    watcherList.add(watcher);
                }

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e);
            }

            @Override
            public void onComplete() {
            }
        };
    }


}