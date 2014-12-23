/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buzz.utilityapp;

import com.buzz.buzzdata.IBuzzDB;
import com.buzz.buzzdata.MongoBuzz;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author sdhalli
 */
public class Main {
    public static void main(String[] args) throws UnknownHostException
    {
        int count = 1000;
        Random random = new Random();
        double maxlat = 48.987386;
        double minlat = 18.005611;
        double minlng = -124.626080; 
        double maxlng = -62.361014;
        long start = (new Date()).getTime();
        for(int i =0;i<count;i++)
        {
            IBuzzDB buzzDB = new MongoBuzz("162.219.245.33", 27017, "Buzz", "admin", "qNjneHcKyl");
            String userid = Integer.toString(random.nextInt(10));
            
            double lat = minlat + random.nextDouble()*(maxlat-minlat);
            double lng = minlng + random.nextDouble()*(maxlng-minlng);
            
            String header = randomString(10);
            String content = randomString(30);
            String[] tags_array = new String[]{"Something1","Something2","Something3","Something4","Something5","Something6","Something7"};
            String tags_in = tags_array[random.nextInt(6)]+","+tags_array[random.nextInt(6)];
            buzzDB.Insert(userid, header, content, lat, lng, String.join(",", tags_in));
            buzzDB = null;
        }
       
        long end = (new Date()).getTime();
        System.out.println(end-start);
    }
    
    static String randomString(final int length) 
    {
        Random r = new Random(); // perhaps make it a class variable so you don't make a new one every time
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++) {
            char c = (char)(r.nextInt((int)(Character.MAX_VALUE)));
            sb.append(c);
        }
        return sb.toString();
    }
    
}
