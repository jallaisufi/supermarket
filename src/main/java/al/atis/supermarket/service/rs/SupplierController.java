package al.atis.supermarket.service.rs;

import al.atis.api.service.RsRepositoryService;
import al.atis.supermarket.model.Supplier;
import al.atis.supermarket.model.User;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static al.atis.supermarket.managment.AppConstants.SUPPLIERS_PATH;

@RestController
@RequestMapping(SUPPLIERS_PATH)
public class SupplierController extends RsRepositoryService<Supplier, String > {
    public SupplierController() {
        super(Supplier.class);
    }

    @Override
    public void applyFilters() throws Exception {
        if (nn("like.name")){
            getEntityManager().unwrap(Session.class).enableFilter("like.name").setParameter("name", likeParamToLowerCase("like.name"));
        }
    }

    @Override
    protected String getDefaultOrderBy() {
        return "name asc";
    }

}
