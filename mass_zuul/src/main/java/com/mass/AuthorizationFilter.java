package com.mass;

import com.mass.common.service.JwtAuthenticationServiceImpl;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationFilter extends ZuulFilter {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static Logger LOGGER = LoggerFactory.getLogger(AuthorizationFilter.class);
    @Autowired
    public JwtAuthenticationServiceImpl jwtAuthenticationService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 判断是否需要监听.
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String requestUri = ctx.getRequest().getRequestURI();
        //return false;
        //以下接口不需要做token的拦截和校验
        return !requestUri.startsWith("/api/user/login");
    }

    /**
     * 进行校验.
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        LOGGER.info(
                String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        String token = request.getHeader(HEADER_AUTHORIZATION);
        //调用user模块进行权限校验
        try {

            jwtAuthenticationService.customizedValidation(token);

        } catch (Exception e) {
            if (e instanceof FeignException) {
                ctx.setResponseStatusCode(((FeignException) e).status());
                ctx.setResponseBody(e.getLocalizedMessage());
            } else {
                ctx.setResponseStatusCode(401);
                ctx.setResponseBody("Authorization check failed!");
            }
            ctx.setSendZuulResponse(false);
            e.printStackTrace();
        }
        return null;
    }
}
