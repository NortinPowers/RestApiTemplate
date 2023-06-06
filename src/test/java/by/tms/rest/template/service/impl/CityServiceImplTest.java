package by.tms.rest.template.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import by.tms.rest.template.domain.City;
import by.tms.rest.template.dto.CityDto;
import by.tms.rest.template.mapper.CityMapper;
import by.tms.rest.template.repository.CityRepository;
import by.tms.rest.template.service.CityService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CityServiceImplTest {

    @Autowired
    private CityService cityService;

    @MockBean
    private CityRepository cityRepository;

    @MockBean
    private CityMapper cityMapper;

    @Test
    void getAllCities() {
        List<City> cities = new ArrayList<>();
        cities.add(new City());
        cities.add(new City());
        Mockito.when(cityRepository.findAll()).thenReturn(cities);
        List<CityDto> allCities = cityService.getAllCities();
        verify(cityRepository, Mockito.times(1)).findAll();
        assertThat(allCities.size()).isEqualTo(2);
    }

    @Test
    void getCity() {
        City city = new City();
        Mockito.when(cityRepository.findById(1L)).thenReturn(Optional.of(city));
        CityDto cityDto = cityService.getCity(1L);
        verify(cityRepository, Mockito.times(1)).findById(1L);
        verify(cityMapper, Mockito.times(1)).convertToCityDto(city);
        assertThat(cityDto).isEqualTo(cityMapper.convertToCityDto(city));
    }

    @Test
    void addCity() {
        CityDto cityDto = new CityDto();
        cityService.addCity(cityDto);
        verify(cityMapper, Mockito.times(1)).convertToCity(cityDto);
        City city = cityMapper.convertToCity(cityDto);
        verify(cityRepository, Mockito.times(1)).save(city);
    }

    @Test
    void updateCity() {
        City city = new City();
        Mockito.when(cityRepository.findById(2L)).thenReturn(Optional.of(city));
        cityService.getCity(2L);
        CityDto cityUpdateDto = new CityDto();
        City cityUpdate = new City();
        Mockito.when(cityMapper.convertToCity(cityUpdateDto)).thenReturn(cityUpdate);
        cityService.updateCity(2L, cityUpdateDto);
        verify(cityRepository, Mockito.times(1)).save(city);
    }

    @Test
    void deleteCity() {
        City city = new City();
        Mockito.when(cityRepository.findById(1L)).thenReturn(Optional.of(city));
        cityService.deleteCity(1L);
        verify(cityRepository, Mockito.times(1)).delete(city);
    }
}
