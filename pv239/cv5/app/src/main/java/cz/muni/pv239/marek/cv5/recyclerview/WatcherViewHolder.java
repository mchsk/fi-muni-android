package cz.muni.pv239.marek.cv5.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cz.muni.pv239.marek.cv5.R;

/**
 * Created by marek on 04.04.17.
 */

public class WatcherViewHolder extends RecyclerView.ViewHolder {
    public ImageView avatarImageView;
    public TextView loginTextView;

    public WatcherViewHolder(View view) {
        super(view);
        avatarImageView = (ImageView) view.findViewById(R.id.avatarImageView);
        loginTextView = (TextView) view.findViewById(R.id.loginTextView);
    }
}