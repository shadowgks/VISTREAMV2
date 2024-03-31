package com.example.vistreamv2.services;

import com.example.vistreamv2.models.entity.Credit;
import com.example.vistreamv2.repositories.CreditRepository;
import com.example.vistreamv2.services.impls.CreditServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CreditServiceImplTest {

    @Mock
    private CreditRepository creditRepository;

    @InjectMocks
    private CreditServiceImpl creditService;

    @Test
    void testFindAllCreditPageable_CreditsFound() {
        // Arrange
        String searchTerm = "example";
        Pageable pageable = Pageable.unpaged();
        Page<Credit> expectedPage = Page.empty();
        when(creditRepository.findMediaBySearch(searchTerm, pageable)).thenReturn(Optional.of(expectedPage));

        // Act
        Page<Credit> resultPage = creditService.findAllCreditPageable(searchTerm, pageable);

        // Assert
        assertEquals(expectedPage, resultPage, "Returned page should match expected page");
    }

    @Test
    void testFindAllCreditPageable_NoCreditsFound() {
        // Arrange
        String searchTerm = "example";
        Pageable pageable = Pageable.unpaged();
        when(creditRepository.findMediaBySearch(searchTerm, pageable)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> creditService.findAllCreditPageable(searchTerm, pageable),
                "Should throw IllegalArgumentException when no credits are found");
    }

    @Test
    void testUpdateCredit_CreditExists() {
        // Arrange
        Long id = 1L;
        Credit originalCredit = new Credit();
        originalCredit.setId(id);
        originalCredit.setName("Original Name");
        Credit updatedCredit = new Credit();
        updatedCredit.setName("Updated Name");

        when(creditRepository.findById(id)).thenReturn(Optional.of(originalCredit));
        when(creditRepository.save(originalCredit)).thenReturn(originalCredit);

        // Act
        Credit resultCredit = creditService.updateCredit(updatedCredit, id);

        // Assert
        assertEquals(updatedCredit.getName(), resultCredit.getName(), "Name should be updated");
    }

    @Test
    void testUpdateCredit_CreditNotFound() {
        // Arrange
        Long id = 1L;
        Credit updatedCredit = new Credit();
        updatedCredit.setName("Updated Name");

        when(creditRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> creditService.updateCredit(updatedCredit, id),
                "Should throw IllegalArgumentException when credit is not found");
    }


    @Test
    void testSaveCredit() {
        // Arrange
        Credit credit = new Credit();
        credit.setId(1L);
        credit.setName("Test Name");

        // Act
        creditService.saveCredit(credit);

        // Assert
        verify(creditRepository, times(1)).save(credit);
    }

}
