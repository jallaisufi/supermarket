package al.atis.supermarket.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static al.atis.supermarket.managment.AppConstants.BILLPRODUCT_TABLE_NAME;

//@Data
@Entity
@Table(name = BILLPRODUCT_TABLE_NAME)
public class BillProduct {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    @Id
    public String uuid;

    public String product_uuid;
    public int quantity;
}
