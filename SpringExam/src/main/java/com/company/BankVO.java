package com.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankVO {
	public String bank_name;
	public String account_alias;
	public String account_num_masked;
	public String fintech_use_num;
	public String access_token;
}
