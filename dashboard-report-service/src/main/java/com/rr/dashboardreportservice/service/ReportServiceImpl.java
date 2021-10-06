package com.rr.dashboardreportservice.service;

import com.rr.dashboardreportservice.feign.ClientServiceProxy;
import com.rr.dashboardreportservice.model.dto.*;
import com.rr.dashboardreportservice.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements  ReportService{

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    private ClientServiceProxy clientServiceProxy;

    @Autowired
    ClientReportService clientReportService;

    @Override
    public List<SellStatsResponse> getSellsReport(LocalDate form_date, LocalDate to_date) {
        List<SellStats> statsList = reportRepository.getSellsReport(form_date, to_date);
        String joinedList = statsList.stream().map(SellStats::getClientId)
                .map(String::valueOf).distinct().collect(Collectors.joining(","));

        List<ClientDto> clientDtos = clientServiceProxy
                .getAllClient(joinedList);
        List<SellStatsResponse> sellStatsResponses = new ArrayList<>();

        for (SellStats sellStats : statsList) {
            ClientDto clientDto = clientDtos.stream()
                    .filter(x -> x.getClientId() == sellStats.getClientId())
                    .findFirst().get();
            sellStatsResponses.add(
            SellStatsResponse.builder()
                    .clientId(sellStats.getClientId())
                    .clientName(clientDto.getClientName())
                    .discountAmount(sellStats.getDiscountAmount())
                    .grandTotalAmount(sellStats.getGrandTotalAmount())
                    .invoiceDate(sellStats.getInvoiceDate())
                    .invoiceId(sellStats.getInvoiceId())
                    .mobile(clientDto.getMobile())
                    .subTotalAmount(sellStats.getSubTotalAmount())
                    .taxAmount(sellStats.getTaxAmount())
                    .build());
        }

        return sellStatsResponses;
    }

    @Override
    public List<CollectionStatsResponse> getCollectionsReport(LocalDate form_date, LocalDate to_date) {
        List<CollectionStats> collectionStats = reportRepository.getCollectionsReport(form_date, to_date);
        String joinedList = collectionStats.stream().map(CollectionStats::getClientId)
                .map(String::valueOf).distinct().collect(Collectors.joining(","));

        List<ClientDto> clientDtos = clientServiceProxy
                .getAllClient(joinedList);
        List<CollectionStatsResponse> collectionStatsResponse = new ArrayList<>();

        for (CollectionStats collectionStat : collectionStats) {
            ClientDto clientDto = clientDtos.stream()
                    .filter(x -> x.getClientId() == collectionStat.getClientId())
                    .findFirst().get();
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
    public ClientReportResponse getClientReport(LocalDate form_date, LocalDate to_date, int clientId) {
        ClientReportResponse clientReportResponse = new ClientReportResponse();
        List<CollectionStatsResponse> collectionStatsResponses = clientReportService.getClientCollectionsReport(form_date, to_date, clientId);
        List<SellStatsResponse> sellStatsResponses = clientReportService.getClientSellsReport(form_date, to_date, clientId);
        clientReportResponse.setClientCollection(collectionStatsResponses);
        clientReportResponse.setClientSell(sellStatsResponses);

        return clientReportResponse;
    }

    @Override
    public List<TradeBookResponse> getTradeBookReport(LocalDate form_date, LocalDate to_date) {
        List<TradeBook> tradeBooks = reportRepository.getTradeBookReport(form_date, to_date);
        String joinedList = tradeBooks.stream().map(TradeBook::getClientId)
                .map(String::valueOf).distinct().collect(Collectors.joining(","));

        List<ClientDto> clientDtos = clientServiceProxy
                .getAllClient(joinedList);
        List<TradeBookResponse> tradeBookResponse = new ArrayList<>();

        for (TradeBook tradeBook : tradeBooks) {
            ClientDto clientDto = clientDtos.stream()
                    .filter(x -> x.getClientId() == tradeBook.getClientId())
                    .findFirst().get();
            tradeBookResponse.add(
                    TradeBookResponse.builder()
                            .id(tradeBook.getId())
                            .clientId(tradeBook.getClientId())
                            .clientName(clientDto.getClientName())
                            .amount(tradeBook.getAmount())
                            .billPaymentDate(tradeBook.getBillPaymentDate())
                            .transactionType(tradeBook.getTransactionType())
                            .build());
        }
        return tradeBookResponse;
    }
}
