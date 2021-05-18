package com.meest.view.adapters.chat;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.example.loginapp.model.chat.ChatListModel;
import com.meest.R;
import com.meest.databinding.ChatListAdapterBinding;
import com.meest.view.activities.chat.ChatBoatActivity;
import com.meest.viewmodel.chat.ChatViewModel;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {


    ChatListAdapterBinding chatListAdapterBinding;
    FragmentActivity activity;
    List<ChatListModel> chatListModelList;

    ChatListAdapter.onChatListItemClickListener mItemClickListener;


    public ChatListAdapter(FragmentActivity activity, List<ChatListModel> chatListModelList) {
        this.activity = activity;
        this.chatListModelList = chatListModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        chatListAdapterBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.chat_list_adapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(chatListAdapterBinding.getRoot());
        viewHolder.setBinding(chatListAdapterBinding);

        return viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.setChatStoryList(chatListModelList.get(position));

        holder.getChatListAdapterBinding().layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
     //           mItemClickListener.onItemClick(position);

                Intent intent = new Intent(activity, ChatBoatActivity.class);
                activity.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return chatListModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{


        ChatListAdapterBinding chatListAdapterBinding;

        public ViewHolder( View itemView) {
            super(itemView);
        }

        public ChatListAdapterBinding getChatListAdapterBinding() {
            return chatListAdapterBinding;
        }

        public void setBinding(ChatListAdapterBinding chatListAdapterBinding){

            this.chatListAdapterBinding = chatListAdapterBinding;

        }

        public  void setChatStoryList(ChatListModel chatListModel){

            chatListAdapterBinding.setChatViewModel(new ChatViewModel(chatListModel));
        }
    }

    public void setOnItemClickListener(final ChatListAdapter.onChatListItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }



    public interface onChatListItemClickListener{

        void onItemClick(int position);
    }
    }

