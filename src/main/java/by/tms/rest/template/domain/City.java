package by.tms.rest.template.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cities")
@NoArgsConstructor
@Getter
@Setter
public class City extends BaseEntity {

    private String name;
    private String info;
}
