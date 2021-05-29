package com.rr.billingservice.feign;

import com.rr.billingservice.model.dto.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "clientService", url = "localhost:8080")
public interface ClientServiceProxy {
    @RequestMapping(method= RequestMethod.GET, value = "/clients")
    List<ClientDto> getAllClient();

    @PostMapping(value = "/clients/validateClient")
    boolean isClientPresent(@RequestBody ClientDto client);

}
