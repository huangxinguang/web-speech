package com.iflytek.speech.webspeech;

import javafx.scene.Parent;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

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
