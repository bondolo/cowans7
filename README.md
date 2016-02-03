# COWANS7
## CopyOnWriteArrayNavigableSet Implementation for Java 7

A `NavigableSet` that uses an internal sorted `CopyOnWriteArrayList` for all of its operations.  Thus, it shares the same basic properties:
* It is best suited for applications in which set sizes generally stay small, read-only operations vastly outnumber mutative operations, and you need to prevent interference among threads during traversal.
* It is thread-safe.
* Mutative operations (`add`, `set`, `remove`, etc.) are expensive since they usually entail copying the entire underlying array.
* Iterators do not support the mutative `remove` operation.
* Traversal via iterators is fast and cannot encounter interference from other threads. Iterators rely on unchanging snapshots of the array at the time the iterators were constructed.


### Sample Usage
The following code sketch uses a copy-on-write navigable set to maintain a set of ordered Handler objects that perform some action upon state updates until one of the handlers returns true indicating that the update has been handled.
    
    import java.util.concurrent.CopyOnWriteArrayNavigableSet;
 
    abstract class Handler implements Comparable<Handler> {
        final int priority;
        
        protected Handler(int priority) {
            this.priority = priority;
        }
        
        // returns true if update has been handled
        abstract boolean handle();
        
        // ordered from highest to lowest
        public final int compareTo(Handler other) { 
            return -Integer.compare(priority, other.priority);
        }
    }
        
    class X {
        // Will use "Natural Order" of Comparables
        private final CopyOnWriteArrayNavigableSet<Handler> handlers
          = new CopyOnWriteArrayNavigableSet<>();
        public void addHandler(Handler h) { handlers.add(h); }
        
        private long internalState;
        private synchronized void changeState() { internalState = ...; }
        
        public void update() {
            changeState();
            for (Handler handler : handlers)
                 if(handler.handle()) break;
       }
    }

### Maven Usage

Include the project as a dependency in your `pom.xml` file:

    <dependency>
      <groupId>org.bondolo</groupId>
      <artifactId>cowans7</artifactId>
      <version>3</version>
    </dependency>
    
COWANS7 is distributed on Maven Central and should be automatically be found in most cases.
    
### License

Public Domain : [CC0 1.0 Universal](http://creativecommons.org/publicdomain/zero/1.0/)
