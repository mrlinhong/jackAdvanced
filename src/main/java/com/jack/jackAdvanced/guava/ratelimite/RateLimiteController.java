package com.jack.jackAdvanced.guava.ratelimite;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * 限速的接口
 */

@RestController
@RequestMapping("/rate/limite")
public class RateLimiteController {

    @GetMapping("/test")
    public Object test () {
        return Collections.singletonMap("success", "true");
    }
}
