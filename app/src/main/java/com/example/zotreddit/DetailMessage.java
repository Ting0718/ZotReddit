package com.example.zotreddit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import adapters.MessageAdapter;
import adapters.ReplyAdapter;

public class DetailMessage extends AppCompatActivity {

    TextView tvUsername;
    TextView tvPost;
    TextView tvUpvotes;
    Button btnReply;
    Button btnUpvote;
    TextView tvReply;
    TextView tvReplier;

    Message message;
    String message_key;
    List<Reply> replies;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_message);
        RecyclerView rvReply = findViewById(R.id.rvReply);

        replies = new ArrayList<>();

        tvUsername = findViewById(R.id.tvUsername);
        tvPost = findViewById(R.id.tvPost);
        tvUpvotes = findViewById(R.id.tvUpVotes);
        btnReply = findViewById(R.id.btnReply);
        btnUpvote = findViewById(R.id.btnUpvote);
        tvReply = findViewById(R.id.tvReply);
        tvReplier = findViewById(R.id.tvReplier);

        String currentUser = getIntent().getStringExtra("currentUser");
        Message message = Parcels.unwrap(getIntent().getParcelableExtra("message"));
        message_key = message.getKey();
        System.out.println(message_key);
        tvUsername.setText(message.getPoster());
        tvPost.setText(message.getPost());
        String upvotes = String.valueOf(message.getUpvotes());
        tvUpvotes.setText(upvotes);
        tvReplier.setText(currentUser);


        ReplyAdapter replyAdapter = new ReplyAdapter(this, replies);
        rvReply.setAdapter(replyAdapter);
        rvReply.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("message").child(message_key);


        btnReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String replier = tvReplier.getText().toString();
                String post = tvReply.getText().toString();
                int initial_upvote = 0;

                if (!post.isEmpty())
                {
                    Reply reply = new Reply(replier, post, initial_upvote);
                    replies.add(reply);
                    databaseReference.child("replies").setValue(replies);
                }
                else
                {
                    tvPost.setError("the Post content cannot be empty");
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        final RecyclerView rvReply = findViewById(R.id.rvReply);

        databaseReference.child("replies").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                replies.clear();
                for (DataSnapshot replyDataSnapshot: dataSnapshot.getChildren()) {
                    Reply reply = replyDataSnapshot.getValue(Reply.class);
                    replies.add(reply);
                }
                ReplyAdapter replyAdapter = new ReplyAdapter(DetailMessage.this, replies);
                rvReply.setAdapter(replyAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
