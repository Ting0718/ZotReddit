package com.example.zotreddit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import adapters.ReplyAdapter;

public class DetailMessage extends AppCompatActivity {

    TextView tvUsername;
    TextView tvPost;
    TextView tvUpvotes;
    Button btnReply;
    Button btnUpvote;
    TextView tvReply;

    List<Reply> replies;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_message);
        RecyclerView rvReply = findViewById(R.id.rvReply);
        replies = new ArrayList<>();

        ReplyAdapter replyAdapter = new ReplyAdapter(this, replies);
        rvReply.setAdapter(replyAdapter);
        rvReply.setLayoutManager(new LinearLayoutManager(this));


        tvUsername = findViewById(R.id.tvUsername);
        tvPost = findViewById(R.id.tvPost);
        tvUpvotes = findViewById(R.id.tvUpVotes);
        btnReply = findViewById(R.id.btnReply);
        btnUpvote = findViewById(R.id.btnUpvote);
        tvReply = findViewById(R.id.tvReply);

        Message message = Parcels.unwrap(getIntent().getParcelableExtra("message"));
        tvUsername.setText(message.getPoster());
        tvPost.setText(message.getPost());
        String upvotes = String.valueOf(message.getUpvotes());
        tvUpvotes.setText(upvotes);

        databaseReference = FirebaseDatabase.getInstance().getReference("reply");


        btnReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = tvUsername.getText().toString();
                String post = tvReply.getText().toString();
                int initial_upvote = 0;

                if (!post.isEmpty())
                {
                    String key = databaseReference.push().getKey();

                    Reply reply = new Reply(username, post, initial_upvote);

                    replies.add(reply);

                    databaseReference.child(key).setValue(reply);
                }
                else
                {
                    tvPost.setError("the Post content cannot be empty");
                }

            }
        });

    }
}
