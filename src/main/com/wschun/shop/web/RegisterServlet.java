package wschun.shop.web;

import oracle.jrockit.jfr.events.RequestableEventEnvironment;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import wschun.shop.bean.User;
import wschun.shop.service.UserService;
import wschun.shop.service.impl.UserServiceImpl;
import wschun.shop.utils.JDBCUtils;
import wschun.shop.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by wschun on 2018/3/26.
 */
@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codep = request.getParameter("captcha");
        String code = (String) request.getSession().getAttribute("code");

        if (codep == null || codep.equals("") || !codep.equalsIgnoreCase(code)) {
            request.setAttribute("errMsg", "验证码错误");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        String uuid = UUIDUtils.getUUID();
        user.setId(uuid);
        try {
            BeanUtils.populate(user, request.getParameterMap());

            UserService servie=new UserServiceImpl();
            int result=servie.register(user);
            if (result==1){
                //ok
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            } else if (result==2){
                request.setAttribute("errMsg", "用户名已存在");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }else {
                request.setAttribute("errMsg", "系统忙，请稍后尝试");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
