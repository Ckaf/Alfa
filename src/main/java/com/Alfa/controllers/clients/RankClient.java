package com.Alfa.controllers.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "RankClient", url = "${feign.client.rank.url}")

public interface RankClient {
    @RequestMapping(method = RequestMethod.GET, value = "${feign.client.rank.json.latest}" + "${feign.client.rank.token}&base={currency}")
    ResponseEntity<Map> getJson(@PathVariable("currency") String currency);

    @RequestMapping(method = RequestMethod.GET, value = "${feign.client.rank.json.historical}" +
            "{date}" + "${feign.client.rank.json.historical.end}" + "${feign.client.rank.token}&base={currency}")
    ResponseEntity<Map> getYesterdayJson(@PathVariable("date") String date, @PathVariable("currency") String currency);
}
