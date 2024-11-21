package org.QuarkusProjectReactive.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Orders", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"WebOrderNo", "CustomerEmail", "CustomerId", "CustomerPhone"})
})
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "WebOrderNo", length = 50, unique = true)
    private String webOrderNo;

    @Column(name = "OrderStatus")
    private Integer orderStatus;

    @Column(name = "OrderDateTime")
    private LocalDateTime orderDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "PaymentMethod", length = 10)
    private PaymentMethod paymentMethod;

    @Column(name = "UserAssign", length = 45)
    private String userAssign;

    @Column(name = "OrderCategory", length = 50)
    private String orderCategory;

    @Column(name = "FirstName", length = 100)
    private String firstName;

    @Column(name = "MiddleName", length = 100)
    private String middleName;

    @Column(name = "LastName", length = 100)
    private String lastName;

    @Column(name = "CustomerId", length = 30)
    private String customerId;

    @Column(name = "CustomerEmail", length = 100)
    private String customerEmail;

    @Column(name = "CustomerPhone", length = 100)
    private String customerPhone;

    @Column(name = "PackagingLocation")
    private Integer packagingLocation;

    @Column(name = "InsertedOn", updatable = false)
    private LocalDateTime insertedOn;

    @Column(name = "InsertedBy", length = 45)
    private String insertedBy;

    @Column(name = "UpdatedOn")
    private LocalDateTime updatedOn;

    @Column(name = "UpdatedBy", length = 45)
    private String updatedBy;

    @Column(name = "ReadyForArchive")
    private Boolean readyForArchive;

    @Column(name = "Country", length = 100)
    private String country;

    @Column(name = "OrderSource", length = 50)
    private String orderSource;

    @Column(name = "OrderAmount", precision = 10, scale = 2)
    private BigDecimal orderAmount;

    @Column(name = "Currency", length = 45)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "OrderType", length = 20)
    private OrderType orderType;

    @Column(name = "ReferenceOrderNo", length = 50)
    private String referenceOrderNo;

    @Column(name = "AppOrderNo", length = 50)
    private String appOrderNo;

    @Column(name = "Company", length = 50)
    private String company;

    @Column(name = "IsRuleRun")
    private Integer isRuleRun;

    @Column(name = "AssigndByUserId")
    private Integer assignedByUserId;

    @Column(name = "StoreId", length = 20)
    private String storeId;

    @Column(name = "IsAmeyoSync")
    private Boolean isAmeyoSync;

    @Column(name = "SyncMessage", length = 500)
    private String syncMessage;

    @Column(name = "SyncUpdatedOn")
    private LocalDateTime syncUpdatedOn;

    @Column(name = "SyncDateTime")
    private LocalDateTime syncDateTime;

    @Column(name = "CustomerType", length = 50)
    private String customerType;

    // Enum for PaymentMethod
    public enum PaymentMethod {
        COD, PREPAID, EXCHANGE
    }

    // Enum for OrderType
    public enum OrderType {
        NORMAL, EXPRESS, SURFACE, EXCHANGE, PRIORITY, SPECIALDELIVERY, GIFT, GiftCard, PREORDER, VAS
    }
}
