package by.tms.rest.template.dto;

import static by.tms.rest.template.utils.Constants.CITY_NAMES_VALIDATION_PATTERN;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Entity of City")
public class CityDto {

    @Schema(description = "Identifier", example = "1")
    @Min(1)
    private Long id;
    @NotBlank(message = "The 'name' field is required")
    @Pattern(regexp = CITY_NAMES_VALIDATION_PATTERN, message = "Incorrect city`s name")
    @Schema(description = "City`s name", example = "London")
    private String name;
    @Schema(description = "Information about the City", example = "Some information")
    private String info;
}
