package uttug.bookmarkserver.global.successResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice(basePackages = "gdsc.RunEatServer")
public class SuccessResponseAdvice implements ResponseBodyAdvice {

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {


        HttpServletResponse servletResponse =
                ((ServletServerHttpResponse) response).getServletResponse();

        log.info("-----------------------1---");

        int status = servletResponse.getStatus();
        HttpStatus resolve = HttpStatus.resolve(status);


        if (resolve == null) {
            log.info("-----------------------2---");
            return body;
        }

        if (resolve.is2xxSuccessful()) {
            log.info("resolve={}",resolve);
            log.info("-----------------------3---");
            return new SuccessResponse(status, body);
        }
        log.info("-----------------------4---");

        return body;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {

        log.info("-----------------------suport---");

        return true;
    }


}