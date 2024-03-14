import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;
import java.util.*;

public class JsonParser<T> {

    //Если есть вложенный объект, тогда можно рекурсивно вызвать метод

    //Регулярка для ключа-> "Что-то":
    //Использовать регулярки

    /*
    Строки
    Числа
    Boolean
    Массивы
    Объекты
    Null
    Одиночные элементы, т.е. когда в JSONе только один элемент и он является значением
     */
    public T parse(String json, Class<T> clazz) {





        return null;
    }



    /*TODO: Не нужны кавычки Числа <? extends Number>  , Null, Boolean
      TODO: Нужны кавычки строкам
      TODO: Скобки массивы и объекты

     */
    public String serialize(T obj) throws IllegalAccessException {
        if (IgnoreFields.checkIgnore(obj)){
            StringBuilder json = new StringBuilder();
            json.append("{");
            json.append(valueOf(obj));
            json.append("}");
            return json.toString();
        }

        StringBuilder json = new StringBuilder();
        json.append("{");



            Class<?> clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            Map<String, Object> properties = new HashMap<>();

            for (int i = 0; i < fields.length; i++) {
                try {
                    fields[i].setAccessible(true);
                } catch (InaccessibleObjectException e) {
                    json.append(valueOf(obj));
                    break;
                }

                String key = fields[i].getName();
                Object value = fields[i].get(obj);
                properties.put(key, value);

                //valueOf(value);

                json.append("\"" + key + "\":");

                json.append(valueOf(value));

                if (i != fields.length - 1) {
                    json.append(",");
                }

            }

        /*for (Map.Entry<String, Object> entry : properties.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }*/
        json.append("}");
        return json.toString();
    }

    public String valueOf(Object obj) throws IllegalAccessException {
        if (obj != null) {
            Class<?> clazz = obj.getClass();
            if (ClassUtils.isAssignable(clazz, Number.class)) {
                return obj.toString();
            }
            if (clazz.isAssignableFrom(Boolean.class))
                return obj.toString();
            if (clazz.isAssignableFrom(String.class)) {
                return "\"" + obj + "\"";
            }
            if (clazz.isArray()) {
                StringBuilder sb = new StringBuilder();
                sb.append("[");

                for (int i = 0; i < Array.getLength(obj); i++) {
                    if (!IgnoreFields.checkIgnore(Array.get(obj, i))) {
                       sb.append(new JsonParser<>().serialize(Array.get(obj, i)));
                    } else {
                        sb.append(valueOf(Array.get(obj, i)));
                    }

                    if (i != Array.getLength(obj) - 1) {
                        sb.append(",");
                    }
                }

                sb.append("]");
                return sb.toString();
            }

            if (ClassUtils.isAssignable(clazz, Collection.class)) {
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                List<?> list = (List<?>) clazz.cast(obj);

                for (int i = 0; i < list.size(); i++) {
                    if (!IgnoreFields.checkIgnore(list.get(i))) {
                        sb.append(new JsonParser<>().serialize(list.get(i)));
                    } else {
                        sb.append(valueOf(list.get(i)));
                    }

                    if (i != list.size() - 1) {
                        sb.append(",");
                    }
                }


                sb.append("]");

                return sb.toString();
            }

            return new JsonParser<>().serialize(obj);
        }

        return null;
    }
    //Можно написать отдельные методоты для парсинга массивов
}
