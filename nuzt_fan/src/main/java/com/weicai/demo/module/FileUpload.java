package com.weicai.demo.module;

import httl.spi.codecs.json.JSONObject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.FieldMeta;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fan on 2015/9/14.
 */
@IocBean
public class FileUpload {

    @At("/test_up")
    @Ok("httl:test.file")
    public Object test_up() {
        String s="2345678";
        return s;
    }
    @At("/test_up2")
    @Ok("jsp:/index")
    public Object test_up2() {
        String s="2345678";
        return s;
    }

    @At("/test_up3")
    @Ok("httl:test.file")
    public Map<String, Object>  test_up3() throws SQLException {
        Map<String, Object> map = new HashMap();
        map.put("fff", 78787);
        map.put("obj2", "obj2222");
        return map;
    }
    @At("/upload")
    @AdaptBy(type = UploadAdaptor.class, args = { "${app.root}/WEB-INF/tmp" })
    @Ok("json")
    public void uploadPhoto(@Param("id") int id, @Param("photo") TempFile tf,HttpServletRequest req) throws IOException {
        File f = tf.getFile();                       // 这个是保存的临时文件
        FieldMeta meta = tf.getMeta();               // 这个原本的文件信息
        String oldName = meta.getFileLocalName();    // 这个时原本的文件名称
        String pro= Mvcs.getServletContext().getRealPath("/");
        Path p=Paths.get(pro,"WEB-INF","tmp",oldName);
        InputStream in= new FileInputStream(f);
        Files.copy(in, p);
        in.close();
        System.out.println(f.delete());
    }
}
