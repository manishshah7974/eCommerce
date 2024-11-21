package org.QuarkusProjectReactive.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@FieldNameConstants
public class OrderFilterRequest {

    private String webOrderNo;
    private String customerPhone;
    private String customerEmail;
    private String appOrderNo;
    private String agentName;
    private String countryName;
    private String city;
    private String customerCategory;
    private String orderStatus;
    private String paymentMethod;
    private String customerId;
    private String customerName;
    private String awbNo;
    private String company;
    private String orderType;
    private String myFatoorahInvNo;
    private String forwardPG;
    private String expression;
    private String value;
    private Boolean isStockReserved;
    private String pkgLocation;
    private String userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private LocalDateTime fromDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private LocalDateTime toDate;

    private Integer page;
    private Integer size;

}
