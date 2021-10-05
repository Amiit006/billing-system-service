package com.rr.dashboardreportservice.feign;

import com.rr.dashboardreportservice.model.dto.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "clientService", url = "${clientService.ribbon.listOfServers}")
public interface ClientServiceProxy {
    @RequestMapping(method= RequestMethod.GET, value = "/clients/byIds")
    List<ClientDto> getAllClient(@RequestParam String clientIds);

    @RequestMapping(method= RequestMethod.GET, value = "/clients/client")
    ClientDto getClientById(@RequestParam("clientId") int clientId);
}
