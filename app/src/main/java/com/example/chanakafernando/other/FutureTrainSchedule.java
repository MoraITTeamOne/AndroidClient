package com.example.chanakafernando.other;

/**
 * Created by Chanaka Fernando on 3/22/2017.
 */


public class FutureTrainSchedule {
    public String trinName;
    public String trainId;
    public String sLocation;
    public String eLocation;
    public String sTime;
    public String eTime;
    public String type;
    public String tRouteNo;

    public FutureTrainSchedule(){

    }

    public FutureTrainSchedule(String trainId, String trinName,String sLocation,String sTime,String eLocation,String eTime,String type,String tRouteNo){
        this.trainId=trainId;
        this.trinName=trinName;
        this.sLocation =sLocation;
        this.sTime =sTime;
        this.eLocation=eLocation;
        this.eTime=eTime;
        this.type=type;
        this.tRouteNo=tRouteNo;


    }
}

