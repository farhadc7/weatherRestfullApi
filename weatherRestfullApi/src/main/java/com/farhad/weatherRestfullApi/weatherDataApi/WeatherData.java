package com.farhad.weatherRestfullApi.weatherDataApi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class WeatherData {
    private final String weatherApi="http://api.openweathermap.org/data/2.5/forecast?APPID=f5f63b4ec25ed4a2bc19fcbd1d340976&cnt=1&units=metric&q=";

    public String getTempByCity(String cityName){
        String rawTempInfo=getIpiInfo(cityName);
        String temp=getTemp(rawTempInfo);
        return temp;
    }

    private String getIpiInfo(String name){
        StringBuffer sb=new StringBuffer();
        sb.append(weatherApi);
        sb.append(name);
        Client client= ClientBuilder.newClient();
        WebTarget target=client.target(sb.toString());
        String json=target.request().get(String.class);
        Gson gson=new Gson();
        Map<String,Object> map=gson.fromJson(json,new TypeToken<HashMap<String,Object>>(){}.getType());
        Object temp=new Object();
        for(Map.Entry<String,Object> a: map.entrySet()){
            if(a.getKey().equals("list")){
                temp=a.getValue();
            }
        }
        return temp.toString();
    }
    private String getTemp(String name){
        String result="";
        Pattern pattern=Pattern.compile("temp=\\d*.\\d*");
        Matcher m1 =pattern.matcher(name);
        if(m1.find()){
            String m2=m1.group();
            result =m2.substring(5);
        }
        return result;
    }

}
