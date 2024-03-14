import org.apache.commons.lang3.ClassUtils;

public class IgnoreFields {
    static boolean checkIgnore(Object obj) {
        if (obj == null) {
            return true;
        }
        if (ClassUtils.isAssignable(obj.getClass(), Number.class)) {
            return true;
        }
        if (ClassUtils.isAssignable(obj.getClass(), String.class)) {
            return true;
        }
        if (ClassUtils.isAssignable(obj.getClass(), Boolean.class)) {
            return true;
        }

        return false;
    }
}
