package com.dgq.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class DelayElment<T> implements Delayed {

	private static final long NANO_ORIFIN = System.nanoTime();

	private final long time;

	private final T item;

	private final long sequenceNumber;

	private static final AtomicLong sequencer = new AtomicLong();

	final static long now() {
		return System.nanoTime() - NANO_ORIFIN;
	}

	public DelayElment(T submitItem, long timeout) {
		this.item = submitItem;
		this.time = timeout;
		this.sequenceNumber = sequencer.getAndDecrement();
	}

	public T getItem() {
		return this.item;
	}

	@Override
	public int compareTo(Delayed o) {
		if (this == o) {
			return 0;
		}
		if (o instanceof DelayElment) {
			DelayElment x = (DelayElment) o;
			long diff = time - x.time;
			if (diff < 0) {
				return -1;
			} else if (diff > 0) {
				return 1;
			} else if (sequenceNumber < x.sequenceNumber) {
				return -1;
			} else {
				return 1;
			}
		}

		long d = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
		return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
	}

	@Override
	public long getDelay(TimeUnit unit) {

		long d = unit.convert(time - now(), TimeUnit.NANOSECONDS);
		return d;
	}
	
}
