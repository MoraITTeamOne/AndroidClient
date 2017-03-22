package com.example.chanakafernando.other;

/**
 * Created by Chanaka Fernando on 3/22/2017.
 */

public class FutureBusSchedule {

        public String busId;
        public String sLocation;
        public String eLocation;
        public String sTime;
        public String eTime;
        public String bRouteNo;


        public FutureBusSchedule(){

        }

        public FutureBusSchedule(String sLocation,String busId, String sTime, String eLocation, String eTime, String bRouteNo){
            this.busId=busId;
            this.sLocation =sLocation;
            this.sTime =sTime;
            this.eLocation=eLocation;
            this.eTime=eTime;
            this.bRouteNo=bRouteNo;

        }



}
