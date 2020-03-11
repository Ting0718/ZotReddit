package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zotreddit.R;
import com.example.zotreddit.Reply;

import java.util.List;

public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.ViewHolder> {


    Context context;
    List<Reply> replies;

    public ReplyAdapter(Context context, List<Reply> replies) {
        this.context = context;
        this.replies = replies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View replyView = LayoutInflater.from(context).inflate(R.layout.activity_reply, parent, false);
        return new ViewHolder(replyView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reply reply = replies.get(position);
        holder.bind(reply);

    }

    @Override
    public int getItemCount() {
        return replies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUsername;
        TextView tvPost;
        TextView tvUpvotes;
        Button btnUpvote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvPost = itemView.findViewById(R.id.tvPost);
            tvUpvotes = itemView.findViewById(R.id.tvUpvotes);
            btnUpvote = itemView.findViewById(R.id.btnUpvote);
        }

        public void bind(final Reply reply) {
            tvUsername.setText(reply.getPoster());
            tvPost.setText(reply.getPost());
            tvUpvotes.setText(String.valueOf(reply.getUpvotes()));

            btnUpvote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
