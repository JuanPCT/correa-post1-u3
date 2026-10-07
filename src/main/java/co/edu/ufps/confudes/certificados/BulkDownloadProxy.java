package co.edu.ufps.confudes.certificados;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

/** Proxy de protección: la comprobación de rol ocurre antes de generar el archivo. */
@Component
public class BulkDownloadProxy {
    private final CertificateArchiveService archive;
    public BulkDownloadProxy(CertificateArchiveService archive) { this.archive = archive; }
    public byte[] download(List<String> ids, Authentication authentication) {
        boolean authorized = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(authority -> "ROLE_ADMIN_CERTIFICADOS".equals(authority.getAuthority()));
        if (!authorized) {
            throw new SecurityException("Se requiere el rol ADMIN_CERTIFICADOS");
        }
        if (ids == null || ids.isEmpty()) throw new IllegalArgumentException("La selección está vacía");
        return archive.zip(ids);
    }
}
