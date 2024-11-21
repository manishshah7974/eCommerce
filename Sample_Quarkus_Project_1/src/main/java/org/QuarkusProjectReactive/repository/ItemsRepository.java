package org.QuarkusProjectReactive.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.QuarkusProjectReactive.model.Items;

import java.util.List;

@ApplicationScoped
public class ItemsRepository implements PanacheRepository<Items> {

    // Fetch all items
    public Uni<List<Items>> findAllItems() {
        return listAll();
    }

    // Fetch an item by ID
    public Uni<Items> findItemById(Long itemId) {
        return findById(itemId);
    }

    // Save a new item
    @WithTransaction
    public Uni<Items> saveItem(Items item) {
        return persistAndFlush(item)
                .onFailure().invoke(ex -> System.out.println("Error saving item: " + ex.getMessage()))
                .onItem().transform(savedItem -> {
                    System.out.println("Item saved: " + savedItem.getItemId());
                    return savedItem;
                });
    }

    // Update an existing item
    @WithTransaction
    public Uni<Items> updateItem(Long itemId, Items item) {
        item.setItemId(itemId); // Ensure the ID is set
        return findById(itemId) // Find the item by ID
                .onItem().ifNotNull().transformToUni(existingItem -> {
                    existingItem.setWebOrderNo(item.getWebOrderNo());
                    existingItem.setCouponCode(item.getCouponCode());
                    existingItem.setCouponType(item.getCouponType());
                    existingItem.setCampaignId(item.getCampaignId());
                    existingItem.setProductId(item.getProductId());
                    existingItem.setDescription(item.getDescription());
                    existingItem.setSku(item.getSku());
                    existingItem.setQuantity(item.getQuantity());
                    existingItem.setBundleId(item.getBundleId());
                    existingItem.setBundleQuantity(item.getBundleQuantity());
                    existingItem.setExpectedDispatchDate(item.getExpectedDispatchDate());
                    existingItem.setItemNo(item.getItemNo());
                    existingItem.setVendorId(item.getVendorId());
                    existingItem.setDeliveryType(item.getDeliveryType());
                    existingItem.setSpecialDeliveryDate(item.getSpecialDeliveryDate());
                    existingItem.setIsFragile(item.getIsFragile());
                    existingItem.setIsPrecious(item.getIsPrecious());
                    existingItem.setIsSurface(item.getIsSurface());
                    existingItem.setIsCustomized(item.getIsCustomized());
                    existingItem.setPickLocation(item.getPickLocation());
                    existingItem.setPackagingLocation(item.getPackagingLocation());
                    existingItem.setDspCode(item.getDspCode());
                    existingItem.setIsProcessed(item.getIsProcessed());
                    existingItem.setReadyForArchive(item.getReadyForArchive());
                    existingItem.setInsertedOn(item.getInsertedOn());
                    existingItem.setInsertedBy(item.getInsertedBy());
                    existingItem.setUpdatedOn(item.getUpdatedOn());
                    existingItem.setUpdatedBy(item.getUpdatedBy());
                    existingItem.setLeafCategory(item.getLeafCategory());
                    existingItem.setIsFoc(item.getIsFoc());
                    existingItem.setIsDanger(item.getIsDanger());
                    existingItem.setArabicDescription(item.getArabicDescription());
                    existingItem.setBundleSeqId(item.getBundleSeqId());
                    existingItem.setBundleDescription(item.getBundleDescription());
                    existingItem.setRowId(item.getRowId());
                    existingItem.setBundleProductId(item.getBundleProductId());
                    existingItem.setIsGwp(item.getIsGwp());
                    existingItem.setOfferType(item.getOfferType());
                    existingItem.setPromoGroup(item.getPromoGroup());
                    existingItem.setPromoDesc(item.getPromoDesc());
                    existingItem.setPromoRuleId(item.getPromoRuleId());
                    existingItem.setPromoStartDate(item.getPromoStartDate());
                    existingItem.setPromoEndDate(item.getPromoEndDate());
                    existingItem.setIsPreorder(item.getIsPreorder());
                    existingItem.setBrand(item.getBrand());
                    existingItem.setCategory(item.getCategory());

                    return persistAndFlush(existingItem);
                })
                .onItem().ifNull().failWith(new Exception("Item not found"));
    }

    // Delete an item by ID
    public Uni<Void> deleteItem(Long itemId) {
        return findById(itemId)
                .onItem().transformToUni(item -> {
                    if (item != null) {
                        return delete(item).replaceWithVoid();
                    } else {
                        return Uni.createFrom().voidItem();
                    }
                });
    }

    // Find an item by webOrderNo
    public Uni<List<Items>> findItemByOrderId(String webOrderNo) {
        return find("webOrderNo", webOrderNo).list(); // returns a list of items
    }
}

