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

    @Override
    public List<ParticularReport> getParticularsReport(LocalDate form_date, LocalDate to_date) {
//        String listString = particulars.stream().map(Object::toString)
//                .collect(Collectors.joining(","));
        List<ParticularReport> result = reportRepository.getParticularsReport(form_date, to_date);
        return result;
    }

    @Override
    public ClientOutStandingReport getClientOutstandingReport() {
        List<ClientOutstandingAmount> clientOutstandingAmounts = reportRepository.getClientOutstandingReport();

        double purchasedAmount = clientOutstandingAmounts.stream().mapToDouble(ClientOutstandingAmount::getPurchasedAmount).sum();
        double paymentAmount = clientOutstandingAmounts.stream().mapToDouble(ClientOutstandingAmount::getPaymentAmount).sum();
        double outstandingAmount = clientOutstandingAmounts.stream().mapToDouble(ClientOutstandingAmount::getOutstandingAmount).sum();

        List<ClientOutstandingAmountSummary> chartResponses = new ArrayList<>();
        chartResponses.add((ClientOutstandingAmountSummary.builder()
                .name("PurchasedAmount").value(purchasedAmount).build()));
        chartResponses.add((ClientOutstandingAmountSummary.builder()
                .name("PaymentAmount").value(paymentAmount).build()));
        chartResponses.add((ClientOutstandingAmountSummary.builder()
                .name("OutstandingAmount").value(outstandingAmount).build()));

        ClientOutStandingReport clientOutStandingReport = ClientOutStandingReport.builder()
                .clientOutstandingAmount(clientOutstandingAmounts)
                .clientOutstandingAmountSummary(chartResponses)
                .build();

        return clientOutStandingReport;
    }

    @Override
    public List<ClientTradeBookResponse> getClientTradeBookReport(int clientId, LocalDate form_date, LocalDate to_date) {
        List<ClientTradeBook> clientTradeBooks = reportRepository.getClientTradeBookReport(clientId, form_date, to_date);
        List<ClientTradeBookResponse> clientTradeBookResponses = new ArrayList<>();
        double balance = 0;
        for(ClientTradeBook clientTradeBook : clientTradeBooks) {
            if(clientTradeBook.getType().equalsIgnoreCase("Purchase")) {
                balance = balance + clientTradeBook.getAmount();
                clientTradeBookResponses.add(buildClientTradeBookResponse(clientTradeBookResponses, balance, clientTradeBook, "+"));
            } else if(clientTradeBook.getType().equalsIgnoreCase("Payment")) {
                balance = balance - clientTradeBook.getAmount();
                clientTradeBookResponses.add(buildClientTradeBookResponse(clientTradeBookResponses, balance, clientTradeBook, "-"));
            } else {
                balance = clientTradeBook.getAmount();
                clientTradeBookResponses.add(buildClientTradeBookResponse(clientTradeBookResponses, balance, clientTradeBook, "*"));
            }
        }
        return clientTradeBookResponses;
    }

    private ClientTradeBookResponse buildClientTradeBookResponse(List<ClientTradeBookResponse> clientTradeBookResponses, double balance, ClientTradeBook clientTradeBook, String op) {
        if(op.equalsIgnoreCase("+"))
        return ClientTradeBookResponse.builder()
                .billAmount(op + " " + clientTradeBook.getAmount())
                .date(clientTradeBook.getDate())
                .remark(clientTradeBook.getRemark())
                .type(clientTradeBook.getType())
                .balance(balance)
                .build();
        else if(op.equalsIgnoreCase("-"))
            return ClientTradeBookResponse.builder()
                    .paymentAmount(op + " " + clientTradeBook.getAmount())
                    .date(clientTradeBook.getDate())
                    .remark(clientTradeBook.getRemark())
                    .type(clientTradeBook.getType())
                    .balance(balance)
                    .build();
        else
            return ClientTradeBookResponse.builder()
                    .date(clientTradeBook.getDate())
                    .remark(clientTradeBook.getRemark())
                    .type(clientTradeBook.getType())
                    .balance(balance)
                    .build();
    }
}
