package cz.muni.pv239.marek.cv5.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import cz.muni.pv239.marek.cv5.R;
import cz.muni.pv239.marek.cv5.model.User;

public class WatchersAdapter extends RecyclerView.Adapter<WatcherViewHolder> {
    private final Context context;
    private List<User> watcherList = Collections.emptyList();

    @Inject
    WatchersAdapter(Context context) {
        this.context = context;
    }

    @Override
    public WatcherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_user_item, parent, false);

        return new WatcherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WatcherViewHolder holder, int position) {
        // we make use of
        User watcher = watcherList.get(position);
        holder.loginTextView.setText(watcher.getLogin());
        Glide.with(context).load(watcher.getAvatarUrl()).into(holder.avatarImageView);
    }

    @Override
    public int getItemCount() {
        return watcherList.size();
    }

    public void setWatcherList(List<User> watcherList) {
        this.watcherList = watcherList;
    }
}
