package org.QuarkusProjectReactive.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.QuarkusProjectReactive.repository.OrdersRepository;

@ApplicationScoped
public class FetchOrders {
    @Inject
    private OrdersRepository ordersRepository;


}
