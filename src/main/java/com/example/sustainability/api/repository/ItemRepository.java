package com.example.sustainability.api.repository;

import com.example.sustainability.api.data.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByType(String type);

    List<Item> findAllByUserId(Long userId);

}
