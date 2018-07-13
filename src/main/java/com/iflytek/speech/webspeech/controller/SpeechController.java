package com.iflytek.speech.webspeech.controller;

import com.iflytek.speech.webspeech.service.SpeechService;
import com.iflytek.speech.webspeech.util.Result;
import com.iflytek.speech.webspeech.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xghuang
 * @date 2018/7/12
 * @time 9:55
 * @desc:
 */
@RestController
@RequestMapping("/speech/")
public class SpeechController {

    @Autowired
    private SpeechService speechService;

    @RequestMapping(value = "iat")
    public Result iat(@RequestParam("audioFile") MultipartFile audioFile) {
        try {
            return speechService.iat(audioFile);
        } catch (Exception e) {
            return ResultUtil.getResultError(e.getMessage());
        }
    }
}
