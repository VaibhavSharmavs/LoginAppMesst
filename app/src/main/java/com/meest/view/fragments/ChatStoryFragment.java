package com.meest.view.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meest.R;
import com.meest.databinding.FragmentChatStoryBinding;
import com.meest.model.chat.ChatStoryFragmentModel;
import com.meest.model.chat.ChatStoryListModel;
import com.meest.view.adapters.chat.ChatStoryListAdapter;
import com.meest.viewmodel.chat.ChatStoryFragmentViewModel;

import java.util.ArrayList;
import java.util.List;


public class ChatStoryFragment extends Fragment {

    FragmentChatStoryBinding fragmentChatStoryBinding;
    ChatStoryListAdapter chatStoryListAdapter;
    List<ChatStoryListModel> chatStoryListModelList ;
    ChatStoryFragmentModel chatStoryFragmentModel = new ChatStoryFragmentModel();




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentChatStoryBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_chat_story, container, false);
        fragmentChatStoryBinding.setChatStoryFragmentModel(new ChatStoryFragmentViewModel(chatStoryFragmentModel));
        fragmentChatStoryBinding.setLifecycleOwner(this);


        chatStoryListModelList = new ArrayList<>();
        ChatStoryListModel chatStoryListModel = new ChatStoryListModel();
        chatStoryListModel.setImage("");
        chatStoryListModel.setName("vbv");
        chatStoryListModel.setTime("4.00");
        chatStoryListModelList.add(chatStoryListModel);


        fragmentChatStoryBinding.mRecyclerViewStatus.setLayoutManager(new LinearLayoutManager(getContext()));

        chatStoryListAdapter = new ChatStoryListAdapter(chatStoryListModelList);
        fragmentChatStoryBinding.setChatStoryListAdapter(chatStoryListAdapter );
        chatStoryListAdapter.notifyDataSetChanged();





        return fragmentChatStoryBinding.getRoot();
    }
}