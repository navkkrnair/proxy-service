package com.ams.proxy;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@EnableZuulProxy
@Controller
public class ProxyServiceApplication
{
    private static final Logger logger = LoggerFactory.getLogger(ProxyServiceApplication.class);

    public static void main(String[] args)
    {
        SpringApplication.run(ProxyServiceApplication.class,
                              args);
    }

    @RequestMapping("/user")
    @ResponseBody
    public Map<String, Object> user(Principal user)
    {
        logger.info("Proxy service '/user' endpoint accessed");
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("name",
                user.getName());
        map.put("roles",
                AuthorityUtils.authorityListToSet(((Authentication) user).getAuthorities()));
        return map;
    }

}
