package edu.parammanagment.parammanagment.rest.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@FunctionalInterface
public interface EntityMapper<E, R> {

    R map(E model);

    default List<R> map(Collection<E> models) {
        return models.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
