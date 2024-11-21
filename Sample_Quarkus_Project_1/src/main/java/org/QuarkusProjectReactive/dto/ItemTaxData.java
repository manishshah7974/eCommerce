package org.QuarkusProjectReactive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemTaxData {
    private double itemValue;
    private double shippingCharges;
    private double giftCharges;
    private double customizationCharges;
    private double orderProcessCharges;
    private double customDuty;
    private double taxAmount;
    private double discountAmount;
    private double couponAmount;
    private double invoiceValue;
    private double collectableAmount;
}
