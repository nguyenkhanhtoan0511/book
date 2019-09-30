package www.book.response;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BookResponse {

    private String status;
    private String message;

    public BookResponse() {
    }

    public BookResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BookResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
