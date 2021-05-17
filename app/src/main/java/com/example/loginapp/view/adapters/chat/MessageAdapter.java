package com.example.loginapp.view.adapters.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.example.loginapp.R;
import com.example.loginapp.databinding.ItemChatMyBinding;
import com.example.loginapp.databinding.RowChatStoryItemBinding;
import com.example.loginapp.model.chat.ChatStoryListModel;
import com.example.loginapp.model.chat.MessageModel;
import com.example.loginapp.model.chat.chatmodel.ChatTextModel;
import com.example.loginapp.view.activities.chat.ChatBoatActivity;
import com.example.loginapp.viewmodel.chat.StoryViewModel;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private Context context;
    private List<MessageModel> messageModelList;
    private String chatHeadId;
    private ChatBoatActivity chatBoatActivity;

    public MessageAdapter(Context context, List<MessageModel> messageModelList, String chatHeadId, ChatBoatActivity chatBoatActivity) {
        this.context = context;
        this.messageModelList = messageModelList;
        this.chatHeadId = chatHeadId;
        this.chatBoatActivity = chatBoatActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        ItemChatMyBinding itemChatMyBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_chat_my,parent,false);


        ViewHolder viewHolder = getViewHolder(itemChatMyBinding);

        return viewHolder;
    }

    private ViewHolder getViewHolder(ViewDataBinding viewBinding){

        ViewHolder viewHolder = new ViewHolder(viewBinding.getRoot());
        viewHolder.setBinding(viewBinding);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.setChatText(messageModelList.get(position));

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ViewDataBinding viewDataBinding;

        public ViewHolder( View itemView) {
            super(itemView);
        }

        public void setBinding(ViewDataBinding viewDataBinding){

            this.viewDataBinding = viewDataBinding;

        }



        public  void setChatText(MessageModel messageModel){

          //  viewDataBinding(new StoryViewModel(chatStoryListModel));
        }
    }
}
