package com.example.zotreddit;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUsername;
        TextView tvPost;
        TextView tvUpvotes;
        Button btnReply;
        Button btnUpvote;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername.findViewById(R.id.tvUsername);
            tvPost.findViewById(R.id.tvPost);
            tvUpvotes.findViewById(R.id.tvUpvotes);
            btnReply.findViewById(R.id.btnReply);
            btnUpvote.findViewById(R.id.btnUpvote);

        }
    }

}
