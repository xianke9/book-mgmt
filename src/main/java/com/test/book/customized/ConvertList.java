package com.test.book.customized;

import javax.persistence.AttributeConverter;
import java.util.List;

public class ConvertList implements AttributeConverter<List<String>,String> {

    @Override
    public String convertToDatabaseColumn(List<String> strings) {


        if (strings != null && strings.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(strings.get(0));
            for (int i = 1; i < strings.size(); i++) {
                sb.append(",");
                sb.append(strings.get(i));
            }
            return sb.toString();
        }
        return null;
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        return null;
    }
}
