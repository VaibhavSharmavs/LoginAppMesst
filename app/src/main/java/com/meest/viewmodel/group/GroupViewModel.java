package com.meest.viewmodel.group;

import com.meest.model.group.GroupChatModel;

public class GroupViewModel {

    GroupChatModel groupChatModel;
    public String groupName;
    public String groupIcon;
    public String textMessage;
    public String textTime;
    public String textCount;

    public GroupViewModel(GroupChatModel groupChatModel) {
        this.groupChatModel = groupChatModel;
        this.groupName = groupChatModel.groupName;
        this.groupIcon = groupChatModel.groupIcon;
        this.textMessage = groupChatModel.textMessage;
        this.textTime = groupChatModel.textTime;
        this.textCount = groupChatModel.textMessageCount;
    }
}
