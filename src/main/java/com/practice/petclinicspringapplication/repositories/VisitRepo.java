package com.guru.sftpetclinic.repositories;

import com.guru.sftpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepo extends CrudRepository<Visit, Long> {
}
