package com.example.loginapp.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.loginapp.R;
import com.example.loginapp.databinding.FragmentChatListBinding;
import com.example.loginapp.databinding.FragmentChatStoryBinding;
import com.example.loginapp.model.chat.ChatListModel;
import com.example.loginapp.model.chat.ChatStoryFragmentModel;
import com.example.loginapp.model.chat.ChatStoryListModel;
import com.example.loginapp.view.activities.chat.ChatBoatActivity;
import com.example.loginapp.view.adapters.chat.ChatListAdapter;
import com.example.loginapp.view.adapters.chat.ChatStoryListAdapter;

import java.util.ArrayList;
import java.util.List;


public class ChatListFragment extends Fragment  {

    FragmentChatListBinding fragmentChatListBinding;
    ChatListAdapter chatListAdapter;
    List<ChatListModel> chatListModelList= new ArrayList<>();



    public ChatListFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentChatListBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_chat_list, container, false);

        ChatListModel chatListModel = new ChatListModel();
        chatListModel.setMsg("hi");
        chatListModel.setUsername("vbv");
        chatListModel.setMsgCount("1");
        chatListModel.setTime("14:19");
        chatListModelList.add(chatListModel);

        fragmentChatListBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        chatListAdapter = new ChatListAdapter(getActivity(),chatListModelList);
        fragmentChatListBinding.setChatListAdapter(chatListAdapter );
        chatListAdapter.notifyDataSetChanged();

        chatListAdapter.setOnItemClickListener(new ChatListAdapter.onChatListItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Intent intent = new Intent(getActivity(), ChatBoatActivity.class);
                startActivity(intent);


            }
        });





       return fragmentChatListBinding.getRoot();
    }
}