package com.rr.billingservice.service;

import com.rr.billingservice.exception.InvoiceException;
import com.rr.billingservice.feign.ClientServiceProxy;
import com.rr.billingservice.feign.ParticularServiceProxy;
import com.rr.billingservice.model.InvoiceDetails;
import com.rr.billingservice.model.InvoiceOverView;
import com.rr.billingservice.model.Payment;
import com.rr.billingservice.model.dto.*;
import com.rr.billingservice.repository.InvoiceDetailsRepository;
import com.rr.billingservice.repository.InvoiceOverviewRepository;
import com.rr.billingservice.repository.PaymentRepository;
import io.micrometer.core.instrument.util.DoubleFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    InvoiceOverviewRepository invoiceOverviewRepository;

    @Autowired
    InvoiceDetailsRepository invoiceDetailsRepository;

    @Autowired
    ClientOutstandingService clientOutstandingService;

    @Autowired
    private ClientServiceProxy clientServiceProxy;

    @Autowired
    private ParticularServiceProxy particularServiceProxy;

    @Override
    public boolean createBill(InvoiceDetailsDto invoiceDetailsDto) throws InvoiceException {
        List<InvoiceDto> invoice = invoiceDetailsDto.getInvoice();
        BillAmountDetailsDto billAmountDetails = invoiceDetailsDto.getBillAmountDetails();
        ClientDto client = invoiceDetailsDto.getClient();
        PaymentDto payment = invoiceDetailsDto.getPayment();
        LocalDateTime createdDate = LocalDateTime.now(), modifiedDate = LocalDateTime.now();

        List<InvoiceDetails> invoiceDetails = new ArrayList<>();

        boolean result = validateInputData(invoice, billAmountDetails, client);

        Payment paymentToSave = buildPayment(client, payment, createdDate, modifiedDate).build();

        InvoiceOverView invoiceOverView =
                buildInvoiceOverView(billAmountDetails, client, payment, paymentToSave, createdDate, modifiedDate).build();

        createParticulars(invoice);

        InvoiceOverView newInvoiceOverView = invoiceOverviewRepository.save(invoiceOverView);

        buildInvoiceDetails(invoice, invoiceDetails, newInvoiceOverView, createdDate, modifiedDate);

        invoiceDetailsRepository.saveAll(invoiceDetails);
        clientOutstandingService.updateCustomerOutstanding(client.getClientId());
        return result;
    }

    @Override
    public boolean updateBill(int invoiceId, InvoiceDetailsDto invoiceDetailsDto) throws InvoiceException {
        List<InvoiceDto> invoice = invoiceDetailsDto.getInvoice();
        BillAmountDetailsDto billAmountDetails = invoiceDetailsDto.getBillAmountDetails();
        ClientDto client = invoiceDetailsDto.getClient();
        PaymentDto payment = invoiceDetailsDto.getPayment();

        List<InvoiceDetails> invoiceDetails = new ArrayList<>();

        Payment dbPayment = paymentRepository.findById(payment.getPaymentId()).orElseThrow(() -> new InvoiceException("Subtotal mismatch!", HttpStatus.BAD_REQUEST));
        LocalDateTime createdDate = dbPayment.getCreatedDate(), modifiedDate = LocalDateTime.now();

        boolean result = validateInputData(invoice, billAmountDetails, client);

        List<InvoiceDetails> invoiceDetailsList = invoiceDetailsRepository.findAllByInvoiceIdIn(Arrays.asList(invoiceId));
        invoiceDetailsRepository.deleteAll(invoiceDetailsList);

        Payment paymentToSave = buildPayment(client, payment, createdDate, modifiedDate).paymentId(payment.getPaymentId()).build();

        InvoiceOverView invoiceOverView =
                buildInvoiceOverView(billAmountDetails, client, payment, paymentToSave, createdDate, modifiedDate)
                        .invoiceId(invoiceId)
                        .build();

        createParticulars(invoice);

        InvoiceOverView newInvoiceOverView = invoiceOverviewRepository.save(invoiceOverView);

        buildInvoiceDetails(invoice, invoiceDetails, newInvoiceOverView, createdDate, modifiedDate);

        invoiceDetailsRepository.saveAll(invoiceDetails);
        clientOutstandingService.updateCustomerOutstanding(client.getClientId());
        return result;
    }

    private void buildInvoiceDetails(List<InvoiceDto> invoice, List<InvoiceDetails> invoiceDetails, InvoiceOverView newInvoiceOverView
            , LocalDateTime createdDate, LocalDateTime modifiedDate) {

        for (InvoiceDto data : invoice) {
            InvoiceDetails details = InvoiceDetails.builder()
                    .slNo(data.getSlNo())
                    .perticulars(data.getPerticulars())
                    .amount(data.getAmount())
                    .quanity(data.getQuanity())
                    .discountPercentage(data.getDiscount())
                    .total(data.getTotal())
                    .discountTotal(data.getDiscountPrice())
                    .quantityType(data.getQuantityType())
                    .verified(data.isVerified())
                    .invoiceId(newInvoiceOverView)
                    .createdDate(createdDate)
                    .modifiedDate(modifiedDate)
                    .build();
            invoiceDetails.add(details);
        }
    }

    private InvoiceOverView.InvoiceOverViewBuilder buildInvoiceOverView(BillAmountDetailsDto billAmountDetails, ClientDto client, PaymentDto payment, Payment paymentToSave
            , LocalDateTime createdDate, LocalDateTime modifiedDate) {
        return InvoiceOverView.builder()
                .clientId(client.getClientId())
                .payment(paymentToSave)
                .invoiceDate(payment.getPaymentDate().toLocalDate())
                .subTotalAmount(billAmountDetails.getSubTotalAmount())
                .taxAmount(billAmountDetails.getTaxAmount())
                .taxPercentage(billAmountDetails.getTaxPercentage())
                .grandTotalAmount(billAmountDetails.getGrandTotalAmount())
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now());
    }

    private Payment.PaymentBuilder buildPayment(ClientDto client, PaymentDto payment
            , LocalDateTime createdDate, LocalDateTime modifiedDate) {
        return Payment.builder()
                .clientId(client.getClientId())
                .amount(payment.getPaymentAmount())
                .paymentMode(payment.getPaymentMode())
                .paymentDate(payment.getPaymentDate().toLocalDate())
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now());
    }

    private boolean validateInputData(List<InvoiceDto> invoice, BillAmountDetailsDto billAmountDetails, ClientDto client) throws InvoiceException {
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);

        double grandTotalAmount = 0, subTotalAmount = 0, taxAmount = 0;

        boolean result = clientServiceProxy.isClientPresent(client);
        if (!result) {
            throw new InvoiceException("Client not found!", HttpStatus.NOT_FOUND);
        }

        for (InvoiceDto data : invoice) {
            subTotalAmount = subTotalAmount + ((data.getAmount() * data.getQuanity())
                    - (data.getAmount() * data.getQuanity() * data.getDiscount() / 100));
        }

        double overallDiscountPercentage = billAmountDetails.getOverallDiscountPercentage();
        double overallDiscountAmount = subTotalAmount * overallDiscountPercentage / 100;

        taxAmount = (subTotalAmount - overallDiscountAmount) * billAmountDetails.getTaxPercentage() / 100;
        grandTotalAmount = subTotalAmount - overallDiscountAmount + taxAmount;

        if (subTotalAmount != billAmountDetails.getSubTotalAmount())
            throw new InvoiceException("Subtotal mismatch!", HttpStatus.BAD_REQUEST);
        if (Math.round(taxAmount * 100.0) / 100.0 != Math.round(billAmountDetails.getTaxAmount() * 100.0) / 100.0 )
            throw new InvoiceException("Tax amount mismatch!", HttpStatus.BAD_REQUEST);
        if (Math.round(grandTotalAmount * 100.0) / 100.0 != Math.round(billAmountDetails.getGrandTotalAmount() * 100.0) / 100.0)
            throw new InvoiceException("Grand total mismatch!", HttpStatus.BAD_REQUEST);
        boolean billNotVerified = invoice.stream().map(InvoiceDto::isVerified).distinct().filter(data -> data.equals(Boolean.FALSE)).findAny().isPresent();
        if (billNotVerified)
            throw new InvoiceException("Bill Not verified!", HttpStatus.BAD_REQUEST);
        return result;
    }

    private void createParticulars(List<InvoiceDto> invoice) {
        List<ParticularDto> particulars = invoice.stream().map(x -> ParticularDto.builder().particularName(x.getPerticulars())
                                                                    .discountPercentage(x.getDiscount()).build()).collect(Collectors.toList());
        particularServiceProxy.createMultipleParticular(particulars);
    }

    @Override
    public int generateInvoiceId() {
        InvoiceOverView invoice = invoiceOverviewRepository.findTopByOrderByInvoiceIdDesc();
        return invoice == null ? 1 : invoice.getInvoiceId() + 1;
    }

    @Override
    public InvoiceOverView getInvoiceById(int id) throws InvoiceException {
        return invoiceOverviewRepository.findById(id)
                .orElseThrow(() -> new InvoiceException("Invoice not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<InvoiceOverView> getInvoiceByClientId(int clientId){
        clientServiceProxy.isClientPresentByClientId(clientId);
        return invoiceOverviewRepository.findByClientId(clientId);
    }
}
