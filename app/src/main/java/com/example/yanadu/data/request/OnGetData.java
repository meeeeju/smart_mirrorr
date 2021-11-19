package com.example.yanadu.data.request;

import com.example.yanadu.data.model.ObjectData;
import com.example.yanadu.data.model.UserData;

import java.util.List;

public interface OnGetData {
    public void onGetData(ObjectData objectData);
    public void onSendDate(ObjectData objectData);
    public void onGetDataList(List<ObjectData> objectDataList );

}
