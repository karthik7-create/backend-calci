package com.example.calci.Repository;

import com.example.calci.Entity.CalciEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalciRepo extends JpaRepository<CalciEntity,Long> {


}
