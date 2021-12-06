package al.atis.api.service;

import al.atis.api.management.AppConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public abstract class RsResponseService implements Serializable {

    @Autowired
    WebRequest ui;

    private static final long serialVersionUID = 1L;

    public static ResponseEntity jsonResponse(Map<String, String> toJson, HttpStatus status) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = "";
        try {
            jsonStr = objectMapper.writeValueAsString(toJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(status).body(jsonStr);
    }

    public static ResponseEntity jsonResponse(HttpStatus status, String key, Object value) {
        Map<String, String> toJson = new HashMap<>();
        toJson.put(key, value.toString());
        return jsonResponse(toJson, status);
    }

    public static ResponseEntity jsonMessageResponse(HttpStatus status, Object object) {
        if (object instanceof Throwable) {
            Throwable t = (Throwable) object;
            return jsonResponse(status, AppConstants.JSON_GENERIC_MESSAGE_KEY, getErrorMessage(t));
        } else {
            return jsonResponse(status, AppConstants.JSON_GENERIC_MESSAGE_KEY, "" + object);

        }
    }

    public static ResponseEntity jsonErrorMessageResponse(Object error) {
        if (error instanceof Throwable) {
            Throwable t = (Throwable) error;
            return jsonResponse(HttpStatus.INTERNAL_SERVER_ERROR, AppConstants.JSON_GENERIC_MESSAGE_KEY, getErrorMessage(t));
        } else {
            return jsonResponse(HttpStatus.INTERNAL_SERVER_ERROR, AppConstants.JSON_GENERIC_MESSAGE_KEY, "" + error);
        }
    }

    private static String getErrorMessage(Throwable t) {
        String exceptionClass = t.getClass().getCanonicalName();
        return t.getMessage() == null ?
                exceptionClass : MessageFormat.format("{0}: {1}", exceptionClass, t.getMessage());
    }

    @SuppressWarnings("unchecked")
    public <T> T cast(String key, Class<T> clazz) {
        String value = ui.getParameter(key);
        if (Long.class.equals(clazz)) {
            return (T) Long.valueOf(value);
        }
        if (Integer.class.equals(clazz)) {
            return (T) Integer.valueOf(value);
        }
        if (Boolean.class.equals(clazz)) {
            return (T) Boolean.valueOf(value);
        }
        return (T) value;
    }

    public String get(String key) {
        return ui.getParameter(key);
    }

    public String lowercase(String key) {
        return get(key) != null ? get(key).toLowerCase() : null;
    }

    public Integer _integer(String key) {
        String value = ui.getParameter(key);
        return Integer.valueOf(value);
    }

    public Long _long(String key) {
        String value = ui.getParameter(key);
        return Long.valueOf(value);
    }

    public Boolean _boolean(String key) {
        String value = ui.getParameter(key);
        return Boolean.valueOf(value);
    }

    protected final String likeParamToLowerCase(String value) {
        return "%" + get(value).toLowerCase() + "%";
    }


    protected boolean nn(String key) {
        return ui.getParameterMap().containsKey(key)
                && ui.getParameter(key) != null
                && !ui.getParameter(key).trim().isEmpty();
    }

    protected String likeParam(String param) {
        return "%" + get(param) + "%";
    }

    protected String likeParamL(String param) {
        return "%" + get(param);
    }

    protected String likeParamR(String param) {
        return get(param) + "%";
    }

    public List<String> asList(String key) {
        String value = get(key);
        return Stream.of(value.split(",", -1))
                .collect(Collectors.toList());
    }

    public List<String> fromValueToList(String value) {
        return Stream.of(value.split(",", -1))
                .collect(Collectors.toList());
    }
}