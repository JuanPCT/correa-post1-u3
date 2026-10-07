package co.edu.ufps.confudes.certificados;

import java.util.Map;

/** Simula el SDK externo, cuyo contrato no coincide con el puerto del dominio. */
public class LegacyQrSdk {
    public Map<String, Object> encode(String resource, String callback) {
        return Map.of("payload", resource + "|callback=" + callback, "format", "QR_V1");
    }
}
