package com.weicai.demo.service;

import org.patchca.background.SingleColorBackgroundFactory;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.AbstractCaptchaService;
import org.patchca.text.renderer.BestFitTextRenderer;
import org.patchca.word.RandomWordFactory;

import java.awt.*;

/**
 * Created by fan on 2015/9/16.
 */
public class MyCaptchaService extends AbstractCaptchaService {
    public MyCaptchaService() {
//文本内容
        wordFactory = new MyWordFactory();
//字体
        fontFactory = new RandomFontFactory();
//效果
        textRenderer = new BestFitTextRenderer();
//背景
        backgroundFactory = new SingleColorBackgroundFactory();
//字体颜色
        colorFactory = new SingleColorFactory(new Color(25, 60, 170));
//样式(曲线波纹加干扰线)
        filterFactory = new CurvesRippleFilterFactory(colorFactory);
//图片长宽
        width = 150;
        height = 50;
    }

    private  static MyCaptchaService instance = null;
    public static MyCaptchaService getInstance(){
        //先检查实例是否存在，如果不存在才进入下面的同步块
        if(instance == null){
            //同步块，线程安全的创建实例
            synchronized (MyCaptchaService.class) {
                //再次检查实例是否存在，如果不存在才真正的创建实例
                if(instance == null){
                    instance = new MyCaptchaService();
                }
            }
        }
        return instance;
    }
}
  class MyWordFactory extends RandomWordFactory {
    public MyWordFactory() {
//文本范围和长度
        characters = "absdekmnowx23456789";
        minLength = 5;
        maxLength = 4;
    }
}
