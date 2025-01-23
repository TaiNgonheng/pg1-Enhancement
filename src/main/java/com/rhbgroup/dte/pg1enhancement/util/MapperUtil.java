package com.rhbgroup.dte.pg1enhancement.util;

import static com.rhbgroup.dte.pg1enhancement.constant.ErrorMessageConstants.JSON_TO_OBJECT_CONVERSION_FAILED;
import static com.rhbgroup.dte.pg1enhancement.constant.ErrorMessageConstants.OBJECT_TO_JSON_CONVERSION_FAILED;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhbgroup.dte.pg1enhancement.exception.ObjectMapperException;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapperUtil {
  private static ObjectMapper objectMapper = new ObjectMapper();

  public static Object getObjectFromJSON(String json, Class<? extends Object> type) {
    if (json == null) return null;
    try {
      return objectMapper.readValue(json, type);
    } catch (JsonProcessingException e) {
      throw new ObjectMapperException(JSON_TO_OBJECT_CONVERSION_FAILED);
    }
  }

  public static String getJSONFromObject(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new ObjectMapperException(OBJECT_TO_JSON_CONVERSION_FAILED);
    }
  }

  public static Object getObjectFromMap(Map map, Class<? extends Object> type) {
    return objectMapper.convertValue(map, type);
  }
}
