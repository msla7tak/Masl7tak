package com.application.masl7tak.Repository;

import com.application.masl7tak.model.Admin;
import com.application.masl7tak.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PointRepository extends JpaRepository<Point, Long> {

    @Query("select C from Point C where C.user_id=:userId")
    List<Point> findRequestsByUserId(Long userId);
}