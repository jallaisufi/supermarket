package al.atis.supermarket.service.rs;

import al.atis.api.service.RsRepositoryService;
import al.atis.supermarket.model.Product;
import al.atis.supermarket.model.StorageOperation;
import al.atis.supermarket.model.enums.Category;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static al.atis.supermarket.managment.AppConstants.PRODUCTS_PATH;

@RestController
@RequestMapping(PRODUCTS_PATH)
public class ProductController extends RsRepositoryService<Product, String> {

    @Autowired
    private RsRepositoryService<StorageOperation, String > storageOperationStringRsRepositoryService;



    public ProductController() {
        super(Product.class);
    }

    @Override
    public void applyFilters() throws Exception {
        if (nn("gt.price")){
            double price = Double.parseDouble(get("gt.price"));
            getEntityManager().unwrap(Session.class).enableFilter("gt.price").setParameter("price", price);
        }

        if (nn("lt.price")){
            double price = Double.parseDouble(get("lt.price"));
            getEntityManager().unwrap(Session.class).enableFilter("lt.price").setParameter("price", price);
        }

        if (nn("like.name")){
            getEntityManager().unwrap(Session.class).enableFilter("like.name").setParameter("name", likeParamToLowerCase("like.name"));
        }

        if (nn("obj.category")){
            String category = get("obj.category");
            getEntityManager().unwrap(Session.class).enableFilter("obj.category").setParameter("category", category);
        }
    }

    @Override
    protected void postPersist(Product object) throws Exception {

    }

    @Override
    protected String getDefaultOrderBy() {
        return "price desc";
    }
}
