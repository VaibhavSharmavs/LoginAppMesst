package com.meest.viewmodel.call;

import com.meest.model.call.CallHistoryModel;

public class CallHistoryViewModel {

    public CallHistoryModel callHistoryModel;
    public String callAVImage;
    public String usernName;
    public String callTime;
    public String callType;
    public String userImage;

    public CallHistoryViewModel(CallHistoryModel callHistoryModel) {
        this.callHistoryModel = callHistoryModel;
        this.userImage = callHistoryModel.getPhoneNumber();
        this.callTime = String.valueOf(callHistoryModel.getTimestamp());
        this.callType = String.valueOf(callHistoryModel.getDirection());


    }
}
