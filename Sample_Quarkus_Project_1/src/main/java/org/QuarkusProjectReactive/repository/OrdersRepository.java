package org.QuarkusProjectReactive.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.QuarkusProjectReactive.model.Orders;

import java.util.List;

@ApplicationScoped
public class OrdersRepository implements PanacheRepository<Orders> {

    // Fetch all orders
    public Uni<List<Orders>> findAllOrders() {

        return listAll();
    }

    // Fetch an order by ID

    public Uni<Orders> findOrderById(Long id) {
        return findById(id);
    }

    @WithTransaction
    public Uni<Orders> saveOrders(Orders order) {
        return persistAndFlush(order)
                .onFailure().invoke(ex -> System.out.println("Error saving order: " + ex.getMessage()))
                .onItem().transform(savedOrder -> {
                    System.out.println("Person saved: " + savedOrder.getId());
                    return savedOrder;
                });
    }


    // Update an existing order
    @WithTransaction
    public Uni<Orders> updateOrder(Long id, Orders order) {
        return findById(id)
                .onItem().ifNotNull().transformToUni(existingOrder -> {
                    existingOrder.setWebOrderNo(order.getWebOrderNo());
                    existingOrder.setOrderStatus(order.getOrderStatus());
                    existingOrder.setOrderDateTime(order.getOrderDateTime());
                    existingOrder.setPaymentMethod(order.getPaymentMethod());
                    existingOrder.setUserAssign(order.getUserAssign());
                    existingOrder.setOrderCategory(order.getOrderCategory());
                    existingOrder.setFirstName(order.getFirstName());
                    existingOrder.setMiddleName(order.getMiddleName());
                    existingOrder.setLastName(order.getLastName());
                    existingOrder.setCustomerId(order.getCustomerId());
                    existingOrder.setCustomerEmail(order.getCustomerEmail());
                    existingOrder.setCustomerPhone(order.getCustomerPhone());
                    existingOrder.setPackagingLocation(order.getPackagingLocation());
                    existingOrder.setUpdatedOn(order.getUpdatedOn());
                    existingOrder.setUpdatedBy(order.getUpdatedBy());
                    existingOrder.setReadyForArchive(order.getReadyForArchive());
                    existingOrder.setCountry(order.getCountry());
                    existingOrder.setOrderSource(order.getOrderSource());
                    existingOrder.setOrderAmount(order.getOrderAmount());
                    existingOrder.setCurrency(order.getCurrency());
                    existingOrder.setOrderType(order.getOrderType());
                    existingOrder.setReferenceOrderNo(order.getReferenceOrderNo());
                    existingOrder.setAppOrderNo(order.getAppOrderNo());
                    existingOrder.setCompany(order.getCompany());
                    existingOrder.setIsRuleRun(order.getIsRuleRun());
                    existingOrder.setAssignedByUserId(order.getAssignedByUserId());
                    existingOrder.setStoreId(order.getStoreId());
                    existingOrder.setIsAmeyoSync(order.getIsAmeyoSync());
                    existingOrder.setSyncMessage(order.getSyncMessage());
                    existingOrder.setSyncUpdatedOn(order.getSyncUpdatedOn());
                    existingOrder.setSyncDateTime(order.getSyncDateTime());
                    existingOrder.setCustomerType(order.getCustomerType());

                    return persistAndFlush(existingOrder);
                });
    }

    // Delete a order by ID
    public Uni<Void> deleteOrder(Long id) {
        return findById(id)
                .onItem().transformToUni(order -> {
                    if (order != null) {
                        return delete(order).replaceWithVoid();
                    } else {
                        return Uni.createFrom().voidItem();
                    }
                });
    }
}
