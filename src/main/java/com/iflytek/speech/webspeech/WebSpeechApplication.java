package com.iflytek.speech.webspeech;

import javafx.scene.Parent;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author xghuang
 * @date 2018/7/12
 * @time 9:55
 * @desc:
 */
@SpringBootApplication
public class WebSpeechApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(Parent.class)
                .child(WebSpeechApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
