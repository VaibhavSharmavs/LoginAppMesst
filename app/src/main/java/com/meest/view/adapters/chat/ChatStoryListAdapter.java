package com.example.loginapp.view.adapters.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.R;
import com.example.loginapp.databinding.RowChatStoryItemBinding;
import com.example.loginapp.model.chat.ChatStoryListModel;
import com.example.loginapp.viewmodel.chat.StoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChatStoryListAdapter extends RecyclerView.Adapter<ChatStoryListAdapter.CustomViewHolder> {

    private RowChatStoryItemBinding rowChatStoryItemBinding;
    private List<ChatStoryListModel> chatStoryListModels = new ArrayList<>();

    public ChatStoryListAdapter(List<ChatStoryListModel> chatStoryListModels) {
        this.chatStoryListModels = chatStoryListModels;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        rowChatStoryItemBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.row_chat_story_item, parent, false);
        CustomViewHolder customViewHolder = new CustomViewHolder(rowChatStoryItemBinding.getRoot());
        customViewHolder.setBinding(rowChatStoryItemBinding);

        return customViewHolder;
    }

    @Override
    public void onBindViewHolder( ChatStoryListAdapter.CustomViewHolder holder, int position) {


            holder.setChatStoryList(chatStoryListModels.get(position));





    }

    @Override
    public int getItemCount() {
        return chatStoryListModels.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        RowChatStoryItemBinding rowChatStoryItemBinding;

        public CustomViewHolder( View itemView) {
            super(itemView);
        }

        public void setBinding(RowChatStoryItemBinding rowChatStoryItemBinding){

            this.rowChatStoryItemBinding = rowChatStoryItemBinding;

        }

        public  void setChatStoryList(ChatStoryListModel chatStoryListModel){

            rowChatStoryItemBinding.setStoryViewModel(new StoryViewModel(chatStoryListModel));
        }
    }
}
