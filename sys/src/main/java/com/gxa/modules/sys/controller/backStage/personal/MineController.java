package com.gxa.modules.sys.controller.backStage.personal;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author :林溪
 * @date : 2022/11/11 8:41
 */
@RequestMapping("/Mine")
public class MineController {
    @PostMapping("/")
    public void mine(HttpRequest httpRequest){
        HttpHeaders headers = httpRequest.getHeaders();
        List<String> token = headers.get("token");

    }
}
