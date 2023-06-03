package by.tms.rest.template.service.impl;

import static by.tms.rest.template.utils.ObjectHandlerUtils.getIgnoreProperties;

import by.tms.rest.template.domain.City;
import by.tms.rest.template.dto.CityDto;
import by.tms.rest.template.dto.conversion.Convector;
import by.tms.rest.template.exception.NotFoundException;
import by.tms.rest.template.repository.CityRepository;
import by.tms.rest.template.service.CityService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final Convector convector;

    @Override
    public List<CityDto> getAllCities() {
        return cityRepository.findAll().stream()
                .map(city -> convector.convertToCityDto(city.getId(), city))
                .toList();
    }

    @Override
    public CityDto getCity(Long id) {
        return convector.convertToCityDto(id, cityRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public void addCity(CityDto cityDto) {
        City city = convector.convertToCity(cityDto);
        cityRepository.save(city);
    }

    @Override
    public void updateCity(Long id, CityDto updatedCity) {
        CityDto cityDto = getCity(id);
        if (cityDto != null) {
            BeanUtils.copyProperties(updatedCity, cityDto, getIgnoreProperties(updatedCity, "id"));
            cityRepository.save(convector.convertToCity(id, cityDto));
        }
    }

    @Override
    public void deleteCity(Long id) {
        CityDto cityDto = getCity(id);
        if (cityDto != null) {
            cityRepository.delete(convector.convertToCity(id, cityDto));
        }
    }
}
