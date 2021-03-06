package cz.muni.pv239.marek.exercise5.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import cz.muni.pv239.marek.exercise5.R;
import cz.muni.pv239.marek.exercise5.model.User;

public class WatchersAdapter extends RecyclerView.Adapter<WatcherViewHolder> {
    private final Context mContext;
    private List<User> mWatcherList = Collections.emptyList();

    @Inject
    WatchersAdapter(Context context) {
        this.mContext = context;
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
        User watcher = mWatcherList.get(position);
        holder.loginTextView.setText(watcher.getLogin());
        Glide.with(mContext).load(watcher.getAvatarUrl()).into(holder.avatarImageView);
    }

    @Override
    public int getItemCount() {
        return mWatcherList.size();
    }

    public void setmWatcherList(List<User> mWatcherList) {
        this.mWatcherList = mWatcherList;
    }
}
