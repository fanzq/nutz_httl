package com.weicai.demo.service;

/**
 * Created by fan on 2015/9/16.
 */
public interface EmailService {
    boolean send(String to, String subject, String html,String msg);
}
