package ru.neoflex.conveyor.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.models.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
@Slf4j
public class CalculationService {
    @Value(value = "${rate}")
    private BigDecimal baseRate;

    @Value(value = "${insurance}")
    private BigDecimal insurance;

    public BigDecimal score(ScoringDataDTO data) {
        BigDecimal rate = baseRate;
        if(!Optional.of(data.getEmployment().getEmploymentStatus()).isPresent()){
            log.error("API /conveyor/calculation: Неверные входные данные в поле employment.employmentStatus");
            return null;
        }
        EmploymentStatus employmentStatus = data.getEmployment().getEmploymentStatus();
        switch (employmentStatus) {
            case UNEMPLOYED:
                log.info("API /conveyor/calculation: Безработный. Скоринг не пройден");
                return null;
            case SELF_EMPLOYED:
                rate = rate.add(BigDecimal.valueOf(0.001));
                log.info("API /conveyor/calculation: Самоанятый. Ставка увеличина на 0.1%");
                break;
            case BUSINESS_OWNER:
                rate = rate.add(BigDecimal.valueOf(0.003));
                log.info("API /conveyor/calculation: Владелец бизнесса. Ставка увеличина на 0.3%");
                break;
            case EMPLOYED:
                break;
            default:
                log.error("API /conveyor/calculation: Неверные входные данные в поле employment.employmentStatus");
                return null;
        }
        if(!Optional.of(data.getEmployment().getPosition()).isPresent()){
            log.error("API /conveyor/calculation: Неверные входные данные в поле employment.position");
            return null;
        }
        Position position = data.getEmployment().getPosition();
        switch (position) {
            case MID_MANAGER:
                rate = rate.subtract(BigDecimal.valueOf(0.002));
                log.info("API /conveyor/calculation: Менеджер среднего звена. Ставка уменьшина 0.2%");
                break;
            case TOP_MANAGER:
                rate = rate.subtract(BigDecimal.valueOf(0.004));
                log.info("API /conveyor/calculation: Топ менеджер. Ставка уменьшина на 0.4%");
                break;
            case WORKER:
            case OWNER:
                break;
            default:
                log.error("API /conveyor/calculation: Неверные входные данные в поле employment.position");
                return null;
        }
        try{
            if (data.getAmount().compareTo(data.getEmployment().getSalary().multiply(BigDecimal.valueOf(20))) > 0) {
                log.info("API /conveyor/calculation: Запрашиваемая сумма больше 20 зарплат. Скоринг не пройден");
                return null;
            }
        }
        catch (Exception ex){
            log.error("API /conveyor/calculation: Неверные входные данные в поле amount");
            return null;
        }
        if(!Optional.of(data.getMaritalStatus()).isPresent()){
            log.error("API /conveyor/calculation: Неверные входные данные в поле maritalStatus");
            return null;
        }
        MaritalStatus maritalStatus = data.getMaritalStatus();
        switch (maritalStatus) {
            case MARRIED:
                rate = rate.subtract(BigDecimal.valueOf(0.003));
                log.info("API /conveyor/calculation: женат/замужем. Ставка уменьшина на 0.3%");
                break;
            case DIVORCED:
                rate = rate.add(BigDecimal.valueOf(0.001));
                log.info("API /conveyor/calculation: в разводе. Ставка увеличина 0.1%");
                break;
            case SINGLE:
            case WIDOW_WIDOWER:
                break;
            default:
                log.error("API /conveyor/calculation: Неверные входные данные в поле maritalStatus");
                return null;
        }
        try{
            if (data.getDependentAmount() > 1) {
                rate = rate.add(BigDecimal.valueOf(0.001));
                log.info("API /conveyor/calculation: Число иждевенцев больше 1. Ставка увеличина на 0.1%");
            }
        }
        catch (Exception exception){
            log.error("API /conveyor/calculation: Неверные входные данные в поле DependentAmount");
            return null;
        }
        int age = Period.between(data.getBirthdate(), LocalDate.now()).getYears();
        try{
            if (age < 20 || age > 60) {
                log.info("API /conveyor/calculation: Возраст меньше 20 или больше 60. Скоринг не пройден");
                return null;
            }
        }
        catch (Exception exception){
            log.error("API /conveyor/calculation: Неверные входные данные в поле Birthday");
            return null;
        }
        if(!Optional.of(data.getGender()).isPresent()){
            log.error("API /conveyor/calculation: Неверные входные данные в поле gender");
            return null;
        }
        Gender gender = data.getGender();
        switch (gender) {
            case FEMALE:
                if (age >= 35 && age <= 60) {
                    rate = rate.subtract(BigDecimal.valueOf(0.003));
                    log.info("API /conveyor/calculation: Женщина от 35 до 60 лет. Ставка умеьшина на 0.3%");
                }
                break;
            case MALE:
                if (age >= 30 && age <= 55) {
                    rate = rate.subtract(BigDecimal.valueOf(0.003));
                    log.info("API /conveyor/calculation: Мужчина от 30 до 55 лет. Ставка уменьшина на 0.3%");
                }
                break;
            case NON_BINARY:
                rate = rate.add(BigDecimal.valueOf(0.003));
                log.info("API /conveyor/calculation: Гендер не бинарный. Ставка увеличина на 0.3%");
                break;
            default:
                log.error("API /conveyor/calculation: Неверные входные данные в поле gender");
                return null;
        }
        try{
            if (data.getEmployment().getWorkExperienceTotal() < 12 && data.getEmployment().getWorkExperienceCurrent() < 3) {
                log.info("API /conveyor/calculation: общий рабочий стаж меньше 12 или текущий рабочий стаж меньше 3. Скоринг не пройден");
                return null;
            }
        }
        catch (Exception ex){
            log.error("API /conveyor/calculation: Неверные входные данные в поле Employment.WorkExperienceTotal");
            return null;
        }
        try{
            if (data.getIsInsuranceEnabled()) {
                rate = rate.subtract(BigDecimal.valueOf(0.03));
                log.info("API /conveyor/calculation: Страхование. Ставка уменьшина на 3%");
            }
        }
        catch (Exception exception){
            log.error("API /conveyor/calculation: Неверные входные данные в поле IsInsuranceEnabled");
            return null;
        }
        try{
            if (data.getIsSalaryClient()) {
                rate = rate.subtract(BigDecimal.valueOf(0.01));
                log.info("API /conveyor/calculation: Зарплатный клиент. Ставка уменьшина на 1%");
            }
        }
        catch (Exception exception){
            log.error("API /conveyor/calculation: Неверные входные данные в поле IsSalaryClient");
            return null;
        }
        return rate;
    }
}
