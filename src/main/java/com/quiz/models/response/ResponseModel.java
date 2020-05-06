package com.quiz.models.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ResponseModel<T> {

    private boolean success;
    private String message;
    private T data;
    private HttpStatus httpStatus;

    public ResponseModel(ResponseModelBuilder<T> responseModelBuilder) {
        this.success = responseModelBuilder.isSuccess();
        this.message = responseModelBuilder.getMessage();
        this.data = responseModelBuilder.getData();
        this.httpStatus = responseModelBuilder.getHttpStatus();
    }

    @Getter
    public static class ResponseModelBuilder<T>{

        private boolean success;
        private String message;
        private T data;
        private HttpStatus httpStatus;

        public ResponseModelBuilder() {
        }

        public ResponseModel<T> build(){
            return new ResponseModel<>(this);
        }
        
        public ResponseModelBuilder<T> success(boolean success){
            this.success = success;
            return this;
        }

        public ResponseModelBuilder<T> message(String message){
            this.message = message;
            return this;
        }

        public ResponseModelBuilder<T> data(T data){
            this.data = data;
            return this;
        }

        public ResponseModelBuilder<T> httpStatus(HttpStatus httpStatus){
            this.httpStatus = httpStatus;
            return this;
        }

    }

}