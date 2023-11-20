package br.com.tangerino.tangerino.model.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ResponseErrorDTO {

    private String message;
    private String code;

    public ResponseErrorDTO(String message) {
        this.message = message;
    }

    public ResponseErrorDTO(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
