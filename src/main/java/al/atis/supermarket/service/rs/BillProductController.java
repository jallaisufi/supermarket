package al.atis.supermarket.service.rs;

import al.atis.api.service.RsRepositoryService;
import al.atis.api.service.RsResponseService;
import al.atis.supermarket.model.Bill;
import al.atis.supermarket.model.BillProduct;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static al.atis.supermarket.managment.AppConstants.BILLPRODUCTS_PATH;

@RestController
@RequestMapping(BILLPRODUCTS_PATH)
public class BillProductController extends RsRepositoryService<BillProduct, String> {


    public BillProductController() {
        super(BillProduct.class);
    }

    @Override
    protected String getDefaultOrderBy() {
        return "quantity desc";
    }

    @Override
    public void applyFilters() throws Exception {

    }

    @Override
    protected void prePersist(BillProduct object) throws Exception {
    }
}
