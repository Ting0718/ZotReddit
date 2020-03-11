package adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zotreddit.DetailMessage;
import com.example.zotreddit.Message;
import com.example.zotreddit.R;

import org.parceler.Parcels;

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
        RelativeLayout rlLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvPost = itemView.findViewById(R.id.tvPost);
            tvUpvotes = itemView.findViewById(R.id.tvUpvotes);
            rlLayout = itemView.findViewById(R.id.rlLayout);
        }

        public void bind(final Message message) {
            tvUsername.setText(message.getPoster());
            tvPost.setText(message.getPost());
            tvUpvotes.setText(String.valueOf(message.getUpvotes()));

            rlLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailMessage.class);
                    i.putExtra("message", Parcels.wrap(message));
                    context.startActivity(i);
                }
            });

        }


    }
}
