package al.atis.supermarket.model;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;

import static al.atis.supermarket.managment.AppConstants.STORAGEPRODUCT_TABLE_NAME;

@Entity
@Table(name = STORAGEPRODUCT_TABLE_NAME)

@FilterDef(name = "obj.product_uuid", parameters = @ParamDef(name = "product_uuid", type = "string"))
@Filter(name = "obj.product_uuid", condition = "product_uuid = :product_uuid")

public class StorageProduct {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    @Id
    public String uuid;

    public String product_uuid;
    public int quantity;

    public StorageProduct(){}

    public StorageProduct(String product_uuid, int quantity) {
        this.product_uuid = product_uuid;
        this.quantity = quantity;
    }
}
