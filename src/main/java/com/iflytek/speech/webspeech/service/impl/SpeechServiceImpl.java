package com.iflytek.speech.webspeech.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.speech.webspeech.component.AIUIComponent;
import com.iflytek.speech.webspeech.component.IATComponent;
import com.iflytek.speech.webspeech.component.TTSComponent;
import com.iflytek.speech.webspeech.service.SpeechService;
import com.iflytek.speech.webspeech.util.FileUtil;
import com.iflytek.speech.webspeech.util.JsonParser;
import com.iflytek.speech.webspeech.util.Result;
import com.iflytek.speech.webspeech.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

/**
 * @author xghuang
 * @date 2018/7/12
 * @time 9:58
 * @desc:语音功能
 */
@Service
public class SpeechServiceImpl implements SpeechService {

    private Logger logger = LoggerFactory.getLogger(SpeechServiceImpl.class);

    @Autowired
    private IATComponent iatComponent;

    @Autowired
    private AIUIComponent aiuiComponent;

    @Autowired
    private TTSComponent ttsComponent;

    @Value("${file-path}")
    private String FILE_PATH;

    @Override
    public Result iat(MultipartFile audioFile) throws Exception {
        logger.info("iat params:"+audioFile);
        String result = iatComponent.getResult(audioFile);
        JSONObject jsonObject = JSON.parseObject(result);
        Object convertResult = jsonObject.getString("data");
        return ResultUtil.getResultSuccess(convertResult);
    }

    @Override
    public Result aiui(MultipartFile audioFile) throws Exception {
        logger.info("aiui params :"+audioFile);
        String result = aiuiComponent.getResult(audioFile);
        JSONObject jsonObject = JSON.parseObject(result);
        Object convertResult = jsonObject.getString("data");
        return ResultUtil.getResultSuccess(convertResult);
    }

    @Override
    public Result tts(MultipartFile audioFile) throws Exception {
        logger.info("tts params:"+audioFile);
        String result = aiuiComponent.getResult(audioFile);
        JSONObject jsonObject = JSON.parseObject(result);
        String convertResult = jsonObject.getString("data");
        return convertAndSaveToAudio(convertResult);
    }

    @Override
    public void download(HttpServletResponse response,String id) throws Exception {
        FileInputStream in = null;
        ServletOutputStream out = null;
        try {
            File file = new File(FILE_PATH+id+".wav");
            in = new FileInputStream(file);
            out = response.getOutputStream();

            byte[] buffer = new byte[1024 * 4];
            int n = 0;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
        }catch (Exception e) {
            logger.error("read file error:"+e.getMessage());
            e.printStackTrace();
        }finally {
            in.close();
            out.flush();
            out.close();
        }
    }


    /**
     * 转化为语音并保存（语音合成）
     * @param convertResult
     * @return
     * @throws Exception
     */
    private Result convertAndSaveToAudio(String convertResult){
        logger.info("convertAndSaveToAudio params:"+convertResult);
        try {
            //语义理解回答的结果转成语音
            String answerText = JsonParser.parseAIUIResult(convertResult);
            Map<String, Object> resultMap = ttsComponent.getResultMap(answerText);
            // 合成成功
            if ("audio/mpeg".equals(resultMap.get("Content-Type"))) {
                FileUtil.save(FILE_PATH, resultMap.get("sid") + ".wav", (byte[]) resultMap.get("body"));
                return ResultUtil.getResultSuccess("成功", resultMap.get("sid"));
            } else { // 合成失败
                return ResultUtil.getResultError(resultMap.get("body").toString());
            }
        }catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return ResultUtil.getResultError();
    }

}
