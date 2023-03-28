package com.example.microgrowth.DAO.Entities;

import org.springframework.data.jpa.domain.Specification;

public class TrainingSpecs {
    public static Specification<Training> titleContains(String title) {
        return (root, query, cb) -> cb.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<Training> priceLessThanOrEqual(float price) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Training> subjectContains(String subject) {
        return (root, query, cb) -> cb.like(root.get("subject"), "%" + subject + "%");
    }
}
