package al.atis.supermarket.service.rs;

import al.atis.api.service.RsRepositoryService;
import al.atis.supermarket.model.StorageOperation;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static al.atis.supermarket.managment.AppConstants.STORAGEOPERATIONS_PATH;

@RestController
@RequestMapping(STORAGEOPERATIONS_PATH)
public class StorageOperationController extends RsRepositoryService<StorageOperation, String> {
    public StorageOperationController() {
        super(StorageOperation.class);
    }

    @Override
    public void applyFilters() throws Exception {
        if (nn("obj.operation_type")){
            getEntityManager().unwrap(Session.class).enableFilter("obj.operation_type").setParameter("operation_type",likeParamToLowerCase("obj.operation_type"));
        }

        if (nn("obj.bill_uuid")){
            String id = get("obj.bill_uuid");
            getEntityManager().unwrap(Session.class).enableFilter("obj.bill_uuid").setParameter("bill_uuid", id);
        }

        if (nn("from.operation_date")){
            LocalDateTime date = LocalDateTime.parse(get("from.operation_date"));
            getEntityManager().unwrap(Session.class).enableFilter("from.operation_date").setParameter("operation_date",date);
        }

        if (nn("to.operation_date")){
            LocalDateTime date = LocalDateTime.parse(get("to.operation_date"));
            getEntityManager().unwrap(Session.class).enableFilter("to.operation_date").setParameter("operation_date",date);
        }
    }

    @Override
    protected String getDefaultOrderBy() {
        return "operation_date desc";
    }
}
