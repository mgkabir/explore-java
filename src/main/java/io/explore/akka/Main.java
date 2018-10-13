package io.explore.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Main {

	static class Counter extends AbstractActor {

		private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

		// protocol
		static class Message {
		}

		private int counter = 0;

		@Override
		public Receive createReceive() {
			return receiveBuilder().match(Message.class, this::onMessage).build();
		}

		private void onMessage(Message m) {
			counter++;
			log.info("Counting -> " + counter);
		}

		public static Props props() {
			return Props.create(Counter.class);
		}
	}

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create();
		ActorRef counter = system.actorOf(Counter.props(), "counter");

		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 5; j++) {
						counter.tell(new Counter.Message(), ActorRef.noSender());
					}
				}
			}).start();
		}
	}
}
