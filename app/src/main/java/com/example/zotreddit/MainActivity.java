package com.example.zotreddit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import adapters.MessageAdapter;

public class MainActivity extends AppCompatActivity {

    private TextView tvUsername;
    private Button btnPost;
    private TextView tvPost;
    private String key;
    MessageAdapter messageAdapter;

    List<Message> messages;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("message");

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback
            = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Message message_to_remove = messages.get(viewHolder.getAdapterPosition());
            String key_to_remove = message_to_remove.getKey();
            messageAdapter.notifyDataSetChanged();
            deleteMessage(key_to_remove);
        }
    };

    private void deleteMessage(String key) {
        DatabaseReference databaseReference_message = databaseReference.child(key);

        databaseReference_message.removeValue();
        Toast.makeText(this,"The message is deleted", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView rvMessages = findViewById(R.id.rvMessage);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rvMessages);
        messages = new ArrayList<>();


        tvUsername = findViewById(R.id.tvUsername);
        btnPost = findViewById(R.id.btnPost);
        tvPost = findViewById(R.id.tvPost);

        // message adapter
        messageAdapter = new MessageAdapter(this, messages, tvUsername.getText().toString());
        rvMessages.setAdapter(messageAdapter);
        rvMessages.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvMessages.addItemDecoration(divider);


        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            String username = extras.getString("user");
            tvUsername.setText(username);
        }



        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = tvUsername.getText().toString();
                String post = tvPost.getText().toString();
                int initial_upvote = 0;
                ArrayList<Reply> replies = new ArrayList<Reply>();
                Reply reply = new Reply("Bot","You can upvote or reply to the post", 0, "0", "000");
                replies.add(reply);

                if (!post.isEmpty())
                {
                    key = databaseReference.push().getKey();

                    reply.setParent_key(key);

                    Message message = new Message(post,username,initial_upvote,replies, key);

                    messages.add(message);

                    databaseReference.child(key).setValue(message);

                    tvPost.setText("");
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
        final RecyclerView rvMessages = findViewById(R.id.rvMessage);

        ValueEventListener valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messages.clear();
                for (DataSnapshot messageDataSnapshot : dataSnapshot.getChildren()) {
                    Message message = messageDataSnapshot.getValue(Message.class);
                    messages.add(message);
                    Collections.sort(messages);
                    Collections.reverse(messages);
                }
                MessageAdapter messageAdapter = new MessageAdapter(MainActivity.this, messages, tvUsername.getText().toString());
                rvMessages.setAdapter(messageAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
