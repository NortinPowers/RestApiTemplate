package by.tms.rest.template.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MessageResponse extends BaseResponse {

    @Schema(description = "A message describing the completed request", example = "Some message")
    private String message;
    @JsonIgnore
    private Object object;
}
