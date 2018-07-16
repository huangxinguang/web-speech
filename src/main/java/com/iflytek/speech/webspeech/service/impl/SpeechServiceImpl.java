package com.iflytek.speech.webspeech.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.speech.webspeech.service.SpeechService;
import com.iflytek.speech.webspeech.util.HttpUtil;
import com.iflytek.speech.webspeech.util.Result;
import com.iflytek.speech.webspeech.util.ResultUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
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

    @Value("${speech.appid}")
    private String APP_ID;

    @Value("${speech.apikey}")
    private String API_KEY;

    @Value("${speech.webiat-url}")
    private String WEBIAT_URL;

    @Value("${aiui.url}")
    private String AIUI_URL;

    @Value("${aiui.appid}")
    private String AIUI_APP_ID;

    @Value("${aiui.apikey}")
    private String AIUI_API_KEY;

    @Value("${aiui.auth-id}")
    private String AIUI_AUTH_ID;

    @Override
    public Result iat(MultipartFile audioFile) throws Exception {
        //调用后端webapi，语音听写
        Map<String, String> header = this.buildIatHeader("raw", "sms16k");
        // 读取音频文件，转二进制数组，然后Base64编码
        String audioBase64 = new String(Base64.encodeBase64(audioFile.getBytes()), "UTF-8");
        String bodyParam = "audio=" + audioBase64;
        String result = HttpUtil.doPost(WEBIAT_URL, header, bodyParam);
        JSONObject jsonObject = JSON.parseObject(result);
        Object convertResult = jsonObject.getString("data");
        return ResultUtil.getResultSuccess(convertResult);
    }

    @Override
    public Result aiui(MultipartFile audioFile) throws Exception {
        Map<String, String> header = this.buildAIUIHeader();
        String result = HttpUtil.doPost(AIUI_URL, header, audioFile.getBytes());
        JSONObject jsonObject = JSON.parseObject(result);
        Object convertResult = jsonObject.getString("data");
        return ResultUtil.getResultSuccess(convertResult);
    }

    /**
     * 组装http请求头
     * @param aue
     * @param engineType
     * @return
     * @throws Exception
     */
    private Map<String, String> buildIatHeader(String aue, String engineType) throws Exception {
        // 系统当前时间戳
        String X_CurTime = System.currentTimeMillis() / 1000L + "";
        // 业务参数
        String param = "{\"aue\":\""+aue+"\""+",\"engine_type\":\"" + engineType + "\"}";
        String X_Param = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
        // 接口密钥
        String apiKey = API_KEY;
        // 讯飞开放平台应用ID
        String X_Appid = APP_ID;
        // 生成令牌
        String X_CheckSum = DigestUtils.md5Hex(apiKey + X_CurTime + X_Param);

        // 组装请求头
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        header.put("X-Param", X_Param);
        header.put("X-CurTime", X_CurTime);
        header.put("X-CheckSum", X_CheckSum);
        header.put("X-Appid", X_Appid);
        return header;
    }

    private Map<String, String> buildAIUIHeader() throws UnsupportedEncodingException, ParseException {
        final String DATA_TYPE = "audio";
        final String SCENE = "main";
        final String SAMPLE_RATE = "16000";
        final String AUE = "raw";

        String curTime = System.currentTimeMillis() / 1000L + "";
        String param = "{\"aue\":\""+AUE+"\",\"sample_rate\":\""+SAMPLE_RATE+"\",\"auth_id\":\""+AIUI_AUTH_ID+"\",\"data_type\":\""+DATA_TYPE+"\",\"scene\":\""+SCENE+"\"}";
        //使用个性化参数时参数格式如下：
        //String param = "{\"aue\":\""+AUE+"\",\"sample_rate\":\""+SAMPLE_RATE+"\",\"auth_id\":\""+AUTH_ID+"\",\"data_type\":\""+DATA_TYPE+"\",\"scene\":\""+SCENE+"\",\"pers_param\":\""+PERS_PARAM+"\"}";
        String paramBase64 = new String(org.apache.commons.codec.binary.Base64.encodeBase64(param.getBytes("UTF-8")));
        String checkSum = DigestUtils.md5Hex(AIUI_API_KEY + curTime + paramBase64);

        Map<String, String> header = new HashMap<>(4);
        header.put("X-Param", paramBase64);
        header.put("X-CurTime", curTime);
        header.put("X-CheckSum", checkSum);
        header.put("X-Appid", AIUI_APP_ID);
        return header;
    }
}
