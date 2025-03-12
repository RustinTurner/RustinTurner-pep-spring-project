package com.example.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.InvalidCredentialsException;
import com.example.exception.UserAlreadyExistsException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

	public Account login(Account account) throws InvalidCredentialsException{
        Optional<Account> oAccount = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if(oAccount.isPresent()){
            return oAccount.get();
        }
        throw new InvalidCredentialsException(null);
	}

    public Account register(Account account) throws UserAlreadyExistsException{
        if(!accountRepository.existsByUsername(account.getUsername())){
            return accountRepository.save(account);
        }
        throw new UserAlreadyExistsException(null);
    }
}
