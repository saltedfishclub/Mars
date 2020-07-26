package cc.sfclub.mars.database.record;

import cc.sfclub.mars.database.result.IResult;

import java.util.Map;
import java.util.Optional;

public interface IRecord<T> {
    Optional<T> get();

    Map<String, Object> getRaw();

    IResult result();
}
