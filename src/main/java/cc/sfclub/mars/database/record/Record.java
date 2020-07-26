package cc.sfclub.mars.database.record;

import cc.sfclub.mars.database.result.IResult;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Optional;

public class Record<E> implements IRecord<E> {
    private final IResult result;
    private final Class<E> classOfE;
    private E element;
    private Map<String, Object> objectMap;

    public Record(Map<String, Object> objectMap, IResult result, Class<E> classOfE) {
        this.result = result;
        this.objectMap = objectMap;
        this.classOfE = classOfE;
    }

    @Override
    public Optional<E> get() {
        if (element == null) {
            try {
                if (!initialize()) {
                    return Optional.empty();
                }
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }
        return Optional.ofNullable(element);
    }

    private boolean initialize() throws IllegalAccessException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        element = classOfE.getDeclaredConstructor().newInstance();
        for (Field declaredField : element.getClass().getDeclaredFields()) {
            if (objectMap.containsKey(declaredField.getName())) {
                declaredField.setAccessible(true);
                declaredField.set(element, objectMap.get(declaredField.getName()));
            }
        }
        return true;
    }

    @Override
    public IResult result() {
        return result;
    }

    @Override
    public Map<String, Object> getRaw() {
        return objectMap;
    }
}
