package com.csun.objectoriented582.system.controller;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.map.MapUtil;
import com.csun.objectoriented582.common.Result;
import com.csun.objectoriented582.system.entity.User;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.security.Principal;
import java.util.UUID;

@RestController
public class AuthenticationController extends BaseController {

    Producer producer;

    @Autowired
    public AuthenticationController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping("/captcha")
    public Result captcha() throws Exception {
        String key = UUID.randomUUID().toString();
        String code = producer.createText();
        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        Base64Encoder encoder = new Base64Encoder();
        String imageStr = "data:image/jpeg;base64,";
        String captchaImg = imageStr + encoder.encode(outputStream.toByteArray());
        Result result = new Result(MapBuilder.create().put("token", key).put("captchaImg", captchaImg).build());
        return result;
    }


    @GetMapping("/sys/user")
    public Result getUserInfo(Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("created",user.getCreated())
                .map()
        );
    }
}
