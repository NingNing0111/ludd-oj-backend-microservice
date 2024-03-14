package com.ningning0111.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @Project: com.ningning0111.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/14 21:21
 * @Description:
 */
@RestController
@RequestMapping("/api/user")
public class GenerateKeyController {

    @GetMapping("/generate")
    public String generate(
            @RequestParam(defaultValue = "64") Integer len
    ) {
        String charList = "0123456789ABCDEF";
        Random random = new Random();
        StringBuilder key = new StringBuilder();
        while (len != 0) {
            int index = random.nextInt(0, charList.length());
            key.append(charList.charAt(index));
            len--;
        }
        return key.toString();


    }
}
