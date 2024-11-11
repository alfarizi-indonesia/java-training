package org.gradle.fins.controller;

import org.gradle.fins.model.VoucherModel;
import org.gradle.fins.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class VoucherController {

    private final VoucherService voucherService;

    @Autowired
    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping("/voucher")
    @CrossOrigin
    public List<VoucherModel> getAllVoucher() {
        return voucherService.getAllVoucher();
    }

    @GetMapping("/voucher/{voucherNumber}")
    @CrossOrigin
    public ResponseEntity<VoucherModel> getCRVById(@PathVariable String voucherNumber) {
        Optional<VoucherModel> crvModel = voucherService.getVoucherById(voucherNumber);
        return crvModel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}