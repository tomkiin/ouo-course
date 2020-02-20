package run.ouo.app.config.handler;

import run.ouo.app.model.common.HttpStatusCode;
import run.ouo.app.model.vo.response.ParameterErrorVO;
import run.ouo.app.model.vo.response.ResultVO;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 全局自定义异常处理
 */
@RestControllerAdvice
public class BindExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)  // 字段验证错误
    @ResponseBody
    public ResultVO handleBindException(MethodArgumentNotValidException ex, HttpServletResponse response) {
        List<ParameterErrorVO> errorVOList = new ArrayList<>();

        // 封装错误信息
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
        for (ObjectError error : errorList) {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorVOList.add(new ParameterErrorVO(fieldName, message));
        }

        errorVOList.sort(Comparator.comparing(ParameterErrorVO::getFieldName));
        String message = "请求参数无效";

        if (errorVOList.size() > 0) {
            ParameterErrorVO firstError = errorVOList.get(0);
            message += ": " + firstError.getFieldName() + "-" + firstError.getMessage();
        }

        response.setStatus(HttpStatusCode.BAD_REQUEST);
        return new ResultVO(ResultVO.INVALID_PARAMETER, message, errorVOList);
    }
}
