package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Training;

public  class Specification {
    public static org.springframework.data.jpa.domain.Specification<Training> firstnameContains(String keyword) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("firstName")), "%" + keyword.toLowerCase() + "%");
    }
    public static org.springframework.data.jpa.domain.Specification<Training> lastnameContains(String keyword) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("lasttName")), "%" + keyword.toLowerCase() + "%");
    }
}
