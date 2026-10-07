package co.edu.ufps.confudes.certificados;

public class QrDecorator extends CertificateDecorator {
    private final String codigo;
    public QrDecorator(CertificateRenderer delegate, String codigo) { super(delegate); this.codigo = codigo; }
    @Override public Certificado render(String estudiante, String curso, String idioma) {
        Certificado c = delegate.render(estudiante, curso, idioma);
        return new Certificado(c.estudiante(), c.curso(), c.idioma(), codigo, c.marcaAgua());
    }
}
