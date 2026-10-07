package co.edu.ufps.confudes.certificados;

import org.springframework.stereotype.Component;

@Component
public class CertificateCollaborators {
    public CertificatePolicy policy() { return new CertificatePolicy(); }
    public record CertificatePolicy(int maximumPageSize, String publicBaseUrl) { }
}
