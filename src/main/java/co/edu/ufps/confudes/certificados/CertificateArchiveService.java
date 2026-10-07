package co.edu.ufps.confudes.certificados;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CertificateArchiveService {
    public byte[] zip(List<String> certificateIds) {
        return String.join("\n", certificateIds).getBytes(java.nio.charset.StandardCharsets.UTF_8);
    }
}
