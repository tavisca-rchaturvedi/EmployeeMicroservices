package com.tavisca.Validation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionRepository extends JpaRepository<ExceptionalModel, String> {
}
