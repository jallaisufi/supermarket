package al.atis.supermarket.service.rs;

import al.atis.api.service.RsRepositoryService;
import al.atis.supermarket.model.StorageProduct;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static al.atis.supermarket.managment.AppConstants.STORAGEPRODUCTS_PATH;

@RestController
@RequestMapping(STORAGEPRODUCTS_PATH)
public class StorageProductController extends RsRepositoryService<StorageProduct, String> {
    public StorageProductController() {
        super(StorageProduct.class);
    }

    @Override
    public void applyFilters() throws Exception {
        if (nn("obj.product_uuid")){
            String id = get("obj.product_uuid");
            getEntityManager().unwrap(Session.class).enableFilter("obj.product_uuid").setParameter("product_uuid", id);
        }
    }

    @Override
    protected String getDefaultOrderBy() {
        return "quantity desc";
    }
}
