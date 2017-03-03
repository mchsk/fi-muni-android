package cz.muni.pv239.marek.cv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import cz.muni.pv239.marek.cv2.api.GitHubApi;
import cz.muni.pv239.marek.cv2.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewActivity extends AppCompatActivity {

    private GitHubApi mGitHubApi = new GitHubApi();
    private ListView mWatchersListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        loadWatchers("openwrt", "openwrt");

    }

    private void populateListView(final List<User> watcherList) {
        UserAdapter adapter = new UserAdapter(ListViewActivity.this, watcherList);
        mWatchersListView = (ListView)findViewById(R.id.list);
        mWatchersListView.setAdapter(adapter);
        mWatchersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(ListViewActivity.this, "Clicked at " +
                        watcherList.get(position).getLogin(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadWatchers(String username, String repositoryName) {
        Call<List<User>> userCall = mGitHubApi.getService().getWatherList(username, repositoryName);
        userCall.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                populateListView(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

    }

}
