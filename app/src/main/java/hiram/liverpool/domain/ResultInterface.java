package hiram.liverpool.domain;

public interface ResultInterface {

    public interface Model<T> {
    void onSucess(T var);

    void onFailure(T var);
}
}
