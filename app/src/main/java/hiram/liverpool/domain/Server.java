package hiram.liverpool.domain;

public class Server {

    public class Response<T> extends Server.Request<T> {
        public String message;
        public String errorGuid;

        public Response() {
            super();
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getErrorGuid() {
            return this.errorGuid;
        }

        public void setErrorGuid(String errorGuid) {
            this.errorGuid = errorGuid;
        }
    }

    public class Request<T> {
        public T responseArray;
        private Class<T> objectType;

        public Request() {
        }

        public T getResponseArray() {
            return this.responseArray;
        }

        public void setResponseArray(T responseArray) {
            this.responseArray = responseArray;
        }

        public Class<T> getObjectType() {
            return this.objectType;
        }
    }
}
