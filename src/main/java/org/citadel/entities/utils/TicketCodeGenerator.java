package org.citadel.entities.utils;

import java.util.UUID;

public class TicketCodeGenerator {

    public static String generateUniqueCodeTicket() {
        String code1 = getHexCode(getRandomUUID(), 0,3);
        String code2 = getHexCode(getRandomUUID(), 4,7);
        String code3 = getHexCode(getRandomUUID(), 0,1);
        return code1.toLowerCase() + code2.toLowerCase() + code3.toLowerCase();
    }

    private static String getHexCode(UUID uuid, int min, int max) {
        assert min < max;
        return Long.toHexString(uuid.getMostSignificantBits()).substring(min, max);
    }

    private static UUID getRandomUUID() {
        return UUID.randomUUID();
    }
}
