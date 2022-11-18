package com.alok.accounts.feign.client;

import com.alok.accounts.feign.response.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "cards")
public interface CardsFeignClient {

@RequestMapping(method = RequestMethod.GET, value = "/myCards/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
     List<CardsDto> getCardDetails(@PathVariable Integer customerId, @RequestHeader("eazybank-correlation-id")  String correlationId);

}
