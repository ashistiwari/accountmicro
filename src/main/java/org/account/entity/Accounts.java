package org.account.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity{

    private Long accountId;
    @Id
    private String accountNumber;
    private String accountType;
    private String branchAddress;
}
