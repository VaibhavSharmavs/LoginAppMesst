package com.example.loginapp.model.chat;

public class MessageModel {
    public static final int TYPE_MESSAGE = 0;
    public static final int TYPE_MESSAGE_REC = 1;
    public static final int TYPE_LOG = 1;
    public static final int TYPE_ACTION = 2;
    public static final int TYPE_Image_Message = 3;

    private int mType;
    private String mMessage;
    private String mUsername;
    private String mRead;
    private String mCreated;
    private String mAttachment;
    private String mFileurl;
    private String mMessageId;
    private String mMessgaeStatus;
    private String mJsonData;
    private String mSenderName;

    public String getSenderName() {
        return mSenderName;
    }

    public String getmJsonData() {
        return mJsonData;
    }



    public String getmAttachmentType() {
        return mAttachmentType;
    }

    private String mAttachmentType;

    public String getmMessgaeStatus() {
        return mMessgaeStatus;
    }


    public String getmMessageId() {
        return mMessageId;
    }

    public String getmFileurl() {
        return mFileurl;
    }



    public String getmAttachment() {
        return mAttachment;
    }

    public String getmCreated() {
        return mCreated;
    }


    public MessageModel() {}

    public String getmRead() {
        return mRead;
    }

    public int getType() {
        return mType;
    };

    public String getMessage() {
        return mMessage;
    };

    public String getUsername() {
        return mUsername;
    };


    public static class Builder {
        private final int mType;
        private String mUsername;
        private String mMessage;
        private String mRead;
        private String mCreated;
        private String mAttachment;
        private String mFileurl;
        private String mMessageId;
        private String mMessgaeStatus;
        private String mAttachmentType;
        private String mJsonData;
        private String mSenderName;

        public Builder(int type) {
            mType = type;
        }




        public Builder senderName(String senderName) {
            mSenderName = senderName;
            return this;
        }

        public Builder jsondata(String jsondata) {
            mJsonData = jsondata;
            return this;
        }

        public Builder attachmentType(String attachmentType) {
            mAttachmentType = attachmentType;
            return this;
        }


        public Builder fileurl(String fileurl) {
            mFileurl = fileurl;
            return this;
        }



        public Builder username(String username) {
            mUsername = username;
            return this;
        }

        public Builder message(String message) {
            mMessage = message;
            return this;
        }
        public Builder read(String read) {
            mRead = read;
            return this;
        }

        public Builder create(String create) {
            mCreated = create;
            return this;
        }

        public Builder attach(String attach) {
            mAttachment = attach;
            return this;
        }

        public Builder messageid(String messageid) {
            mMessageId = messageid;
            return this;
        }


        public Builder status(String status) {
            mMessgaeStatus = status;
            return this;
        }


        public MessageModel build() {
            MessageModel message = new MessageModel();
            message.mType = mType;
            message.mUsername = mUsername;
            message.mMessage = mMessage;
            message.mRead =mRead;
            message.mCreated =mCreated;
            message.mFileurl =mFileurl;
            message.mAttachment =mAttachment;
            message.mMessageId = mMessageId;
            message.mMessgaeStatus =mMessgaeStatus;
            message.mAttachmentType=mAttachmentType;
            message.mJsonData=mJsonData;
            message.mSenderName=mSenderName;
            return message;
        }
    }

}
