package com.iflytek.speech.webspeech.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.speech.webspeech.component.AIUIComponent;
import com.iflytek.speech.webspeech.component.IATComponent;
import com.iflytek.speech.webspeech.component.TTSComponent;
import com.iflytek.speech.webspeech.service.SpeechService;
import com.iflytek.speech.webspeech.util.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xghuang
 * @date 2018/7/12
 * @time 9:58
 * @desc:语音功能
 */
@Service
public class SpeechServiceImpl implements SpeechService {

    @Autowired
    private IATComponent iatComponent;

    @Autowired
    private AIUIComponent aiuiComponent;

    @Autowired
    private TTSComponent ttsComponent;

    @Override
    public Result iat(MultipartFile audioFile) throws Exception {
        String result = iatComponent.getResult(audioFile);
        JSONObject jsonObject = JSON.parseObject(result);
        Object convertResult = jsonObject.getString("data");
        return ResultUtil.getResultSuccess(convertResult);
    }

    @Override
    public Result aiui(MultipartFile audioFile) throws Exception {
        String result = aiuiComponent.getResult(audioFile);
        JSONObject jsonObject = JSON.parseObject(result);
        Object convertResult = jsonObject.getString("data");
        return ResultUtil.getResultSuccess(convertResult);
    }

    @Override
    public Result tts(MultipartFile audioFile) throws Exception {
        String result = aiuiComponent.getResult(audioFile);
        JSONObject jsonObject = JSON.parseObject(result);
        String convertResult = jsonObject.getString("data");
        return convertAndSaveToAudio(convertResult);
    }


    /**
     * 转化为语音并保存（语音合成）
     * @param convertResult
     * @return
     * @throws Exception
     */
    private Result convertAndSaveToAudio(String convertResult){
        try {
            //语义理解回答的结果转成语音
            String answerText = JsonParser.parseAIUIResult(convertResult);
            Map<String, Object> resultMap = ttsComponent.getResultMap(answerText);
            // 合成成功
            if ("audio/mpeg".equals(resultMap.get("Content-Type"))) {
                String savePath = this.getClass().getResource("/static/audio").getPath();
                FileUtil.save(savePath, resultMap.get("sid") + ".wav", (byte[]) resultMap.get("body"));
                return ResultUtil.getResultSuccess("成功", resultMap.get("sid"));
            } else { // 合成失败
                return ResultUtil.getResultError(resultMap.get("body").toString());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.getResultError();
    }

}
