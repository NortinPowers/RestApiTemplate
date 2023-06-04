package by.tms.rest.template.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponse extends CustomApiResponse implements ResponseAble {

    @Schema(description = "Message describing the exception", example = "Some message")
    private String message;
    @Schema(description = "Exception type", example = "Some exception type")
    private String type;
}
