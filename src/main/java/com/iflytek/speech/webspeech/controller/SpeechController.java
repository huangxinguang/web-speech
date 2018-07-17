package com.iflytek.speech.webspeech.controller;

import com.iflytek.speech.webspeech.service.SpeechService;
import com.iflytek.speech.webspeech.util.Result;
import com.iflytek.speech.webspeech.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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

    /**
     * 语音听写
     * @param audioFile
     * @return
     */
    @RequestMapping(value = "iat")
    public Result iat(@RequestParam("audioFile") MultipartFile audioFile) {
        try {
            return speechService.iat(audioFile);
        } catch (Exception e) {
            return ResultUtil.getResultError(e.getMessage());
        }
    }

    /**
     * 语义理解
     * @param audioFile
     * @return
     */
    @RequestMapping(value = "aiui")
    public Result aiui(@RequestParam("audioFile") MultipartFile audioFile) {
        try {
            return speechService.aiui(audioFile);
        } catch (Exception e) {
            return ResultUtil.getResultError(e.getMessage());
        }
    }

    /**
     * 语义理解转合成语音
     * @param audioFile
     * @return
     */
    @RequestMapping(value = "tts")
    public Result tts(@RequestParam("audioFile") MultipartFile audioFile) {
        try {
            return speechService.tts(audioFile);
        } catch (Exception e) {
            return ResultUtil.getResultError(e.getMessage());
        }
    }

    /**
     * 获取音频
     * @param response
     * @param id
     */
    @RequestMapping(value = "getAudio")
    public void getAudio(HttpServletResponse response,@RequestParam("id")String id) {
        try {
            speechService.download(response,id);
        } catch (Exception e) {
            throw new RuntimeException("出错了！");
        }
    }
}
