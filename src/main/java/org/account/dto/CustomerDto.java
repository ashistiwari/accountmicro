package org.account.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min=5, max = 30, message = "Name should be between 5 to 30 characters")
    private String name;
    @NotEmpty(message = "Email cannot be null or empty")
    @Size(min=5, max = 30, message = "Email  should be valid value")
    private String email;
    @Pattern(regexp = "[0-9]{10}",message = "Mobile number should be 10 digits")
    private String mobileNumber;
    private AccountsDto accountsDto;
}
