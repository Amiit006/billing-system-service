package com.rr.dashboardreportservice.repository;

import com.rr.dashboardreportservice.model.InvoiceDetails;
import com.rr.dashboardreportservice.model.dto.ChartResponse;
import com.rr.dashboardreportservice.model.dto.TopBuyer;
import com.rr.dashboardreportservice.model.dto.TopSellingProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopContentRepository extends JpaRepository<InvoiceDetails, Integer> {

    @Query(nativeQuery = true,value = "call sp_top_selling_products(:topCount)")
    List<TopSellingProducts> getTopSellingProducts(@Param("topCount") int topCount);

    @Query(nativeQuery = true,value = "call sp_top_buyer(:topCount)")
    List<TopBuyer> getTopBuyer(@Param("topCount") int topCount);

    @Query(nativeQuery = true,value = "call sp_get_toal_sell_collection_stats(:year)")
    List<ChartResponse> getSellCollectionStats(int year);

    @Query(nativeQuery = true,value = "call sp_get_total_sell_per_month_for_one_year()")
    List<ChartResponse> getMonthlySellStats();
}
