package org.citadel.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.citadel.entities.UserAccount;
import org.citadel.services.UserAccountService;

@Path("/user-accounts")
@Produces(MediaType.APPLICATION_JSON)
public class UserAccountResource {

    private final UserAccountService userAccountService;

    @Inject
    public UserAccountResource(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @POST
    @Path("/create-user-account")
    public Response createUserAccount(UserAccount userAccount) {
        return userAccountService.createCustomer(userAccount);
    }

    @GET
    @Path("/get-all-user-accounts")
    public Response getAllUserAccounts() {
        return userAccountService.findAllUsers();
    }
}
