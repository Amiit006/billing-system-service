package com.rr.dashboardreportservice.service;

import com.rr.dashboardreportservice.feign.ClientServiceProxy;
import com.rr.dashboardreportservice.model.dto.*;
import com.rr.dashboardreportservice.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientReportServiceImpl implements ClientReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    private ClientServiceProxy clientServiceProxy;

    @Override
    public List<CollectionStatsResponse> getClientCollectionsReport(LocalDate form_date, LocalDate to_date, int clientId) {
        List<CollectionStats> collectionStats = reportRepository.getClientCollectionsReport(form_date, to_date, clientId);
        ClientDto clientDto = clientServiceProxy.getClientById(clientId);
        List<CollectionStatsResponse> collectionStatsResponse = new ArrayList<>();

        for (CollectionStats collectionStat : collectionStats) {
            collectionStatsResponse.add(
                    CollectionStatsResponse.builder()
                            .paymentId(collectionStat.getPaymentId())
                            .clientId(collectionStat.getClientId())
                            .clientName(clientDto.getClientName())
                            .mobile(clientDto.getMobile())
                            .amount(collectionStat.getAmount())
                            .paymentMode(collectionStat.getPaymentMode())
                            .paymentDate(collectionStat.getPaymentDate())
                            .build());
        }
        return collectionStatsResponse;
    }

    @Override
    public List<SellStatsResponse> getClientSellsReport(LocalDate form_date, LocalDate to_date, int clientId) {
        List<SellStats> sellStats = reportRepository.getClientSellReport(form_date, to_date, clientId);
        ClientDto clientDto = clientServiceProxy.getClientById(clientId);
        List<SellStatsResponse> sellStatsResponse = new ArrayList<>();

        for (SellStats sellStat : sellStats) {
            sellStatsResponse.add(
                    SellStatsResponse.builder()
                            .clientId(sellStat.getClientId())
                            .clientName(clientDto.getClientName())
                            .discountAmount(sellStat.getDiscountAmount())
                            .grandTotalAmount(sellStat.getGrandTotalAmount())
                            .invoiceDate(sellStat.getInvoiceDate())
                            .invoiceId(sellStat.getInvoiceId())
                            .mobile(clientDto.getMobile())
                            .subTotalAmount(sellStat.getSubTotalAmount())
                            .taxAmount(sellStat.getTaxAmount())
                            .build());
        }
        return sellStatsResponse;
    }
}
