package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
public class CarDTO {
    private String serialNumber; //": "string",
    private String manufacture; //": "string",
    private String model; //": "string",
    private String year; //": "string",
    private String fuel; //": "string",
    private int seats; //": 0,
    private String carClass; //": "string",
    private double pricePerDay;//": 0,
    private String about; //: "string",
    private String city; //": "string",
    private double lat; //": 0,
    private double lng; //": 0,
    private String image; //": "string",
    private String owner; //": "string",
    private List<BookedDto> bookedPeriods;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO carDTO = (CarDTO) o;
        return seats == carDTO.seats && Double.compare(pricePerDay, carDTO.pricePerDay) == 0 && Objects.equals(serialNumber, carDTO.serialNumber) && Objects.equals(manufacture, carDTO.manufacture) && Objects.equals(model, carDTO.model) && Objects.equals(year, carDTO.year) && Objects.equals(fuel, carDTO.fuel) && Objects.equals(carClass, carDTO.carClass) && Objects.equals(about, carDTO.about) && Objects.equals(city, carDTO.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, manufacture, model, year, fuel, seats, carClass, pricePerDay, about, city);
    }
}
