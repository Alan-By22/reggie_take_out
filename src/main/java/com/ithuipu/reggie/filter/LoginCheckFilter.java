package com.ithuipu.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.ithuipu.reggie.common.BaseContext;
import com.ithuipu.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: LoginCheckFilter
 * @author: Mr.BingYu
 * @description:
 * @date: 2022/12/12 14:25
 **/

@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")

public class LoginCheckFilter implements Filter {

    /**
     * 路径匹配器,支持通配符
     */
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.请求本次请求的URI
        String requestURI = request.getRequestURI();
        log.info("拦截到请求:{}", requestURI);
        //2.定义不需要处理的请求路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };

        //3.判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

        //4.如果不需要处理,就会放行
        if (check) {
            log.info("本次请求{}不用处理", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        //5.判断登陆的状态,如果已经登陆,则直接放行
        if (request.getSession().getAttribute("employee") != null) {
            log.info("用户已经登陆,id为{}", request.getSession().getAttribute("employee"));

            //获得线程id
            long id = Thread.currentThread().getId();
            log.info("filterId为：{}", id);

            //LoginCheckFilter中存放当前登录用户到ThreadLocal**
            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

            filterChain.doFilter(request, response);
            return;
        }


        //用户未登录
        log.info("用户未登录");

        //6.如果用户未登录,则返回未登录结果,通过输出流的方式向客户端页面响应
        servletResponse.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));

        return;

    }


    /**
     * 路径匹配，检查本次请求是否需要放行
     */
    private boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }

    /**需要在引导类上, 加上Servlet组件扫描的注解, 来扫描过滤器配置的@WebFilter注解-@ServletComponentScan*/
}
