package com.musalasoft.drones.drones.repository;

import com.musalasoft.drones.drones.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
