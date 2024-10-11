package ru.alemakave.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.alemakave.test.dto.WalletOperationDto;
import ru.alemakave.test.model.Wallet;
import ru.alemakave.test.model.WalletOperationType;
import ru.alemakave.test.repository.WalletRepository;
import ru.alemakave.test.service.impl.WalletServiceImpl;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WalletController.class)
class WalletControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WalletRepository walletRepository;
    @SpyBean
    private WalletServiceImpl walletService;

    @BeforeEach
    void setUp() {
        when(walletRepository.findById(any())).thenReturn(Optional.of(new Wallet(UUID.fromString("02082213-51b4-4c8e-b587-0a55ab6d0c85"), 100)));
    }

    @Test
    void getBalance() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/wallets/{WALLET_UUID}", "02082213-51b4-4c8e-b587-0a55ab6d0c85"))
                .andExpect(status().isOk())
                .andExpect(content().string("100"));
    }

    @Test
    void postOperationBalance() throws Exception {
        WalletOperationDto walletOperationDto = new WalletOperationDto();
        walletOperationDto.setWalletId(UUID.fromString("02082213-51b4-4c8e-b587-0a55ab6d0c85"));
        walletOperationDto.setOperationType(WalletOperationType.DEPOSIT);
        walletOperationDto.setAmount(100);

        ObjectMapper mapper = new ObjectMapper();
        String jsonWalletOperation = mapper.writeValueAsString(walletOperationDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/wallet")
                    .content(jsonWalletOperation)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertThat(walletService.getBalance(walletOperationDto.getWalletId())).isEqualTo(200);


        walletOperationDto.setOperationType(WalletOperationType.WITHDRAW);
        walletOperationDto.setAmount(150);
        jsonWalletOperation = mapper.writeValueAsString(walletOperationDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/wallet")
                        .content(jsonWalletOperation)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertThat(walletService.getBalance(walletOperationDto.getWalletId())).isEqualTo(50);
    }
}