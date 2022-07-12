package ru.neoflex.deal.repositories;

import ru.neoflex.deal.models.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {
}
