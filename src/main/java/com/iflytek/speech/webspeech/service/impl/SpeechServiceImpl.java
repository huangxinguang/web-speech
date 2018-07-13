package com.iflytek.speech.webspeech.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.speech.webspeech.service.SpeechService;
import com.iflytek.speech.webspeech.util.FileUtil;
import com.iflytek.speech.webspeech.util.HttpUtil;
import com.iflytek.speech.webspeech.util.Result;
import com.iflytek.speech.webspeech.util.ResultUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
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

    @Override
    public Result iat(MultipartFile audioFile) throws Exception {
        //调用后端webapi，语音听写
        Map<String, String> header = this.constructHeader("raw", "sms16k");
        // 读取音频文件，转二进制数组，然后Base64编码
        String audioBase64 = new String(Base64.encodeBase64(audioFile.getBytes()), "UTF-8");
        String bodyParam = "audio=" + audioBase64;
        String result = HttpUtil.doPost(WEBIAT_URL, header, bodyParam);
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
    private Map<String, String> constructHeader(String aue, String engineType) throws Exception {
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
}
