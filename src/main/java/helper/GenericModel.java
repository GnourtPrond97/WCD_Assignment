package helper;

import hannotation.Column;
import hannotation.Entity;
import hannotation.Id;
import helper.ConnectionHelper;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GenericModel<T> {
    private Class<T> clazz; // chứa tất cả thông tin của generic type đang dùng.

    public GenericModel(Class<T> clazz) {
        // kiểm tra class có được đánh dấu là @Entity hay không?
        if (!clazz.isAnnotationPresent(Entity.class)) {
            System.err.printf("Class %s không được đăng ký làm việc với database.", clazz.getSimpleName());
            return;
        }
        this.clazz = clazz;
    }

    public boolean save(T obj) {
        try {
            // Lấy ra giá trị của annotation @Entity vì cần những thông tin liên quan đến tableName.
            Entity entityInfor = clazz.getAnnotation(Entity.class);
            // Build lên câu query string.
            StringBuilder strQuery = new StringBuilder();
            // Build chuỗi chứa giá trị các trường tương ứng.
            StringBuilder fieldValues = new StringBuilder();
            fieldValues.append(SQLConstant.OPEN_PARENTHESES);
            // Xây dựng câu lệnh insert theo tên bảng, theo tên các field cùa đối tượng truyền vào.
            strQuery.append(SQLConstant.INSERT_INTO); // insert into
            strQuery.append(SQLConstant.SPACE); //
            strQuery.append(entityInfor.tableName()); // giangvien
            strQuery.append(SQLConstant.SPACE); //
            strQuery.append(SQLConstant.OPEN_PARENTHESES); // (
            for (Field field : clazz.getDeclaredFields()) {
                // check xem trường có phải là @Column không.
                if (!field.isAnnotationPresent(Column.class)) {
                    // bỏ qua trong trường hợp không được đánh là @Column.
                    continue;
                }
                // cần set bằng true để có thể set, get giá trị của field trong một object nào đó.
                field.setAccessible(true);
                // lấy thông tin column để check tên trường, kiểu giá trị của trường.
                // Không lấy danh sách column theo tên field mà lấy theo annotation đặt tại field đó.
                Column columnInfor = field.getAnnotation(Column.class);
                // check xem trường có phải là id không.
                if (field.isAnnotationPresent(Id.class)) {
                    // lấy thông tin id.
                    Id idInfor = field.getAnnotation(Id.class);
                    if (idInfor.autoIncrement()) {
                        // trường hợp đây là trường tự tăng, thì next sang trường tiếp theo.
                        continue;
                    }
                }
                strQuery.append(columnInfor.columnName()); // nối tên trường.
                strQuery.append(SQLConstant.COMMA); //,
                strQuery.append(SQLConstant.SPACE); //
                // nhanh trí, xử lý luôn phần value, tránh sử dụng 2 vòng lặp.
                // check kiểu của trường, nếu là string thì thêm dấu '
//                if (field.getType().getSimpleName().equals(String.class.getSimpleName())) {
//                    fieldValues.append(SQLConstant.QUOTE);
//                }

                // lấy ra thông tin giá trị của trường đó tại obj truyền vào.
                fieldValues.append(SQLConstant.QUOTE);

                fieldValues.append(field.get(obj)); // field.setAccessible(true);
                fieldValues.append(SQLConstant.QUOTE);
                // check kiểu của trường, nếu là string thì thêm dấu '
//                if (field.getType().getSimpleName().equals(String.class.getSimpleName())) {
//                    fieldValues.append(SQLConstant.QUOTE);
//                }
                fieldValues.append(SQLConstant.COMMA); //,
                fieldValues.append(SQLConstant.SPACE); //
            }
            strQuery.setLength(strQuery.length() - 2); // trường hợp là field cuối cùng thì bỏ dấu , và khoảng trắng đi.
            fieldValues.setLength(fieldValues.length() - 2);
            strQuery.append(SQLConstant.CLOSE_PARENTHESES); // )
            fieldValues.append(SQLConstant.CLOSE_PARENTHESES); // )
            strQuery.append(SQLConstant.SPACE);
            strQuery.append(SQLConstant.VALUES); // values
            strQuery.append(SQLConstant.SPACE);
            strQuery.append(fieldValues); // nối giá trị các trường vào.
            System.out.printf(strQuery.toString());
            return ConnectionHelper.getConnection().createStatement().execute(strQuery.toString());
        } catch (IllegalAccessException | SQLException e) {
            System.err.printf("Có lỗi xảy ra trong quá trình làm việc với database. Error %s.\n", e.getMessage());
        }
        return false;
    }

    public List<T> findAll() {
        List<T> result = new ArrayList<>(); // khởi tạo một danh sách rỗng.
        Entity entityInfor = clazz.getAnnotation(Entity.class);
        StringBuilder stringQuery = new StringBuilder();
        stringQuery.append(SQLConstant.SELECT_ASTERISK); // select *
        stringQuery.append(SQLConstant.SPACE);
        stringQuery.append(SQLConstant.FROM); // from
        stringQuery.append(SQLConstant.SPACE);
        stringQuery.append(entityInfor.tableName()); // tableName

        try {
            PreparedStatement preparedStatement = ConnectionHelper.getConnection().prepareStatement(stringQuery.toString());
            // thực thi câu lệnh select * from.
            // trả về ResultSet (nó thêm thằng con trỏ)
            ResultSet resultSet = preparedStatement.executeQuery();
            Field[] fields = clazz.getDeclaredFields(); //
            while (resultSet.next()) { // trỏ đến các bản ghi cho đến khi trả về false.
                T obj = clazz.newInstance(); // khởi tạo ra đối tượng cụ thể của class T.
                for (Field field : fields) {
                    // check nếu không là @Column
                    if (!field.isAnnotationPresent(Column.class)) {
                        continue;
                    }

                    field.setAccessible(true);
                    // lấy thông tin column để check tên trường, kiểu giá trị của trường.
                    Column columnInfor = field.getAnnotation(Column.class);

                    // tuỳ thuộc vào kiểu dữ liệu của trường, lấy giá trị ra theo các hàm khác nhau.
                    // phải bổ sung các kiểu dữ liệu cần thiết.

                    switch (field.getType().getSimpleName()) {
                        case SQLConstant.PRIMITIVE_STRING:
                            field.set(obj, resultSet.getString(columnInfor.columnName().trim()));
                            break;
                        case SQLConstant.PRIMITIVE_INT:
                            // set giá trị của trường đó cho đối tượng mới tạo ở trên.
                            field.set(obj, resultSet.getInt(columnInfor.columnName().trim()));
                            break;
                        case SQLConstant.PRIMITIVE_DOUBLE:
                            field.set(obj, resultSet.getDouble(columnInfor.columnName().trim()));
                            break;
                        case SQLConstant.PRIMITIVE_DATE:
                            field.set(obj, resultSet.getDate(columnInfor.columnName().trim()));
                            break;

                    }

                }
                // đối tượng obj kiểu T đã có đầy đủ giá trị.
                // add vào trong danh sách trả về.
                result.add(obj);
            }
            return result;

        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            System.err.printf("Có lỗi xảy ra trong quá trình làm việc với database. Error %s.\n", e.getMessage());
        }
        return result;
    }

    public T findById(int id) {
        Entity entityInfor = clazz.getAnnotation(Entity.class);

        Field[] fields = clazz.getDeclaredFields(); //

        StringBuilder stringQuery = new StringBuilder();
        stringQuery.append(SQLConstant.SELECT_ASTERISK);
        stringQuery.append(SQLConstant.SPACE);
        stringQuery.append(SQLConstant.FROM);
        stringQuery.append(SQLConstant.SPACE);
        stringQuery.append(entityInfor.tableName());
        stringQuery.append(SQLConstant.SPACE);
        stringQuery.append(SQLConstant.WHERE);
        stringQuery.append(SQLConstant.SPACE);

        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                Column columnInfor = field.getAnnotation(Column.class);
                stringQuery.append(columnInfor.columnName());
            }
        }
        stringQuery.append(SQLConstant.SPACE);
        stringQuery.append(SQLConstant.EQUAL);
        stringQuery.append(SQLConstant.SPACE);
        stringQuery.append(SQLConstant.QUOTE);
        stringQuery.append(id);
        stringQuery.append(SQLConstant.QUOTE);


        try {
            PreparedStatement preparedStatement = ConnectionHelper.getConnection().prepareStatement(stringQuery.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> result = new ArrayList<>();
            while (resultSet.next()) { // trỏ đến các bản ghi cho đến khi trả về false.
                T obj = clazz.newInstance(); // khởi tạo ra đối tượng cụ thể của class T.
                for (Field field : fields) {
                    // check nếu không là @Column
                    if (!field.isAnnotationPresent(Column.class)) {
                        continue;
                    }

                    field.setAccessible(true);
                    // lấy thông tin column để check tên trường, kiểu giá trị của trường.
                    Column columnInfor = field.getAnnotation(Column.class);

                    // tuỳ thuộc vào kiểu dữ liệu của trường, lấy giá trị ra theo các hàm khác nhau.
                    // phải bổ sung các kiểu dữ liệu cần thiết.

                    switch (field.getType().getSimpleName()) {
                        case SQLConstant.PRIMITIVE_STRING:
                            field.set(obj, resultSet.getString(columnInfor.columnName().trim()));
                            break;
                        case SQLConstant.PRIMITIVE_INT:
                            // set giá trị của trường đó cho đối tượng mới tạo ở trên.
                            field.set(obj, resultSet.getInt(columnInfor.columnName().trim()));
                            break;
                        case SQLConstant.PRIMITIVE_DOUBLE:
                            field.set(obj, resultSet.getDouble(columnInfor.columnName().trim()));
                            break;
                    }

                }
                // đối tượng obj kiểu T đã có đầy đủ giá trị.
                // add vào trong danh sách trả về.
                result.add(obj);
            }
            if (result.size() == 1) {
                return result.get(0);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    public boolean update(T obj) {

        try {
            Entity entityInfor = clazz.getAnnotation(Entity.class);
            Field[] fields = clazz.getDeclaredFields();


            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(SQLConstant.UPDATE);
            stringBuilder.append(SQLConstant.SPACE);
            stringBuilder.append(entityInfor.tableName());
            stringBuilder.append(SQLConstant.SPACE);
            stringBuilder.append(SQLConstant.SET);
            stringBuilder.append(SQLConstant.SPACE);

            for (Field field : fields) {
                // check nếu không là @Column
                if (!field.isAnnotationPresent(Column.class)  ) {
                    continue;
                }
               if(field.isAnnotationPresent(Id.class)){
                   continue;
               }

                field.setAccessible(true);
                Column columnInfor = field.getAnnotation(Column.class);

                stringBuilder.append(columnInfor.columnName());
                stringBuilder.append(SQLConstant.EQUAL);

                stringBuilder.append(SQLConstant.QUOTE);

                stringBuilder.append(field.get(obj));

                stringBuilder.append(SQLConstant.QUOTE);

                stringBuilder.append(SQLConstant.COMMA);
            }
            stringBuilder.setLength(stringBuilder.length() - 1);
            stringBuilder.append(SQLConstant.SPACE);
            stringBuilder.append(SQLConstant.WHERE);
            stringBuilder.append(SQLConstant.SPACE);
            for (Field field : fields) {

                if (field.isAnnotationPresent(Id.class)) {
                    field.setAccessible(true);
                    Column columnInfor = field.getAnnotation(Column.class);
                    stringBuilder.append(columnInfor.columnName());
                    stringBuilder.append(SQLConstant.EQUAL);
                    stringBuilder.append(SQLConstant.QUOTE);

                    stringBuilder.append(field.get(obj));
                    stringBuilder.append(SQLConstant.QUOTE);

                }
            }
            System.out.printf(stringBuilder.toString());
            PreparedStatement preparedStatement = ConnectionHelper.getConnection().prepareStatement(stringBuilder.toString());
            int resultSet = preparedStatement.executeUpdate();
            if(resultSet == 1){
                return true;
            }
            return  false;
        } catch (IllegalAccessException | SQLException  e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean delete(int id) {
        return false;
    }
}
