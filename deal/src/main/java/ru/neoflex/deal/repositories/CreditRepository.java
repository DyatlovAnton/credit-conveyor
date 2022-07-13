package ru.neoflex.deal.repositories;

import ru.neoflex.deal.models.CreditDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<CreditDTO, Long> {
}
