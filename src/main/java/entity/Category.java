package entity;

import hannotation.Column;
import hannotation.Entity;
import hannotation.Id;

@Entity(tableName = "DanhMucMonAn")
public class Category {
    @Id(autoIncrement = true)
    @Column(columnName = "MaDanhMuc",columnType = "int")
    private int id;
    @Column(columnName = "TenDanhMuc",columnType = "varchar(200)")
    private String name;

    public Category() {
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
