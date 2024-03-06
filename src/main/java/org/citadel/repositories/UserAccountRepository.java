package org.citadel.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.citadel.entities.UserAccount;

@ApplicationScoped
public class UserAccountRepository implements PanacheRepository<UserAccount> {
}
