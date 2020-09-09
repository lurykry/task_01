package edu.parammanagment.parammanagment.rest.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@FunctionalInterface
public interface ModelMapper<E, R> {

    R map(E entity);

    default List<R> map(Collection<E> entities) {
        return entities.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
