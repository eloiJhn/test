package org.epitech.pacman2dgame.Converter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public abstract class Converter {
    public void toJson(Object object, File file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(file, object);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Object toString(File file, Class<?> object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(file, object);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
