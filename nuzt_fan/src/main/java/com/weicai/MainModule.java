package com.weicai;

import httl.web.nutz.HttlViewMaker;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 * Created by fan on 2015/7/2.
 *
 @Modules - 声明应用的所有子模块
 @IocBy - 设置应用所采用的 Ioc 容器
 @SetupBy - 应用启动以及关闭时的额外处理
 @Views - 自定义的扩展视图
 @Localization - 应用的本地化字符串设定
 *
 *
 *
 *
 */
@Encoding(input="utf-8",output="utf-8")
//<1> @Modules - 声明应用的所有子模块
@Modules(scanPackage = true)
//@Modules(value={Abc.class, Xyz.class}, scanPackage = true)
//--将自动搜索主模块类，Abc.class，Xyz.class 所在的包（包括子包）下所有的 类，如果有类包括了一个以上的入口函数将被认为是模块类
//@IocBy(type=JsonIocProvider.class, args={"ioc"})
//ComboIocProvider的args参数, 星号开头的是类名或内置缩写,剩余的是各加载器的参数
//        *js 是JsonIocLoader,负责加载js/json结尾的ioc配置文件
//        *anno 是AnnotationIocLoader,负责处理注解式Ioc, 例如@IocBean
//*tx 是TransIocLoader,负责加载内置的事务拦截器定义, 1.b.52开始自带
@IocBy(type=ComboIocProvider.class, args={"*js", "ioc/",
                                          "*anno", "com.weicai.demo",
                                          "*tx","*org.nutz.integration.quartz.QuartzIocLoader"})
@SetupBy(value=MainSetup.class)
@Views({HttlViewMaker.class})
public class MainModule {
}
