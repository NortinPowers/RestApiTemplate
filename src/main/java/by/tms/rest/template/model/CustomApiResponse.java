package by.tms.rest.template.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomApiResponse {

    @Schema(description = "Timestamp of the message", example = "1685180470632")
    private final Long timestamp = System.currentTimeMillis();
}
