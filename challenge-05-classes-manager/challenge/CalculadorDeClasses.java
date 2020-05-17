package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {

    private BigDecimal calcular(Object object, Class<? extends Annotation> annotation_class) throws IllegalAccessException {
        verificarObjeto(object);
        BigDecimal resultado = BigDecimal.ZERO;
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            boolean atributo_bigDecimal = field.getType().equals(BigDecimal.class);
            boolean annotation_solicitada = field.isAnnotationPresent(annotation_class);
            if (atributo_bigDecimal && annotation_solicitada) {
                resultado = resultado.add((BigDecimal) field.get(object));
            }
        }
        return resultado;
    }

    private void verificarObjeto(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
    }

    @Override
    public BigDecimal somar(Object object) throws IllegalAccessException {
        return calcular(object, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object object) throws IllegalAccessException {
        return calcular(object, Subtrair.class);
    }

    @Override
    public BigDecimal totalizar(Object object) throws IllegalAccessException {
        return somar(object).subtract(subtrair(object));
    }

}
