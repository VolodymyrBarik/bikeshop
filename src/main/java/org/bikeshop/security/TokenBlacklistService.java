package org.bikeshop.security;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {
    private final Set<String> blacklist = ConcurrentHashMap.newKeySet();

    public void addToBlacklist(String token) {
        blacklist.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        if (token == null) {
            throw new IllegalArgumentException("Token cannot be null");
        }
        return blacklist.contains(token);
    }
}