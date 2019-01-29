import org.junit.Ignore;
import org.junit.Test;

import java.util.Iterator;
import java.util.function.Function;

import static org.mockito.Mockito.*;

public class IteratorMapIsLazy {

  @Test public void mapIsLazyUsingSpy() {
    final MyIterator<Integer> it = spy(MyIterator.from(1));
    final MyIterator<Integer> result = it.map((i) -> i + 1);
    verify(it, never()).next();
    result.next();
    verify(it, times(1)).next();
  }
}

abstract class MyIterator<E> implements Iterator<E> {

  public static MyIterator<Integer> from(final int start) {
    return new MyIterator<Integer>() {
      private int i = start;
      @Override public boolean hasNext() { return true; }
      @Override public Integer next() {
        final int result = i;
        i += 1;
        return result;
      }
    };
  }

  public <F> MyIterator<F> map(final Function<E, F> f) {
    return new MyIterator<F>() {
      @Override public boolean hasNext() { return MyIterator.this.hasNext(); }
      @Override public F next() { return f.apply(MyIterator.this.next()); }
    };
  }
}
