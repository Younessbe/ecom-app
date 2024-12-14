package org.sid.buillingservice.repository;

import org.sid.buillingservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuillRepository extends JpaRepository<Bill,Long> {
}
