package com.rs2.ecommerce.product;

import javax.persistence.Converter;
import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ProductTypeConverter implements AttributeConverter<ProductType, Character> {

    @Override
    public Character convertToDatabaseColumn(ProductType productType) {
        if (productType == null) {
            return null;
        }
        return productType.getType();
    }

    @Override
    public ProductType convertToEntityAttribute(Character character) {
        if (character == null) {
            return null;
        }

        return Stream.of(ProductType.values())
                .filter(p -> p.getType() == character)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
