package com.GotYourBac.gotYourBac.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
     public ApplicationUser findByUsername(String username); // delete the public
}
