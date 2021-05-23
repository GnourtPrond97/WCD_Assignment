package repository;

import entity.Category;
import entity.Item;
import helper.GenericModel;

import java.util.List;

public class CategoryRepository {
    private GenericModel<Category> itemGenericModel ;

    public CategoryRepository(){
        this.itemGenericModel = new GenericModel<>(Category.class);
    }
    public List<Category> findAll(){
        return itemGenericModel.findAll();
    }
}
