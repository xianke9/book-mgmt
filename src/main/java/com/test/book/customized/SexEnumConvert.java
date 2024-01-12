package com.test.book.customized;

import com.test.book.constants.Sex;

import javax.persistence.AttributeConverter;

public class SexEnumConvert  implements AttributeConverter<Sex,String> {

    @Override
    public String convertToDatabaseColumn(Sex sex) {
        return sex.getValue();
    }

    @Override
    public Sex convertToEntityAttribute(String sex) {
        if ("男".equals(sex)) {
            return Sex.MALE;
        } else {
            return Sex.FEMALE;
        }

    }
}
