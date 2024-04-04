package br.ucsal.biblioteca.util;

import java.lang.reflect.Field;

public class ValidationUtils {

    public static boolean validateStringSizeMin(Object obj) {
        if (obj == null) {
            return false;
        }

        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            StringSizeMin annotation = field.getAnnotation(StringSizeMin.class);
            if (annotation != null) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    if (value instanceof String) {
                        String stringValue = (String) value;
                        if (stringValue.length() <= annotation.value()) {
                            return false; // Falha na validação
                        }
                    } else {
                        throw new IllegalArgumentException("O campo " + field.getName() + " não é uma String.");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace(); // Trata o erro adequadamente
                }
            }
        }
        return true; // Todos os campos validados com sucesso
    }
}

