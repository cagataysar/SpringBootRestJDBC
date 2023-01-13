package com.garanti.SpringBootRestJDBC.security;

// web xml 'de olmadığı için
//@Component
//// sıralama verilebilir
//@org.springframework.core.annotation.Order(value = 200)
//public class MyFilter implements javax.servlet.Filter
//{
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
//    {
//        // ((HttpServletRequest)servletRequest).getHeader("username")
//        // ((HttpServletRequest)servletRequest).getHeader("password")
//        HttpServletRequest req = ((HttpServletRequest) servletRequest);
////        int k = 7 / 0;
////        if (req.getRequestURI().contains("/ogretmen"))
////        {
//        System.err.println("-------> " + req.getHeader("username") + " " + req.getHeader("password"));
//        // ((HttpServletResponse)servletResponse).sendRedirect("/error/unauthorized");
//        // servletResponse.getWriter().println("Kullanıcı adı şifre yok !!!");
////        }
//        // next ini çalıştır
//        if (filterChain != null)
//        {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }
//}