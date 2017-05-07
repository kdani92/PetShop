package hu.bme.xj4vjg.petshop.interactor.event;

public class Event<T> {
	protected T content;
	protected Throwable throwable;

	public Event() {

	}

	public Event(T content, Throwable throwable) {
		this.content = content;
		this.throwable = throwable;
	}

	public Event(T content) {
		this(content, null);
	}

	public Event(Throwable throwable) {
		this(null, throwable);
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
}
