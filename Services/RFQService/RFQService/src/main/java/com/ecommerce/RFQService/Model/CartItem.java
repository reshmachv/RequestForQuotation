package com.ecommerce.RFQService.Model;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class CartItem{
        private String name;
        private String description;
        private String msn;
        private float price;
        private double quantity;
}
