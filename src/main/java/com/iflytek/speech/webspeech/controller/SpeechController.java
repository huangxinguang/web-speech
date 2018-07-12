package com.iflytek.speech.webspeech.controller;

import com.iflytek.speech.webspeech.service.SpeechService;
import com.iflytek.speech.webspeech.util.Result;
import com.iflytek.speech.webspeech.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xghuang
 * @date 2018/7/12
 * @time 9:55
 * @desc:
 */
@RestController
@RequestMapping("/")
public class SpeechController {

    @Autowired
    private SpeechService speechService;

    @RequestMapping(value = "speech/iat")
    public Result iat(HttpServletRequest request) {
        try {
            return speechService.iat(request);
        } catch (Exception e) {
            return ResultUtil.getResultError(e.getMessage());
        }
    }
}
