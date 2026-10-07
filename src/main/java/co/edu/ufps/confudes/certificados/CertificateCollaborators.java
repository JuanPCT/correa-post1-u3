package co.edu.ufps.confudes.certificados;

import org.springframework.stereotype.Component;

@Component
public class CertificateCollaborators {
    public CertificatePolicy policy() { return new CertificatePolicy(50, "https://confudes.ufps.edu.co/certificados"); }
    public record CertificatePolicy(int maximumPageSize, String publicBaseUrl) { }
}
