package org.gradle.fins.service;

import org.gradle.fins.model.VoucherModel;
import org.gradle.fins.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherService {

    private final VoucherRepository voucherRepository;

    @Autowired
    public VoucherService(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    public List<VoucherModel> getAllVoucher() {
        return voucherRepository.findAll();
    }

    public Optional<VoucherModel> getVoucherById(String voucherNumber) {
        return voucherRepository.findById(voucherNumber);
    }
}