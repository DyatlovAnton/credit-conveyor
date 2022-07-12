package ru.neoflex.deal.repositories;

import ru.neoflex.deal.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
