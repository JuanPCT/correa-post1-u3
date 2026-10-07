package co.edu.ufps.confudes.certificados;

public class WatermarkDecorator extends CertificateDecorator {
    public WatermarkDecorator(CertificateRenderer delegate) { super(delegate); }
    @Override public Certificado render(String estudiante, String curso, String idioma) {
        Certificado c = delegate.render(estudiante, curso, idioma);
        return new Certificado(c.estudiante(), c.curso(), c.idioma(), c.codigoQr(), "CONFUDES");
    }
}
