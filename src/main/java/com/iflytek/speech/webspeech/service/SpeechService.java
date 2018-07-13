package com.iflytek.speech.webspeech.service;

import com.iflytek.speech.webspeech.util.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xghuang
 * @date 2018/7/12
 * @time 9:56
 * @desc:
 */
public interface SpeechService {

    Result iat(MultipartFile audioFile) throws Exception;
}
