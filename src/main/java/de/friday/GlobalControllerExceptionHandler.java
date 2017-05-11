package de.friday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage genericException(RuntimeException ex) {
        log.error("Unexpected error", ex);
        return createErrorMessage("An internal error happend while the system tried to fulfill the request.");
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage badRequest(Exception ex) {
        return createErrorMessage(ex.getLocalizedMessage() != null ? ex.getLocalizedMessage() : ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage typeMismatch(MethodArgumentTypeMismatchException ex) {
        String validationMsg = getLocalizedMessage(ex.getErrorCode(), new Object[] {ex.getValue(), ex.getName()});
        return createErrorMessage(validationMsg);
    }

   

    private static ErrorMessage createErrorMessage(String message) {
        ErrorMessage msg = new ErrorMessage();
        ErrorMessage.Message m = new ErrorMessage.Message(message);
        msg.addError(m);
        return msg;
    }

    private String getLocalizedMessage(String messageCode, Object[] args) {
        try {
            return messageSource.getMessage(messageCode, args, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            log.warn("Error code {} not found. Returning the code", messageCode);
            return messageCode;
        }
    }
}
