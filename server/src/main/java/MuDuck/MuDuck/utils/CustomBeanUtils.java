package MuDuck.MuDuck.utils;


import MuDuck.MuDuck.values.Money;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Collection;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

@Component
public class CustomBeanUtils<T> {

    public T copyNonNullProperties(T source, T destination) {
        if (source == null || destination == null || source.getClass() != destination.getClass()) {
            return null;
        }

        final BeanWrapper src = new BeanWrapperImpl(source);
        final BeanWrapper dest = new BeanWrapperImpl(destination);

        for (final Field field : source.getClass().getDeclaredFields()) {
            PropertyDescriptor propertyDescriptor = src.getPropertyDescriptor(field.getName());
            String propertyName = propertyDescriptor.getPropertyType().getSimpleName();
            // JPA Lazy 로딩 특성으로 인해 연관된 컬렉션의 조회 쿼리가 발생하므로 컬렉션의 경우 패스한다.
            if (propertyName.equals("List")) {
                continue;
            }
            Object sourceProperty = src.getPropertyValue(field.getName());
            if (sourceProperty instanceof Money && ((Money) sourceProperty).getValue() != null) {
                dest.setPropertyValue(field.getName(), sourceProperty);
            } else if (sourceProperty != null &&
                    !(sourceProperty instanceof Collection<?>)
                    && !(sourceProperty instanceof Money)) {
                dest.setPropertyValue(field.getName(), sourceProperty);
            }
        }

        return destination;
    }
}
