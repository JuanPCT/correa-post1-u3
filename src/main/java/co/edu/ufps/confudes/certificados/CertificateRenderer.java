package co.edu.ufps.confudes.certificados;

public interface CertificateRenderer {
    Certificado render(String estudiante, String curso, String idioma);
}
