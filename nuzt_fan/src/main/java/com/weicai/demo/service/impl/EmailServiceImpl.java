package com.weicai.demo.service.impl;

import com.weicai.demo.service.EmailService;
import org.apache.commons.mail.HtmlEmail;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * Created by fan on 2015/9/16.
 */
@IocBean(name = "emailService")
public class EmailServiceImpl implements EmailService {
    private static final Log log = Logs.get();

    @Inject("refer:$ioc")
    protected Ioc ioc;

    public boolean send(String to, String subject, String html, String msg) {
        try {
            HtmlEmail email = ioc.get(HtmlEmail.class);
            email.setSubject(subject);
            if (!Strings.isBlank(html))
                email.setHtmlMsg(html);
            if (!Strings.isBlank(msg))
                email.setMsg(msg);
            email.addTo(to);
            email.buildMimeMessage();
            email.sendMimeMessage();
            return true;
        } catch (Throwable e) {
            log.info("send email fail", e);
            return false;
        }
    }
}
