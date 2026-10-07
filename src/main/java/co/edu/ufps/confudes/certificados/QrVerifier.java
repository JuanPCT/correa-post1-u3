package co.edu.ufps.confudes.certificados;

public interface QrVerifier {
    String crearCodigo(String certificadoId, String urlPublica);
}
