package org.QuarkusProjectReactive.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "items")
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(name = "WebOrderNo", length = 50)
    private String webOrderNo;

    @Column
    private double price;

    @Column(name = "CouponCode", length = 100)
    private String couponCode;

    @Column(name = "CouponType", length = 150)
    private String couponType;

    @Column(name = "CampaignId", length = 100)
    private String campaignId;

    @Column(name = "ProductId", length = 45)
    private String productId;

    @Column(name = "Description", length = 1000)
    private String description;

    @Column(name = "SKU", length = 255)
    private String sku;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "BundleID", length = 100)
    private String bundleId;

    @Column(name = "BundleQuantity")
    private Integer bundleQuantity;

    @Column(name = "ExpectedDispatchDate")
    private LocalDateTime expectedDispatchDate;

    @Column(name = "ItemNo", length = 20)
    private String itemNo;

    @Column(name = "VendorID")
    private Integer vendorId;

    @Enumerated(EnumType.STRING)
    @Column(name = "DeliveryType", length = 50)
    private DeliveryType deliveryType;

    @Column(name = "SpecialDeliveryDate")
    private LocalDateTime specialDeliveryDate;

    @Column(name = "IsFragile")
    private Boolean isFragile;

    @Column(name = "IsPrecious")
    private Boolean isPrecious;

    @Column(name = "IsSurface")
    private Boolean isSurface;

    @Column(name = "IsCustomized")
    private Boolean isCustomized;

    @Column(name = "PickLocation", length = 50)
    private String pickLocation;

    @Column(name = "PackagingLocation")
    private Integer packagingLocation;

    @Column(name = "DspCode", length = 45)
    private String dspCode;

    @Column(name = "IsProcessed")
    private Boolean isProcessed;

    @Column(name = "ReadyForArchive")
    private Boolean readyForArchive;

    @Column(name = "InsertedOn")
    private LocalDateTime insertedOn;

    @Column(name = "InsertedBy", length = 100)
    private String insertedBy;

    @Column(name = "UpdatedOn")
    private LocalDateTime updatedOn;

    @Column(name = "UpdatedBy", length = 100)
    private String updatedBy;

    @Column(name = "LeafCategory", length = 255)
    private String leafCategory;

    @Column(name = "IsFOC")
    private Boolean isFoc;

    @Column(name = "IsDanger")
    private Boolean isDanger;

    @Column(name = "ArabicDescription", length = 1000)
    private String arabicDescription;

    @Column(name = "BundleSeqID", length = 100)
    private String bundleSeqId;

    @Column(name = "BundleDescription", length = 1000)
    private String bundleDescription;

    @Column(name = "RowId")
    private Integer rowId;

    @Column(name = "BundleProductId", length = 45)
    private String bundleProductId;

    @Column(name = "IsGWP")
    private Boolean isGwp;

    @Column(name = "OfferType", length = 50)
    private String offerType;

    @Column(name = "PromoGroup", length = 100)
    private String promoGroup;

    @Column(name = "PromoDesc", length = 1000)
    private String promoDesc;

    @Column(name = "PromoRuleId", length = 50)
    private String promoRuleId;

    @Column(name = "PromoStartDate")
    private LocalDateTime promoStartDate;

    @Column(name = "PromoEndDate")
    private LocalDateTime promoEndDate;

    @Column(name = "IsPreorder")
    private Boolean isPreorder;

    @Column(name = "Brand", length = 100)
    private String brand;

    @Column(name = "Category", length = 100)
    private String category;

    public enum DeliveryType {
        NORMAL, EXPRESS, SPECIAL, SURFACE
    }
}
