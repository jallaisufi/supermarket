package al.atis.supermarket.service.rs;

import al.atis.api.service.RsRepositoryService;
import al.atis.supermarket.model.User;
import al.atis.supermarket.model.enums.Role;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static al.atis.supermarket.managment.AppConstants.USERS_PATH;

@RestController
@RequestMapping(USERS_PATH)
public class UserController extends RsRepositoryService<User, String> {


    public UserController() {
        super(User.class);
    }

    @Override
    public void applyFilters() throws Exception {
        if (nn("obj.role")){
            Role role = Role.valueOf(get("obj.role"));
            getEntityManager().unwrap(Session.class).enableFilter("obj.role").setParameter("role",role);
        }
    }

    @Override
    protected String getDefaultOrderBy() {
        return "username asc";
    }
}
