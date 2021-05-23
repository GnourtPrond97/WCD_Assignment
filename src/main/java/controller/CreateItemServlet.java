package controller;


import entity.Item;
import repository.CategoryRepository;
import service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;


public class CreateItemServlet extends HttpServlet {
    private CategoryRepository categoryRepository = new CategoryRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("ListCate", categoryRepository.findAll());
        req.getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String itemId = req.getParameter("itemId");
        String itemName = req.getParameter("itemName");
        int categoryId = parseInt(req.getParameter("categoryId"));
        String description = req.getParameter("description");
        String avatar = req.getParameter("avatar");
        int price = parseInt(req.getParameter("price"));
        String status = req.getParameter("status");
        Item item = new Item(itemId, itemName, categoryId, description, avatar, (double) price, status);
        ItemService itemService = new ItemService();
        itemService.create(item);

        resp.sendRedirect("/ ");

    }
}
