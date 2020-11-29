package com.thoughtmechanix.licensingservice.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiscoveryService {
    List<String> getEurekaServices();
}
