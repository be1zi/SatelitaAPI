package Satelita.DataBase.Tools;

public class ServiceResult<T, R> {

    private T data;
    private R enumValue;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public R getEnumValue() {
        return enumValue;
    }

    public void setEnumValue(R enumValue) {
        this.enumValue = enumValue;
    }
}
