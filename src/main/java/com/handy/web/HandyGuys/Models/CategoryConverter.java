package com.handy.web.HandyGuys.Models;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<ECategory, String> {
    @Override
    public String convertToDatabaseColumn(ECategory category) {
        return category != null ? category.getDisplayName() : null;
    }

    @Override
    public ECategory convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        for (ECategory category : ECategory.values()) {
            if (category.getDisplayName().equals(dbData)) {
                return category;
            }
        }

        throw new IllegalArgumentException("Unknown category: " + dbData);
    }
}