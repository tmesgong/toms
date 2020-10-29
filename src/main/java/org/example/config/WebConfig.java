package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private boolean isLogin(HttpServletRequest request, HttpServletResponse response, String type) throws IOException, ServletException {


        HttpSession session = request.getSession();



        Object o = session.getAttribute(type);

        if (o == null) {

            request.setAttribute("myerror","权限不够或未登录");
            request.getRequestDispatcher("/error").forward(request,response);
            return false;
        } else {
            return true;
        }


    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                HttpSession session = request.getSession();
                String[] types = {"teacher", "textbookAdmin", "buyer", "wareHouseKeeper"};
                for (String type : types) {
                    Object user = session.getAttribute(type);
                    if (user != null) {
                        response.sendRedirect(request.getContextPath() + "/" + type + "/main");
                        return false;
                    }
                }

                return true;
            }
        }).addPathPatterns("/","/index");

        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

                return isLogin(request, response, "teacher");
            }
        }).addPathPatterns("/teacher/**");
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


                return isLogin(request, response, "textbookAdmin");

            }
        }).addPathPatterns("/textbookAdmin/**");
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

                return isLogin(request, response, "buyer");
            }
        }).addPathPatterns("/buyer/**");
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                return isLogin(request, response, "wareHouseKeeper");
            }
        }).addPathPatterns("/wareHouseKeeper/**");

    }


}
