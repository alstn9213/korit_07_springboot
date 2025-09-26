package com.example.cardatabase;


import com.example.cardatabase.domain.Car;
import com.example.cardatabase.domain.CarRepository;
import com.example.cardatabase.domain.Owner;
import com.example.cardatabase.domain.OwnerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    @DisplayName("차량 저장 메서드 테스트")
    void saveCar() {
//        given - 제반 준비 사항
//        Car Entity를 확인했을 때 field로 Owner를 요구하기 때문에
//        얘부터 먼저 만들고 -> ownerRepositor에 저장
        Owner owner = new Owner("Gemini", "GPT");
        ownerRepository.save(owner);
//        그리고 Car 객체를 만든다.
        Car car = new Car("Ford", "Mustang", "Red", "ABCDEF", 2021, 567890, owner);

//        when - 테스트 실행
//        저장이 됐는가를 확인하기 위한 부분
        carRepository.save(car);
//        then - 그 결과가 어떠할지
        assertThat(carRepository.findById(car.getId())).isPresent(); // 이건 그냥 결과값이 하나일테니까
        assertThat(carRepository.findById(car.getId()).get().getBrand()).isEqualTo("Ford");
    }

    @Test
    @DisplayName("테스트메서드")
    void deleteCar() {
//        given -> Owner 객체 생성 / 저장 -> Car 객체 생성 / 저장
        Owner owner2 = new Owner("kim", "minsu");
        ownerRepository.save(owner2);
        Car car2 = new Car("Ford", "Mustang", "Red", "EFGHIJ", 1998, 60000000, owner2);
        carRepository.save(car2);
//        when -> 삭제
//        Id로 삭제하는 방법
//        carRepository.deleteById(car2.getId());
        carRepository.deleteAll();
//        then -> 삭제가 올바로 되었는지 검증하는 assertThat() 구문
//        assertThat(carRepository.count()).isEqualTo(3);

        assertThat(carRepository.count()).isEqualTo(0);

    }

    @Test
    @DisplayName("테스트")
    void findByBrandShouldReturnCar() {
//        given - Owner 하나 생성 및 저장 / Car 객체 3대 생성 및 저장
        Owner owner1 = new Owner("kim", "il");
        ownerRepository.save(owner1);
        Car car1 = new Car("Ford", "Mustang", "Red", "EFGHIJ", 1998, 60000000, owner1);
        Car car2 = new Car("Ford", "Mustang", "Red", "EFGHIJ", 1998, 60000000, owner1);
        Car car3 = new Car("Ford3", "Mustang", "Red", "EFGHIJ", 1998, 60000000, owner1);

//        when - carRepository.findByBrand("브랜드명") -> 얘의 자료형이 list
        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);

//        then에서의 검증은 list 내부의 element 자료형이 Car객체일테니까
//        그 객체의 getBrand()의 결과값이 우리가 findByBrand()의 argument로 쓴 값과 동일한지를 체크할 수 있다.
        List<Car> cars = carRepository.findByBrand("Ford");
        assertThat(cars.get(0).getBrand()).isEqualTo("Ford");
        assertThat(cars.size()).isEqualTo(2);

    }

}
