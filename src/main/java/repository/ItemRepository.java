package repository;

import entity.Item;
import helper.GenericModel;

import java.util.ArrayList;
import java.util.List;

public class ItemRepository {
    private  GenericModel<Item> itemGenericModel ;

    public ItemRepository(){
        this.itemGenericModel = new GenericModel<>(Item.class);
    }

    public boolean save(Item item){
        return itemGenericModel.save(item);
    }

    public boolean update(String id, Item updateItem){
        Item existItem =findById(id);
        if(existItem == null ){
            return  false;
        }
        existItem.setItemId(updateItem.getItemId());
        existItem.setItemName(updateItem.getItemName());
        existItem.setAvatar(updateItem.getAvatar());
        existItem.setCategoryId(updateItem.getCategoryId());
        existItem.setDescription(updateItem.getDescription());
        existItem.setUpdateDay(updateItem.getUpdateDay());
        existItem.setPrice(updateItem.getPrice());
        existItem.setStatus(updateItem.getStatus().toString());
        itemGenericModel.update(existItem);
        return true;
    }

    public Item findById(String id){
        List<Item> list = itemGenericModel.findAll();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getItemId().equals(id)){
                return list.get(i);
            }
        }
        return null;
    }
    public List<Item> findAll(){
        return itemGenericModel.findAll();
    }
}
