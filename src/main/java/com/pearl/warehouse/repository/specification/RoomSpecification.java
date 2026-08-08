package com.pearl.warehouse.repository.specification;

import com.pearl.warehouse.model.Room;
import org.springframework.data.jpa.domain.Specification;

public class RoomSpecification {
    public static Specification<Room> search(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.isBlank()) {
                return cb.conjunction();
            }
            String likePattern = "%" + keyword.toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("roomNo")), likePattern),
                    cb.like(cb.lower(root.get("roomType")), likePattern)
            );
        };
    }
    public static Specification<Room> hasStatus(Boolean status) {
        return (root, query, cb)-> {
            if (status == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("status"), status);
        };

    }
}
