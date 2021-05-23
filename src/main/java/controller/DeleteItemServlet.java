package controller;

import entity.Item;
import service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteItemServlet extends HttpServlet {
    private ItemService itemService = new ItemService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Item item = itemService.findById(id);
        item.setStatus("DELETE");
        itemService.edit(id,item);
        resp.sendRedirect("/ ");

    }
}
