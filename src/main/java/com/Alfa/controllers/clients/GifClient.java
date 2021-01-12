package com.Alfa.controllers.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "GifClient", url = "${feign.client.gif.url}")
public interface GifClient {
    @RequestMapping(method = RequestMethod.GET, value = "${feign.client.gif.token}" + "${feign.client.gif.tag.broke}")
    ResponseEntity<Map> getBrokeGif();

    @RequestMapping(method = RequestMethod.GET, value = "${feign.client.gif.token}" + "${feign.client.gif.tag.rich}")
    ResponseEntity<Map> getRichGif();
}
