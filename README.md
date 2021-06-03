# RxSpigot

## Usage

### Basic

```kotlin
val plugin: Javaplugin

Observable.interval(20)
    .take(10)
    .doOnNext {
        plugin.server.broadcastMessage(it)
    }
    .subscribe(plugin)
```

This code broadcasts a message every 20 ticks. This operation ends after repeating 10 times.

### Custom

```kotlin
Observable.interval(20)
    .take(10)
    .isAsync(false)
    .delay(1L)
    .doOnNext {
        player.damage(0.1)
    }
    .doOnCompleteCondition {
        player.health <= 5.0
    }
    .doOnComplete {
        player.playSound(player.location, Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 1.0f, 1.0f)
    }
    .doOnErrorCondition {
        player.isDead
    }
    .doOnError {
        player.playSound(player.location, Sound.ENTITY_WOLF_HOWL, 1.0f, 1.0f)
    }
    .subscribe(plugin)
```

* take
  * Number of repetitions
* isAsync
  * When true, execute as an asynchronous task.
* doOnNext
  * Process to be repeated.
* doOnComplete
  * Processing to be performed when subscribe is completed.
* doOnCompleteCondition
  * Add conditions for completion.
* doOnError
  * Processing when an error occurs.
* doOnErrorCondition
  * Conditions that cause an error
* subscribe
  * Start processing.
