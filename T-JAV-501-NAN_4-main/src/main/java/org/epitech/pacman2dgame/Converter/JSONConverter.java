package org.epitech.pacman2dgame.Converter;

import java.io.File;

public class JSONConverter extends Converter {
    public void objectToJson(Object object, File file) {
        super.toJson(object, file);
    }

    public Object stringToObject(File file, Class<?> object) {
        return super.toString(file, object);
    }
}
