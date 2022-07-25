package com.kodilla.transfersgateway.controller;

import com.kodilla.commons.Transfer;
import com.kodilla.transfersgateway.controller.request.TransferRequest;
import com.kodilla.transfersgateway.service.TransferProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/transfers")
public class TransferController {

    private final TransferProducer transferProducer;

    @PostMapping
    public void saveTransfer(@RequestBody TransferRequest request){

        log.info("Recieved transfer request: {}", request);
        Transfer transfer = new Transfer();
        transfer.setAmount(request.getAmount());
        transfer.setRecipientAccount(request.getRecipientAccount());
        transfer.setTitle(request.getTitle());
        transfer.setSenderAccount(request.getSenderAccount());

        transferProducer.sendTransfer(transfer);
    }
}
