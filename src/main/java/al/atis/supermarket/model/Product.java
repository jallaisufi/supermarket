package al.atis.supermarket.model;

import al.atis.supermarket.model.enums.Category;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;

import static al.atis.supermarket.managment.AppConstants.PRODUCT_TABLE_NAME;

@Entity
@Table(name = PRODUCT_TABLE_NAME)

@FilterDef(name = "gt.price", parameters = @ParamDef(name = "price", type = "double"))
@Filter(name = "gt.price", condition = "price > :price")

@FilterDef(name = "lt.price", parameters = @ParamDef(name = "price", type = "double"))
@Filter(name = "lt.price", condition = "price < :price")

@FilterDef(name = "like.name", parameters = @ParamDef(name = "name", type = "string"))
@Filter(name = "like.name", condition = "lower(name) LIKE :name")

@FilterDef(name = "obj.category", parameters = @ParamDef(name = "category", type = "string"))
@Filter(name = "obj.category", condition = "category = :category")


public class Product {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    @Id
    public String uuid;

    public String name;
    public double price;
    public String category;

    public Product(){}

    public Product(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category.toString();
    }
}
