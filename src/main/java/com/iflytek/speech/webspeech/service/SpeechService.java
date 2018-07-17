package com.iflytek.speech.webspeech.service;

import com.iflytek.speech.webspeech.util.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xghuang
 * @date 2018/7/12
 * @time 9:56
 * @desc:
 */
public interface SpeechService {
    /**
     * 语音听写
     * @param audioFile
     * @return
     * @throws Exception
     */
    Result iat(MultipartFile audioFile) throws Exception;

    /**
     * 语义理解
     * @param audioFile
     * @return
     * @throws Exception
     */
    Result aiui(MultipartFile audioFile) throws Exception;

    /**
     * 语音合成
     * @param audioFile
     * @return
     * @throws Exception
     */
    Result tts(MultipartFile audioFile) throws Exception;

    /**
     * 下载音频文件
     * @param response
     * @param id
     * @throws Exception
     */
    void download(HttpServletResponse response,String id) throws Exception;
}
