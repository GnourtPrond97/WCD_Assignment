package controller;

import repository.CategoryRepository;
import service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListItemServlet extends HttpServlet {
    private final ItemService itemService = new ItemService();
    private final CategoryRepository categoryRepository = new CategoryRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("ListItem",itemService.getListOnSAle());
        req.setAttribute("ListCate",categoryRepository.findAll());
        req.getRequestDispatcher("/list.jsp").forward(req,resp);
    }
}
