package com.meest.view.adapters.chat;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.meest.R;
import com.meest.databinding.ChatListAdapterBinding;
import com.meest.databinding.GroupAdapterBinding;
import com.meest.model.group.GroupChatModel;
import com.meest.viewmodel.chat.ChatViewModel;
import com.meest.viewmodel.group.GroupViewModel;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    public GroupAdapterBinding groupAdapterBinding;
    public List<GroupChatModel> groupChatModelList;
    public Activity activity;

    public GroupAdapter(FragmentActivity activity, List<GroupChatModel> groupChatModelList) {
        this.activity = activity;
        this.groupChatModelList = groupChatModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        groupAdapterBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.group_adapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(groupAdapterBinding.getRoot());
        viewHolder.setBinding(groupAdapterBinding);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GroupAdapter.ViewHolder holder, int position) {

        holder.setGroupList(groupChatModelList.get(position));

    }

    @Override
    public int getItemCount() {
        return groupChatModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder( View itemView) {
            super(itemView);
        }

        GroupAdapterBinding groupAdapterBinding;


        public GroupAdapterBinding getChatListAdapterBinding() {
            return groupAdapterBinding;
        }

        public void setBinding(GroupAdapterBinding groupAdapterBinding){

            this.groupAdapterBinding = groupAdapterBinding;

        }

        public  void setGroupList(GroupChatModel groupChatModel){

            groupAdapterBinding.setGroupViewModel(new GroupViewModel(groupChatModel));
        }
    }
}
