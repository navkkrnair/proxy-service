package com.ams.proxy;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class AMSPreProxyFilter extends ZuulFilter
{
    private static final Logger logger = LoggerFactory.getLogger(AMSPreProxyFilter.class);

    @Override
    public boolean shouldFilter()
    {
        return true;
    }

    @Override
    public Object run() throws ZuulException
    {
        logger.info("Filter running");
        RequestContext     ctx        = RequestContext.getCurrentContext();
        HttpServletRequest request    = ctx.getRequest();
        String             requestURI = request.getRequestURI();
        logger.info("Requested URI: " + requestURI);
        logger.info("Adding additional headers in filter");
        ctx.addZuulRequestHeader("BOOKING-ORDER",
                                 "SPECIAL_REQUEST");
        return null;
    }

    @Override
    public String filterType()
    {
        return "pre";
    }

    @Override
    public int filterOrder()
    {
        return 1;
    }

}
