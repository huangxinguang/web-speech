package com.iflytek.speech.webspeech.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.speech.webspeech.util.HttpUtil;
import com.iflytek.speech.webspeech.util.Result;
import com.iflytek.speech.webspeech.util.ResultUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xghuang
 * @date 2018/7/17
 * @time 10:32
 * @desc:语义理解
 */
@Component
public class AIUIComponent {

    @Value("${aiui.url}")
    private String AIUI_URL;

    @Value("${aiui.appid}")
    private String AIUI_APP_ID;

    @Value("${aiui.apikey}")
    private String AIUI_API_KEY;

    @Value("${aiui.auth-id}")
    private String AIUI_AUTH_ID;

    /**
     * 请求webapi获取结果
     * @param audioFile
     * @return
     * @throws Exception
     */
    public String getResult(MultipartFile audioFile) throws Exception {
        Map<String, String> header = this.buildHeader();
        return HttpUtil.doPost(AIUI_URL, header, audioFile.getBytes());
    }

    private Map<String, String> buildHeader() throws Exception {
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
