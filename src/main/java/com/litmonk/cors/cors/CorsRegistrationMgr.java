package com.litmonk.cors.cors;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * 跨域请求配置
 * Created by lu on 2017/8/6.
 */
@Configuration
public class CorsRegistrationMgr {
    private final Map<String, CorsConfiguration> corsMap = new HashMap<String, CorsConfiguration>();

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 默认不允许跨域，如果有默认值，格式如下：http://domain.com:8080
        config.addAllowedOrigin("");
        config.addAllowedHeader("*");
        config.addAllowedMethod("POST");
        source.registerCorsConfiguration("/api/login", config);
        corsMap.put("/api/login", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    /**
     * 是否允许请求带验证信息，为false时无法修改Cookie内容
     * @param url
     * @param allowCredentials
     */
    public void setAllowCredentials(String url, boolean allowCredentials) {
        CorsConfiguration corsConfiguration = corsMap.get(url);
        if (corsConfiguration != null) {
            corsConfiguration.setAllowCredentials(allowCredentials);
        }
    }

    /**
     * 添加允许跨域的域名信息
     * @param url
     * @param origin
     */
    public void addAllowedOrigin(String url, String origin) {
        CorsConfiguration corsConfiguration = corsMap.get(url);
        if (corsConfiguration != null) {
            corsConfiguration.addAllowedOrigin(origin);
        }
    }
}
