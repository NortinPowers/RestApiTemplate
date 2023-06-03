package by.tms.rest.template.service;

import by.tms.rest.template.dto.CityDto;
import java.util.List;

public interface CityService {

    List<CityDto> getAllCities();

    CityDto getCity(Long id);

    void addCity(CityDto cityDto);

    void updateCity(Long id, CityDto cityDto);

    void deleteCity(Long id);
}
