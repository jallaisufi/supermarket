package al.atis.supermarket.service.rs;

import al.atis.api.service.RsRepositoryService;
import al.atis.supermarket.model.Bill;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static al.atis.supermarket.managment.AppConstants.BILLS_PATH;

@RestController
@RequestMapping(BILLS_PATH)
public class BillController extends RsRepositoryService<Bill, String> {

    public BillController() {
        super(Bill.class);
    }

    @Override
    protected String getDefaultOrderBy() {
        return "created_date desc";
    }

    @Override
    public void applyFilters() throws Exception {

        if (nn("like.created_by")) {
            getEntityManager().unwrap(Session.class).enableFilter("like.created_by").setParameter("created_by", likeParamToLowerCase("like.created_by"));
        }

        if (nn("gt.total_price")){
            double price = Double.parseDouble(get("gt.total_price"));
            getEntityManager().unwrap(Session.class).enableFilter("gt.total_price").setParameter("total_price",price);
        }

        if (nn("lt.total_price")){
            double price = Double.parseDouble(get("lt.total_price"));
            getEntityManager().unwrap(Session.class).enableFilter("lt.total_price").setParameter("total_price",price);
        }

        if (nn("from.created_date")){
            LocalDateTime date = LocalDateTime.parse(get("from.created_date"));
            getEntityManager().unwrap(Session.class).enableFilter("from.created_date").setParameter("created_date", date);
        }

        if (nn("to.created_date")){
            LocalDateTime date = LocalDateTime.parse(get("to.created_date"));
            getEntityManager().unwrap(Session.class).enableFilter("to.created_date").setParameter("created_date", date);
        }
    }

    @Override
    protected void prePersist(Bill object) throws Exception {
    }
}
