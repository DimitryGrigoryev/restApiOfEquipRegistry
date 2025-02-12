package org.example.repository;

import org.example.repository.entity.Model;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ModelRepository extends JpaRepository<Model, String> {
    List<Model> findModelsByEquipmentId(Long id);
    List<Model> findModelsByEquipmentId_NameAndAvailableIsTrue(String name);
    Model findByNameIgnoreCase(String name);
    List<Model> findModelsByColor(String color);
    List<Model> findModelsByPriceBetweenOrderByPrice(double price, double price2, Pageable pageable);
    Model findFirstByPriceIsNotNullOrderByPriceDesc();
    Model findFirstByPriceIsNotNullOrderByPriceAsc();
    List<Model> findModelsByEquipmentId_Name(String equipName, Pageable pageable);
}
