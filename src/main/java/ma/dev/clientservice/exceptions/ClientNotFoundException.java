package ma.dev.clientservice.exceptions;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long id) {
        super("Could not find the client with the id "+ id);
    }
}
