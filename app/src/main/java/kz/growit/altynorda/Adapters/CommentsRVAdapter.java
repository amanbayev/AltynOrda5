package kz.growit.altynorda.Adapters;

/**
 * Created by Талгат on 27.11.2015.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import kz.growit.altynorda.Models.Comments;
import kz.growit.altynorda.R;
import kz.growit.altynorda.singleton.AppController;

public class CommentsRVAdapter extends RecyclerView.Adapter<CommentsRVAdapter.CommentViewHolder> {
    private ArrayList<Comments> comments;

    public CommentsRVAdapter(ArrayList<Comments> comments) {
        this.comments = comments;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.comment_row_cv, parent, false);

        return new CommentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Comments temp = comments.get(position);
        holder.commentText.setText(temp.getText());
        holder.thumb.setImageUrl(temp.getImageUrl(), AppController.getInstance().getImageLoader());
        holder.name.setText(temp.getCommentatorName());
        holder.timestamp.setText(temp.getFormattedDate());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        private TextView commentText, name, timestamp;
        private NetworkImageView thumb;

        public CommentViewHolder(View itemView) {
            super(itemView);
            commentText = (TextView) itemView.findViewById(R.id.commentTextCommentRowTV);
            thumb = (NetworkImageView) itemView.findViewById(R.id.commentatorThumbNIV);
            name = (TextView) itemView.findViewById(R.id.commentatorNameTV);
            timestamp = (TextView) itemView.findViewById(R.id.timestampCommentatorTV);
        }
    }
}
