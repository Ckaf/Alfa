package com.Alfa;

import com.Alfa.controllers.MainController;
import com.Alfa.controllers.clients.GifClient;
import com.Alfa.controllers.clients.RankClient;
import com.Alfa.tools.JsonParser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class AlfaApplicationTests {
    @Autowired
    private JsonParser jsonParser;
    @MockBean
    private RankClient rankClient;
    @MockBean
    private GifClient gifClient;
    @MockBean
    private Model model;
    @Test
    void isRankHigherTodayTest() {
        Map yesterday = new HashMap();
        Map today = new HashMap();
        Map oneMap = new HashMap();
        Map twoMap = new HashMap();
        oneMap.put("RUB", 73);
        yesterday.put("rates", oneMap);
        twoMap.put("RUB", 100);
        today.put("rates", twoMap);
        Assert.isTrue(jsonParser.isRankHigherToday(yesterday, today), "isRankHigherTodayTest");
    }

    @Test
    void MainControllerTest() {
        Map mapToday=new HashMap();
        Map inMapToday=new HashMap();
        inMapToday.put("RUB",100);
        mapToday.put("rates",inMapToday);
        Map mapYesterday=new HashMap();
        Map inMapYesterday=new HashMap();
        inMapYesterday.put("RUB",70);
        mapYesterday.put("rates",inMapYesterday);
        System.out.println(mapToday);
        Mockito.when(rankClient.getYesterdayJson(new MainController(rankClient,gifClient, jsonParser).getYesterdayDate()
                ,"usd")).thenReturn(new ResponseEntity<Map>(mapYesterday,HttpStatus.OK));
        Mockito.when(rankClient.getJson("usd")).thenReturn(new ResponseEntity<Map>(mapToday,HttpStatus.OK));

        Map gifMap=new HashMap();
        Map secondMapLevel=new HashMap();
        secondMapLevel.put("url","path");
        Map thirdMapLevel=new HashMap();
        thirdMapLevel.put("downsized_large",secondMapLevel);
        Map fourthMapLevel=new HashMap();
        fourthMapLevel.put("images",thirdMapLevel);
        gifMap.put("data",fourthMapLevel);
        Mockito.when(gifClient.getRichGif()).thenReturn(new ResponseEntity<Map>(gifMap,HttpStatus.OK));
        MainController mainController=new MainController(rankClient,gifClient, jsonParser);

        Assert.isTrue(mainController.getGif("usd",model).equals("index"),"MainControllerTest");
    }
}
