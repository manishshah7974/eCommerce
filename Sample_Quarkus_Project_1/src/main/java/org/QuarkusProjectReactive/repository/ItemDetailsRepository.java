package org.QuarkusProjectReactive.repository;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.QuarkusProjectReactive.model.ItemDetails;

import java.util.List;

@ApplicationScoped
public class ItemDetailsRepository implements PanacheRepository<ItemDetails> {

    // Fetch all item details
    public Uni<List<ItemDetails>> findAllItemDetails() {
        return listAll();
    }

    // Fetch item details by ID
    public Uni<ItemDetails> findItemDetailsById(Long itemId) {
        return findById(itemId);
    }

    // Save new item details
    @WithTransaction
    public Uni<ItemDetails> saveItemDetails(ItemDetails itemDetails) {
        return persistAndFlush(itemDetails)
                .onFailure().invoke(ex -> System.out.println("Error saving item details: " + ex.getMessage()))
                .onItem().transform(savedItemDetails -> {
                    System.out.println("Item details saved: " + savedItemDetails.getItemId());
                    return savedItemDetails;
                });
    }

    // Update existing item details
    @WithTransaction
    public Uni<ItemDetails> updateItemDetails(Long itemId, ItemDetails itemDetails) {
        itemDetails.setItemId(itemId); // Ensure the ID is set
        return findById(itemId)
                .onItem().ifNotNull().transformToUni(existingItemDetails -> {
                    existingItemDetails.setWebOrderNo(itemDetails.getWebOrderNo());
                    existingItemDetails.setReferenceOrderNo(itemDetails.getReferenceOrderNo());
                    existingItemDetails.setBoxId(itemDetails.getBoxId());
                    existingItemDetails.setItemValue(itemDetails.getItemValue());
                    existingItemDetails.setCodCharges(itemDetails.getCodCharges());
                    existingItemDetails.setShippingCharges(itemDetails.getShippingCharges());
                    existingItemDetails.setGiftCharges(itemDetails.getGiftCharges());
                    existingItemDetails.setCustomizationCharges(itemDetails.getCustomizationCharges());
                    existingItemDetails.setOrderProcessCharges(itemDetails.getOrderProcessCharges());
                    existingItemDetails.setCustomDuty(itemDetails.getCustomDuty());
                    existingItemDetails.setTaxMasterId(itemDetails.getTaxMasterId());
                    existingItemDetails.setTaxAmount(itemDetails.getTaxAmount());
                    existingItemDetails.setTaxPercentage(itemDetails.getTaxPercentage());
                    existingItemDetails.setCustomDutyPercentage(itemDetails.getCustomDutyPercentage());
                    existingItemDetails.setInvoiceValue(itemDetails.getInvoiceValue());
                    existingItemDetails.setCollectableAmount(itemDetails.getCollectableAmount());
                    existingItemDetails.setTaxBaseAmount(itemDetails.getTaxBaseAmount());
                    existingItemDetails.setDiscountAmount(itemDetails.getDiscountAmount());
                    existingItemDetails.setCouponAmount(itemDetails.getCouponAmount());
                    existingItemDetails.setInvoiceNo(itemDetails.getInvoiceNo());
                    existingItemDetails.setPackagingLocation(itemDetails.getPackagingLocation());
                    existingItemDetails.setCurrencyCode(itemDetails.getCurrencyCode());
                    existingItemDetails.setCurrencyFactor(itemDetails.getCurrencyFactor());
                    existingItemDetails.setErrorMessage(itemDetails.getErrorMessage());
                    existingItemDetails.setReadyForArchive(itemDetails.getReadyForArchive());
                    existingItemDetails.setInsertedOn(itemDetails.getInsertedOn());
                    existingItemDetails.setInsertedBy(itemDetails.getInsertedBy());
                    existingItemDetails.setUpdatedOn(itemDetails.getUpdatedOn());
                    existingItemDetails.setUpdatedBy(itemDetails.getUpdatedBy());
                    existingItemDetails.setRetryCount(itemDetails.getRetryCount());
                    existingItemDetails.setCustomDutyUsd(itemDetails.getCustomDutyUsd());
                    existingItemDetails.setCollectableAmountUsd(itemDetails.getCollectableAmountUsd());
                    existingItemDetails.setInvoiceValueUsd(itemDetails.getInvoiceValueUsd());
                    existingItemDetails.setOtherCharges(itemDetails.getOtherCharges());
                    existingItemDetails.setUnitPrice(itemDetails.getUnitPrice());
                    existingItemDetails.setUsdCurrencyFactor(itemDetails.getUsdCurrencyFactor());
                    existingItemDetails.setItemValueIncCharges(itemDetails.getItemValueIncCharges());
                    existingItemDetails.setBoutiqaatCredit(itemDetails.getBoutiqaatCredit());
                    existingItemDetails.setDiscountItemPrice(itemDetails.getDiscountItemPrice());
                    existingItemDetails.setDiscountItemPercent(itemDetails.getDiscountItemPercent());
                    existingItemDetails.setMrpPrice(itemDetails.getMrpPrice());
                    existingItemDetails.setUnitPriceIncludingTax(itemDetails.getUnitPriceIncludingTax());
                    existingItemDetails.setOriginalPriceIncludingTax(itemDetails.getOriginalPriceIncludingTax());

                    return persistAndFlush(existingItemDetails);
                })
                .onItem().ifNull().failWith(new Exception("Item details not found"));
    }

    // Delete item details by ID
    public Uni<Void> deleteItemDetails(Long itemId) {
        return findById(itemId)
                .onItem().transformToUni(itemDetails -> {
                    if (itemDetails != null) {
                        return delete(itemDetails).replaceWithVoid();
                    } else {
                        return Uni.createFrom().voidItem();
                    }
                });
    }

    public Uni<List<ItemDetails>> findItemDetailsByOrderId(String webOrderNo) {
        return find("webOrderNo", webOrderNo).list(); // returns a list of items
    }
}
