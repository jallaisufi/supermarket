package al.atis.supermarket.model;

import al.atis.supermarket.model.enums.Role;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;

import static al.atis.supermarket.managment.AppConstants.USER_TABLE_NAME;


@Entity
@Table(name = USER_TABLE_NAME)

@FilterDef(name = "obj.role", parameters = @ParamDef(name = "role", type = "string"))
@Filter(name = "obj.role", condition = "role = :role")

public class User {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    @Id
    public String uuid;

    public Role role;
    public String username;
    public String password;

    public User(){}

    public User(Role role, String username, String password) {
        this.role = role;
        this.username = username;
        this.password = password;
    }
}
