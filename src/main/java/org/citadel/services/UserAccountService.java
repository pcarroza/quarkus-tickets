package org.citadel.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.citadel.constants.HttpStatus;
import org.citadel.entities.UserAccount;
import org.citadel.repositories.UserAccountRepository;

import java.util.List;

@ApplicationScoped
@Transactional
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Inject
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public Response createCustomer(UserAccount userAccount) {
        userAccountRepository.persist(userAccount);
        return Response.status(HttpStatus.CREATED).entity(userAccount).build();
    }

    public Response getAllCustomers() {
        List<UserAccount> userAccounts = userAccountRepository.listAll();
        if (!userAccounts.isEmpty()) {
            return Response.status(HttpStatus.OK).entity(userAccounts).build();
        }
        return Response.status(HttpStatus.NOT_FOUND).entity("NO HAY CLIENTES").build();
    }
}
