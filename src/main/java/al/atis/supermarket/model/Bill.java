package al.atis.supermarket.model;

        import al.atis.api.service.RsRepositoryService;
        import lombok.Data;
        import org.hibernate.annotations.Filter;
        import org.hibernate.annotations.FilterDef;
        import org.hibernate.annotations.GenericGenerator;
        import org.hibernate.annotations.ParamDef;

        import javax.persistence.*;
        import java.time.LocalDateTime;
        import java.util.List;

        import static al.atis.supermarket.managment.AppConstants.BILL_TABLE_NAME;

//@Data
@Entity
@Table(name = BILL_TABLE_NAME)

@FilterDef(name = "like.created_by", parameters = @ParamDef(name = "created_by", type = "string"))
@Filter(name = "like.created_by", condition = "created_by LIKE :created_by")

@FilterDef(name = "gt.total_price", parameters = @ParamDef(name = "total_price", type = "double"))
@Filter(name = "gt.total_price", condition = "total_price > :total_price")

@FilterDef(name = "lt.total_price", parameters = @ParamDef(name = "total_price", type = "double"))
@Filter(name = "lt.total_price", condition = "total_price < :total_price")

@FilterDef(name = "from.created_date", parameters = @ParamDef(name = "created_date", type = "LocalDateTime"))
@Filter(name = "from.created_date", condition = "created_date >= :created_date")

@FilterDef(name = "to.created_date", parameters = @ParamDef(name = "created_date", type = "LocalDateTime"))
@Filter(name = "to.created_date", condition = "created_date <= :created_date")

public class Bill{

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    @Id
    public String uuid;

    public double total_price;
    public LocalDateTime created_date;
    public String created_by;
    public Bill(){}

    public Bill(double total_price, LocalDateTime created_date, String created_by) {
        this.total_price = total_price;
        this.created_date = created_date;
        this.created_by = created_by;
    }
}
