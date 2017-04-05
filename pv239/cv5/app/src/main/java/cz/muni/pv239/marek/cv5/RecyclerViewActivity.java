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

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.muni.pv239.marek.cv5.api.GitHubService;
import cz.muni.pv239.marek.cv5.model.User;
import cz.muni.pv239.marek.cv5.recyclerview.DividerItemDecoration;
import cz.muni.pv239.marek.cv5.recyclerview.RecyclerTouchListener;
import cz.muni.pv239.marek.cv5.recyclerview.WatchersAdapter;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

public class RecyclerViewActivity extends AppCompatActivity {

    private final List<User> mWatcherList = new ArrayList<>();

    @Inject
    WatchersAdapter mAdapter;

    @Inject
    GitHubService mGitHubService;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        // butterknife
        ButterKnife.bind(this);

        // dagger injection init
        ((Cv5App) getApplication()).getAppComponent().inject(this);

        // recyclerview setup
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // setting the adapter
        mAdapter.setmWatcherList(mWatcherList);
        mRecyclerView.setAdapter(mAdapter);

        // onitemtouch
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                User user = mWatcherList.get(position);
                Toast.makeText(getApplicationContext(),
                        "You clicked + " + user.getLogin(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        loadWatchers("openwrt", "openwrt");
    }


    private void loadWatchers(String username, String repositoryName) {

        // observable
        Observable<Response<List<User>>> watchersObservable = mGitHubService.getWatcherList(username, repositoryName);

        // create subscription
        watchersObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(createWatchersObserver());
    }

    private Observer createWatchersObserver() {
        return new DisposableObserver<Response<List<User>>>() {

            @Override
            public void onNext(Response<List<User>> listResponse) {
                mWatcherList.addAll(listResponse.body());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e);
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
            }
        };
    }


}
