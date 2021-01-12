package com.Alfa.controllers;


import com.Alfa.controllers.clients.GifClient;
import com.Alfa.controllers.clients.RankClient;
import com.Alfa.tools.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

@SpringBootApplication
@Controller
public class MainController {
    private final RankClient rank;
    private final GifClient gifClient;
    private final JsonParser jsonParser;
    @Autowired
    public MainController(RankClient rank, GifClient gifClient, JsonParser jsonParser) {
        this.rank = rank;
        this.gifClient = gifClient;
        this.jsonParser = jsonParser;
    }

    @GetMapping("app/{currency}")
    public String getGif(@PathVariable String currency, Model model) {
        boolean res = jsonParser.isRankHigherToday(rank.getYesterdayJson(getYesterdayDate(), currency).getBody(), rank.getJson(currency).getBody());
        Map gif;
        if (res == false) gif = gifClient.getBrokeGif().getBody();
        else gif = gifClient.getRichGif().getBody();
        gif = (Map) gif.get("data");
        gif = (Map) gif.get("images");
        gif = (Map) gif.get("downsized_large");
        String GifSrc = (String) gif.get("url");
        model.addAttribute("src", GifSrc);
        return "index";
    }

    public String getYesterdayDate() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(cal.getTime());
    }


}

