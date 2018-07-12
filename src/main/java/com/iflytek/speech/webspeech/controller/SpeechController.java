package com.iflytek.speech.webspeech.controller;

import com.iflytek.speech.webspeech.service.SpeechService;
import com.iflytek.speech.webspeech.util.Result;
import com.iflytek.speech.webspeech.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

/**
 * @author xghuang
 * @date 2018/7/12
 * @time 9:55
 * @desc:
 */
@Controller
@RequestMapping("/")
public class SpeechController {

    @Autowired
    private SpeechService speechService;

    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "speech/iat")
    public Result iat(HttpServletRequest request) {
        // 转型为MultipartHttpRequest(重点的所在)
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获得第1张图片（根据前台的name名称得到上传的文件）
        MultipartFile file = null;
        Iterator<String> itr = multipartRequest.getFileNames();
        while (itr.hasNext()) {
            String str = itr.next();
            file = multipartRequest.getFile(str);
            if (file == null || file.isEmpty()) {
                return ResultUtil.getResultError("音频文件不得为空！");
            }
            break;
        }
        try {
            return speechService.iat(file);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.getResultError(e.getMessage());
        }
    }
}
