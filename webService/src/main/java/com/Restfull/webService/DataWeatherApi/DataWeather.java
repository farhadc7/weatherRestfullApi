package com.Restfull.webService.DataWeatherApi;

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
public class DataWeather {
    private final String weatherApi="http://api.openweathermap.org/data/2.5/forecast?APPID=f5f63b4ec25ed4a2bc19fcbd1d340976&cnt=1&units=metric&q=";

    public String getTempByCity(String cityName){
        String rawTempInfo=getIpiInfo(cityName);
        String temp=getTemp(rawTempInfo,"temp=\\d*.\\d*",5);
        return temp;
    }
    public String getMinTempByCity(String cityName){
        String rawTempInfo=getIpiInfo(cityName);
        String temp=getTemp(rawTempInfo,"temp_min=\\d*.\\d*",9);
        return temp;
    }
    public String getMaxTempByCity(String cityName){
        String rawTempInfo=getIpiInfo(cityName);
        String temp=getTemp(rawTempInfo,"temp_max=\\d*.\\d*",9);
        return temp;
    }
    public String getPressureByCity(String cityName){
        String rawTempInfo=getIpiInfo(cityName);
        String temp=getTemp(rawTempInfo,"pressure=\\d*.\\d*",9);
        return temp;
    }
    public String getHumidityByCity(String cityName){
        String rawTempInfo=getIpiInfo(cityName);
        String temp=getTemp(rawTempInfo,"humidity=\\d*.\\d*",9);
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
                System.out.println(temp);
            }
        }
        return temp.toString();
    }
    private String getTemp(String name,String regexPattern,int cutPoint){
        String result="";
        Pattern pattern=Pattern.compile(regexPattern);
        Matcher m1 =pattern.matcher(name);
        if(m1.find()){
            String m2=m1.group();
            result =m2.substring(cutPoint);
        }
        return result;
    }


}
