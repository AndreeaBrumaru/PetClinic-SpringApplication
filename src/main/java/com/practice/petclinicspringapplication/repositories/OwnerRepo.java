package com.guru.sftpetclinic.repositories;

import com.guru.sftpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepo extends CrudRepository<Owner, Long> {
}
