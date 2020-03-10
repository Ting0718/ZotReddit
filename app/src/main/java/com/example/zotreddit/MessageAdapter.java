package com.example.zotreddit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    Context context;
    List<Message> messages;

    public MessageAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View messageView = LayoutInflater.from(context).inflate(R.layout.activity_message, parent, false);
        return new ViewHolder(messageView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        return messages.size();
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
            tvPost.findViewById(R.id.tvMessage);
            tvUpvotes.findViewById(R.id.tvUpvotes);
            btnReply.findViewById(R.id.btnReply);
            btnUpvote.findViewById(R.id.btnUpvote);

        }

        public void bind(Message message) {
            tvUsername.setText(message.getPoster());
            tvPost.setText(message.getPost());
            tvUpvotes.setText(message.getUpvotes());
        }
    }

}
