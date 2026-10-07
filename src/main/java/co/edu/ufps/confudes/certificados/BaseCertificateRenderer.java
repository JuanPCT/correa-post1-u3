package co.edu.ufps.confudes.certificados;

import org.springframework.stereotype.Component;

@Component
public class BaseCertificateRenderer implements CertificateRenderer {
    @Override
    public Certificado render(String estudiante, String curso, String idioma) {
        return new Certificado(estudiante, curso, idioma, "", "");
    }
}
