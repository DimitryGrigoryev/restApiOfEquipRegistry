package org.example.exception;

public class EquipmentNotFoundException extends CodeAbleException{

    public EquipmentNotFoundException() {
        super(2, "api.registry.equipment.by-name.api-responses.404.description");
    }
}
