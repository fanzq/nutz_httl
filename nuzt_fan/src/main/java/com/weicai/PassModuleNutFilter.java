package com.weicai;

import org.nutz.mvc.NutFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fan on 2015/9/11.
 */
public class PassModuleNutFilter extends NutFilter {
    protected Set<String> prefixs = new HashSet<String>();

//如果访问/druid或/rs下的路径,就无条件跳过NutFilter
    public void init(FilterConfig conf) throws ServletException {
        super.init(conf);
        prefixs.add(conf.getServletContext().getContextPath() + "/druid/");
        prefixs.add(conf.getServletContext().getContextPath() + "/rs/");
      //  prefixs.add(conf.getServletContext().getContextPath() + "*.httl");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (req instanceof HttpServletRequest) {
            String uri = ((HttpServletRequest) req).getRequestURI();
            for (String prefix : prefixs) {
                if (uri.startsWith(prefix)) {
                    chain.doFilter(req, resp);
                    return;
                }
            }
        }
        super.doFilter(req, resp, chain);
    }
}