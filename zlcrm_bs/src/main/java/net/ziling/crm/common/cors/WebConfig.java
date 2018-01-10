package net.ziling.crm.common.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Description:
 * 解决跨域问题
 *
 * @author huaxin
 * @create 2018/01/09 18:37
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //使用spring boot的时候使用
//        registry.addMapping("/api/**")
//                .allowedOrigins("http://domain2.com")
//                .allowedMethods("PUT", "DELETE")
//                .allowedHeaders("header1", "header2", "header3")
//                .exposedHeaders("header1", "header2")
//                .allowCredentials(false).maxAge(3600);

//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "TRACE")
//                .allowedHeaders("Access-Control-Allow-Origin","*")
//                .allowedHeaders("Access-Control-Allow-Methods","*")
//                .allowedHeaders("Access-Control-Allow-Headers","Access-Control");
    }
}
