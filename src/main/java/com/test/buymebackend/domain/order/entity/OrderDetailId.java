package com.test.buymebackend.domain.order.entity;

import java.io.Serializable;
import java.util.Objects;

public class OrderDetailId implements Serializable {

    private Long id;
    private Long order;
    private Long menu;

    public OrderDetailId() {}

    public OrderDetailId(Long id, Long order, Long menu) {
        this.id = id;
        this.order = order;
        this.menu = menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetailId)) return false;
        OrderDetailId that = (OrderDetailId) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(order, that.order) &&
                Objects.equals(menu, that.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, menu);
    }
}
