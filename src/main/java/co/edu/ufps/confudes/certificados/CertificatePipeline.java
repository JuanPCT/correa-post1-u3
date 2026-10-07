package co.edu.ufps.confudes.certificados;

import org.springframework.stereotype.Component;

@Component
public class CertificatePipeline {
    private final CertificateRenderer base;
    private final QrVerifier qrVerifier;
    public CertificatePipeline(CertificateRenderer base, QrVerifier qrVerifier) {
        this.base = base;
        this.qrVerifier = qrVerifier;
    }
    public Certificado build(String id, String estudiante, String curso, String idioma,
                             boolean watermark, boolean qr, boolean english) {
        CertificateRenderer renderer = base;
        if (watermark) renderer = new WatermarkDecorator(renderer);
        if (english) renderer = new EnglishTranslationDecorator(renderer);
        if (qr) renderer = new QrDecorator(renderer,
                qrVerifier.crearCodigo(id, "https://confudes.ufps.edu.co/certificados"));
        return renderer.render(estudiante, curso, idioma);
    }
}
