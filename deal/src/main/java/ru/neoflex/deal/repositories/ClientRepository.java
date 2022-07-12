package ru.neoflex.deal.repositories;

import ru.neoflex.deal.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
