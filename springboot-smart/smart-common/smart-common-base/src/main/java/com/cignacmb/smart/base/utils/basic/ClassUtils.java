package com.cignacmb.smart.base.utils.basic;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ClassUtils {
    /**
     * 获取class的 field，包括父类的
     *
     * @param clazz
     * @return
     */
    public static Field[] getClassFields(Class<?> clazz) {

        List<Field> list = new ArrayList<Field>();
        Field[] fields;
        do {
            fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                list.add(fields[i]);
            }
            clazz = clazz.getSuperclass();
        } while (clazz != Object.class && clazz != null);
        return list.toArray(fields);
    }

    /**
     * 根据字段名称获取该字段的类型
     * @param clazz
     * @param fieldName
     * @return
     */
    public static String getFieldGenericType(Class<?> clazz, String fieldName) {

        String genericType = "";
        Field[] fields;

        do {
            fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {

//            	System.out.println(fields[i].getName() + "***" + BeanUtils.findPropertyType(fields[i].getName(), clazz));
//                System.out.println(fields[i].getName() + "***" + fields[i].getGenericType().toString());
                if(fieldName.equals(fields[i].getName())) {
                    genericType = fields[i].getGenericType().toString();
                    break;
                }
            }
            clazz = clazz.getSuperclass();
        } while (clazz != Object.class && clazz != null);

        return genericType;

    }

}
