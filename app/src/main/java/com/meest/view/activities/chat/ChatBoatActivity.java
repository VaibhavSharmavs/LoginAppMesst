package com.example.loginapp.view.activities.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.loginapp.R;
import com.example.loginapp.databinding.ActivityChatBoatBinding;
import com.example.loginapp.view.adapters.chat.MessageAdapter;

public class ChatBoatActivity extends AppCompatActivity {

    ActivityChatBoatBinding activityChatBoatBinding;
    MessageAdapter messageAdapter;

    private String chatHeatId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    //    chatHeatId = getIntent().getExtras().getString("chatHeadId");
        activityChatBoatBinding = DataBindingUtil.setContentView(ChatBoatActivity.this,R.layout.activity_chat_boat);


        activityChatBoatBinding.mMessagesView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));




    }
}