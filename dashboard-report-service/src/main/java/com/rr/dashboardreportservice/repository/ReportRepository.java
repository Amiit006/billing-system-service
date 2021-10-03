package com.rr.dashboardreportservice.repository;

import com.rr.dashboardreportservice.model.InvoiceOverView;
import com.rr.dashboardreportservice.model.dto.CollectionStats;
import com.rr.dashboardreportservice.model.dto.SellStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<InvoiceOverView, Integer> {

    @Query(nativeQuery = true, value = "call sp_get_all_sell_in_period(:from_date, :to_date)")
    List<SellStats> getSellsReport(@Param("from_date") LocalDate from_date, @Param("to_date") LocalDate to_date);

    @Query(nativeQuery = true, value = "call sp_get_all_collection_in_period(:from_date, :to_date)")
    List<CollectionStats> getCollectionsReport(@Param("from_date") LocalDate from_date, @Param("to_date") LocalDate to_date);

}
