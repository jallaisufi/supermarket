package al.atis.supermarket.model;

import al.atis.supermarket.model.enums.OperationType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;

import java.time.LocalDateTime;

import static al.atis.supermarket.managment.AppConstants.STORAGEOPERATIONS_PATH;
import static al.atis.supermarket.managment.AppConstants.STORAGEOPERATION_TABLE_NAME;

@Entity
@Table(name = STORAGEOPERATION_TABLE_NAME)

@FilterDef(name = "obj.operation_type", parameters = @ParamDef(name = "operation_type", type = "OperationType"))
@Filter(name = "obj.operation_type", condition = "operation_type = :operation_type")

@FilterDef(name = "obj.bill_uuid", parameters = @ParamDef(name = "bill_uuid", type = "string"))
@Filter(name = "obj.bill_uuid", condition = "bil_uuid = :bill_uuid")

@FilterDef(name = "from.operation_day", parameters = @ParamDef(name = "operation_day", type = "LocalDateTime"))
@Filter(name = "from.operation_day", condition = "operation_day > :operation_day")

@FilterDef(name = "to.operation_date", parameters = @ParamDef(name = "operation_date", type = "LocalDateTime"))
@Filter(name = "to.operation_date", condition = "operation_date < :operation_date")

public class StorageOperation {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    @Id
    public String uuid;

    public OperationType operation_type;
    public String bill_uuid;
    public LocalDateTime operation_date;

    public StorageOperation(){}

    public StorageOperation(OperationType operation_type, String bill_uuid, LocalDateTime operation_date) {
        this.operation_type = operation_type;
        this.bill_uuid = bill_uuid;
        this.operation_date = operation_date;
    }
}
