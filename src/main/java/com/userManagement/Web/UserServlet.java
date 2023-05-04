package com.userManagement.Web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.userManagement.Dao.UserDao;
import com.userManagement.Model.User;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public void init(ServletConfig config) throws ServletException {
        userDao=new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getServletPath();
        System.out.println(action);
         switch (action) {
             case "/new":
                 showNewForm(req, resp);
                 break;
             case "/insert":
                 try {
                     insertUser(req, resp);
                 } catch (SQLException | ClassNotFoundException e) {
                     throw new RuntimeException(e);
                 }
                 break;
             case "/update":
                 try {
                     updateUser(req, resp);
                 } catch (SQLException | ClassNotFoundException e) {
                     throw new RuntimeException(e);
                 }
                 break;
             case "/delete":
                 try {
                     deleteUser(req, resp);
                 } catch (SQLException | ClassNotFoundException e) {
                     throw new RuntimeException(e);
                 }
                 break;
             case "/edit":
                 try {
                     showEditForm(req, resp);
                 } catch (SQLException | ClassNotFoundException e) {
                     throw new RuntimeException(e);
                 }
                 break;
             default:
                 try {
                     listUser(req, resp);
                 } catch (SQLException | ClassNotFoundException e) {
                     throw new RuntimeException(e);
                 }
                 break;
         }
     }

     public void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
         List<User> list = (List<User>) userDao.selectAllUsers();
         request.setAttribute("AllUserList", list);
         RequestDispatcher rd = request.getRequestDispatcher("user-list.jsp");
         rd.forward(request, response);
     }

     public void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         RequestDispatcher rd = request.getRequestDispatcher("user-form.jsp");
         rd.forward(request, response);
     }

     public void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
         String name = request.getParameter("name");
         String email = request.getParameter("email");
         String country = request.getParameter("country");
         User user = new User(name, email, country);
         userDao.insertUser(user);
         response.sendRedirect("list");
     }

     public void showEditForm(HttpServletRequest req, HttpServletResponse res) throws SQLException, ClassNotFoundException, ServletException, IOException {
         int id = Integer.parseInt(req.getParameter("id"));
         User existingUser = userDao.selectUser(id);
         RequestDispatcher rd = req.getRequestDispatcher("user-form.jsp");
         req.setAttribute("user", existingUser);
         rd.forward(req, res);
     }

     public void deleteUser(HttpServletRequest req, HttpServletResponse res) throws SQLException, ClassNotFoundException, IOException {
         int id = Integer.parseInt(req.getParameter("id"));
         userDao.deleteUser(id);
         res.sendRedirect("list");
     }

     public void updateUser(HttpServletRequest req, HttpServletResponse res) throws SQLException, ClassNotFoundException, IOException {
         int id = Integer.parseInt(req.getParameter("id"));
         String name = req.getParameter("name");
         String email = req.getParameter("email");
         String country = req.getParameter("country");
         User user = new User(id, name, email, country);
         userDao.updateUser(user);
         res.sendRedirect("list");
     }
 }

