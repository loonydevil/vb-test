package nef.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class BaseController implements ErrorController {

    public static final String PATH = "/error";
    public static final String EXCEPTION_KEY = "org.springframework.boot.autoconfigure.web.DefaultErrorAttributes.ERROR";
    public static final String STATUS_KEY = "javax.servlet.error.status_code";
    
    @RequestMapping(PATH)
    public Map<String, Object> error(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", request.getAttribute(STATUS_KEY));
        Exception e = (Exception) request.getAttribute(EXCEPTION_KEY);
        if (e != null) {
            map.put("details", e.getMessage());
        }
        return map;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
    
}
