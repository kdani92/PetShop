package hu.bme.xj4vjg.petshop.interactor.event;

public class NetworkEvent<T> extends Event<T> {
	protected int code;

	public NetworkEvent() {
		super();
	}

	public NetworkEvent(T content, int code, Throwable throwable) {
		super(content, throwable);
		this.code = code;
	}

	public NetworkEvent(T content, int code) {
		this(content, code, null);
	}

	public NetworkEvent(int code) {
		this(null, code, null);
	}

	public NetworkEvent(Throwable throwable) {
		this(null, 0, throwable);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
