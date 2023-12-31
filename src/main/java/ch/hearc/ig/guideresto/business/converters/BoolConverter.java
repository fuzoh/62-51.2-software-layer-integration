package ch.hearc.ig.guideresto.business.converters;

import jakarta.persistence.AttributeConverter;

public class BoolConverter implements AttributeConverter<Boolean, Character> {

    @Override
    public Character convertToDatabaseColumn(Boolean attribute) {
        return attribute ? 'T' : 'F';
    }

    @Override
    public Boolean convertToEntityAttribute(Character dbData) {
        return dbData.equals('T');
    }

}
