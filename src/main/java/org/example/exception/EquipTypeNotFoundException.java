package org.example.exception;

public class EquipTypeNotFoundException extends CodeAbleException{

    public EquipTypeNotFoundException (){
        super(2, "api.registry.equip-types.api-responses.404.description");
    }
}
