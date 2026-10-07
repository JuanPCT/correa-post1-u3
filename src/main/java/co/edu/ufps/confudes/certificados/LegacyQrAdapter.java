package co.edu.ufps.confudes.certificados;

import org.springframework.stereotype.Component;

/** Adapter: traduce el modelo del dominio al contrato del SDK y normaliza su respuesta. */
@Component
public class LegacyQrAdapter implements QrVerifier {
    private final LegacyQrSdk sdk = new LegacyQrSdk();

    @Override
    public String crearCodigo(String certificadoId, String urlPublica) {
        Object payload = sdk.encode(urlPublica + "/" + certificadoId, "").get("payload");
        if (payload == null) throw new IllegalStateException("El SDK QR no devolvió un código");
        return payload.toString();
    }
}
