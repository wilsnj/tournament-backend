package net.wilsn.tournament;

import io.quarkus.panache.common.Sort;

import java.util.List;

public class Utils {
    public static Sort getSortFromQuery(List<String> sortQuery) {
        return Sort.ascending("id");
    }
}
