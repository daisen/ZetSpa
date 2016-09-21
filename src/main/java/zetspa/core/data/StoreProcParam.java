package zetspa.core.data;

public class StoreProcParam {

    private String paramName;
    private DataType paramDataType;
    private int paramSize;
    private ProcParamType paramIoType;
    private Object value;

    public StoreProcParam(String name, DataType dataType, int size, ProcParamType ioType, Object vaule) {
        setParamName(name);
        setParamDataType(dataType);
        setParamSize(size);
        setParamIoType(ioType);
        setValue(vaule);
    }

    public String getParamName() {
        return paramName;
    }

    public StoreProcParam setParamName(String paramName) {
        this.paramName = paramName;
        return this;
    }

    public DataType getParamDataType() {
        return paramDataType;
    }

    public StoreProcParam setParamDataType(DataType paramDataType) {
        this.paramDataType = paramDataType;
        return this;
    }

    public int getParamSize() {
        return paramSize;
    }

    public StoreProcParam setParamSize(int paramSize) {
        this.paramSize = paramSize;
        return this;
    }

    public ProcParamType getParamIoType() {
        return paramIoType;
    }

    public StoreProcParam setParamIoType(ProcParamType paramIoType) {
        this.paramIoType = paramIoType;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public StoreProcParam setValue(Object value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("paramName:").append(this.getParamName()).append(";")
                .append("value:").append(this.getValue()).append(";");
        return sb.toString();
    }

}
