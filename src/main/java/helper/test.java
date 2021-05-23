package helper;



import entity.Category;
import entity.Item;
import repository.CategoryRepository;
import repository.ItemRepository;
import service.ItemService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;


public class test {
    public static void main(String[] args) throws SQLException {

        ItemService itemService = new ItemService();
        ItemRepository itemRepository = new ItemRepository();
        GenericModel<Item> itemGenericModel = new GenericModel<>(Item.class);
        List<Item> list = itemService.getListOnSAle();
        for (Item obj:
                list) {
            obj.setAvatar("oatzhsm8spakuoo5gxoj");
            obj.setDescription("ok 2");
            itemService.edit(obj.getItemId(),obj);
        }



    }
}
