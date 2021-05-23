package entity;

import hannotation.Column;
import hannotation.Entity;
import hannotation.ForeignKey;
import hannotation.Id;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity(tableName = "MonAn")
public class Item {
    private static String _cloudinaryDomain = "https://res.cloudinary.com/";
    private static String _cloudinaryProjectId = "dryj1v2fn";

    @Id
    @Column(columnName = "MaMonAn", columnType = "varchar(200)")

    private String itemId;

    @Column(columnName = "TenMonAn", columnType = "varchar(200)")
    private String itemName;

    @Column(columnName = "MaDanhMucMonAn", columnType = "int")
    @ForeignKey(className = "DanhMucMonAn", fieldName = "MaDanhMuc")
    private int categoryId;

    @Column(columnName = "MoTa", columnType = "varchar(400)")
    private String description;

    @Column(columnName = "AnhDaiDien",columnType = "varchar(200)")
    private String avatar;

    @Column(columnName = "Gia",columnType = "double")
    private Double price;

    @Column(columnName = "NgayBatDauBan" , columnType = "DATE")
    private Date startDay ;

    @Column(columnName = "NgaySua" , columnType = "DATE")
    private Date updateDay;
    @Column(columnName = "TrangThai", columnType="ENUM('ON_SALE', 'STOP_SELLING', 'DELETE')")
    private String status ;

//    public enum ItemStatus{
//                ON_SALE,
//        STOP_SELLING,
//        DELETE;
////        ON_SALE(1),
////        STOP_SELLING(0),
////        DELETE(-1);
////
////        private final int code;
////
////        private ItemStatus(int code) {
////            this.code = code;
////        }
////
////        public int toInt() {
////            return code;
////        }
////
////        public String toString() {
////            //only override toString, if the returned value has a meaning for the
////            //human viewing this value
////            return String.valueOf(code);
////        }
//    }



    public Item() {
    }

    public Item(String itemId, String itemName, int categoryId, String description, String avatar, Double price, String status) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.categoryId = categoryId;
        this.description = description;
        this.avatar = avatar;
        this.price = price;
        this.status = status ;
    }

    public Item(String itemId, String itemName, int categoryId, String description, String avatar, Double price, Date startDay, Date updateDay, String status) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.categoryId = categoryId;
        this.description = description;
        this.avatar = avatar;
        this.price = price;
        this.startDay = startDay;
        this.updateDay = updateDay;
        this.status = status;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getUpdateDay() {
        return updateDay;
    }

    public void setUpdateDay(Date updateDay) {
        this.updateDay = updateDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status ;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", categoryId=" + categoryId +
                ", description='" + description + '\'' +
                ", avatar='" + avatar + '\'' +
                ", price=" + price +
                ", startDay=" + startDay +
                ", updateDay=" + updateDay +
                ", status=" + status +
                '}';
    }


    public String getSmallImage()
    {
        if(this.avatar == null || this.avatar.length() == 0)
        {
            this.avatar = "n2ssze3joengkhuzgzr3";
        }
        //get first avatar
        String[] listavatar = this.avatar.split(",");
        String firstavatar = listavatar[0];
        String url = _cloudinaryDomain + _cloudinaryProjectId + "/image/upload/c_scale,w_100/v1621765154/" + firstavatar + ".jpg" ;
        return url ;
    }
    public String getImage()
    {
        if(this.avatar == null || this.avatar.length() == 0)
        {
            this.avatar = "n2ssze3joengkhuzgzr3";
        }
        //get first avatar
        String[] listavatar = this.avatar.split(",");
        String firstavatar = listavatar[0];
        String url = _cloudinaryDomain + _cloudinaryProjectId + "/image/upload/v1621765154/" + firstavatar + ".jpg" ;
        return url ;
    }

    public List<String> getAllImages()
    {
        if (this.avatar == null || this.avatar.length() == 0)
        {
            this.avatar = "vwg6d5hsjeur046qwwmg";
        }
        String[] listavatar = this.avatar.split(",");
        List<String> listImagesUrl = new ArrayList<String>()  ;
        for (String str:
                listImagesUrl) {
            String url = _cloudinaryDomain + _cloudinaryProjectId + "/image/upload/v1621765154/" + str + ".jpg";
            listImagesUrl.add(url);

        }

        return listImagesUrl;
    }

    public List<String> getMediumavatars()
    {
        if (this.avatar == null || this.avatar.length() == 0)
        {
            this.avatar = "n2ssze3joengkhuzgzr3";
        }
        String[] listavatar = this.avatar.split(",");
        List<String> listImagesUrl = new ArrayList<String>();
        for (String str:
                listImagesUrl) {
            String url = _cloudinaryDomain + _cloudinaryProjectId + "/image/upload/c_scale,w_550,h_520/v1621765154/" + str + ".jpg";
            listImagesUrl.add(url);
        }

        return listImagesUrl;
    }
}
