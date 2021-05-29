package com.rr.billingservice.feign;

import com.rr.billingservice.model.dto.ParticularDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "particularService", url = "localhost:8080")
public interface ParticularServiceProxy {

    @PostMapping(value = "/particulars/bulkCreate")
    List<ParticularDto> createMultipleParticular(@RequestBody List<String> particular);

}
