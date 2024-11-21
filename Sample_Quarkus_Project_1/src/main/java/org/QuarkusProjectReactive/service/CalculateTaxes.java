package org.QuarkusProjectReactive.service;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.QuarkusProjectReactive.model.ItemDetails;
import org.QuarkusProjectReactive.model.Items;
import org.QuarkusProjectReactive.repository.ItemDetailsRepository;
import org.QuarkusProjectReactive.repository.ItemsRepository;

import java.math.BigDecimal;

@ApplicationScoped
public class CalculateTaxes {

    @Inject
    ItemsRepository itemsRepository;

    @Inject
    ItemDetailsRepository itemDetailsRepository;

    @WithTransaction
    public Uni<Void> calculateTaxforOrder(String id) {
        return itemsRepository.findItemByOrderId(id)  // Find items for the given order ID
                .onItem().transformToMulti(items -> Multi.createFrom().iterable(items)) // Convert List<Items> to Multi<Items>
                .onItem().transform(this::computeTax) // Compute tax for each item
                .onItem().invoke(itemDetails -> {
                    // Save the computed tax data for the item asynchronously
                    itemDetailsRepository.saveItemDetails(itemDetails).subscribe().with(
                            success -> System.out.println("Successfully saved tax details for item: " + itemDetails.getItemId()),
                            failure -> System.err.println("Failed to save tax details for item: " + itemDetails.getItemId())
                    );
                })
                .toUni().replaceWithVoid(); // Convert Multi back to Uni for a single response
    }


    private ItemDetails computeTax(Items item) {
        // Create a new ItemDetails object and calculate taxes
        ItemDetails itemDetails = new ItemDetails();

        itemDetails.setItemId(item.getItemId());
        itemDetails.setWebOrderNo(item.getWebOrderNo());

        // Example tax computation logic, assuming itemValue and charges
        BigDecimal itemValue = item.getQuantity() != null ? BigDecimal.valueOf(item.getQuantity()).multiply(BigDecimal.valueOf(item.getPrice())) : BigDecimal.valueOf(item.getPrice());
        BigDecimal customDuty = itemValue.multiply(BigDecimal.valueOf(0.0));  // Assuming custom duty = 0
        BigDecimal taxAmount = itemValue.multiply(BigDecimal.valueOf(10.0)).divide(BigDecimal.valueOf(100));  // 10% tax
        BigDecimal discountAmount = itemValue.multiply(BigDecimal.valueOf(0.0));  // No discount assumed
        BigDecimal couponAmount = BigDecimal.ZERO;  // No coupon applied

        BigDecimal invoiceValue = itemValue.add(customDuty).add(taxAmount).subtract(discountAmount).subtract(couponAmount);
        BigDecimal collectableAmount = invoiceValue.multiply(BigDecimal.valueOf(12.25));  // Assuming a currency factor

        itemDetails.setItemValue(itemValue);
        itemDetails.setShippingCharges(BigDecimal.ZERO);
        itemDetails.setGiftCharges(BigDecimal.ZERO);
        itemDetails.setCustomizationCharges(BigDecimal.ZERO);
        itemDetails.setOrderProcessCharges(BigDecimal.ZERO);
        itemDetails.setCustomDuty(customDuty);
        itemDetails.setTaxAmount(taxAmount);
        itemDetails.setDiscountAmount(discountAmount);
        itemDetails.setCouponAmount(couponAmount);
        itemDetails.setInvoiceValue(invoiceValue);
        itemDetails.setCollectableAmount(collectableAmount);

        return itemDetails;
    }
}
