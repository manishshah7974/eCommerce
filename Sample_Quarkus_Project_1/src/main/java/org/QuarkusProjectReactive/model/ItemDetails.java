package org.QuarkusProjectReactive.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_detail")
public class ItemDetails {

    @Id
    @Column(name = "ItemId")
    private long itemId;

    @Column(name = "WebOrderNo", length = 50)
    private String webOrderNo;

    @Column(name = "ReferenceOrderNo", length = 50)
    private String referenceOrderNo;

    @Column(name = "BoxId", length = 50)
    private String boxId;

    @Column(name = "ItemValue", precision = 10, scale = 3)
    private BigDecimal itemValue;

    @Column(name = "CODCharges", precision = 10, scale = 3)
    private BigDecimal codCharges;

    @Column(name = "ShippingCharges", precision = 10, scale = 3)
    private BigDecimal shippingCharges;

    @Column(name = "GiftCharges", precision = 10, scale = 3)
    private BigDecimal giftCharges;

    @Column(name = "CustomizationCharges", precision = 10, scale = 3)
    private BigDecimal customizationCharges;

    @Column(name = "OrderProcessCharges", precision = 10, scale = 3)
    private BigDecimal orderProcessCharges;

    @Column(name = "CustomDuty", precision = 10, scale = 3)
    private BigDecimal customDuty;

    @Column(name = "TaxMasterId")
    private Integer taxMasterId;

    @Column(name = "TaxAmount", precision = 10, scale = 3)
    private BigDecimal taxAmount;

    @Column(name = "TaxPercentage", precision = 10, scale = 3)
    private BigDecimal taxPercentage;

    @Column(name = "CutomDutyPercentage", precision = 10, scale = 3)
    private BigDecimal customDutyPercentage;

    @Column(name = "InvoiceValue", precision = 10, scale = 3)
    private BigDecimal invoiceValue;

    @Column(name = "CollectableAmount", precision = 10, scale = 3)
    private BigDecimal collectableAmount;

    @Column(name = "TaxBaseAmount", precision = 10, scale = 3)
    private BigDecimal taxBaseAmount;

    @Column(name = "DiscountAmount", precision = 10, scale = 3)
    private BigDecimal discountAmount;

    @Column(name = "CouponAmount", precision = 10, scale = 3)
    private BigDecimal couponAmount;

    @Column(name = "InvoiceNo", length = 50)
    private String invoiceNo;

    @Column(name = "PackagingLocation")
    private Integer packagingLocation;

    @Column(name = "CurrencyCode", length = 20)
    private String currencyCode;

    @Column(name = "CurrencyFactor", precision = 25, scale = 20)
    private BigDecimal currencyFactor;

    @Column(name = "ErrorMessage", length = 500)
    private String errorMessage;

    @Column(name = "ReadyForArchive")
    private Boolean readyForArchive = false;

    @Column(name = "InsertedOn")
    private LocalDateTime insertedOn;

    @Column(name = "InsertedBy", length = 50)
    private String insertedBy;

    @Column(name = "UpdatedOn")
    private LocalDateTime updatedOn;

    @Column(name = "UpdatedBy", length = 50)
    private String updatedBy;

    @Column(name = "RetryCount")
    private Integer retryCount = 0;

    @Column(name = "CustomDuty_USD", precision = 10, scale = 3)
    private BigDecimal customDutyUsd = BigDecimal.ZERO;

    @Column(name = "CollectableAmount_USD", precision = 10, scale = 3)
    private BigDecimal collectableAmountUsd = BigDecimal.ZERO;

    @Column(name = "InvoiceValue_USD", precision = 10, scale = 3)
    private BigDecimal invoiceValueUsd = BigDecimal.ZERO;

    @Column(name = "OtherCharges", precision = 10, scale = 3)
    private BigDecimal otherCharges;

    @Column(name = "UnitPrice", precision = 10, scale = 3)
    private BigDecimal unitPrice;

    @Column(name = "USDCurrencyFactor", precision = 25, scale = 20)
    private BigDecimal usdCurrencyFactor;

    @Column(name = "ItemValueIncCharges", length = 255)
    private String itemValueIncCharges;

    @Column(name = "BoutiqaatCredit", precision = 10, scale = 3)
    private BigDecimal boutiqaatCredit;

    @Column(name = "DiscountItemPrice", precision = 10, scale = 3)
    private BigDecimal discountItemPrice;

    @Column(name = "DiscountItemPercent", precision = 10, scale = 3)
    private BigDecimal discountItemPercent;

    @Column(name = "MrpPrice", precision = 10, scale = 3)
    private BigDecimal mrpPrice;

    @Column(name = "UnitPriceIncludingTax", precision = 10, scale = 3)
    private BigDecimal unitPriceIncludingTax;

    @Column(name = "OriginalPriceIncludingTax", precision = 10, scale = 3)
    private BigDecimal originalPriceIncludingTax;
}
