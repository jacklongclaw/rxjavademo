package com.jack.demo;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

public class TestTranpoline {
  @Test
  public void test() throws Exception {
    /*List<Integer> result = List.of();
    Observable.just(2, 4, 6, 8)
        .subscribeOn(Schedulers.trampoline())
        .subscribe(i -> result.add(i));
    Observable.just(1, 3, 5, 7, 9)
        .subscribeOn(Schedulers.trampoline())
        .subscribe(i -> result.add(i));
    Thread.sleep(5000);
    Assert.assertTrue(result.equals("246813579"));*/



    /*Scheduler scheduler = Schedulers.trampoline();
    Scheduler.Worker worker = scheduler.createWorker();
    AtomicReference<String> result = new AtomicReference<>("");
    worker.schedule(() -> {
      result += Thread.currentThread().getName() + "Start";
      worker.schedule(() -> {
        result += "_middleStart";
        worker.schedule(() ->
            result += "_worker_"
        );
        result += "_middleEnd";
      });
      result += "_mainEnd";
    });
    Thread.sleep(500);
    Assert.assertTrue(result
        .equals("mainStart_mainEnd_middleStart_middleEnd_worker_"));*/
  }
}
