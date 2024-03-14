import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        int i = 13;
        String s = "hello";
        Dog dog = new Dog(3, "Bobik");
        Dog nulldog = null;
        List<Dog> dogsList = new ArrayList<>(Arrays.asList(dog, new Dog(22, "Tolik"), nulldog));
        User user = new User(1, "Peter", new int[]{1, 3, 5}, null, true, dog, dogsList);

        JsonParser<User> parser = new JsonParser<>();

        System.out.println(parser.serialize(user));
        System.out.println(new JsonParser<>().serialize("qerqerqw"));
        System.out.println(new JsonParser<>().serialize(33));
        System.out.println(new JsonParser<>().serialize(null));



        //System.out.println(new JsonParser<>().serialize(33));
        //System.out.println(int.class.isPrimitive());
        //System.out.println(obj.getClass().getName());
        //System.out.println(new JsonParser<String>().serialize("qqqq"));
        //System.out.println(new int[] {111}.getClass().getCanonicalName());

        /*int primitive = 33;
        Integer wrap = 22;
        Object obP = primitive;
        Object obW =wrap;

        System.out.println("obP " + obP.getClass().isAssignableFrom(Number.class));
        System.out.println("obW " + obW.getClass().isAssignableFrom(Number.class));


        System.out.println("obP " + ClassUtils.isAssignable(int.class, Number.class));
        System.out.println("obW " + ClassUtils.isAssignable(Integer.class, Number.class));
*/
        /*Number num = Integer.valueOf(23);
        Number prim = 33;*/

        /*Object list = dogsList;
        Object array = new int[] {1, 2, 3};
        System.out.println(Array.getLength(array));
        System.out.println(Array.get(array, 2));
        System.out.println(array.getClass().getComponentType());


        //Arrays.toString(array.getClass().cast(array));
        System.out.println(array.getClass().cast(array));
        System.out.println(dogsList.getClass().cast(list));*/

    }


}
