package by.tms.rest.template.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorValidationResponse extends BaseResponse {

    @Schema(description = "List of validation errors", type = "array", example = "[\"The 'name' field is required\", \"Some validation error\"]")
    private List<String> errors;
    @Schema(description = "Exception type", example = "Some validation exception")
    private String type;
}
