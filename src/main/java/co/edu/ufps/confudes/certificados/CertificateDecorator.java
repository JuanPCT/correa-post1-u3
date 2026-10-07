package co.edu.ufps.confudes.certificados;

abstract class CertificateDecorator implements CertificateRenderer {
    protected final CertificateRenderer delegate;
    protected CertificateDecorator(CertificateRenderer delegate) { this.delegate = delegate; }
}
