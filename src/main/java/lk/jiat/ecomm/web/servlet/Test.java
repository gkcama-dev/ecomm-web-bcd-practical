package lk.jiat.ecomm.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ecomm.user.dto.UserDTO;
import lk.jiat.ecomm.user.remote.TestRemote;
import lk.jiat.ecomm.user.remote.UserRemote;

import javax.naming.InitialContext;
import java.io.IOException;
import java.util.List;

@WebServlet("/test")
public class Test extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.getWriter().write("Ecomm Web Module Test");

       try {

           InitialContext ic = new InitialContext();
           UserRemote userRemote = (UserRemote)
                   ic.lookup("java:global/ecomm_user_ejb_exploded/UserSessionBean");

           TestRemote tr = (TestRemote)
                   ic.lookup("java:global/ecomm_user_ejb_exploded/TestSessionBean");

           tr.test();

          List<UserDTO> allUsers = userRemote.getAllUsers();
          for (UserDTO user : allUsers) {
              user.toString();
          }

       } catch (Exception e) {
           e.printStackTrace();
       }

    }

}
