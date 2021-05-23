package service;

import entity.Item;
import repository.ItemRepository;

import java.sql.Date;
import java.util.*;

public class ItemService {
    private ItemRepository itemRepository;

    public ItemService() {
        this.itemRepository = new ItemRepository();
    }

    public boolean create(Item obj) {
        obj.setUpdateDay(new Date(Calendar.getInstance().getTimeInMillis()));
        obj.setStartDay(new Date(Calendar.getInstance().getTimeInMillis()));
        return itemRepository.save(obj);
    }

    public boolean edit(String id, Item obj){
        Item inDbItem = findById(id);
        if(inDbItem == null ){
            return false;
        }
        if(inDbItem.getStatus().equals("DELETE")){
            return false;
        }


        inDbItem.setItemName(obj.getItemName());
        inDbItem.setAvatar(obj.getAvatar());
        inDbItem.setCategoryId(obj.getCategoryId());
        inDbItem.setDescription(obj.getDescription());
        inDbItem.setUpdateDay(new Date(Calendar.getInstance().getTimeInMillis()));
        inDbItem.setPrice(obj.getPrice());
        inDbItem.setStatus(obj.getStatus().toString());

        return itemRepository.update(inDbItem.getItemId(),inDbItem);
    }
    public List<Item> getList(){
        return itemRepository.findAll();
    }

    public List<Item> getListOnSAle(){
        List<Item> listOnSale = new ArrayList<>();
        List<Item> list= itemRepository.findAll();
        for (Item item: list
             ) {
            if(item.getStatus().equals("ON_SALE") ){
                listOnSale.add(item);
            }
        }
        return listOnSale;
    }

    public Item findById(String id){
        return itemRepository.findById(id);
    }

}
