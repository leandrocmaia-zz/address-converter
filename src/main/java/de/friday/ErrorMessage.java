package de.friday;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.val;

public class ErrorMessage {
    @Getter
    private List<Message> errors = new ArrayList<>();

    public void addError(Message message) {
        errors.add(message);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {
        @Getter
        private String message;
    }

    @JsonIgnore
    public static ErrorMessage createErrorMessage(Exception ex) {
        val msg = new ErrorMessage();
        ErrorMessage.Message m = new ErrorMessage.Message(ex.getMessage());
        msg.addError(m);
        return msg;
    }
}
