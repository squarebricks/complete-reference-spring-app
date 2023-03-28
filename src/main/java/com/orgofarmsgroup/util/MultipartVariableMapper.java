package com.orgofarmsgroup.util;

import com.orgofarmsgroup.exception.AppException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.regex.Pattern;

public class MultipartVariableMapper {

    private static final Pattern PERIOD = Pattern.compile("\\.");

    private static final Mapper<Map<String, Object>> MAP_MAPPER =
            new Mapper<Map<String, Object>>() {
                @Override
                public Object set(Map<String, Object> location, String target, MultipartFile value) {
                    return location.put(target, value);
                }

                @Override
                public Object recurse(Map<String, Object> location, String target) {
                    return location.get(target);
                }
            };

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void mapVariable(String objectPath, Map<String, Object> variables, MultipartFile part) {
        String[] segments = PERIOD.split(objectPath);

        if (segments.length < 2) {
            throw new AppException("object-path in map must have at least two segments");
        } else if (!"variables".equals(segments[0])) {
            throw new AppException("can only map into variables");
        }

        Object currentLocation = variables;
        for (int i = 1; i < segments.length; i++) {
            String segmentName = segments[i];
            Mapper mapper = determineMapper(currentLocation, objectPath, segmentName);

            if (i == segments.length - 1) {
                if (null != mapper.set(currentLocation, segmentName, part)) {
                    throw new AppException("expected null value when mapping " + objectPath);
                }
            } else {
                currentLocation = mapper.recurse(currentLocation, segmentName);
                if (null == currentLocation) {
                    throw new AppException(
                            "found null intermediate value when trying to map " + objectPath);
                }
            }
        }
    }

    private static Mapper<?> determineMapper(
            Object currentLocation, String objectPath, String segmentName) {
        if (currentLocation instanceof Map) {
            return MAP_MAPPER;
        }

        throw new AppException(
                "expected a map or list at " + segmentName + " when trying to map " + objectPath);
    }

    interface Mapper<T> {

        Object set(T location, String target, MultipartFile value);

        Object recurse(T location, String target);
    }
}